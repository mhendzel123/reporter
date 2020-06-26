package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class Raport2 implements Raport {

    private List<Projekt> projekty = new ArrayList<>();

    public Raport2(List<Projekt> projekty) {
        this.projekty = projekty;
    }

    public Map<String, Float> generateReport() {
        Map<String, Float> projektyCalkowitaLiczbaGodzin = new HashMap<>();
        for (Projekt projekt : projekty) {
            String nazwaProjektu = projekt.getNazwa();
            float calkowityCzas = projekt.calkowityCzasPracyNadProjektem();
            projektyCalkowitaLiczbaGodzin.put(nazwaProjektu, calkowityCzas);
        }
        return projektyCalkowitaLiczbaGodzin;
    }

    @Override
    public void printReportConsole() {
        System.out.println("\nRaport 2\n");
        Map<String, Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Projekt: " + key + ", całkowity czas pracy: " + value));
    	System.out.println("\n-----------------------------------"); 
        System.out.println("Raport gotowy!\n"); 
    }

    @Override
    public void generateReportExcel() {
        XlsCreator.createReportFile(this.generateReport(), "Projekt", "Całkowity czas pracy", "raport_2");
    }

    public void generateReportChart() throws IOException{
        ChartCreator chartCreator=new ChartCreator();
        chartCreator.showChart(chartCreator.generateChart(this.generateReport(), "Raport 2", "Projekty",  "Liczba godzin w projekcie"));
        } 
    
    public void saveReportChart() throws IOException{
        ChartCreator chartCreator=new ChartCreator();
        chartCreator.saveReportAsChartPNG(this.generateReport(), "Raport 2", "Projekty",  "Liczba godzin przepracowanych w projekcie");
        } 

}
