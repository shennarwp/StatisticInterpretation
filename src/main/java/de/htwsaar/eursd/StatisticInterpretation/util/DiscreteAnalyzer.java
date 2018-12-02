package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;
import org.apache.commons.math3.stat.Frequency;

import java.util.List;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class DiscreteAnalyzer extends Analyzer
{
	private Frequency frequency;

	/**
	 * Constructor, call the superclass constructor just for setting the CHOSENCATEGORY,
	 * @param category the chosen category
	 */
	public DiscreteAnalyzer(int category) {
		super(category);
	}

	/**
	 * collecting all the data of the chosen category in the records and then,
	 * create Frequency-Object for counting the frequencies by adding it with all the data
	 */
	private void initializeData() {
		frequency = new Frequency();
		switch(getCHOSENCATEGORY()) {
			case SEX		: records.stream().mapToInt(MedicalRecord::getSex).forEach(frequency::addValue);
							  break;
			case BLOODTYPE	: records.stream().mapToInt(MedicalRecord::getBloodType).forEach(frequency::addValue);
							  break;
			case DISCIPLINE	: records.stream().mapToInt(MedicalRecord::getDiscipline).forEach(frequency::addValue);
							  break;
			default			: break;
		}
	}

	/**
	 * switching between Discrete categories (stetige Merkmale) based on CHOSENCATEGORY attribute in superclass-field
	 * which the frequencies want to be counted
	 * @return ft FrequencyTable Object which contains the table data
	 */
	@Override
	public FrequencyTable countFrequency() {
		initializeData();
		FrequencyTable ft = new FrequencyTable();
		switch(getCHOSENCATEGORY()) {
			case SEX		: ft = countDiscreteFrequencySex(); break;
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
	private FrequencyTable countDiscreteFrequencySex() {
		String[] groups = {"Weiblich", "Männlich"};

		long[] totalAbsolut = {frequency.getCount(FEMALE),
								frequency.getCount(MALE)};

		double[] totalRelative = {	frequency.getPct(FEMALE),
									frequency.getPct(MALE)};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	/**
	 * count the frequency of the category bloodtype
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private FrequencyTable countDiscreteFrequencyBloodType() {
		String[] groups = {"O", "A", "B", "AB"};

		long[] totalAbsolut = {frequency.getCount(BLOOD_0),
								frequency.getCount(BLOOD_A),
								frequency.getCount(BLOOD_B),
								frequency.getCount(BLOOD_AB)};

		double[] totalRelative = {	frequency.getPct(BLOOD_0),
								   	frequency.getPct(BLOOD_A),
								   	frequency.getPct(BLOOD_B),
									frequency.getPct(BLOOD_AB)};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	/**
	 * count the frequency of the category discipline
	 * @return ft FrequencyTable Object which contains the table data
	 */
	private FrequencyTable countDiscreteFrequencyDiscipline() {
		String[] groups = {"Pünktlich", "Manchmal", "Selten"};

		long[] totalAbsolut = {frequency.getCount(PUENKTLICH),
								frequency.getCount(MANCHMAL),
								frequency.getCount(SELTEN)};

		double[] totalRelative = {	frequency.getPct(PUENKTLICH),
									frequency.getPct(MANCHMAL),
									frequency.getPct(SELTEN)};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	/**
	 * find the most frequent element in the data
	 * @return List containing element(s) which have the highest frequency
	 */
	List<Comparable<?>> findMode() {
		Frequency frequency = new Frequency();
		switch(getCHOSENCATEGORY()) {
			case SEX:
				records.stream().mapToInt(MedicalRecord::getSex).mapToObj(intToSex).forEach(frequency::addValue);
				break;
			case BLOODTYPE:
				records.stream().mapToInt(MedicalRecord::getBloodType).mapToObj(intToBloodType).forEach(frequency::addValue);
				break;
			case DISCIPLINE:
				records.stream().mapToInt(MedicalRecord::getDiscipline).mapToObj(intToDiscipline) .forEach(frequency::addValue);
				break;
			default:
				break;
		}
		return frequency.getMode();
	}
}
