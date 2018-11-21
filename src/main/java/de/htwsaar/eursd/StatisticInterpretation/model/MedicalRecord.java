package de.htwsaar.eursd.StatisticInterpretation.model;

import lombok.Getter;
import lombok.Setter;

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
	private int dicipline;
	private double difference;
}
