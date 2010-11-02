package com.self_managment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class ExtractDDL {
    public static void main(final String[] args) throws SQLException {

	final ApplicationContext apContext = new ClassPathXmlApplicationContext(
		"spring/config/beanlocations.xml");
	AnnotationSessionFactoryBean sessionFactory = (AnnotationSessionFactoryBean) apContext
		.getBean("&sessionFactory");

	sessionFactory.dropDatabaseSchema();
	sessionFactory.createDatabaseSchema();
	
	final Configuration configuration = sessionFactory.getConfiguration();
	// final Connection connection = sessionFactory.getDataSource()
	// .getConnection();
	Dialect dialect = new MySQL5InnoDBDialect();
	// DatabaseMetadata metaData = new DatabaseMetadata(connection,
	// dialect);

	final String[] dropDDLs = configuration
		.generateDropSchemaScript(dialect);
	final String[] createDLLs = configuration
		.generateSchemaCreationScript(dialect);
	// final String[] updateDDLs = configuration.generateSchemaUpdateScript(
	// dialect, metaData);

	BufferedWriter schemaWriter = null;
	try {

	    schemaWriter = new BufferedWriter(new FileWriter(new File(
		    "target/schema.sql"), false));

	    printDDL(dropDDLs, schemaWriter);
	    printDDL(createDLLs, schemaWriter);
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (schemaWriter != null) {
		try {
		    schemaWriter.flush();
		    schemaWriter.close();
		} catch (Exception e2) {
		    // ignore it, nothing is to be done
		}
	    }
	}
    }

    private static void printDDL(String[] ddls, BufferedWriter schemaWriter)
	    throws IOException {
	for (String ddl : ddls) {
	    schemaWriter.write(ddl.concat(";"));
	    schemaWriter.newLine();
	}
    }
}
