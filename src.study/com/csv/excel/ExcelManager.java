package com.csv.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelManager {

	private static final String TC = "TC";
	private static final String SG = "SG";
	private static final String PATTERN_FINE_NUMBER = "00";
	private static final String PATTERN_OGG_NUMBER = "000";
	private static final int COLUMN = 2;
	private static final int ROW = 1;
	private static final int SHEET = 1;
	private static String fineNumber;

	public static void main(String[] args) {

		try {
			DataFormatter formatter = new DataFormatter();
			// FileInputStream file = new FileInputStream(new
			// File("C:\\Users\\sabatinija\\Desktop\\VODAFONE\\SG7581 -
			// GigaRicarica\\TestBook\\TestBook_Progettazione Giga Ricarica - Copy.xls"));
			FileInputStream file = new FileInputStream(new File("TestBook_Progettazione Giga Ricarica - Copy.xls"));
			Workbook workbook = new HSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(SHEET);
			setFineNumber(formatter, sheet, COLUMN, ROW, PATTERN_FINE_NUMBER);
			System.out.println("fineNumber: " + fineNumber);
			renumbering(sheet, COLUMN, TC.concat(fineNumber));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setFineNumber(final DataFormatter formatter, final Sheet sheet, final int COLUMN, final int ROW,
			final String pattern) {
		String tempNumber = findFineNumber(formatter, sheet, COLUMN, ROW);
		fineNumber = formatNumber(Integer.valueOf(tempNumber), pattern);
	}

	private static String findFineNumber(DataFormatter formatter, Sheet sheet, final int COLUMN, final int ROW) {
		Row row = sheet.getRow(ROW);
		Cell cell = row.getCell(COLUMN);
		String text = formatter.formatCellValue(cell);
		return findFineNumber(text);
	}

	private static String findFineNumber(String text) {
		Integer fineNumber = 0;
		Pattern word = Pattern.compile(TC);
		Matcher match = word.matcher(text);
		while (match.find()) {
			System.out.println("Found TC at index " + match.start() + " - " + (match.end()) + " - " + text.length());
			fineNumber = Integer.valueOf(text.substring(match.end(), match.end() + 2));
		}
		return String.valueOf(fineNumber);
	}

	private static String formatNumber(final Integer tempNum, final String pattern) {
		String fineNumber;
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		fineNumber = myFormatter.format(tempNum);
		return fineNumber;
	}

	private static void renumbering(final Sheet sheet, final int COLUMN, final String TC_NUM) {
		final String INTIAL_NUMB = "001";
		DataFormatter formatter = new DataFormatter();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (Objects.nonNull(row)) {
				Cell cell = row.getCell(COLUMN);
				String cellValue;
				if ((cellValue = cell.getStringCellValue()).contains(SG)) {
					cellValue = cellValue.trim();
					final int start = cellValue.indexOf(TC_NUM) + TC_NUM.length();
					final int end = cellValue.length();
					String lll = cellValue.substring(start, end);
					System.out.println(lll);
				}
				/*
				 * if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				 * valuesadd(cell.getNumericCellValue()); } else if(cell.getCellType() ==
				 * Cell.CELL_TYPE_FORMULA && cell.getCachedFormulaResultType() ==
				 * Cell.CELL_TYPE_NUMERIC) { valuesadd(c.getNumericCellValue()); }
				 */

			}
		}
	}
}
