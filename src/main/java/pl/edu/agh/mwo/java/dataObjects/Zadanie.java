package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Row;

public class Zadanie {
    // TODO: kastowanie data do LocalDate celem latwiejszej pozniejszej obrobki/wykonywania zadan
    private String data;
    private String nazwaZadania;
    private int czasPracy;

    public Zadanie(String data, String nazwaZadania, int czasPracy) {
        this.data = data;
        this.nazwaZadania = nazwaZadania;
        this.czasPracy = czasPracy;
    }

    public Zadanie(Row zadanieWiersz) {
        this.data = zadanieWiersz.getCell(0).toString();
        this.nazwaZadania = zadanieWiersz.getCell(1).toString();
        this.czasPracy = Integer.parseInt(String.valueOf(zadanieWiersz.getCell(2)));
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNazwaZadania() {
        return nazwaZadania;
    }

    public void setNazwaZadania(String nazwaZadania) {
        this.nazwaZadania = nazwaZadania;
    }

    public int getCzasPracy() {
        return czasPracy;
    }

    public void setCzasPracy(int czasPracy) {
        this.czasPracy = czasPracy;
    }
}
