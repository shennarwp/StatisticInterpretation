package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Abstract Superclass for analyzer, only used for setting the chosen category
 * to whatever passed as parameter in constructor, see class Constants.java
 */
public abstract class Analyzer
{
	static final ArrayList<MedicalRecord> records = Parser.parseRecords();
	static final int totalPeople = records.size();

	@Getter @Setter
	private int CHOSENCATEGORY;

	/**
	 * Constructor, setting the CHOSENCATEGORY based on the passed parameter
	 */
	Analyzer(int category)
	{
		CategoryException.checkCategoryIndex(category);
		setCHOSENCATEGORY(category);
	}

	/**
	 * will be implemented and overridden in all subclass of Analyzer
	 */
	public abstract FrequencyTable countFrequency();

}
