package pl.edu.agh.mwo.java.Reports;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

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
        //SwingWrapper displayChart = 
        new SwingWrapper(chart).displayChart();
        //displayChart.displayChart();
    }

    public void saveReportAsChartPNG(Map<String,Float> report) throws IOException {
        BitmapEncoder.saveBitmap(generateChart(report, "Raport 1", "Pracownicy",  "Liczba przepracowanych godzin"), "./Employee_Chart", BitmapFormat.PNG);
    }
	
	
	
}
