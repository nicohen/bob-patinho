package com.self_managment.importFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.self_managment.importFile.parser.ParseAgent;
import com.self_managment.model.entity.QA;
import com.self_managment.service.QAService;

@Component("importFileQA")
public class ImportFileQA implements ImportFile {
	@Autowired
	private QAService qaService;

	public void setQaService(QAService qaService) {
		this.qaService = qaService;
	}

	@Override
	public void importFile(String name) throws IOException {
		ICsvBeanReader inFile = new CsvBeanReader(
				new FileReader(new File(name)), CsvPreference.EXCEL_PREFERENCE);
		try {
			String[] header = inFile.getCSVHeader(true);
			header = new String[] { "agent", "date", "evaluationsQuantity",
					"posiblePointsQuantity", "achievedPointsQuantity" };

			final CellProcessor[] processor = new CellProcessor[] {
					new ParseAgent(), new ParseDate("dd/MM/yyyy"),
					new ParseInt(), new ParseInt(), new ParseInt() };

			QA qa;
			while ((qa = inFile.read(QA.class, header, processor)) != null) {
				qaService.saveOrUpdate(qa);
			}
		} finally {
			inFile.close();
		}

	}

}
