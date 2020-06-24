package pl.edu.agh.mwo.java.dataObjects;

import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

public class Zadanie {
    private Date data;
    private String nazwaZadania;
    private float czasPracy;

    public Zadanie(Date data, String nazwaZadania, float czasPracy) {
        this.data = data;
        this.nazwaZadania = nazwaZadania;
        this.czasPracy = czasPracy;
    }

    public Zadanie(Row zadanieWiersz) {
        this.data = zadanieWiersz.getCell(0).getDateCellValue();
        this.nazwaZadania = zadanieWiersz.getCell(1).toString();
        this.czasPracy = Float.parseFloat(String.valueOf(zadanieWiersz.getCell(2)));
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNazwaZadania() {
        return nazwaZadania;
    }

    public void setNazwaZadania(String nazwaZadania) {
        this.nazwaZadania = nazwaZadania;
    }

    public float getCzasPracy() {
        return czasPracy;
    }

    public void setCzasPracy(float czasPracy) {
        this.czasPracy = czasPracy;
    }

    @Override
    public String toString() {
        return "Zadanie{" +
                "data='" + data + '\'' +
                ", nazwaZadania='" + nazwaZadania + '\'' +
                ", czasPracy=" + czasPracy +
                "}\n";
    }
}