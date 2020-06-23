package pl.edu.agh.mwo.java;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ErrorFinder {

	public void findErrors(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				findErrors(file.listFiles());
			} else {
				try {
					Workbook wb = WorkbookFactory.create(file);
					for (Sheet sheet : wb) {
						for (Row row : sheet) {
							for (Cell c : row) {
								if (row.getRowNum() >= 1) {
									if (c == null || c.getCellType() == CellType.BLANK) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
									if (c.getColumnIndex() == 0 && c.getCellType() == CellType.STRING) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
									if (c.getColumnIndex() == 0 && c.getCellType() == CellType.NUMERIC && c.getNumericCellValue() < 1000) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
									
									if (c.getColumnIndex() == 1 && c.getCellType() == CellType.NUMERIC) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
									
									if (c.getColumnIndex() == 2 && c.getCellType() == CellType.STRING) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
									if (c.getColumnIndex() == 2 && c.getCellType() == CellType.NUMERIC && c.getNumericCellValue() > 1000) {
										System.out.println("Error in: " + file.getAbsolutePath() + " "	+ wb.getSheetName(wb.getSheetIndex(sheet)) + " " + c.getAddress());
									}
								}
							}
						}
					}
				} catch (EncryptedDocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}