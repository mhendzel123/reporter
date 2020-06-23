package pl.edu.agh.mwo.java.Reports;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pl.edu.agh.mwo.java.dataObjects.Projekt;
import pl.edu.agh.mwo.java.dataObjects.Zadanie;

public class Raport4 implements Raport {
	private List<Projekt> projekty = new ArrayList<>();

	public Raport4(List<Projekt> projekty) {
		this.projekty = projekty;
	}

	public LinkedHashMap<String, Float> sortHashMapByValues(
	        HashMap<String, Float> passedMap) {
        List<Entry<String, Float>> list = new LinkedList<>(passedMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));
	}

	public LinkedHashMap<String, Float> generateReport() {
		// Map<Pair<String, String>, float[]> report = new HashMap<>();
		HashMap<String, Float> report = new HashMap<>();
		for (Projekt projekt : projekty) {
			for (Zadanie zadanie : projekt.getListaZadan()) {
				String nazwaZadania = zadanie.getNazwaZadania();
				String nazwaProjektu = projekt.getNazwa();
				float taskHours = zadanie.getCzasPracy();
				String reportKey = nazwaProjektu + '\t' + nazwaZadania;
				// Pair<String, String> reportKey = new Pair.of(nazwaProjektu,
				// nazwaZadania);
				// Pary nie dzialaja w javie... wiec
				if (report.containsKey(reportKey)) {
					float czasIstniejacegoZadania = report.get(reportKey);
					report.put(reportKey, czasIstniejacegoZadania + taskHours);
				} else {
					report.put(reportKey, taskHours);
				}
			}
		}
		return sortHashMapByValues(report);
	}

	@Override
	public void printReportConsole() {
		System.out.println("Raport 4 - TOP 20");
	}

	@Override
	public void generateReportExcel() {
		int maxLenProject = 0, maxLenTask = 0;
		LinkedHashMap<String, Float> report = this.generateReport();
		int i = 0;
		for (Entry<String, Float> entry : report.entrySet()) {
			if (i == 20)
				break;
			i++;
			String[] fields = entry.getKey().split("\t");
			if (fields[0].length() > maxLenProject)
				maxLenProject = fields[0].length();
			if (fields[1].length() > maxLenTask)
				maxLenTask = fields[1].length();
		}
		i = 0;
		for (Entry<String, Float> entry : report.entrySet()) {
			if (i == 20)
				break;
			i++;
			String[] fields = entry.getKey().split("\t");

			System.out.print(fields[1]);
			for (int numSpace = 0; numSpace < maxLenTask - fields[1].length()+3; numSpace++)
				System.out.print(" ");			
			
			System.out.print(fields[0]);
			for (int numSpace = 0; numSpace < maxLenProject - fields[0].length()+3; numSpace++)
				System.out.print(" ");
			
			System.out.println("   " + entry.getValue());
		}
	}
}
