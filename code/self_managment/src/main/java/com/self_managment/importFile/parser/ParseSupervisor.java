package com.self_managment.importFile.parser;

import org.supercsv.cellprocessor.CellProcessorAdaptor;
import org.supercsv.util.CSVContext;

import com.self_managment.model.entity.Supervisor;

public class ParseSupervisor extends CellProcessorAdaptor {

	public ParseSupervisor() {
		super();
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		Supervisor supervisor = new Supervisor();

		final Integer id = Integer.parseInt((String) value);

		supervisor.setId(id);

		return supervisor;

	}

}
