package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

import pl.edu.agh.mwo.java.dataObjects.Projekt;

public class ChartCreator {
    public CategoryChart generateChart(Map<String,Float> report, String title, String xAxis, String yAxis) throws IOException {
        int i = 0;
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title(title)
        .xAxisTitle(xAxis).yAxisTitle(yAxis).build();
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        String[] names = new String[report.size()];
        Float[] hours = new Float[report.size()];
        for (Entry<String, Float> entry : report.entrySet()) {
            names[i] = entry.getKey();
            hours[i] = entry.getValue();
            i++;
        }
        chart.addSeries(xAxis, Arrays.asList(names), Arrays.asList(hours));
        return chart;
    }

    public void showChart(CategoryChart chart) {
        new SwingWrapper(chart).displayChart();
    }


    public void saveReportAsChartPNG(Map<String, Float> report, String title, String xAxis, String yAxis)
            throws IOException {
        BitmapEncoder.saveBitmap(generateChart(report, title, xAxis, yAxis), "./Chart_" + title, BitmapFormat.PNG);
    }

    public void saveReportAsChartPNG(Map<String, float[]> report, List<Projekt> projekty, String title, String xAxis,
            String yAxis) throws IOException {
        BitmapEncoder.saveBitmap(generateChart(report, projekty, title, xAxis, yAxis), "./Chart_" + title,
                BitmapFormat.PNG);
    }

    public CategoryChart generateChart(Map<String, float[]> report, List<Projekt> projekty, String title, String xAxis,
            String yAxis) {

        ArrayList<String> pr = new ArrayList<>();
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title(title).xAxisTitle(xAxis)
                .yAxisTitle(yAxis).build();
        for (Projekt projekt : projekty) {
            pr.add(projekt.getNazwa());
        }
        for (String entry : report.keySet()) {
            ArrayList<Float> hh = new ArrayList<>();
            for (float hours : report.get(entry)) {
                hh.add(hours);
            }
            chart.addSeries(entry, pr, hh);
        }
        return chart;
    }
	
	
	
}
