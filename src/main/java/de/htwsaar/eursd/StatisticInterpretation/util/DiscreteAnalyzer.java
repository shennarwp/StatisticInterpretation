package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;
import org.apache.commons.math3.stat.Frequency;

import java.util.List;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

public class DiscreteAnalyzer extends Analyzer
{
	public DiscreteAnalyzer(int category) {
		super(category);
	}

	/**
	 * switching between Discrete categories (stetige Merkmale)
	 * which the frequencies want to be counted
	 * @return ft FrequencyTable Object which contains the table data
	 */
	@Override
	public FrequencyTable countFrequency() {
		FrequencyTable ft = new FrequencyTable();
		switch(CHOSENCATEGORY) {
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
	private FrequencyTable countDiscreteFrequencySex() {
		String[] groups = {"Weiblich", "Männlich"};
		Frequency frequency = new Frequency();
		records.stream().mapToInt(MedicalRecord::getSex).forEach(frequency::addValue);

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
		Frequency frequency = new Frequency();
		records.stream().mapToInt(MedicalRecord::getBloodType).forEach(frequency::addValue);

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
		Frequency frequency = new Frequency();
		records.stream().mapToInt(MedicalRecord::getDiscipline).forEach(frequency::addValue);

		long[] totalAbsolut = {frequency.getCount(PUENKTLICH),
								frequency.getCount(MANCHMAL),
								frequency.getCount(SELTEN)};

		double[] totalRelative = {	frequency.getPct(PUENKTLICH),
									frequency.getPct(MANCHMAL),
									frequency.getPct(SELTEN)};

		return new FrequencyTable(groups, totalAbsolut, totalRelative);
	}

	List<Comparable<?>> findMode() {
		Frequency frequency = new Frequency();
		switch(CHOSENCATEGORY) {
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
