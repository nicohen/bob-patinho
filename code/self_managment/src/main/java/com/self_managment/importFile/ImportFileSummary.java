package com.self_managment.importFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.self_managment.importFile.parser.ParseAgent;
import com.self_managment.model.entity.Summary;
import com.self_managment.service.SummaryService;

@Component("importFileSummary")
public class ImportFileSummary implements ImportFile {
	@Autowired
	private SummaryService summaryService;

	public void setTTSService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}

	@Override
	public void importFile(String name) throws IOException {
		ICsvBeanReader inFile = new CsvBeanReader(
				new FileReader(new File(name)), CsvPreference.EXCEL_PREFERENCE);

		List<ImportFileError> errors = new ArrayList<ImportFileError>();
		try {
			String[] header = inFile.getCSVHeader(true);
			header = new String[] { "agent", "date", "quantityOfCalls",
					"inCall", "timeInWait", "transferredCalls", "loggeado",
					"readyForCall", "afterForCall" };

			final CellProcessor[] processor = new CellProcessor[] {
					new ParseAgent(), new ParseDate("dd/MM/yyyy"),
					new ParseInt(), new ParseInt(), new ParseInt(),
					new ParseInt(), new ParseInt(), new ParseInt(),
					new ParseInt() };

			Summary summary = null;
			while (true) {
				try {
					summary = inFile.read(Summary.class, header, processor);

					if (summary == null)
						break;
					
					try {
						summaryService.saveOrUpdate(summary);
					} catch (Throwable e) {
						errors.add(ImportFileError.getPersistError(inFile
								.getLineNumber(), e.getMessage()));
					}
				} catch (Throwable e) {
					errors.add(ImportFileError.getParseError(inFile
							.getLineNumber(), e.getMessage().replaceAll("\n",
							" - ")));
				}
			}
		} finally {
			if (!errors.isEmpty()) {
				FileWriter fstream = new FileWriter(name.substring(0, name
						.lastIndexOf("."))
						+ "_error_"
						+ new SimpleDateFormat("yyyy-MM-dd HH.mm.ss")
								.format(new Date()) + ".txt");

				BufferedWriter errorOut = new BufferedWriter(fstream);

				for (ImportFileError error : errors)
					errorOut.write(error.getErrorMsg() + "\n");

				errorOut.close();
			}

			inFile.close();
		}

	}

}