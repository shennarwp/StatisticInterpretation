package de.htwsaar.eursd.StatisticInterpretation;

import de.htwsaar.eursd.StatisticInterpretation.util.ContinuousAnalyzer;
import de.htwsaar.eursd.StatisticInterpretation.util.DiscreteAnalyzer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.BarChartVisualizer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.PieChartVisualizer;
import de.htwsaar.eursd.StatisticInterpretation.visualizer.TableVisualizer;

import java.util.Arrays;
import java.util.Scanner;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.CATEGORY;
import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.CONTINUOUS;
import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.DISCRETE;

public class Main
{

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Read the input answer from user
     * @return input answer
     */
    private char readAntwort() {
        char antwort;
        System.out.print("Again (y/n)? \n");
        antwort = scanner.next().charAt(0);
        while (antwort != 'y' && antwort != 'n') {
            System.out.println("only y or n!!!");
            System.out.print("Again (y/n)? \n");
            antwort = scanner.next().charAt(0);
        }
        return antwort;
    }


    /**
     * Start function for Dialog class
     */
    private void start() {
        char antwort = 'y';
        while (antwort == 'y') {
            //Select a category
            System.out.println("Choose any category:");
            System.out.println("1.  Sex / Gender");
            System.out.println("2.  Weight");
            System.out.println("3.  Blood Type");
            System.out.println("4.  Age");
            System.out.println("5.  Temperature at month 0");
            System.out.println("6.  Temperature at month 12");
            System.out.println("7.  Blood pressure at month 0");
            System.out.println("8.  Blood pressure at month 12");
            System.out.println("9.  Discipline");
            System.out.println("10. Difference");
            System.out.println("----------------------------------------------\n");
            int category =  scanner.nextInt() -1;


            //Select a function
            System.out.println("----------------------------------------------");
            System.out.println("For category: " +  CATEGORY[category]);
            System.out.println("Choose any function:");
            System.out.println("1. FREQUENCY TABLE");
            System.out.println("2. BAR CHART");
            System.out.println("3. PIE CHART");
            System.out.println("4. STATISTICS");
            System.out.println("0. END!!!");
            System.out.println("----------------------------------------------\n");
            int function = scanner.nextInt();
            switch(function) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                        new TableVisualizer(category).displayTable();
                        break;
                case 2:
                        new BarChartVisualizer(category).displayChart();
                    break;
                case 3:
                        new PieChartVisualizer(category).displayChart();
                        break;
                case 4:
                        if(Arrays.stream(DISCRETE).anyMatch(i -> i == category))
                            new DiscreteAnalyzer(category).printStatistics();
                        if(Arrays.stream(CONTINUOUS).anyMatch(i -> i == category))
                            new ContinuousAnalyzer(category).printStatistics();
                        break;
                default:
                        System.out.println("Not an option!");
                        break;
            }
            System.out.println("---------------------------------");
            System.out.println("Do you want another function?\n");
            antwort = readAntwort();
        }
    }


    /**
     * main method for the Program
     * @param args
     */
    public static void main(String[] args){
        try{
            new Main().start();
        }catch (AssertionError e) {
            System.err.println(e);
        }
    }
}
