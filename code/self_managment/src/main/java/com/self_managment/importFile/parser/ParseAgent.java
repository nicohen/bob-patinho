package com.self_managment.importFile.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CSVContext;

import com.self_managment.model.entity.Agent;

public class ParseAgent extends CellProcessorAdaptor {

	public ParseAgent() {
		super();
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		Agent obj = new Agent();

		final Integer id = Integer.parseInt((String) value);

		obj.setDocket(id);

		return obj;

	}

}
