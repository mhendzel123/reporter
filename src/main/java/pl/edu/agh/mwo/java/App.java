package pl.edu.agh.mwo.java;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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

public class App {
    			public static void main(String[] args) throws Exception 
    			{
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

    			        
    			}

        
     	}
