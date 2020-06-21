package pl.edu.agh.mwo.java.Reports;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PrintData {
    public Long countHours(Workbook wb) {
        Long sum;
        sum = (long) 0.0;
        for (Sheet sheet : wb) {
            for (int rowNum = sheet.getLastRowNum(); rowNum >= 1; rowNum--) {
                final Row row = sheet.getRow(rowNum);
                Long amountOfHours = (long) row.getCell(2).getNumericCellValue();
                sum += amountOfHours;
                // if (row != null && row.getCell(2) != null) {
                // rowIndex = rowNum;
                // break;
                // }
            }

        }
        return sum;
    }

    public Map<String, Long> countHoursWorksheet(Workbook wb) {
        Long sum;
        sum = (long) 0.0;
        Map<String, Long> map = new HashMap<String, Long>();
        for (Sheet sheet : wb) {
            sum = (long) 0.0;
            for (int rowNum = sheet.getLastRowNum(); rowNum >= 1; rowNum--) {
                final Row row = sheet.getRow(rowNum);
                Long amountOfHours = (long) row.getCell(2).getNumericCellValue();
                sum += amountOfHours;
                // if (row != null && row.getCell(2) != null) {
                // rowIndex = rowNum;
                // break;
                // }
            }
            if (map.containsKey(sheet.getSheetName())) {
                map.put(sheet.getSheetName(), map.get(sheet.getSheetName()) + sum);
            } else {
                map.put(sheet.getSheetName(), sum);
            }

        }
        return map;
    }

    public void printProjectsWithHours(String inputPathFile) throws IOException {
        Map<String, Long> map1;
        PrintData printData = new PrintData();
        Map<String, Long> map = new HashMap<String, Long>();
        ListFiles listFiles = new ListFiles();

        List<File> files = null;
        files = listFiles.listXlsFiles(inputPathFile);
        for (File file : files) {
            Workbook wb = openWorkbook(file.toString());
            map1 = printData.countHoursWorksheet(wb);
            for (Map.Entry<String, Long> entry : map1.entrySet())
                if (map.containsKey(entry.getKey())) {
                    map.put(entry.getKey(), map.get(entry.getKey()) + entry.getValue());
                } else {
                    map.put(entry.getKey(), entry.getValue());
                }
        }

        for (String name : map.keySet()) {
            String key = name.toString();
            String value = map.get(name).toString();
            System.out.println(String.format("%-15s %5s %-15s", key, "|", value));
        }

    }

    public void printEmployeesWithHours(String inputPathFile) throws IOException {
        Map<String, Long> map = new HashMap<String, Long>();
        ListFiles listFiles = new ListFiles();
        PrintData printData = new PrintData();
        List<File> files;
        files = listFiles.listXlsFiles(inputPathFile);
        for (File file : files) {
            Workbook wb = openWorkbook(file.toString());
            if (map.containsKey(listFiles.printEmployeeNames(file))) {
                map.put(listFiles.printEmployeeNames(file),
                        map.get(listFiles.printEmployeeNames(file)) + printData.countHours(wb));

            } else {
                map.put(listFiles.printEmployeeNames(file), printData.countHours(wb));
            }

        }
        for (String name : map.keySet()) {
            String key = name.toString();
            String value = map.get(name).toString();
            System.out.println(String.format("%-15s %5s %-15s", key, "|", value));
        }
    }

    public  Workbook openWorkbook(String file) throws EncryptedDocumentException, IOException {
        return WorkbookFactory.create(new File(file));
    }
}
