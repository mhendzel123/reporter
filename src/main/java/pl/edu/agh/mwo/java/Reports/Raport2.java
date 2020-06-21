package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.time.LocalDate;

public class Raport2 implements Raport {

    @Override
    public void printReportConsole(String inputFilePath) throws IOException {
    PrintData printdata=new PrintData();
    System.out.println("Raport numer 2");
    System.out.println("--------------------------------");
    System.out.println(String.format("%-15s %5s %-15s", "Projekt", "|", "Ilosc godzin"));
    System.out.println("--------------------------------");
    printdata.printProjectsWithHours(inputFilePath);  
    }

    @Override
    public void generateReportExcel() {
        // TODO Auto-generated method stub
        
    }

}
