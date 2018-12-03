package de.htwsaar.eursd.StatisticInterpretation.util;

import de.htwsaar.eursd.StatisticInterpretation.model.MedicalRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

class Parser
{
	private static final String FILE_PATH = "medizin.csv";

	private static int parseBloodType(String bloodType) {
		if(bloodType.equals("0"))
			return 0;
		if(bloodType.equals("A"))
			return 1;
		if(bloodType.equals("B"))
			return 2;
		if(bloodType.equals("AB"))
			return 3;
		return 0;
	}

	private static double parseDouble(String toBeParsed) throws ParseException {
		NumberFormat format = NumberFormat.getInstance(Locale.GERMAN);
		Number number = format.parse(toBeParsed);
		return number.doubleValue();
	}

	static ArrayList<MedicalRecord> parseRecords() {
		ArrayList<MedicalRecord> records = new ArrayList<>();
		try {
			InputStream in = Parser.class.getClassLoader().getResourceAsStream(FILE_PATH);
			CSVParser parser = CSVParser.parse(in, Charset.defaultCharset(),
								CSVFormat.DEFAULT
										 .withDelimiter(';')
										 .withFirstRecordAsHeader());

			for (CSVRecord csvRecord : parser) {
				MedicalRecord medicalRecord = new MedicalRecord();

				medicalRecord.setSex(Integer.parseInt(csvRecord.get(0)));
				medicalRecord.setWeight(parseDouble(csvRecord.get(1)));
				medicalRecord.setBloodType(parseBloodType(csvRecord.get(2)));
				medicalRecord.setAge(parseDouble(csvRecord.get(3)));
				medicalRecord.setTemp0(parseDouble(csvRecord.get(4)));
				medicalRecord.setTemp12(parseDouble(csvRecord.get(5)));
				medicalRecord.setBloodPressure0(parseDouble(csvRecord.get(6)));
				medicalRecord.setBloodPressure12(parseDouble(csvRecord.get(7)));
				medicalRecord.setDiscipline(Integer.parseInt(csvRecord.get(8)));
				medicalRecord.setDifference(parseDouble(csvRecord.get(9)));

				records.add(medicalRecord);
			}

		} catch (IOException | ParseException e) {
			System.err.println(e);
		}
		return records;
	}
}
