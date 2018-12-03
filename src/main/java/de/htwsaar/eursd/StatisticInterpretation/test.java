package de.htwsaar.eursd.StatisticInterpretation;

import de.htwsaar.eursd.StatisticInterpretation.visualizer.BarChartVisualizer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.PieChartVisualizer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.TableVisualizer;

import java.io.InputStream;


public class test
{
	public static void main(String[] args)
	{

		for(int i = 0; i < 10; i++)
			new BarChartVisualizer(i).displayChart();

		for(int i = 0; i < 10; i++)
			new PieChartVisualizer(i).displayChart();

		for(int i = 0; i < 10; i++)
			new TableVisualizer(i).displayTable();

	}

}
