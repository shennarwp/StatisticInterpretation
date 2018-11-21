package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import javax.swing.*;
import java.util.ArrayList;

public class TableVisualizer
{
	private static final ArrayList<MedicalRecord> records = Parser.parseRecords();

	private static void createFrequencyTableSex() {
		JFrame jf = new JFrame();
		JTable jt;
		String[] column_header = {"Geschlecht", "Hn(ai)", "hn(ai)"};

		int absolutMales = 0;
		int absolutFemales = 0;

		for(MedicalRecord record: records) {
			if(record.getSex() == 0)
				absolutFemales++;
			else
				absolutMales++;
		}

		int totalPersons = absolutFemales + absolutMales;

		double relativeMales = (double)absolutMales / totalPersons;
		double relativeFemales = (double)absolutFemales / totalPersons;

		String[][] tableData ={{"Weiblich (0)", String.valueOf(absolutFemales), String.valueOf(relativeFemales)},
								{"Männlich (1)", String.valueOf(absolutMales), String.valueOf(relativeMales)}};

		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(300,400);
		jf.setVisible(true);
	}

	public static void createFrequencyTableDiscipline(){
		JFrame jf = new JFrame();
		JTable jt;
		String[] column_header = {"Diziplin", "Hn(ai)", "hn(ai)"};

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

		int totalPersons =  absolutPuenktlich + absolutManchmal + absolutSelten;

		double relativePuenktlich = (double) absolutPuenktlich / totalPersons;
		double relativeManchmal = (double) absolutManchmal / totalPersons;
		double relativeSelten = (double) absolutSelten / totalPersons;

		String[][] tableData ={{"Pünktlich (1)", String.valueOf(absolutPuenktlich), String.valueOf(relativePuenktlich)},
								{"Manchmal (2)", String.valueOf(absolutManchmal), String.valueOf(relativeManchmal)},
								{"Selten (3)", String.valueOf(absolutSelten), String.valueOf(relativeSelten)}};

		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(400,400);
		jf.setVisible(true);

	}

	public static void createFrequencyTableBloodType(){
		JFrame jf = new JFrame();
		JTable jt;
		String[] column_header = {"Blood Type", "Hn(ai)", "hn(ai)"};

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

		int totalPersons =  absolut_0 + absolut_A + absolut_B + absolut_AB;

		double relative_0 = (double) absolut_0 / totalPersons;
		double relative_A = (double) absolut_A / totalPersons;
		double relative_B = (double) absolut_B / totalPersons;
		double relative_AB = (double) absolut_AB / totalPersons;

		String[][] tableData ={{"Blood Type 0", String.valueOf(absolut_0), String.valueOf(relative_0)},
				{"Blood Type A", String.valueOf(absolut_A), String.valueOf(relative_A)},
				{"Blood Type B", String.valueOf(absolut_B), String.valueOf(relative_B)},
				{"Blood Type AB", String.valueOf(absolut_AB), String.valueOf(relative_AB)}};

		jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(400,400);
		jf.setVisible(true);


	}

	public static void main(String[] args) {
		//test
	    //createFrequencyTableSex();
		//createFrequencyTableDiscipline();
		//createFrequencyTableBloodType();
	}
}
