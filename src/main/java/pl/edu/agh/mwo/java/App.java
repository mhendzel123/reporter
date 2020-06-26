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
import pl.edu.agh.mwo.java.Reports.Raport5;
import pl.edu.agh.mwo.java.dataObjects.Pracownik;
import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class App {
	public static void main(String[] args) throws Exception {
		try {
			File example = new File("/home/you/Desktop");

			Options options = new Options();

			Option source = new Option("s", "source", true, "input file path");
			source.setRequired(true);
			options.addOption(source);

			Option report = new Option("r", "report", true, "report type");
			source.setRequired(true);
			options.addOption(report);

			Option excel = new Option("x", "xls", false, "save xls file");
			options.addOption(excel);

			Option saveChart = new Option("png", "chart png", false, "save chart as png");
			options.addOption(saveChart);

			Option displayChart = new Option("ch", "display chart", false, "display chart");
			options.addOption(displayChart);

			Option query = new Option("q", "query", true, "report 5 query");
			options.addOption(query);

			Option employeeFilter = new Option("ef", "employeeFilter", true, "employee filter");
			options.addOption(employeeFilter);

			Option projectFilter = new Option("pf", "projectFilter", true, "project filter");
			options.addOption(projectFilter);

			CommandLineParser parser = new DefaultParser();
			HelpFormatter formatter = new HelpFormatter();
			CommandLine cmd = null;

			try {
				cmd = parser.parse(options, args);
			} catch (ParseException e) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
				formatter.printHelp("utility-name", options);
				System.exit(1);
			}

			String inputFilePath = cmd.getOptionValue("source");
			String reportType = cmd.getOptionValue("report");
			String queryReport5 = cmd.getOptionValue("query");
			String employeeFilterString = cmd.getOptionValue("employeeFilter");
			String projectFilterString = cmd.getOptionValue("projectFilter");

			// generowanie listy pracownikow z ich projektami i zadaniami po plikach po podanej sciezce (rekursywnie)
			File inputPath = new File(inputFilePath);
			List<Projekt> projekty;
			List<Pracownik> pracownicy;
			if (employeeFilterString == null) {
				pracownicy = (new WorkbookLoader()).listaPracownikowZFolderu(inputPath, "");
			} else {
				pracownicy = (new WorkbookLoader()).listaPracownikowZFolderu(inputPath,
						employeeFilterString.toLowerCase());
			}
			if (projectFilterString == null) {
				projekty = (new WorkbookLoader()).listaProjektowZFolderu(inputPath, "");
			} else {
				projekty = (new WorkbookLoader()).listaProjektowZFolderu(inputPath, projectFilterString.toLowerCase());
			}
			// tu mozna sprawdzic ze w przypypadku Jana Kowalskiego zadania zostsaly poprawnie dodane do odpowiadajacych im projektom oraz ze puste linie sa pomijane (02/Kowalski_Jan ma pusta)
			pracownicy.forEach((pracownik) -> {
//			 System.out.println(pracownik);
			});

			// wypisywanie bledow
			File[] files = new File(inputFilePath).listFiles();
			ErrorFinder finder = new ErrorFinder();
			finder.findErrors(files);
			finder.checkDates(files);

			RangeOfDatesPrinter printer = new RangeOfDatesPrinter(projekty);
			printer.printDates();

			// generowanie raportu nr 1
			try {
				if (Integer.parseInt(reportType) == 1) {
					Raport1 report1 = new Raport1(pracownicy);
					report1.printReportConsole();
					if (cmd.hasOption("x"))
						report1.generateReportExcel();
					if (cmd.hasOption("ch"))
						report1.generateReportChart();
					if (cmd.hasOption("png"))
						report1.saveReportChart();
				}
			} catch (Exception ex1) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
			}

			// generowanie raportu nr 2
			try {
				if (Integer.parseInt(reportType) == 2) {
					Raport2 report2 = new Raport2(projekty);
					report2.printReportConsole();
					if (cmd.hasOption("x"))
						report2.generateReportExcel();
					if (cmd.hasOption("ch"))
						report2.generateReportChart();
					if (cmd.hasOption("png"))
						report2.saveReportChart();
				}
			} catch (Exception ex1) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
			}

			// generowanie raportu nr 3
			try {
				if (Integer.parseInt(reportType) == 3) {
					Raport3 report3 = new Raport3(projekty, pracownicy);
					report3.printReportConsole();
					if (cmd.hasOption("x"))
						report3.generateReportExcel();
					if (cmd.hasOption("ch"))
						report3.generateReportChart();
					if (cmd.hasOption("png"))
						report3.saveReportChart();
				}
			} catch (Exception ex1) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
			}

			// generowanie raportu nr 4
			try {
				if (Integer.parseInt(reportType) == 4) {
					Raport4 report4 = new Raport4(projekty);
					report4.printReportConsole();
					if (cmd.hasOption("x"))
						report4.generateReportExcel();
				}
			} catch (Exception ex1) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
			}

			// generowanie raportu nr 5
			try {
				if (Integer.parseInt(reportType) == 5) {
					if (queryReport5 == null) {
						System.out.println("Podaj szukane wyrażenie -q");
					} else {
						Raport5 report5 = new Raport5(projekty, queryReport5);
						report5.printReportConsole();
						if (cmd.hasOption("x"))
							report5.generateReportExcel();
					}
				}
			} catch (Exception ex1) {
				System.out.println("Błąd w poleceniu, nie mogę wygenerować raportu, spróbuj jeszcze raz!");
			}

		} catch (Exception eMain) {
			System.out.println("");
		}
	}
}
