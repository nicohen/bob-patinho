package com.self_managment.importFile;

public class ImportFileError {
	private static final String PERSIST_ERROR = "Error al guardar. Nro de fila: %s. Mensaje: %s";
	private static final String PARSE_ERROR = "Error al leer el archivo. Nro de fila: %s. Mensaje: %s";

	private Integer recordNumber;
	private String errorMsg;

	public static ImportFileError getPersistError(Integer recordNumber,
			String errorMsg) {
		return new ImportFileError(recordNumber, String.format(PERSIST_ERROR,
				recordNumber, errorMsg));
	}
	
	public static ImportFileError getParseError(Integer recordNumber,
			String errorMsg) {
		return new ImportFileError(recordNumber, String.format(PARSE_ERROR,
				recordNumber, errorMsg));
	}

	private ImportFileError(Integer recordNumber, String errorMsg) {
		super();
		this.recordNumber = recordNumber;
		this.errorMsg = errorMsg;
	}

	public Integer getRecordNumber() {
		return recordNumber;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
