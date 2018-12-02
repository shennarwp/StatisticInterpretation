package de.htwsaar.eursd.StatisticInterpretation.util;

import java.util.function.IntFunction;

public class Constants
{
	public static final int SEX = 0;
	public static final int WEIGHT = 1;
	public static final int BLOODTYPE = 2;
	public static final int AGE = 3;
	public static final int TEMP0 = 4;
	public static final int TEMP12 = 5;
	public static final int BLOODPRESSURE0 = 6;
	public static final int BLOODPRESSURE12 = 7;
	public static final int DISCIPLINE = 8;
	public static final int DIFFERENCE = 9;

	public static final String[] CATEGORY = {"Sex/Gender", "Weight", "Blood Type", "Age", "Temperature at month 0", "Temperature at month 12",
											 "Blood pressure at month 0", "Blood pressure at month 12", "Discipline", "Difference"};

	public static final int[] DISCRETE = {SEX, BLOODTYPE, DISCIPLINE};
	public static final int[] CONTINUOUS = {WEIGHT, AGE, TEMP0, TEMP12, BLOODPRESSURE0, BLOODPRESSURE12, DIFFERENCE};

	static final int FEMALE = 0;
	static final int MALE = 1;

	static final int BLOOD_0 = 0;
	static final int BLOOD_A = 1;
	static final int BLOOD_B = 2;
	static final int BLOOD_AB = 3;

	static final int PUENKTLICH = 1;
	static final int MANCHMAL = 2;
	static final int SELTEN = 3;

	static final IntFunction<String> intToSex = i -> {
		if(i == FEMALE)
			return "Female";
		if(i == MALE)
			return "Male";
		return "invalid sex!";
	};

	static final IntFunction<String> intToBloodType = i -> {
		if(i == BLOOD_0)
			return "O";
		if(i == BLOOD_A)
			return "A";
		if(i == BLOOD_B)
			return "B";
		if(i == BLOOD_AB)
			return "AB";
		return "invalid Bloodtype!";
	};

	static final IntFunction<String> intToDiscipline = i -> {
		if(i == PUENKTLICH)
			return "Puenktlich";
		if(i == MANCHMAL)
			return "Manchmal";
		if(i == SELTEN)
			return "Selten";
		return "invalid Discipline!";
	};
}
