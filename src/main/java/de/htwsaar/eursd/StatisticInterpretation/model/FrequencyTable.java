package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Helpclass to represent the Table which contains the absolut and relative frequencies
 * of attributes that have double value (nur stetige Merkmale)
 */
@Getter @Setter
public class FrequencyTable {
	private String[] groups;
	private int[] totalAbsolut;
	private double[] totalRelative;

	public FrequencyTable(){}

	public FrequencyTable(String[] groups, int[] totalAbsolut, double[] totalRelative) {
		this.groups = groups;
		this.totalAbsolut = totalAbsolut;
		this.totalRelative = totalRelative;
	}
}
