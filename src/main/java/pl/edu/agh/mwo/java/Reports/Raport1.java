package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.time.LocalDate;

public class Raport1 implements Raport {

    @Override
    public void printReportConsole(String inputFilePath) throws IOException {
    PrintData printdata=new PrintData();
    System.out.println("Raport numer 1");
    System.out.println("--------------------------------");
    System.out.println(String.format("%-15s %5s %-15s", "Imię i nazwisko", "|", "Czas pracy"));
    System.out.println("--------------------------------");
    printdata.printEmployeesWithHours(inputFilePath);  
    }

    @Override
    public void generateReportExcel() {
        // TODO Auto-generated method stub
        
    }

}
