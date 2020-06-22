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
        Map<String, float[]> report = new HashMap<>();
        for (Pracownik pracownik : pracownicy) {
            float[] employeeHours = new float[projekty.size()];
            for (int i = 0; i < projekty.size(); i++) {
                boolean containsProject = false;
                for (Projekt projektPracownika : pracownik.getListaProjektow()) {
                    if (projekty.get(i).getNazwa().equals(projektPracownika.getNazwa())) {
                        employeeHours[i] = projektPracownika.calkowityCzasPracyNadProjektem();
                        containsProject = true;
                    }
                }
                if (!containsProject) {
                    employeeHours[i] = 0;
                }
            }
            report.put(pracownik.getNazwa(), employeeHours);
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
            System.out.print(name + "\t");
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
