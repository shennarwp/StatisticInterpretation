package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Helpclass to represent the Table which contains the absolut and relative frequencies
 * of attributes that have double value (nur stetige Merkmale)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FrequencyTable {
	private String[] groups;
	private long[] totalAbsolut;
	private double[] totalRelative;
}
