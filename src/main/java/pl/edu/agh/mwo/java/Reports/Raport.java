package pl.edu.agh.mwo.java.Reports;

import java.time.LocalDate;

public interface Raport {

    
    public abstract void printReportConsole();
    public abstract void generateReportExcel();
    
    
}
