package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.commons.cli.*;
import pl.edu.agh.mwo.java.Reports.Raport1;
import pl.edu.agh.mwo.java.dataObjects.Pracownik;

public class App {

	public static void main(String[] args) throws Exception {
		File example = new File("/home/you/Desktop");

		Options options = new Options();

		Option source = new Option("s", "source", true, "input file path");
		source.setRequired(true);
		options.addOption(source);

		Option report = new Option("r", "report", true, "report type");
		source.setRequired(true);
		options.addOption(report);

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("utility-name", options);
			System.exit(1);
		}

		String inputFilePath = cmd.getOptionValue("source");
		String reportType = cmd.getOptionValue("report");

		System.out.println(inputFilePath);
		System.out.println(reportType);

		// generowanie listy pracownikow z ich projektami i zadaniami po plikach po podanej sciezce (rekursywnie)
		File inputPath = new File(inputFilePath);
		List<Pracownik> pracownicy = (new WorkbookLoader()).listaPracownikowZFolderu(inputPath);

		// tu mozna sprawdzic ze w przypypadku Jana Kowalskiego zadania zostsaly poprawnie dodane do odpowiadajacych im projektom
		// oraz ze puste linie sa pomijane (02/Kowalski_Jan ma pusta)
		pracownicy.forEach((pracownik) -> {
			System.out.println(pracownik);
		});

		// generowanie raportu nr 1
		if (Integer.parseInt(reportType) == 1) {
			Raport1 report1 = new Raport1(pracownicy);
			report1.printReportConsole();
		}


	}
}
