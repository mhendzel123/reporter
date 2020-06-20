package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class App {
    public static void main(String[] args) {

        //System.out.println("Hello World!");

        Workbook f1Wb = WorkbookLoader.openF1Workbook();
        
        //zad1(f1Wb);
        //zad2(f1Wb);
        //zad3(f1Wb);
        zad4(f1Wb);
        

        //PoiDemo.printSheetNames(f1Wb);
        //PoiDemo.printCellsFromSheet(f1Wb.getSheetAt(0));
        //PoiDemo.computePointsForARace(f1Wb.getSheetAt(0));

        //Workbook sudokuWb = WorkbookLoader.openSudokuWorkbook();

        //PoiDemo.computeAllNumberInAGivenColumn(sudokuWb.getSheetAt(0), 0);

    }
    
    public static void zad1(Workbook wb) {
    	System.out.println("The winners of F1 races:");
    	Set<String> winners = new HashSet<String>();
    	for (Sheet sheet : wb) {
    		Cell cell = sheet.getRow(0).getCell(2);
    		//System.out.println(cell.getStringCellValue());
    		winners.add(cell.getStringCellValue());
    	}
    	
    	for (String winner : winners) {
    		System.out.println(winner);
    	}
    }
    
    public static void zad2(Workbook wb) {
    	int n = 0;
    	for (Sheet sheet : wb) {
    		for (Row row : sheet) {
    			Cell name = row.getCell(2);    			
    			//System.out.println(name.getStringCellValue() + " " + point.getStringCellValue());
    			if (name.getStringCellValue().equals("Lewis Hamilton HAM")) {
    				Cell points = row.getCell(5);
    				int intPoints = Integer.valueOf(points.getStringCellValue());
    				//System.out.println(intPoints);
    				n += intPoints;
    				break;
    			}
    		}
    	}
    	System.out.print(n);
    }
    
    public static void zad3(Workbook wb) {
    	int n = 0;
    	for (Sheet sheet : wb) {
    		for (Row row : sheet) {
    			Cell team = row.getCell(3);    			
    			if (team.getStringCellValue().equals("Ferrari")) {
    				Cell points = row.getCell(5);
    				//int intPoints = Integer.valueOf(points.getStringCellValue());
    				Integer intPoints = Integer.parseInt(points.getStringCellValue());
    				//System.out.println(intPoints);
    				n += intPoints;
    			}
    		}
    	}
    	System.out.print(n);
    }
    
    public static void zad4(Workbook wb) {
    	Map<String, Integer> results = new HashMap<String, Integer>(); 
    	for (Sheet sheet : wb) {
    		for (Row row : sheet) {
    			String driver = row.getCell(2).getStringCellValue();
    			int points =Integer.parseInt(row.getCell(5).getStringCellValue());
    			if (results.containsKey(driver)) {
    				results.put(driver, (results.get(driver) + points));
    			} else {
    				results.put(driver, points);
    			}
    		}
    	}

    	List<Entry<String, Integer>> resultsList = new ArrayList<Entry<String, Integer>>(results.entrySet());
    	//for (Entry<String, Integer> entry : results.entrySet()) {
    	//   		resultsList.add(entry);
    	//}

    	resultsList.sort(Collections.reverseOrder(Entry.comparingByValue()));

    	int n = 1;
    	for (Map.Entry<String, Integer> entry : resultsList) {
    		//System.out.println(n + " " + entry.getKey() + " " + entry.getValue());
    		//n += 1;
    		System.out.println(String.format("%2d. %s -> %d", ++n, entry.getKey(), entry.getValue()));
     	}
    }
    
    
}
