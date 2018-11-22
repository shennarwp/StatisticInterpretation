package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import javax.swing.*;
import java.util.ArrayList;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class TableVisualizer
{
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
		String[][] tableData = new String[ft.getGroups().length][3];
		for(int i = 0; i < ft.getGroups().length; i++) {
			for(int j = 0; j < 3; j++) {
				if(j == 0)
					tableData[i][j] = ft.getGroups()[i];
				if(j == 1)
					tableData[i][j] = String.valueOf(ft.getTotalAbsolut()[i]);
				if(j == 2)
					tableData[i][j] = String.valueOf(ft.getTotalRelative()[i]);
			}
		}

		return tableData;
	}

	//----------------------------------SEX / GENDER----------------------------------
	private static void createFrequencyTableSex() {
		String[] column_header = {"Geschlecht", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(SEX);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------WEIGHT----------------------------------
	private static void createFrequencyTableWeight() {
		String[] column_header = {"Gewicht", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(WEIGHT);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODTYPE----------------------------------
	private static void createFrequencyTableBloodType() {
		String[] column_header = {"Blutgruppe", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(BLOODTYPE);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------AGE----------------------------------
	private static void createFrequencyTableAge() {
		String[] column_header = {"Alter", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(AGE);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------TEMP0----------------------------------
	private static void createFrequencyTableTemp0() {
		String[] column_header = {"Temp 0", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(TEMP0);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------TEMP12----------------------------------
	private static void createFrequencyTableTemp12() {
		String[] column_header = {"Temp 12", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(TEMP12);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODPRESSURE0----------------------------------
	private static void createFrequencyTableBloodPressure0() {
		String[] column_header = {"BD0", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(BLOODPRESSURE0);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------BLOODPRESSURE12----------------------------------
	private static void createFrequencyTableBloodPressure12() {
		String[] column_header = {"BD12", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(BLOODPRESSURE12);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------DICIPLINE----------------------------------
	private static void createFrequencyTableDiscipline() {
		String[] column_header = {"Diziplin", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(DISCIPLINE);
		displayTable(column_header, extractFrequencyTable(ft));
	}

	//----------------------------------DIFFERENCE----------------------------------
	private static void createFrequencyTableDifference() {
		String[] column_header = {"Differenz", "Hn(ai)", "hn(ai)"};
		FrequencyTable ft = Analyser.countFrequency(DIFFERENCE);
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
