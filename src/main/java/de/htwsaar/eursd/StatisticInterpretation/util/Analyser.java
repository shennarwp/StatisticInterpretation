package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static final double ACCURACY = 0.01;					//Messgenauigkeit
	private static final String THREEDECIMALPLACES = "#.###";

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
	 * switch whether the chosen attribute is discrete or continuous
	 * @param ATTRIBUTE the chosen attribute
	 * @return ft FrequencyTable Object which contains the table data
	 */
	static FrequencyTable countFrequency(int ATTRIBUTE) {
		FrequencyTable ft = new FrequencyTable();
		if(Arrays.stream(DISCRETE).anyMatch(i -> i == ATTRIBUTE))
			ft = countDiscreteFrequency(ATTRIBUTE);
		if(Arrays.stream(CONTINUOUS).anyMatch(i -> i == ATTRIBUTE))
			ft = countContinuousFrequency(ATTRIBUTE);
		return ft;
	}

	//--------------------------------------CONTINUOUS/STETIG--------------------------------------
	/**
	 * count the absolute and relative frequencies of attributes which have double as values
	 * (only continuous categories / stetige Merkmale)
	 * @param ATTRIBUTE the chosen attribute
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private static FrequencyTable countContinuousFrequency(int ATTRIBUTE) {
		double[] boundary = getBoundary(ATTRIBUTE);

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

		String[] group = new String[boundary.length-1];
		for(int i = 0; i < group.length; i++) {
			group[i] = String.valueOf(boundary[i]) + " - " + String.valueOf(boundary[i + 1]);
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

		return new FrequencyTable(group, totalAbsolut, totalRelative);
	}

	/**
	 * find the boundary / limit for each groups in selected categories
	 * @param ATTRIBUTE chosen attribute
	 * @return the boundaries as double array
	 */
	private static double[] getBoundary(int ATTRIBUTE) {
		int numberOfClasses = (int)Math.floor(Math.sqrt(records.size()));			//Klasseneinteilung
		double max = getMax(ATTRIBUTE);
		double min = getMin(ATTRIBUTE);
		double classWidth = (max - min + ACCURACY) / numberOfClasses;

		DecimalFormat df = new DecimalFormat(THREEDECIMALPLACES);						//formatting to 3 decimals behind comma
		double[] boundary = new double[numberOfClasses+1];								//limit: 5 classes --> 6 limit(boundaries)
		boundary[0] = Double.parseDouble(df.format(min - (ACCURACY/2)));			//initialize first lowest limit
		for(int i = 1; i < boundary.length; i++){
			boundary[i] = Double.parseDouble(df.format(boundary[i-1] + classWidth));
		}
		return boundary;
	}

	//--------------------------------------DISCRETE/DISKRETE--------------------------------------
	/**
	 * switching between Discrete categories (stetige Merkmale)
	 * which the frequencies want to be counted
	 * @param ATTRIBUTE the chosen attribute
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private static FrequencyTable countDiscreteFrequency(int ATTRIBUTE) {
		FrequencyTable ft = new FrequencyTable();
		switch(ATTRIBUTE) {
			case SEX		: ft = countDiscreteFrequencySex();	 break;
			case BLOODTYPE	: ft = countDiscreteFrequencyBloodType(); break;
			case DISCIPLINE	: ft = countDiscreteFrequencyDiscipline(); break;
			default			: break;
		}
		return ft;
	}

	/**
	 * count the frequency of the category sex / gender
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private static FrequencyTable countDiscreteFrequencySex() {
		String[] groups = {"Weiblich", "Männlich"};
		int absolutMales = 0;
		int absolutFemales = 0;

		for(MedicalRecord record: records) {
			if(record.getSex() == 0)
				absolutFemales++;
			else
				absolutMales++;
		}
		int[] totalAbsolut = {absolutFemales, absolutMales};

		double relativeMales = absolutMales / (double)totalPeople;
		double relativeFemales = absolutFemales / (double)totalPeople;
		double[] totalRelative = {absolutFemales, absolutMales};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	/**
	 * count the frequency of the category bloodtype
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private static FrequencyTable countDiscreteFrequencyBloodType() {
		String[] groups = {"O", "A", "B", "AB"};
		int absolut_O = 0;
		int absolut_A = 0;
		int absolut_B = 0;
		int absolut_AB = 0;

		for(MedicalRecord record: records) {
			if(record.getBloodType() == 0)
				absolut_O++;
			else if (record.getBloodType() == 1)
				absolut_A++;
			else if (record.getBloodType() == 2)
				absolut_B++;
			else
				absolut_AB++;
		}
		int[] totalAbsolut = {absolut_O, absolut_A, absolut_B, absolut_AB};

		double relative_O = absolut_O / (double)totalPeople;
		double relative_A = absolut_A / (double)totalPeople;
		double relative_B = absolut_B / (double)totalPeople;
		double relative_AB = absolut_AB / (double)totalPeople;
		double[] totalRelative = {relative_O, relative_A, relative_B, relative_AB};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	/**
	 * count the frequency of the category discipline
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private static FrequencyTable countDiscreteFrequencyDiscipline() {
		String[] groups = {"Pünktlich", "Manchmal", "Selten"};
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
		int[] totalAbsolut = {absolutPuenktlich, absolutManchmal, absolutSelten};

		double relativePuenktlich = absolutPuenktlich / (double)totalPeople;
		double relativeManchmal = absolutManchmal / (double)totalPeople;
		double relativeSelten = absolutSelten / (double)totalPeople;
		double[] totalRelative = {relativePuenktlich, relativeManchmal, relativeSelten};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}
}
