package de.htwsaar.eursd.StatisticInterpretation.util;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class Charts {


    private static void initUI_BarChart()  {

        CategoryDataset dataset = createDataset();

        JFreeChart barChartForWeight = ChartFactory.createBarChart3D(
                "Bar Chart for Weight",
                "Weight´s range",
                "Hn(Ki)/183",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = barChartForWeight.getCategoryPlot();
        plot.setRangeGridlinePaint(new Color(192,192,192));
        ChartFrame frame = new ChartFrame("Bar Chart for Weight", barChartForWeight);
        frame.setSize(450,450);
        frame.setVisible(true);

    }

    private static void initUI_PieChart() {

        DefaultPieDataset dataset = createPieDataset();

        JFreeChart pieChartForWeight = ChartFactory.createPieChart3D("Pie Chart for Weight", dataset,
                true, true, true);

        PiePlot3D plot = (PiePlot3D)pieChartForWeight.getPlot();
        plot.setForegroundAlpha(Plot.DEFAULT_BACKGROUND_ALPHA);
        ChartFrame frame = new ChartFrame("Pie Chart for Weight", pieChartForWeight);
        frame.setSize(450,450);
        frame.setVisible(true);
    }

    private static CategoryDataset createDataset() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //dataset.getValue(FrequencyTable.getGroups().length, frequencyTable.getTotalAbsolut().length);
        dataset.setValue(2,"Gewicht", "58.035 - 64.113");
        dataset.setValue(3,"Gewicht", "64.113 - 70.191");
        dataset.setValue(15,"Gewicht", "70.191 - 76.269");
        dataset.setValue(7,"Gewicht", "76.269 - 82.347");
        dataset.setValue(3,"Gewicht", "82.347 - 88.425");
        return dataset;
    }

    private static DefaultPieDataset createPieDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("58.035 - 64.113", 2);
        dataset.setValue("Nginx", 3);
        dataset.setValue("IIS", 15);
        dataset.setValue("LiteSpeed", 7);
        dataset.setValue("Google server", 3);


        return dataset;
    }

    private static void createBarChartWeight(){
        String[] titleCategoryValue = {"Bar Chart for Weight", "Weight´s range", "Hn(Ki)/183",};
        //initUI_BarChart();
        initUI_PieChart();
    }

    public static void main(String[] args){
        /*SwingUtilities.invokeLater(() -> {
            Charts ex = new Charts();
        });*/
        initUI_BarChart();
        initUI_PieChart();

        //createBarChartWeight();
    }

}


