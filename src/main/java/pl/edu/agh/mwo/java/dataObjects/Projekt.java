package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Iterator;
import java.util.List;

public class Projekt {
    private String nazwa;
    private List<Zadanie> listaZadan;

    public Projekt(String nazwa, List<Zadanie> listaZadan) {
        this.nazwa = nazwa;
        this.listaZadan = listaZadan;
    }

    public Projekt(Sheet projektKarta) {
        this.nazwa = projektKarta.getSheetName();
        for (Iterator<Row> zadanieWiersz = projektKarta.rowIterator(); zadanieWiersz.hasNext(); ) {
            listaZadan.add((Zadanie) zadanieWiersz.next());
        }
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Zadanie> getListaZadan() {
        return listaZadan;
    }

    public void setListaZadan(List<Zadanie> listaZadan) {
        this.listaZadan = listaZadan;
    }

    public void updateProjekt(Sheet projektKarta) {
        for (Iterator<Row> zadanieWiersz = projektKarta.rowIterator(); zadanieWiersz.hasNext(); ) {
            listaZadan.add((Zadanie) zadanieWiersz.next());
        }
    }
}
