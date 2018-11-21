package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Helpclass to represent the Table which contains the absolut and relative frequencies
 * of attributes that have double value
 */
@Getter @Setter
public class FrequencyTable {
	private String[] range;
	private int[] totalAbsolut;
	private double[] totalRelative;

	public FrequencyTable(String[] range, int[] totalAbsolut, double[] totalRelative) {
		this.range = range;
		this.totalAbsolut = totalAbsolut;
		this.totalRelative = totalRelative;
	}
}
