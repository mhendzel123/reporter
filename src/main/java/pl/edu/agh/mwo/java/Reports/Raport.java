package pl.edu.agh.mwo.java.Reports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public interface Raport {

    
    public abstract void printReportConsole();
    public abstract void generateReportExcel();
    
    
}
