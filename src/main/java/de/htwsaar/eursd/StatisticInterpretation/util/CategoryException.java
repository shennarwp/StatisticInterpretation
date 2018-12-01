package de.htwsaar.eursd.StatisticInterpretation.util;

class CategoryException extends RuntimeException
{
	private CategoryException(String message) {
		super(message);
	}

	static void checkCategoryIndex(int category) {
		if(category < 0 || category > 9)
			throw new CategoryException("Selected category does not exist!");
	}

}
