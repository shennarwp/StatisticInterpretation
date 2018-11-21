package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import javax.swing.*;
import java.util.ArrayList;

public class Visualizer
{
	private static final ArrayList<MedicalRecord> records = Parser.parseRecords();

	private static void createFrequencyTableSex() {
		JFrame jf = new JFrame();
		JTable jt;
		String[] column_header = {"Geschlecht", "Absolute Anzahl", "Relative Anzahl"};

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

	public static void main(String[] args) {
		createFrequencyTableSex();
	}
}
