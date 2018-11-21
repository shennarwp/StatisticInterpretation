package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
			case 0 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getWeight))
									   .getWeight(); break;
			case 1 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getAge))
									   .getAge(); break;
			case 2 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getTemp0))
									   .getTemp0(); break;
			case 3 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getTemp12))
									   .getTemp12(); break;
			case 4 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getBloodPressure0))
									   .getBloodPressure0(); break;
			case 5 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getBloodPressure12))
									   .getBloodPressure12(); break;
			case 6 : max = Collections.max(records, Comparator.comparing(MedicalRecord::getDifference))
										.getDifference(); break;
			default: break;
		}
		return max;
	}

	/**
	 * find the minimum of chosen attribute
	 * @param ATTRIBUTE chosen attribute als int-constant
	 * @return min the minimum value
	 */
	private static double getMin(int ATTRIBUTE) {
		double max = 0;
		switch (ATTRIBUTE) {
			case 0 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getWeight))
					.getWeight(); break;
			case 1 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getAge))
					.getAge(); break;
			case 2 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getTemp0))
					.getTemp0(); break;
			case 3 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getTemp12))
					.getTemp12(); break;
			case 4 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getBloodPressure0))
					.getBloodPressure0(); break;
			case 5 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getBloodPressure12))
					.getBloodPressure12(); break;
			case 6 : max = Collections.min(records, Comparator.comparing(MedicalRecord::getDifference))
					.getDifference(); break;
			default: break;
		}
		return max;
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
				case 0 : value = record.getWeight(); break;
				case 1 : value = record.getAge(); break;
				case 2 : value = record.getTemp0(); break;
				case 3 : value = record.getTemp12(); break;
				case 4 : value = record.getBloodPressure0(); break;
				case 5 : value = record.getBloodPressure12(); break;
				case 6 : value = record.getDifference(); break;
				default: break;
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

		//count the relative frequencies
		double count0Percentage = count0 / (double)totalPeople;
		double count1Percentage = count1 / (double)totalPeople;
		double count2Percentage = count2 / (double)totalPeople;
		double count3Percentage = count3 / (double)totalPeople;
		double count4Percentage = count4 / (double)totalPeople;

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

		double[] totalRelative = new double[5];
		totalRelative[0] = count0Percentage;
		totalRelative[1] = count1Percentage;
		totalRelative[2] = count2Percentage;
		totalRelative[3] = count3Percentage;
		totalRelative[4] = count4Percentage;

		return new FrequencyTable(range, totalAbsolut, totalRelative);
		//return ft;
	}
}