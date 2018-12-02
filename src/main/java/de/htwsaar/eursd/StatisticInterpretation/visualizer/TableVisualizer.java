package de.htwsaar.eursd.StatisticInterpretation.visualizer;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.util.Analyzer;
import de.htwsaar.eursd.StatisticInterpretation.util.ContinuousAnalyzer;
import de.htwsaar.eursd.StatisticInterpretation.util.DiscreteAnalyzer;
import lombok.Getter;

import javax.swing.*;

import java.util.Arrays;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class TableVisualizer
{
	private Analyzer analyzer;
	private FrequencyTable frequencyTable;

	/**
	 * Constructor, create analyzer-object depends on whether the chosen category is discrete or continuous
	 * @param category the chosen category
	 */
	public TableVisualizer(int category)
	{
		if(Arrays.stream(DISCRETE).anyMatch(i -> i == category))
			analyzer = new DiscreteAnalyzer(category);
		if(Arrays.stream(CONTINUOUS).anyMatch(i -> i == category))
			analyzer = new ContinuousAnalyzer(category);
	}

	/**
	 * Display the table based on given data
	 */
	public void displayTable() {
		frequencyTable = analyzer.countFrequency();			//start counting the frequency
		String[][] tableData = extractFrequencyTable();		//extracting the data for the table
		String[] column_header = createColumnHeader();			//creating the column header

		JFrame jf = new JFrame();
		JTable jt = new JTable(tableData, column_header);
		jt.setBounds(50,50,200,230);
		JScrollPane scrollPane = new JScrollPane(jt);

		jf.add(scrollPane);
		jf.setSize(550,150);
		jf.setVisible(true);
	}

	/**
	 * extract the FrequencyTable-Object which contains the bin intervals with their respective absolute and relatives frequencies
	 * @return the Data formatted as double String-Array, ready to be processed with JTable
	 */
	private String[][] extractFrequencyTable() {
		String[][] tableData = new String[frequencyTable.getGroups().length][3];
		for(int i = 0; i < frequencyTable.getGroups().length; i++) {
			for(int j = 0; j < 3; j++) {
				if(j == 0)
					tableData[i][j] = frequencyTable.getGroups()[i];
				if(j == 1)
					tableData[i][j] = String.valueOf(frequencyTable.getTotalAbsolut()[i]);
				if(j == 2)
					tableData[i][j] = String.valueOf(frequencyTable.getTotalRelative()[i]);
			}
		}

		return tableData;
	}

	/**
	 * create column header for the table based on the chosen category in the analyzer-Object
	 * @return String Array containing the header
	 */
	private String[] createColumnHeader() {
		return new String[] {CATEGORY[analyzer.getCHOSENCATEGORY()], "Hn(ai)", "hn(ai)"};
	}
}
