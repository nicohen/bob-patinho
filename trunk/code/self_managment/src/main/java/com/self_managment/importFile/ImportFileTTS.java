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
import com.self_managment.model.entity.TTS;
import com.self_managment.service.TTSService;

@Component("importFileTTS")
public class ImportFileTTS implements ImportFile {
	@Autowired
	private TTSService ttsService;

	public void setTTSService(TTSService ttsService) {
		this.ttsService = ttsService;
	}

	@Override
	public void importFile(String name) throws IOException {
		ICsvBeanReader inFile = new CsvBeanReader(new FileReader(new File(
				name)),
				CsvPreference.EXCEL_PREFERENCE);
		try {
			String[] header = inFile.getCSVHeader(true);
			header = new String[] { "agent", "date", "scheduleEntered",
					"scheduleGoneOut", "bulgingDate" };

			final CellProcessor[] processor = new CellProcessor[] {
					new ParseAgent(), new ParseDate("dd/MM/yyyy"),
					null, null, new ParseDate("dd/MM/yyyy")};

			TTS tts;
			while ((tts = inFile.read(TTS.class, header, processor)) != null) {
				ttsService.saveOrUpdate(tts);
			}
		} finally {
			inFile.close();
		}

	}

}