package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.*;

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

    public List<Date> datyWProjekcie() {
        List<Date> daty = new ArrayList<>();
        for (int i = 0; i < listaZadan.size(); i++) {
            daty.add(listaZadan.get(i).getData());
        }
        return daty;
    }

    public boolean czyWierszPusty(Row wiersz) {	//pusty lub błędny
        boolean pusty = false;
        for (int i = 0; i <= 2; i++) {
            Cell c = wiersz.getCell(i);
            if (c == null || c.getCellType() == CellType.BLANK) { 
            	pusty = true;
            	break;
			} else if (c.getColumnIndex() == 0 && c.getCellType() == CellType.STRING) {
	            pusty = true;
	            break;
			} else if (c.getColumnIndex() == 0 && c.getCellType() == CellType.NUMERIC && c.getNumericCellValue() < 1000) {
	            pusty = true;
	            break;
			} else if (c.getColumnIndex() == 1 && c.getCellType() == CellType.NUMERIC) {
	            pusty = true;
	            break;
			} else if (c.getColumnIndex() == 2 && c.getCellType() == CellType.STRING) {
	            pusty = true;
	            break;
			} else if (c.getColumnIndex() == 2 && c.getCellType() == CellType.NUMERIC && c.getNumericCellValue() > 1000) {
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