package de.htwsaar.eursd.StatisticInterpretation.util;


import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class Charts {


    private static void initUI_BarChart(String titleBar, String category, String value)  {

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

    private static void initUI_PieChart(String titlePie) {

        DefaultPieDataset dataset = createPieDataset();

        JFreeChart pieChart = ChartFactory.createPieChart3D(titlePie, dataset,
                true, true, true);

        PiePlot3D plot = (PiePlot3D)pieChart.getPlot();
        plot.setForegroundAlpha(Plot.DEFAULT_BACKGROUND_ALPHA);
        ChartFrame frame = new ChartFrame(titlePie, pieChart);
        frame.setSize(450,450);
        frame.setVisible(true);
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        FrequencyTable frequencyTable = Analyser.countFrequency(WEIGHT);
        for(int i= 0; i< frequencyTable.getGroups().length; i++)
        {
            dataset.setValue(frequencyTable.getTotalAbsolut()[i],"", frequencyTable.getGroups()[i]);
        }
        return dataset;
    }

    private static DefaultPieDataset createPieDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        FrequencyTable frequencyTable = Analyser.countFrequency(WEIGHT);
        for (int i = 0 ; i < frequencyTable.getGroups().length; i++){
            dataset.setValue(frequencyTable.getGroups()[i], frequencyTable.getTotalAbsolut()[i]);
        }
        return dataset;
    }


    private static void createChartSex(){
        String titleBar = "Bar Chart for Sex";
        String category = "Category";
        String value = "Value";
        String titlePie = "Pie Chart for Sex";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartWeight(){
        String titleBar = "Bar Chart for Weight";
        String category = "Weight´s range";
        String value = "Hn(Ki)/183";
        String titlePie = "Pie Chart for Weight";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartBloodType(){
        String titleBar = "Bar Chart for Blood Type";
        String category = "Category";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Type";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartAge(){
        String titleBar = "Bar Chart for Age";
        String category = "Age´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Age";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartTemp0(){
        String titleBar = "Bar Chart for Temp0";
        String category = "Temp0´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Temp0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartTemp12(){
        String titleBar = "Bar Chart for Temp12";
        String category = "Temp12´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Temp0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartBloodPressure0(){
        String titleBar = "Bar Chart for Blood Pressure 0";
        String category = "Blood Pressure 0´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Pressure 0";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }
    private static void createChartBloodPressure12(){
        String titleBar = "Bar Chart for Blood Pressure 12";
        String category = "Blood Pressure 12´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Blood Pressure 12";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartDicipline(){
        String titleBar = "Bar Chart for Dicipline";
        String category = "Dicipline´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Dicipline";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    private static void createChartDifference(){
        String titleBar = "Bar Chart for Difference";
        String category = "Difference´s range";
        String value = "Value";
        String titlePie = "Pie Chart for Difference";
        initUI_PieChart(titlePie);
        initUI_BarChart(titleBar,category,value);
    }

    public static void main(String[] args){
        //createChartSex();
        createChartWeight();

    }

}


