package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.visualizer.BarChartVisualizer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.PieChartVisualizer;
import org.jfree.chart.ChartUtilities;

import java.io.File;
import java.io.IOException;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.CATEGORY;

public class ImageGenerator
{
	public static void main(String[] args)
	{
		try {
			for(int i = 0; i < 10; i++) {
				ChartUtilities.saveChartAsPNG(new File("src/main/resources/images/BarChart_" + CATEGORY[i] + ".png"),
						new BarChartVisualizer(i).createChart(),
						900, 600);
			}

			for(int i = 0; i < 10; i++) {
				ChartUtilities.saveChartAsPNG(new File("src/main/resources/images/PieChart_" + CATEGORY[i] + ".png"),
						new PieChartVisualizer(i).createChart(),
						600, 600);
			}

		} catch	(IOException e) {
			System.err.println(e);
		}

	}
}
