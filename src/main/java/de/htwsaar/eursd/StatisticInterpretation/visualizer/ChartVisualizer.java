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
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.Arrays;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class ChartVisualizer
{
    private Analyzer analyzer;
    private FrequencyTable frequencyTable;

    public ChartVisualizer(int category)
    {
        if(Arrays.stream(DISCRETE).anyMatch(i -> i == category))
            analyzer = new DiscreteAnalyzer(category);
        if(Arrays.stream(CONTINUOUS).anyMatch(i -> i == category))
            analyzer = new ContinuousAnalyzer(category);
    }



    private void createChart() {
        switch(analyzer.getCHOSENCATEGORY())
        {
            case SEX: createChartSex(); break;
            case WEIGHT: createChartWeight(); break;
            case BLOODTYPE: createChartBloodType(); break;
            case AGE: createChartAge(); break;
            case TEMP0: createChartTemp0(); break;
            case TEMP12: createChartTemp12(); break;
            case BLOODPRESSURE0: createChartBloodPressure0(); break;
            case BLOODPRESSURE12: createChartBloodPressure12(); break;
            case DISCIPLINE: createChartDicipline(); break;
            case DIFFERENCE: createChartDifference(); break;
        }
    }


    private void initUI_BarChart(String titleBar, String category, String value)  {

        CategoryDataset dataset = createDataset();

        JFreeChart barChartForWeight = ChartFactory.createBarChart3D(
                titleBar,
                category,
                value,
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = barChartForWeight.getCategoryPlot();
        plot.setRangeGridlinePaint(new Color(192,192,192));
        ChartFrame frame = new ChartFrame(titleBar, barChartForWeight);
        frame.setSize(450,450);
        frame.setVisible(true);

    }

    private void initUI_PieChart(String titlePie) {

        DefaultPieDataset dataset = createPieDataset();

        JFreeChart pieChart = ChartFactory.createPieChart3D(titlePie, dataset,
                true, true, true);

        PiePlot3D plot = (PiePlot3D)pieChart.getPlot();
        plot.setForegroundAlpha(Plot.DEFAULT_BACKGROUND_ALPHA);
        ChartFrame frame = new ChartFrame(titlePie, pieChart);
        frame.setSize(450,450);
        frame.setVisible(true);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        frequencyTable = analyzer.countFrequency();

        for(int i= 0; i < frequencyTable.getGroups().length; i++) {
            dataset.setValue(frequencyTable.getTotalAbsolut()[i],"", frequencyTable.getGroups()[i]);
        }
        return dataset;
    }

    private DefaultPieDataset createPieDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        frequencyTable = analyzer.countFrequency();
        for (int i = 0 ; i < frequencyTable.getGroups().length; i++) {
            dataset.setValue(frequencyTable.getGroups()[i], frequencyTable.getTotalAbsolut()[i]);
        }
        return dataset;
    }


    private void createChartSex(){
        String titleBar = "Bar Chart for Sex";
        String category = "Category";
        String value = "Value";
        String titlePie = "Pie Chart for Sex";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartWeight(){
        String titleBar = "Bar Chart for Weight";
        String category = "Weight´s range";
        String value = "Hn(Ki)/183";
        String titlePie = "Pie Chart for Weight";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartBloodType(){
        String titleBar = "Bar Chart for Blood Type";
        String category = "Category";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Type";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartAge(){
        String titleBar = "Bar Chart for Age";
        String category = "Age´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Age";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartTemp0(){
        String titleBar = "Bar Chart for Temp0";
        String category = "Temp0´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Temp0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartTemp12(){
        String titleBar = "Bar Chart for Temp12";
        String category = "Temp12´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Temp0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartBloodPressure0(){
        String titleBar = "Bar Chart for Blood Pressure 0";
        String category = "Blood Pressure 0´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Pressure 0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }
    private void createChartBloodPressure12(){
        String titleBar = "Bar Chart for Blood Pressure 12";
        String category = "Blood Pressure 12´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Pressure 12";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartDicipline(){
        String titleBar = "Bar Chart for Dicipline";
        String category = "Dicipline´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Dicipline";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private void createChartDifference(){
        String titleBar = "Bar Chart for Difference";
        String category = "Difference´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Difference";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    public static void main(String[] args) {
        //createChartSex();
        //createChartWeight();
        new ChartVisualizer(SEX).createChart();
    }

}


