package de.htwsaar.eursd.StatisticInterpretation.visualizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.CATEGORY;

public class BarChartVisualizer extends ChartVisualizer
{
	private CategoryDataset dataset;

	public BarChartVisualizer(int category)
	{
		super(category);
		dataset = createDataset();
	}

	private CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		frequencyTable = analyzer.countFrequency();

		for(int i= 0; i < frequencyTable.getGroups().length; i++) {
			dataset.setValue(frequencyTable.getTotalAbsolut()[i],"", frequencyTable.getGroups()[i]);
		}
		return dataset;
	}

	public JFreeChart createChart() {
		String category = CATEGORY[analyzer.getCHOSENCATEGORY()];
		String title = "Frequency distribution for category : " + category;
		String value = "Number of People";

		JFreeChart barChart = ChartFactory.createBarChart3D(
				title,
				category,
				value,
				dataset,
				PlotOrientation.VERTICAL,
				false,
				true,
				false);
		return barChart;
	}

	@Override
	public void displayChart()  {

		JFreeChart barChart = createChart();

		CategoryPlot plot = barChart.getCategoryPlot();
		plot.setRangeGridlinePaint(new Color(192,192,192));
		ChartFrame frame = new ChartFrame(barChart.getTitle().toString(), barChart);
		frame.setSize(900,600);
		frame.setVisible(true);

	}


}
