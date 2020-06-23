package pl.edu.agh.mwo.java.Reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.mwo.java.dataObjects.Pracownik;
 

public class Raport1 implements Raport {

    private List<Pracownik> pracownicy = new ArrayList<>();

    public Raport1(List<Pracownik> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public Map<String,Float> generateReport() {
        Map<String,Float> pracownikCalkowityCzasPracy = new HashMap<>();
        for (Pracownik pracownik : pracownicy) {
            String nazwaPracownika = pracownik.getNazwa();
            float calkowityCzasPracy = pracownik.calkowityCzasPracy();
            pracownikCalkowityCzasPracy.put(nazwaPracownika, calkowityCzasPracy);
        }
        return pracownikCalkowityCzasPracy;
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 1");
        Map<String,Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Pracownik: " + key + ", całkowity czas pracy: " + value));
        // TODO Auto-generated method stub
    }

    @Override
    public void generateReportExcel(){
    	XlsCreator.createReportFile(this.generateReport(), "Pracownik", "Całkowity czas pracy", "raport_1");
        }        
    }