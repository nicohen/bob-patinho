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
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.self_managment.importFile.parser.ParseCampaign;
import com.self_managment.importFile.parser.ParseSupervisor;
import com.self_managment.model.entity.Agent;
import com.self_managment.service.AgentService;

@Component("importFileAgent")
public class ImportFileAgent implements ImportFile {
	@Autowired
	private AgentService agentService;

	public void setService(AgentService agentService) {
		this.agentService = agentService;
	}

	@Override
	public void importFile(String name) throws IOException {
		ICsvBeanReader inFile = new CsvBeanReader(
				new FileReader(new File(name)), CsvPreference.EXCEL_PREFERENCE);

		List<ImportFileError> errors = new ArrayList<ImportFileError>();
		try {
			String[] header = inFile.getCSVHeader(true);
			header = new String[] { "docket", "dni", "name", "surname",
					"grossSalary", "workingDay", "status", "supervisor",
					"entryDate", "campaign" };

			final CellProcessor[] processor = new CellProcessor[] {
					new ParseInt(), new ParseLong(), null, null,
					new ParseDouble(), null, null, new ParseSupervisor(),
					new ParseDate("dd/MM/yyyy"), new ParseCampaign() };

			Agent agent = null;
			while (true) {
				try {
					agent = inFile.read(Agent.class, header, processor);

					if (agent == null)
						break;
				} catch (Throwable e) {
					errors.add(ImportFileError.getParseError(inFile
							.getLineNumber(), e.getMessage().replaceAll("\n",
							" - ")));
				}

				if (agent != null) {
					try {
						agentService.saveOrUpdate(agent);
					} catch (Throwable e) {
						errors.add(ImportFileError.getPersistError(inFile
								.getLineNumber(), e.getMessage()));
					}
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
