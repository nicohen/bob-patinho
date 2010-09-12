package com.self_managment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.exception.SuperCSVException;
import org.supercsv.exception.SuperCSVReflectionException;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.self_managment.model.entity.Stock;

public class TestCSV {

    /**
     * @param args
     * @throws IOException
     * @throws SuperCSVException
     * @throws SuperCSVReflectionException
     * @throws IOException
     */
    public static void main(String[] args) throws SuperCSVReflectionException,
	    SuperCSVException, IOException {
	ICsvBeanReader inFile = new CsvBeanReader(new FileReader(new File(
		"src/main/java/com/self_managment/test.csv")),
		CsvPreference.EXCEL_PREFERENCE);
	try {
	    // final String[] header = inFile.getCSVHeader(true);
	    final String[] header = new String[] { "stockId", "stockCode",
		    "stockName" };

	    final CellProcessor[] stockProcessors = new CellProcessor[] {
		    new ParseInt(), new Unique(new StrMinMax(5, 10)),
		    new Unique(new StrMinMax(5, 20)) };

	    Stock stock;
	    while ((stock = inFile.read(Stock.class, header, stockProcessors)) != null) {
		System.out.println(stock);
	    }
	} finally {
	    inFile.close();
	}

    }
}
