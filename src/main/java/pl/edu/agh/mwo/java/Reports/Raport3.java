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

    public Map<String, float[]> generateReport() {
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
        Map<String, float[]> report = new HashMap<>();
        for (String name : pracownikProjektyCzas.keySet()) {
            float[] employeeHours = new float[projekty.size()];
            for (int i = 0; i < projekty.size(); i++) {
                boolean containsProject = false;

                for (Projekt projektPracownika : pracownikProjektyCzas.get(name).keySet()) {
                    if (projekty.get(i).getNazwa().equals(projektPracownika.getNazwa())) {
                        float hours = pracownikProjektyCzas.get(name).get(projektPracownika);
                        // line += hours + "\t";
                        employeeHours[i] = hours;
                        containsProject = true;
                    }
                }
                if (!containsProject) {
//                    line += "0\t";
                    employeeHours[i] = 0;
                }
            }
            report.put(name, employeeHours);
        }
        return report;
    }

    @Override
    public void printReportConsole() {
        System.out.println("Raport 3");
        System.out.print("Pracownik ");
        for (Projekt projekt : projekty) {
            System.out.print(projekt.getNazwa() + "\t");
        }
        System.out.println();
        Map<String, float[]> report = this.generateReport();
        for (String name : report.keySet()) {
            // String line = name + "\t";
            System.out.print(name + "\t");
//            float[] hours = new float[projekty.size()];
            for (float hours : report.get(name)) {

                System.out.print(hours + "\t");
            }
            System.out.println();

            // report.forEach((key, value) -> System.out.println("Pracownik: " + key + ",
            // całkowity czas pracy: " + value));
            // TODO Auto-generated method stub
        }
    }

    @Override
    public void generateReportExcel() {
        // TODO Auto-generated method stub

    }

}
