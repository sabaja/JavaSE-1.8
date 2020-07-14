package com.csv.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

//https://codingjam.it/dai-costruttori-al-builder-pattern-in-java/
public class ExcelToProcess {
	/*
	 * private static final String TC = "TC"; private static int TC_START_INDEX;
	 * private static int TC_END_INDEX; private static final String SG = "SG";
	 * private static final String PATTERN_FINE_NUMBER = "00"; private static final
	 * String PATTERN_OGG_NUMBER = "000"; private static final int COLUMN = 2;
	 * private static final int ROW = 1; private static final int SHEET = 1; private
	 * static String fineNumber;
	 */

	HSSFWorkbook workBook; // mandatory
	Sheet sheet;
	Row row;
	Cell cell;
	String fineNumber;
	String ogg;

	private ExcelToProcess() {
		/* Use Builder to create ExcellToProcess */}

	@Override
	public String toString() {
		return "ExcelToProcess [workBook=" + workBook + ", sheet=" + sheet + ", row=" + row + ", cell=" + cell
				+ ", fineNumber=" + fineNumber + ", ogg=" + ogg + "]";
	}

	public ExcelToProcess(Builder builder) {
		this.sheet = builder.sheet;
		this.row = builder.row;
		this.cell = builder.cell;
		this.fineNumber = builder.fineNumber;
		this.ogg = builder.ogg;
	}

	public static class Builder {

		private HSSFWorkbook workBook;
		private Sheet sheet;
		private Row row;
		private Cell cell;
		private String fineNumber;
		private String ogg;

		public Builder(HSSFWorkbook workBook) {
			super();
			this.workBook = workBook;
		}

		public void set_sheet(Sheet sheet) {
			this.sheet = sheet;
		}

		public void set_row(Row row) {
			this.row = row;
		}

		public void set_cell(Cell cell) {
			this.cell = cell;
		}

		public void set_fineNumber(String fineNumber) {
			this.fineNumber = fineNumber;
		}

		public void set_ogg(String ogg) {
			this.ogg = ogg;
		}

		public ExcelToProcess build() {
			return new ExcelToProcess(this);
		}

	}

}
