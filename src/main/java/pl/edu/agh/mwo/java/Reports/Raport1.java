package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.dataObjects.Pracownik;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class Raport1 implements Raport {

    private List<Pracownik> pracownicy = new ArrayList<>();

    public Raport1(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public Map<String,Float> generateReport() {
        Map<String,Float> pracownikCalkowityCzasPracy = new HashMap<>();
        for (Pracownik pracownik : pracownicy) {
            String nazwaPracownika = pracownik.getNazwa();
            float calkowityCzasPracy = pracownik.calkowityCzasPracy();
            pracownikCalkowityCzasPracy.put(nazwaPracownika, calkowityCzasPracy);
        }
        return pracownikCalkowityCzasPracy;
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 1");
        Map<String,Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Pracownik: " + key + ", całkowity czas pracy: " + value));
        // TODO Auto-generated method stub
    }

    @Override
    public void generateReportExcel(){
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("raport");
	         
	        
	        Map<String,Float> report = this.generateReport();
	        int rowCount = 0;
            Row row = sheet.createRow(rowCount);
            Cell cell = row.createCell(0);
            cell.setCellValue("Pracownik");
            cell = row.createCell(1);
            cell.setCellValue("Całkowity czas pracy");
      
	        for (Map.Entry<String, Float> entry : report.entrySet()) {       	

	         
	            row = sheet.createRow(++rowCount);
	             
	            cell = row.createCell(0);
	                    cell.setCellValue((String) entry.getKey());
	              
	            cell = row.createCell(1);
	                    cell.setCellValue((Float) entry.getValue());
	                    
	            }
	                    
	        try (FileOutputStream outputStream = new FileOutputStream("Raport.xlsx")) {
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


