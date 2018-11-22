package de.htwsaar.eursd.StatisticInterpretation.util;

class Constants
{
	static final int SEX = 0;
	static final int WEIGHT = 1;
	static final int BLOODTYPE = 2;
	static final int AGE = 3;
	static final int TEMP0 = 4;
	static final int TEMP12 = 5;
	static final int BLOODPRESSURE0 = 6;
	static final int BLOODPRESSURE12 = 7;
	static final int DISCIPLINE = 8;
	static final int DIFFERENCE = 9;

	static final int[] DISCRETE = {SEX, BLOODTYPE, DISCIPLINE};
	static  final int[] CONTINUOUS = {WEIGHT, AGE, TEMP0, TEMP12, BLOODPRESSURE0, BLOODPRESSURE12, DIFFERENCE};

}
