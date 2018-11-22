package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

/**
 * analyse the imported csv file
 */
public class Analyser
{
	private static final ArrayList<MedicalRecord> records = Parser.parseRecords();
	private static final int totalPeople = records.size();

	/**
	 * find the maximum of chosen attribute
	 * @param ATTRIBUTE chosen attribute als int-constant
	 * @return max the maximum value
	 */
	private static double getMax(int ATTRIBUTE) {
		double max = 0;
		switch (ATTRIBUTE) {
			case WEIGHT 			: max = Collections.max(records, Comparator.comparing(MedicalRecord::getWeight))
													    .getWeight(); break;
			case AGE 				: max = Collections.max(records, Comparator.comparing(MedicalRecord::getAge))
									   					.getAge(); break;
			case TEMP0 				: max = Collections.max(records, Comparator.comparing(MedicalRecord::getTemp0))
									   					.getTemp0(); break;
			case TEMP12 			: max = Collections.max(records, Comparator.comparing(MedicalRecord::getTemp12))
									   					.getTemp12(); break;
			case BLOODPRESSURE0 	: max = Collections.max(records, Comparator.comparing(MedicalRecord::getBloodPressure0))
													 	.getBloodPressure0(); break;
			case BLOODPRESSURE12 	: max = Collections.max(records, Comparator.comparing(MedicalRecord::getBloodPressure12))
													  	.getBloodPressure12(); break;
			case DIFFERENCE 		: max = Collections.max(records, Comparator.comparing(MedicalRecord::getDifference))
														.getDifference(); break;
			default					: break;
		}
		return max;
	}

	/**
	 * find the minimum of chosen attribute
	 * @param ATTRIBUTE chosen attribute als int-constant
	 * @return min the minimum value
	 */
	private static double getMin(int ATTRIBUTE) {
		double min = 0;
		switch (ATTRIBUTE) {
			case WEIGHT 			: min = Collections.min(records, Comparator.comparing(MedicalRecord::getWeight))
														.getWeight(); break;
			case AGE 				: min = Collections.min(records, Comparator.comparing(MedicalRecord::getAge))
														.getAge(); break;
			case TEMP0 				: min = Collections.min(records, Comparator.comparing(MedicalRecord::getTemp0))
														.getTemp0(); break;
			case TEMP12 			: min = Collections.min(records, Comparator.comparing(MedicalRecord::getTemp12))
														.getTemp12(); break;
			case BLOODPRESSURE0 	: min = Collections.min(records, Comparator.comparing(MedicalRecord::getBloodPressure0))
														.getBloodPressure0(); break;
			case BLOODPRESSURE12 	: min = Collections.min(records, Comparator.comparing(MedicalRecord::getBloodPressure12))
														.getBloodPressure12(); break;
			case DIFFERENCE 		: min = Collections.min(records, Comparator.comparing(MedicalRecord::getDifference))
														.getDifference(); break;
			default					: break;
		}
		return min;
	}

	/**
	 * count the absolute and relative frequencies of attributes which have double as values
	 * @param e Measuring accuracy (Messgenauigkeit jeder Merkmale)
	 * @param ATTRIBUTE the chosen attribute
	 * @return ft FrequencyTable Object which contains the table data
	 */
	static FrequencyTable countFrequency(double e, int ATTRIBUTE) {
		int numberOfClasses = (int)Math.floor(Math.sqrt(records.size()));			//Klasseneinteilung
		double max = getMax(ATTRIBUTE);
		double min = getMin(ATTRIBUTE);
		double classWidth = (max - min + e) / numberOfClasses;

		DecimalFormat df = new DecimalFormat("#.###");						//formatting to 3 decimals behind comma
		double[] boundary = new double[numberOfClasses+1];							//limit: 5 classes --> 6 limit(boundaries)
		boundary[0] = Double.parseDouble(df.format(min - (e/2)));			//initialize first lowest limit
		for(int i = 1; i < boundary.length; i++){
			boundary[i] = Double.parseDouble(df.format(boundary[i-1] + classWidth));
		}

		//counters for the absolut frequencies
		int count0 = 0, count1 = 0, count2 = 0;
		int count3 = 0, count4 = 0;

		for(MedicalRecord record : records) {
			double value = 0;
			switch (ATTRIBUTE) {								//switch between attribute chosen
				case WEIGHT 			: value = record.getWeight(); break;
				case AGE 				: value = record.getAge(); break;
				case TEMP0 				: value = record.getTemp0(); break;
				case TEMP12 			: value = record.getTemp12(); break;
				case BLOODPRESSURE0 	: value = record.getBloodPressure0(); break;
				case BLOODPRESSURE12 	: value = record.getBloodPressure12(); break;
				case DIFFERENCE 		: value = record.getDifference(); break;
				default					: break;
			}

			//count the absolute frequencies
			if(value > boundary[0] && value < boundary[1])
				count0++;
			if(value > boundary[1] && value < boundary[2])
				count1++;
			if(value > boundary[2] && value < boundary[3])
				count2++;
			if(value > boundary[3] && value < boundary[4])
				count3++;
			if(value > boundary[4] && value < boundary[5])
				count4++;
		}

		//preparing the arrays for the FrequencyTable-Object
		String[] range = new String[5];
		for(int i = 0; i < range.length; i++) {
			range[i] = String.valueOf(boundary[i]) + " - " + String.valueOf(boundary[i+1]);
		}

		int[] totalAbsolut = new int[5];
		totalAbsolut[0] = count0;
		totalAbsolut[1] = count1;
		totalAbsolut[2] = count2;
		totalAbsolut[3] = count3;
		totalAbsolut[4] = count4;

		//count the relative frequencies
		double[] totalRelative = new double[5];
		totalRelative[0] = count0 / (double)totalPeople;
		totalRelative[1] = count1 / (double)totalPeople;
		totalRelative[2] = count2 / (double)totalPeople;
		totalRelative[3] = count3 / (double)totalPeople;
		totalRelative[4] = count4 / (double)totalPeople;

		return new FrequencyTable(range, totalAbsolut, totalRelative);
	}
}
