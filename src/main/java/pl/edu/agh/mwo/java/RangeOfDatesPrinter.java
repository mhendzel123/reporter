package pl.edu.agh.mwo.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class RangeOfDatesPrinter {
	String daty;

    public RangeOfDatesPrinter(List<Projekt> projekty) {
      List<Date> wszystkieDatyZProjektowWszystkichPracownikow = new ArrayList<>();
        
      for (Projekt projekt : projekty) {
          wszystkieDatyZProjektowWszystkichPracownikow.addAll(projekt.datyWProjekcie());
      }
      Date minData = Collections.min(wszystkieDatyZProjektowWszystkichPracownikow);
      Date maxData = Collections.max(wszystkieDatyZProjektowWszystkichPracownikow);

      this.daty = String.format("Zakres dat od %tF do %tF", minData, maxData);
    }

	public void printDates() {
		System.out.println(daty);
	}
}