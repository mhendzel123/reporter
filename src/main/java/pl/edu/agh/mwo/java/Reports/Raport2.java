package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void generateReportExcel() {
        // TODO Auto-generated method stub
        
    }

}
