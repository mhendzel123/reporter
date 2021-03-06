package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.mwo.java.dataObjects.Pracownik;

public class Raport1 implements Raport {

    private List<Pracownik> pracownicy = new ArrayList<>();

    public Raport1(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public Map<String, Float> generateReport() {
        Map<String, Float> pracownikCalkowityCzasPracy = new HashMap<>();
        for (Pracownik pracownik : pracownicy) {
            String nazwaPracownika = pracownik.getNazwa();
            float calkowityCzasPracy = pracownik.calkowityCzasPracy();
            pracownikCalkowityCzasPracy.put(nazwaPracownika, calkowityCzasPracy);
        }
        return pracownikCalkowityCzasPracy;
    }

    @Override
    public void printReportConsole() {
        System.out.println("\nRaport 1\n");
        Map<String, Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Pracownik: " + key + ", całkowity czas pracy: " + value));
        // TODO Auto-generated method stub
    	System.out.println("\n-----------------------------------"); 
        System.out.println("Raport gotowy!\n"); 

    }

    @Override
    public void generateReportExcel() {
        XlsCreator.createReportFile(this.generateReport(), "Pracownik", "Całkowity czas pracy", "raport_1");
    }

    public void generateReportChart() throws IOException{
        ChartCreator chartCreator=new ChartCreator();
        chartCreator.showChart(chartCreator.generateChart(this.generateReport(), "Raport 1", "Pracownicy",  "Liczba przepracowanych godzin"));
        } 
  

    public void saveReportChart() throws IOException{
        ChartCreator chartCreator=new ChartCreator();
        chartCreator.saveReportAsChartPNG(this.generateReport(), "Raport 1", "Pracownicy",  "Liczba przepracowanych godzin");
        } 
}
