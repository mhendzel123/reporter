package pl.edu.agh.mwo.java.Reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.mwo.java.dataObjects.Projekt;
import pl.edu.agh.mwo.java.dataObjects.Zadanie;

public class Raport5 implements Raport {

    private List<Projekt> projekty = new ArrayList<>();
    private String filter;

    public Raport5(List<Projekt> projekty, String filter) {
        this.projekty = projekty;
        this.filter = filter.toLowerCase();
    }
    
    public Map<String, Float> generateReport() {
        Map<String, Float> report = new HashMap<>();
        for (Projekt projekt : projekty) {
            String nazwaProjektu = projekt.getNazwa();
            for (Zadanie zadanie : projekt.getListaZadan()) {
                String nazwaZadania = zadanie.getNazwaZadania();
                if (nazwaZadania.toLowerCase().contains(filter)) {
                    float taskHours = zadanie.getCzasPracy();
                    String reportKey = nazwaProjektu + '\t' + nazwaZadania;
                    if (report.containsKey(reportKey)) {
                        float czasIstniejacegoZadania = report.get(reportKey);
                        report.put(reportKey, czasIstniejacegoZadania + taskHours);
                    } else {
                        report.put(reportKey, taskHours);
                    }
                }
            }
        }
        return report;
    }
    
    public float getHoursSum() {
        float sum = 0;
        Map<String, Float> report = this.generateReport();
        for (String task : report.keySet()) {
            sum += report.get(task);
        }
        return sum;
    }
    
    
    @Override
    public void printReportConsole() {
        System.out.println("Raport 5");
        System.out.println("Szukane wyrażenie: " + filter);
        System.out.println("Suma godzin: " + this.getHoursSum());
        Map<String, Float> report = this.generateReport();
        for (String task : report.keySet()) {
            System.out.format("%-60s %10.2f \n", task, report.get(task));
        }
    }

    @Override
    public void generateReportExcel() {
    	XlsCreator.createReportFile(this.generateReport(), this.getHoursSum(), filter, "raport_5");

    }

}
