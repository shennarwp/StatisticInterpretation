package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import javax.swing.*;
import java.util.ArrayList;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class TableVisualizer
{
	private static final ArrayList<MedicalRecord> records = Parser.parseRecords();
	private static final int totalPeople = records.size();

	/**
	 * Display the table based on given data
	 * @param column_header names of the column
	 * @param tableData table contents
	 */
	private static void displayTable(String[] column_header, String[][] tableData) {
		JFrame jf = new JFrame();
		JTable jt;
		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(400,400);
		jf.setVisible(true);
	}

	/**
	 * extract the FrequencyTable-Object which contains the limits, the absolute and relatives frequencies
	 * @param ft the Object
	 * @return the Data formatted as double String-Array, ready to be processed with JTable
	 */
	private static String[][] extractFrequencyTable(FrequencyTable ft) {
		String[] range = combineBoundariesToRange(ft.getBoundary());
		String[][] tableData = {{range[0], String.valueOf(ft.getTotalAbsolut()[0]), String.valueOf(ft.getTotalRelative()[0])},
								 {range[1], String.valueOf(ft.getTotalAbsolut()[1]), String.valueOf(ft.getTotalRelative()[1])},
								 {range[2], String.valueOf(ft.getTotalAbsolut()[2]), String.valueOf(ft.getTotalRelative()[2])},
								 {range[3], String.valueOf(ft.getTotalAbsolut()[3]), String.valueOf(ft.getTotalRelative()[3])},
								 {range[4], String.valueOf(ft.getTotalAbsolut()[4]), String.valueOf(ft.getTotalRelative()[4])}};
		return tableData;
	}

	/**
	 * Helpfunction to combine the limits in the array to string
	 * @param boundary the array containing all the limits for the chart/graph/table
	 * @return combined limits as string
	 */
	private static String[] combineBoundariesToRange(double[] boundary) {
		String[] range = new String[boundary.length-1];
		for(int i = 0; i < range.length; i++)
			range[i] = String.valueOf(boundary[i]) + " - " + String.valueOf(boundary[i+1]);
		return range;
	}

	//----------------------------------SEX / GENDER----------------------------------
	private static void createFrequencyTableSex() {
		int absolutMales = 0;
		int absolutFemales = 0;

		for(MedicalRecord record: records) {
			if(record.getSex() == 0)
				absolutFemales++;
			else
				absolutMales++;
		}

		double relativeMales = absolutMales / (double)totalPeople;
		double relativeFemales = absolutFemales / (double)totalPeople;

		String[] column_header = {"Geschlecht", "Hn(ai)", "hn(ai)"};
		String[][] tableData = {{"Weiblich (0)", String.valueOf(absolutFemales), String.valueOf(relativeFemales)},
								 {"Männlich (1)", String.valueOf(absolutMales), String.valueOf(relativeMales)}};

		displayTable(column_header, tableData);
	}

	//----------------------------------WEIGHT----------------------------------
	private static void createFrequencyTableWeight() {
		double e = 0.01;

		String[] column_header = {"Gewicht", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, WEIGHT);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODTYPE----------------------------------
	private static void createFrequencyTableBloodType() {
		int absolut_0 = 0;
		int absolut_A = 0;
		int absolut_B = 0;
		int absolut_AB = 0;

		for(MedicalRecord record: records) {
			if(record.getBloodType() == 0)
				absolut_0++;
			else if (record.getBloodType() == 1)
				absolut_A++;
			else if (record.getBloodType() == 2)
				absolut_B++;
			else
				absolut_AB++;
		}

		double relative_0 = absolut_0 / (double)totalPeople;
		double relative_A = absolut_A / (double)totalPeople;
		double relative_B = absolut_B / (double)totalPeople;
		double relative_AB = absolut_AB / (double)totalPeople;

		String[] column_header = {"Blood Type", "Hn(ai)", "hn(ai)"};
		String[][] tableData ={{"Blood Type 0", String.valueOf(absolut_0), String.valueOf(relative_0)},
								{"Blood Type A", String.valueOf(absolut_A), String.valueOf(relative_A)},
								{"Blood Type B", String.valueOf(absolut_B), String.valueOf(relative_B)},
								{"Blood Type AB", String.valueOf(absolut_AB), String.valueOf(relative_AB)}};

		displayTable(column_header, tableData);
	}

	//----------------------------------AGE----------------------------------
	private static void createFrequencyTableAge() {
		double e = 0.01;

		String[] column_header = {"Alter", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, AGE);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------TEMP0----------------------------------
	private static void createFrequencyTableTemp0() {
		double e = 0.01;

		String[] column_header = {"Temp 0", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, TEMP0);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------TEMP12----------------------------------
	private static void createFrequencyTableTemp12() {
		double e = 0.01;

		String[] column_header = {"Temp 12", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, TEMP12);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODPRESSURE0----------------------------------
	private static void createFrequencyTableBloodPressure0() {
		double e = 0.01;

		String[] column_header = {"BD0", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, BLOODPRESSURE0);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODPRESSURE12----------------------------------
	private static void createFrequencyTableBloodPressure12() {
		double e = 0.01;

		String[] column_header = {"BD12", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, BLOODPRESSURE12);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------DICIPLINE----------------------------------
	private static void createFrequencyTableDiscipline() {
		int absolutPuenktlich = 0;
		int absolutManchmal = 0;
		int absolutSelten = 0;

		for(MedicalRecord record: records) {
			if(record.getDicipline() == 1)
				absolutPuenktlich++;
			else if (record.getDicipline() == 2)
				absolutManchmal++;
			else
				absolutSelten++;
		}

		double relativePuenktlich = absolutPuenktlich / (double)totalPeople;
		double relativeManchmal = absolutManchmal / (double)totalPeople;
		double relativeSelten = absolutSelten / (double)totalPeople;

		String[] column_header = {"Diziplin", "Hn(ai)", "hn(ai)"};
		String[][] tableData ={{"Pünktlich (1)", String.valueOf(absolutPuenktlich), String.valueOf(relativePuenktlich)},
								{"Manchmal (2)", String.valueOf(absolutManchmal), String.valueOf(relativeManchmal)},
								{"Selten (3)", String.valueOf(absolutSelten), String.valueOf(relativeSelten)}};

		displayTable(column_header, tableData);
	}

	//----------------------------------DIFFERENCE----------------------------------
	private static void createFrequencyTableDifference() {
		double e = 0.01;

		String[] column_header = {"Differenz", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(e, DIFFERENCE);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	public static void main(String[] args) {

	    createFrequencyTableSex();
	    createFrequencyTableWeight();
	    createFrequencyTableBloodType();
	    createFrequencyTableAge();
	    createFrequencyTableTemp0();
	    createFrequencyTableTemp12();
	    createFrequencyTableBloodPressure0();
	    createFrequencyTableBloodPressure12();
	    createFrequencyTableDiscipline();
	    createFrequencyTableDifference();
	}
}
