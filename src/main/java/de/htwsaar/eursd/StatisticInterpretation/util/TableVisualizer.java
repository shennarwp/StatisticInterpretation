package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TableVisualizer
{
	private static final ArrayList<MedicalRecord> records = Parser.parseRecords();
	private static final int totalPeople = records.size();

	//aa
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

		JFrame jf = new JFrame();
		JTable jt;
		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(300,400);
		jf.setVisible(true);
	}


	public static void createFrequencyTableDiscipline(){

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

		double relativePuenktlich = (double) absolutPuenktlich / totalPeople;
		double relativeManchmal = (double) absolutManchmal / totalPeople;
		double relativeSelten = (double) absolutSelten / totalPeople;


		String[] column_header = {"Diziplin", "Hn(ai)", "hn(ai)"};
		String[][] tableData ={{"Pünktlich (1)", String.valueOf(absolutPuenktlich), String.valueOf(relativePuenktlich)},
								{"Manchmal (2)", String.valueOf(absolutManchmal), String.valueOf(relativeManchmal)},
								{"Selten (3)", String.valueOf(absolutSelten), String.valueOf(relativeSelten)}};


		JFrame jf = new JFrame();
		JTable jt;
		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(400,400);
		jf.setVisible(true);

	}

	public static void createFrequencyTableBloodType(){
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


		double relative_0 = (double) absolut_0 / totalPeople;
		double relative_A = (double) absolut_A / totalPeople;
		double relative_B = (double) absolut_B / totalPeople;
		double relative_AB = (double) absolut_AB / totalPeople;


		String[] column_header = {"Blood Type", "Hn(ai)", "hn(ai)"};
		String[][] tableData ={{"Blood Type 0", String.valueOf(absolut_0), String.valueOf(relative_0)},
				{"Blood Type A", String.valueOf(absolut_A), String.valueOf(relative_A)},
				{"Blood Type B", String.valueOf(absolut_B), String.valueOf(relative_B)},
				{"Blood Type AB", String.valueOf(absolut_AB), String.valueOf(relative_AB)}};


		JFrame jf = new JFrame();
		JTable jt;
		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(400,400);
		jf.setVisible(true);


	}

	private static void createFrequencyTableWeight() {
		int numberOfClasses = (int)Math.floor(Math.sqrt(records.size()));			//Klasseneinteilung
		double maxWeight = 0;
		double minWeight = 0;
		double e = 0.01;															//Messgenauigkeit

		maxWeight = Collections.max(records, Comparator.comparing(MedicalRecord::getWeight))
								.getWeight();
		minWeight = Collections.min(records, Comparator.comparing(MedicalRecord::getWeight))
								.getWeight();

		double classWidth = (maxWeight - minWeight + e) / numberOfClasses;			//Klassenbreite

		DecimalFormat df = new DecimalFormat("#.###");						//formatting to 3 decimals behind comma
		double[] boundary = new double[6];											//limit
		boundary[0] = Double.parseDouble(df.format(minWeight - (e/2)));
		for(int i = 1; i < boundary.length; i++){
			boundary[i] = Double.parseDouble(df.format(boundary[i-1] + classWidth));
		}

		//absolut
		int count0 = 0;
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;

		for(MedicalRecord record : records) {
			double weight = record.getWeight();
			if(weight > boundary[0] && weight < boundary[1])
				count0++;
			if(weight > boundary[1] && weight < boundary[2])
				count1++;
			if(weight > boundary[2] && weight < boundary[3])
				count2++;
			if(weight > boundary[3] && weight < boundary[4])
				count3++;
			if(weight > boundary[4] && weight < boundary[5])
				count4++;
		}

		//percentage
		double count0Percentage = count0 / (double)totalPeople;
		double count1Percentage = count1 / (double)totalPeople;
		double count2Percentage = count2 / (double)totalPeople;
		double count3Percentage = count3 / (double)totalPeople;
		double count4Percentage = count4 / (double)totalPeople;

		//formatting the
		String a0 = String.valueOf(boundary[0]) + " - " + String.valueOf(boundary[1]);
		String a1 = String.valueOf(boundary[1]) + " - " + String.valueOf(boundary[2]);
		String a2 = String.valueOf(boundary[2]) + " - " + String.valueOf(boundary[3]);
		String a3 = String.valueOf(boundary[3]) + " - " + String.valueOf(boundary[4]);
		String a4 = String.valueOf(boundary[4]) + " - " + String.valueOf(boundary[5]);

		//column name and the data
		String[] column_header = {"Gewicht", "Absolute Anzahl", "Relative Anzahl"};
		String[][] tableData = {{a0, String.valueOf(count0), String.valueOf(count0Percentage)},
								 {a1, String.valueOf(count1), String.valueOf(count1Percentage)},
								 {a2, String.valueOf(count2), String.valueOf(count2Percentage)},
								 {a3, String.valueOf(count3), String.valueOf(count3Percentage)},
								 {a4, String.valueOf(count4), String.valueOf(count4Percentage)}};

		//Table properties
		JFrame jf = new JFrame();
		JTable jt;
		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(350,250);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		//test
	    createFrequencyTableSex();
		createFrequencyTableDiscipline();
		createFrequencyTableBloodType();
	}
}
