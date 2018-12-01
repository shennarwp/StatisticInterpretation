package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * model class which represent the record (each row) in the csv file
 */
@Getter @Setter
public class MedicalRecord
{
	private int sex;
	private double weight;
	private int bloodType;
	private double age;
	private double temp0;
	private double temp12;
	private double bloodPressure0;
	private double bloodPressure12;
	private int discipline;
	private double difference;
}
