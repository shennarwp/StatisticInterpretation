package de.htwsaar.eursd.StatisticInterpretation.visualizer;


import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.util.Analyzer;
import de.htwsaar.eursd.StatisticInterpretation.util.ContinuousAnalyzer;
import de.htwsaar.eursd.StatisticInterpretation.util.DiscreteAnalyzer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.AbstractDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.Arrays;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public abstract class ChartVisualizer
{
    Analyzer analyzer;
    FrequencyTable frequencyTable;

    ChartVisualizer(int category)
    {
        if(Arrays.stream(DISCRETE).anyMatch(i -> i == category))
            analyzer = new DiscreteAnalyzer(category);
        if(Arrays.stream(CONTINUOUS).anyMatch(i -> i == category))
            analyzer = new ContinuousAnalyzer(category);
    }

    public abstract void displayChart();

}


