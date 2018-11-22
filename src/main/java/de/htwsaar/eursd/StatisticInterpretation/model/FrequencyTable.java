package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Helpclass to represent the Table which contains the absolut and relative frequencies
 * of attributes that have double value (nur stetige Merkmale)
 */
@Getter @Setter
public class FrequencyTable {
	private double[] boundary;
	private int[] totalAbsolut;
	private double[] totalRelative;

	public FrequencyTable(double[] boundary, int[] totalAbsolut, double[] totalRelative) {
		this.boundary = boundary;
		this.totalAbsolut = totalAbsolut;
		this.totalRelative = totalRelative;
	}
}
