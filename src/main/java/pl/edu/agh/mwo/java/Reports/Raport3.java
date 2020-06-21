package pl.edu.agh.mwo.java.Reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.mwo.java.dataObjects.Pracownik;
import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class Raport3 implements Raport {

    private List<Projekt> projekty = new ArrayList<>();
    private List<Pracownik> pracownicy = new ArrayList<>();

    public Raport3(List<Projekt> projekty, List<Pracownik> pracownicy) {
        this.projekty = projekty;
        this.pracownicy = pracownicy;
    }

    public Map<String, Map<Projekt, Float>> generateReport() {
        Map<String, Map<Projekt, Float>> pracownikProjektyCzas = new HashMap<>();
        for (Pracownik pracownik : pracownicy) {
            Map<Projekt, Float> hours = new HashMap<>();
            String nazwaPracownika = pracownik.getNazwa();
            for (Projekt projekt : pracownik.getListaProjektow()) {

                hours.put(projekt, projekt.calkowityCzasPracyNadProjektem());

            }
            ;
//            System.out.println(nazwaPracownika);
//            for (Projekt p : hours.keySet()) {
//                System.out.println(p.getNazwa() + " " + hours.get(p));
//            }
            pracownikProjektyCzas.put(nazwaPracownika, hours);

        }
//        for (String pracownik : pracownikProjektyCzas.keySet()) {
//            System.out.println(pracownik);
//            for (Projekt p : pracownikProjektyCzas.get(pracownik).keySet()) {
//                System.out.println(p.getNazwa() + " " + pracownikProjektyCzas.get(pracownik).get(p));
//            }
//        }
        return pracownikProjektyCzas;
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 3");
        System.out.print("Pracownik ");
        for (Projekt projekt : projekty) {
            System.out.print(projekt.getNazwa() + " ");
        }
        System.out.println();
        Map<String, Map<Projekt, Float>> report = this.generateReport();
        for (String name : report.keySet()) {
            String line = name + "\t";
//            float[] hours = new float[projekty.size()];
            for (Projekt projekt : projekty) {
                boolean containsProject = false;
                
                for (Projekt projektPracownika : report.get(name).keySet()) {
                    if(projekt.getNazwa().equals(projektPracownika.getNazwa())) {
                        float hours = report.get(name).get(projektPracownika);
                        line += hours + "\t";
                        containsProject = true;
                    }
                }

                if (!containsProject) {
                    line += "0\t";
                }
            }
//            for (float h : hours) {
//                line += h + "\t";
//            }
            System.out.println(line);
        }

        // report.forEach((key, value) -> System.out.println("Pracownik: " + key + ",
        // całkowity czas pracy: " + value));
        // TODO Auto-generated method stub
    }

    @Override
    public void generateReportExcel() {
        // TODO Auto-generated method stub

    }

}
