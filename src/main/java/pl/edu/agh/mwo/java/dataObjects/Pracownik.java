package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class Pracownik {
    private String nazwa;
    private List<Projekt> listaProjektow;

    public Pracownik(String nazwa, List<Projekt> listaProjektow) {
        this.nazwa = nazwa;
        this.listaProjektow = listaProjektow;
    }

    public Pracownik(String nazwa, Workbook projekty) {
        this.nazwa = nazwa;
        for (int i = 0; i < projekty.getNumberOfSheets(); i++)
        {
            listaProjektow.add((Projekt) projekty.getSheetAt(i));
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Projekt> getListaProjektow() {
        return listaProjektow;
    }

    public void setListaProjektow(List<Projekt> listaProjektow) {
        this.listaProjektow = listaProjektow;
    }

    public void updatePracownik(Workbook projekty) {
        for (int i = 0; i < projekty.getNumberOfSheets(); i++)
        {
            Sheet projektKarta = projekty.getSheetAt(i);
            String nazwaProjektu = projektKarta.getSheetName();
            listaProjektow.forEach((projekt) -> {
                if (projekt.getNazwa().equals(nazwaProjektu)) {
                    projekt.updateProjekt(projektKarta);
                } else {
                    listaProjektow.add((Projekt) projektKarta);
                }
            });
        }
    }
}
