package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

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

public class Raport2 implements Raport {

    private List<Projekt> projekty= new ArrayList<>();

    public Raport2(List<Projekt> projekty) {
        this.projekty = projekty;
    }

    public Map<String,Float> generateReport() {
        Map<String,Float> projektyCalkowitaLiczbaGodzin = new HashMap<>();
        for (Projekt projekt : projekty) {
            String nazwaProjektu = projekt.getNazwa();
            float calkowityCzas = projekt.calkowityCzasPracyNadProjektem();
            projektyCalkowitaLiczbaGodzin.put(nazwaProjektu, calkowityCzas);
        }
        return projektyCalkowitaLiczbaGodzin;
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 2");
        Map<String,Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Projekt: " + key + ", całkowity czas pracy: " + value));
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
        cell.setCellValue("Projekt");
        cell = row.createCell(1);
        cell.setCellValue("Całkowity czas pracy");
  
        for (Map.Entry<String, Float> entry : report.entrySet()) {          

         
            row = sheet.createRow(++rowCount);
             
            cell = row.createCell(0);
                    cell.setCellValue((String) entry.getKey());
              
            cell = row.createCell(1);
                    cell.setCellValue((Float) entry.getValue());
                    
            }
                    
        try (FileOutputStream outputStream = new FileOutputStream("Raport_2.xlsx")) {
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