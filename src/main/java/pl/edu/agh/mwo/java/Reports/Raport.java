package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.time.LocalDate;

public interface Raport {


    public abstract void printReportConsole(String inputFilePath) throws IOException;
    public abstract void generateReportExcel();
    
    
}
