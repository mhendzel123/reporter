package pl.edu.agh.mwo.java;

import java.io.File;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import pl.edu.agh.mwo.java.Reports.Raport1;
import pl.edu.agh.mwo.java.Reports.Raport2;
import pl.edu.agh.mwo.java.Reports.Raport3;
import pl.edu.agh.mwo.java.Reports.Raport4;
import pl.edu.agh.mwo.java.dataObjects.Pracownik;
import pl.edu.agh.mwo.java.dataObjects.Projekt;

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

//		System.out.println(inputFilePath);
//		System.out.println(reportType);

		// generowanie listy pracownikow z ich projektami i zadaniami po plikach po
		// podanej sciezce (rekursywnie)
		File inputPath = new File(inputFilePath);
		List<Pracownik> pracownicy = (new WorkbookLoader()).listaPracownikowZFolderu(inputPath);
		List<Projekt> projekty = (new WorkbookLoader()).listaProjektowZFolderu(inputPath);
		// tu mozna sprawdzic ze w przypypadku Jana Kowalskiego zadania zostsaly
		// poprawnie dodane do odpowiadajacych im projektom
		// oraz ze puste linie sa pomijane (02/Kowalski_Jan ma pusta)
		pracownicy.forEach((pracownik) -> {
//			 System.out.println(pracownik);
		});

		// wypisywanie bledow
		File[] files = new File(inputFilePath).listFiles();
		ErrorFinder finder = new ErrorFinder();
		finder.findErrors(files);

		// generowanie raportu nr 1
		if (Integer.parseInt(reportType) == 1) {
			Raport1 report1 = new Raport1(pracownicy);
			report1.printReportConsole();
			report1.generateReportExcel();
			report1.generateReportChart();
		}

		// generowanie raportu nr 2
		if (Integer.parseInt(reportType) == 2) {
			Raport2 report2 = new Raport2(projekty);
			report2.printReportConsole();
			report2.generateReportExcel();
			report2.generateReportChart();
		}

		// generowanie raportu nr 3
		if (Integer.parseInt(reportType) == 3) {
			Raport3 report3 = new Raport3(projekty, pracownicy);
			report3.printReportConsole();
			report3.generateReportExcel();
		}
		
		if (Integer.parseInt(reportType) == 4) {
            Raport4 report4 = new Raport4(projekty);
            report4.printReportConsole();
            report4.generateReportExcel();
        }
	}
}
