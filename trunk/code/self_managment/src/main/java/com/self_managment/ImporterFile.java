package com.self_managment;

import java.io.IOException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.self_managment.importFile.ImportFile;

public class ImporterFile {

	public enum FileType {
		HF("importFileAgent"), QA("importFileQA"), STS("importFileSTS"), TTS(
				"importFileTTS"), SUMMARY("importFileSummary");

		private final String componentName;

		FileType(String componentName) {
			this.componentName = componentName;
		}

		public String getComponentName() {
			return componentName;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Options opt = new Options();

			opt.addOption("h", false, "Ayuda.");
			opt.addOption("f", true, "Archivo a procesar.");
			opt
					.addOption("t", true,
							"Tipo de archivo. Valores posibles [QA,STS,TTS,HF,SUMMARY]");
			opt.addOption("o", true,
					"Directorio donde se guardar\u00E1n los errores.");

			BasicParser parser = new BasicParser();
			CommandLine cl = parser.parse(opt, args);

			if (cl.hasOption('h')) {
				printHelp(opt);
			} else if (cl.hasOption('f') && cl.hasOption('t')) {
				FileType fileType = null;
				try {
					fileType = FileType.valueOf(cl.getOptionValue('t'));
				} catch (IllegalArgumentException e) {
					System.out.println("Tipo de archivo inv\u00E1lido");
					printHelp(opt);

					return;
				}

				ApplicationContext appContext = new ClassPathXmlApplicationContext(
						"spring/config/beanlocations.xml");

				ImportFile importFile = (ImportFile) appContext
						.getBean(fileType.getComponentName());

				try {
					importFile.importFile(cl.getOptionValue('f'));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				printHelp(opt);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private static void printHelp(Options opt) {
		HelpFormatter f = new HelpFormatter();
		f.printHelp("Importador de archivos", opt);
	}

}
