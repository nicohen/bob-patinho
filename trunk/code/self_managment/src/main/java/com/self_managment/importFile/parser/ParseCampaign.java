package com.self_managment.importFile.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CSVContext;

import com.self_managment.model.entity.Campaign;

public class ParseCampaign extends CellProcessorAdaptor {

	public ParseCampaign() {
		super();
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		Campaign campaign = new Campaign();

		final Integer id = Integer.parseInt((String) value);

		campaign.setId(id);

		return campaign;

	}

}
