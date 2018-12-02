package de.htwsaar.eursd.StatisticInterpretation.visualizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.CATEGORY;

public class PieChartVisualizer extends ChartVisualizer
{
	private PieDataset dataset;

	public PieChartVisualizer(int category) {
		super(category);
		dataset = createDataset();
	}

	private PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		frequencyTable = analyzer.countFrequency();
		for (int i = 0 ; i < frequencyTable.getGroups().length; i++) {
			dataset.setValue(frequencyTable.getGroups()[i], frequencyTable.getTotalAbsolut()[i]);
		}
		return dataset;
	}

	@Override
	public void displayChart() {
		String category = CATEGORY[analyzer.getCHOSENCATEGORY()];
		String title = "Frequency distribution for category : " + category;
		JFreeChart pieChart = ChartFactory.createPieChart(
											title,
											dataset,
									true,
									true,
										false);

		PiePlot plot = (PiePlot)pieChart.getPlot();
		plot.setForegroundAlpha(Plot.DEFAULT_BACKGROUND_ALPHA);
		ChartFrame frame = new ChartFrame(title, pieChart);
		frame.setSize(600,600);
		frame.setVisible(true);
	}

}
