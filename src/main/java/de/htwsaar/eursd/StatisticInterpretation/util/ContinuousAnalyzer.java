package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.FrequencyTable;
import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;
import org.apache.commons.math3.random.EmpiricalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.*;

import static de.htwsaar.eursd.StatisticInterpretation.util.Constants.*;

/**
 * Analyzer-subclass for evaluating data and statistics only of continuous categories (stetige Merkmale)
 */
public class ContinuousAnalyzer extends Analyzer
{
	private static final int numberOfClasses = (int)Math.floor(Math.sqrt(records.size()));
	private static final double ACCURACY = 0.01;					//Messgenauigkeit
	private static final String THREEDECIMALPLACES = "#.###";
	private DescriptiveStatistics statistics;
	private EmpiricalDistribution distribution;

	/**
	 * Constructor, call the superclass constructor just for setting the CHOSENCATEGORY,
	 * collecting all the data of the chosen category in the records and then,
	 * create DescriptiveStatistics-Object for various statistical terms with the data as parameter, and
	 * create EmpiricalDistribution-Object for counting the frequencies and all the boundaries by loading it with the data
	 * @param category the chosen category
	 */
	public ContinuousAnalyzer(int category) {
		super(category);
		double[] data = new double[totalPeople];
		switch(category)
		{
			case WEIGHT:
				data = records.stream().mapToDouble(MedicalRecord::getWeight).toArray();
				break;
			case AGE :
				data = records.stream().mapToDouble(MedicalRecord::getAge).toArray();
				break;
			case TEMP0 :
				data = records.stream().mapToDouble(MedicalRecord::getTemp0).toArray();
				break;
			case TEMP12 :
				data = records.stream().mapToDouble(MedicalRecord::getTemp12).toArray();
				break;
			case BLOODPRESSURE0 :
				data = records.stream().mapToDouble(MedicalRecord::getBloodPressure0).toArray();
				break;
			case BLOODPRESSURE12 :
				data = records.stream().mapToDouble(MedicalRecord::getBloodPressure12).toArray();
				break;
			case DIFFERENCE :
				data = records.stream().mapToDouble(MedicalRecord::getDifference).toArray();
				break;
			default :
				break;
		}
		statistics = new DescriptiveStatistics(data);
		distribution = new EmpiricalDistribution(numberOfClasses);
		distribution.load(data);
	}

	/**
	 * count the absolute and relative frequencies
	 * @return ft FrequencyTable Object which contains the table data
	 */
	@Override
	public FrequencyTable countFrequency() {
		Double[] boundary = getBoundary();

		String[] group = new String[boundary.length-1];
		for(int i = 0; i < group.length; i++) {
			group[i] = String.valueOf(boundary[i]) + " - " + String.valueOf(boundary[i + 1]);
		}

		//retrieving absolute frequencies from List<SummaryStatistic> returned from distribution.getBinStats()
		long[] totalAbsolut = distribution.getBinStats()
										   .stream()
										   .mapToLong(SummaryStatistics::getN)
										   .toArray();

		//count the relative frequencies
		LongToDoubleFunction countRelative = l -> l / (double) totalPeople;
		double[] totalRelative = Arrays.stream(totalAbsolut)
										.mapToDouble(countRelative)
										.toArray();

		return new FrequencyTable(group, totalAbsolut, totalRelative);
	}

	/**
	 * calculate the boundaries of the intervals of the category
	 * @return Double-Array containing all the boundaries
	 */
	Double[] getBoundary() {
		DecimalFormat df = new DecimalFormat(THREEDECIMALPLACES);			//formatting to only 3 decimal places
		ArrayList<Double> boundary = new ArrayList<>();
		double lowestBound = Double.parseDouble(df.format(getMin() - (ACCURACY / 2)));

		boundary.add(lowestBound);											//have to add lowestBound because getUpperBounds()
		Arrays.stream(distribution.getUpperBounds())						//returned all the boundaries EXCEPT the lowest one
								   .mapToObj(df::format)
								   .map(Double::parseDouble)
								   .sorted()
								   .forEach(boundary::add);
																			//adding (ACCURACY / 2) to the highest boundary because
		int lastIndex = boundary.size() - 1;								//getUpperBounds() only set the maximum value as highest boundary instead
		double highestBound = Double.parseDouble(df.format(boundary.get(lastIndex) + (ACCURACY / 2)));
		boundary.set(lastIndex, highestBound);
		return boundary.toArray(new Double[0]);
	}

	/**
	 * find the maximum of the category
	 */
	private double getMax() {
		return statistics.getMax();
	}

	/**
	 * find the minimum of the category
	 */
	private double getMin() {
		return statistics.getMin();
	}

	/**
	 * find the average value of the category
	 */
	double findMean() {
		return statistics.getMean();
	}

	/**
	 * find the Median value (50% quantile) of the category
	 */
	double findMedian() {
		return statistics.getPercentile(50);
	}

	/**
	 * find the 25% quantile of the category
	 */
	double find25PercentQuantile() {
		return statistics.getPercentile(25);
	}

	/**
	 * find the 75% quantile of the category
	 */
	double find75PercentQuantile() {
		return statistics.getPercentile(25);
	}

	/**
	 * find the percentile range (Quartilabstand) of the category
	 */
	double findPercentileRange() {
		return find75PercentQuantile() - find25PercentQuantile();
	}

	/**
	 * find the range (Spannweite) of the category
	 */
	double findRange() {
		return getMax() - getMin();
	}

	/**
	 * find the variance (Streuung) of the category
	 */
	double findVariance() {
		return statistics.getVariance();
	}

	/**
	 * find the standard deviation (Standardabweichung) of the category
	 */
	double findStandardDeviation() {
		return statistics.getStandardDeviation();
	}
}
