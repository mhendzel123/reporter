package pl.edu.agh.mwo.java.Reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class XlsCreator {

	
		public static void createReportFile(Map<String,Float> report, String column1Title, String column2Title, String outputFilename){
		
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("raport");
         
        int rowCount = 0;
        Row row = sheet.createRow(rowCount);
        Cell cell = row.createCell(0);
        cell.setCellValue(column1Title);
        cell = row.createCell(1);
        cell.setCellValue(column2Title);
  
        for (Map.Entry<String, Float> entry : report.entrySet()) {          

         
            row = sheet.createRow(++rowCount);
             
            cell = row.createCell(0);
                    cell.setCellValue((String) entry.getKey());
              
            cell = row.createCell(1);
                    cell.setCellValue((Float) entry.getValue());
                    
            }
                    
        try (FileOutputStream outputStream = new FileOutputStream(outputFilename + ".xls")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		}


		public static void createReportFile(Map<String, float[]> report, String column1Title, List<Projekt> projekty, String outputFilename){
			
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("raport");
	         
	        int rowCount = 0;
	        Row row = sheet.createRow(rowCount);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(column1Title);
	        
	        int i =1;
	        for(Projekt projekt : projekty) {
	        cell = row.createCell(i);
	        cell.setCellValue(projekt.getNazwa());
	        i++;
	        }
	  
	        for (Map.Entry<String, float[]> entry : report.entrySet()) {          

	         
	            row = sheet.createRow(++rowCount);
	             
	            cell = row.createCell(0);
	                    cell.setCellValue((String) entry.getKey());
	                    
	                    int j = 1;
	                    for(Float value : (float[]) entry.getValue()) {
	                    	cell = row.createCell(j);
	   	                    cell.setCellValue(value);
	                    	j++;
	                    	
	                    }
	             	            
	              
	                    
	            }
	                    
	        try (FileOutputStream outputStream = new FileOutputStream(outputFilename + ".xls")) {
	            workbook.write(outputStream);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
}
		
		
		public static void createReportFile(LinkedHashMap<String, Float> report, String column1Title, String outputFilename){
			
			XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("raport");
	         
	        int rowCount = 0;
	        Row row = sheet.createRow(rowCount);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(column1Title);
	  
	        int i = 0;
	        for (Entry<String, Float> entry : report.entrySet()) {          

	         
	        	if (i == 20)
					break;
				i++;
				String[] fields = entry.getKey().split("\t");
	        	
	            row = sheet.createRow(++rowCount);
	             
	            cell = row.createCell(0);
	                    cell.setCellValue((String) fields[1]);
	    	    cell = row.createCell(1);
	                    cell.setCellValue((String) fields[0]);	              
	            cell = row.createCell(2);
	                    cell.setCellValue((Float) entry.getValue());
	                    
	            }
	                    
	        try (FileOutputStream outputStream = new FileOutputStream(outputFilename + ".xls")) {
	            workbook.write(outputStream);
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			}
		
}
