package pl.edu.agh.mwo.java;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.cli.*;
import pl.edu.agh.mwo.java.dataObjects.Pracownik;

public class App {

				public static Workbook openWorkbook() {
					try {
						return WorkbookFactory.create(new File("src/main/resources/2012/01/Kowalski_Jan.xls"));
					} catch (EncryptedDocumentException | IOException e) {
						e.printStackTrace();
						return null;
					}
	}
    			public static void main(String[] args) throws Exception 
    			{
    				Pracownik newPracownik = new Pracownik("nazwa",openWorkbook());
					System.out.println(newPracownik);
//    				  Options options = new Options();
//
//    			        Option source = new Option("s", "source", true, "input file path");
//    			        source.setRequired(true);
//    			        options.addOption(source);
//
//    			        Option report = new Option("r", "report", true, "report type");
//    			        source.setRequired(true);
//    			        options.addOption(report);
//
//    			        CommandLineParser parser = new DefaultParser();
//    			        HelpFormatter formatter = new HelpFormatter();
//    			        CommandLine cmd = null;
//
//    			        try {
//    			            cmd = parser.parse(options, args);
//    			        } catch (ParseException e) {
//    			            System.out.println(e.getMessage());
//    			            formatter.printHelp("utility-name", options);
//
//    			            System.exit(1);
//    			        }
//
//    			        String inputFilePath = cmd.getOptionValue("source");
//    			        String reportType = cmd.getOptionValue("report");
//
//
//    			        System.out.println(inputFilePath);
//    			        System.out.println(reportType);




    			}

        
     	}
