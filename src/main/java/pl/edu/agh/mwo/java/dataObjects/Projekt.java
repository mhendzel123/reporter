package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Projekt {
    private String nazwa;
    private List<Zadanie> listaZadan = new ArrayList<>();

    public Projekt(String nazwa, List<Zadanie> listaZadan) {
        this.nazwa = nazwa;
        this.listaZadan = listaZadan;
    }

    public Projekt(Sheet projektKarta) {
        this.nazwa = projektKarta.getSheetName();
        this.updateProjekt(projektKarta);
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
        Iterator<Row> zadanieWiersz = projektKarta.rowIterator();
        zadanieWiersz.next();
        while (zadanieWiersz.hasNext()) {
            Row noweZadanie = zadanieWiersz.next();
            if (!czyWierszPusty(noweZadanie)) {
                listaZadan.add(new Zadanie(noweZadanie));
            }
        }
    }

    public float calkowityCzasPracyNadProjektem() {
        float czasCalkowity = 0;
        for (int i = 0; i < listaZadan.size(); i++) {
            czasCalkowity = czasCalkowity + listaZadan.get(i).getCzasPracy();
        }
        return czasCalkowity;
    }

    public boolean czyWierszPusty(Row wiersz) {
        boolean pusty = false;
        for (int i = 0; i <= 2; i++) {
            Cell c = wiersz.getCell(i);
            if (c == null || c.getCellType() == CellType.BLANK) {
                pusty = true;
                break;
            }
        }
        return pusty;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "nazwa='" + nazwa + '\'' +
                ", listaZadan=" + listaZadan +
                "}\n";
    }
}
