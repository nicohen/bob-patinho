package com.self_managment.importFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.self_managment.importFile.parser.ParseAgent;
import com.self_managment.model.entity.STS;
import com.self_managment.service.STSService;

@Component("importFileSTS")
public class ImportFileSTS implements ImportFile {
	@Autowired
	private STSService stsService;

	public void setSTSService(STSService stsService) {
		this.stsService = stsService;
	}

	@Override
	public void importFile(String name) throws IOException {
		ICsvBeanReader inFile = new CsvBeanReader(
				new FileReader(new File(name)), CsvPreference.EXCEL_PREFERENCE);
		try {
			String[] header = inFile.getCSVHeader(true);
			header = new String[] { "agent", "date", "scheduleEntered",
					"scheduleGoneOut", "bulgingDate" };

			final CellProcessor[] processor = new CellProcessor[] {
					new ParseAgent(), new ParseDate("dd/MM/yyyy"), null, null,
					new ParseDate("dd/MM/yyyy") };

			STS sts;
			while ((sts = inFile.read(STS.class, header, processor)) != null) {
				stsService.saveOrUpdate(sts);
			}
		} finally {
			inFile.close();
		}

	}

}