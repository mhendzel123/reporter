package pl.edu.agh.mwo.java.Reports;

import pl.edu.agh.mwo.java.dataObjects.Pracownik;

import java.util.*;

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

    public String wypiszZakresDat() {
        List<Date> wszystkieDatyZProjektowWszystkichPracownikow = new ArrayList<>();
        for (Pracownik pracownik : pracownicy) {
            wszystkieDatyZProjektowWszystkichPracownikow.addAll(pracownik.datyZWszystkichProjektow());
        }
        Date minData = Collections.min(wszystkieDatyZProjektowWszystkichPracownikow);
        Date maxData = Collections.max(wszystkieDatyZProjektowWszystkichPracownikow);

        return String.format("Zakres dat od %tF do %tF", minData, maxData);
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 1");
        System.out.println(this.wypiszZakresDat());
        Map<String,Float> report = this.generateReport();
        report.forEach((key, value) -> System.out.println("Pracownik: " + key + ", całkowity czas pracy: " + value));
        // TODO Auto-generated method stub
    }

    @Override
    public void generateReportExcel() {
        // TODO Auto-generated method stub
        
    }

}
