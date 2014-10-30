package com.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Converter {
	private static final int timesCharactersCanBeRepeated = 3;
	int sum;

	private static HashMap<Character, Integer> repeatableSymbols = new HashMap<Character, Integer>() {
		{
			put('I', 0);
			put('X', 0);
			put('C', 0);
			put('M', 0);
		}

	};
	private static final HashMap<Integer, Integer[]> subtractionList = new HashMap<Integer, Integer[]>() {
		{

			put(1, new Integer[] { 5, 10 });
			put(10, new Integer[] { 50, 100 });
			put(100, new Integer[] { 500, 1000 });
		}
	};
	boolean lastOperation;
	private static Map<Character, Integer> romanToNumMapping = new HashMap<Character, Integer>() {
		{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}
	};

	public int decode(String str) {

		int current, next, prev;
		char currentChar, nextChar, prevChar;

		int strLen = str.length();
		int charLeft = strLen;
		resetValues();

		for (int i = 0; i < strLen; i++) {
			currentChar = str.charAt(i);
			current = romanToNumMapping.get(currentChar);

			if (strLen == 1) {
				return current;
			} else if (charLeft == 1) {
				prevChar = str.charAt(i - 1);
				prev = romanToNumMapping.get(prevChar);
				if (prev > current) {
					lastOperation = false;
					return sum + current;
				} else if (current > prev) {
					charLeft--;
					sum = performSubtraction(current, prev, sum);

				} else {
					lastOperation = false;
					sum = checkForRepeatableCharacters(str, current, sum, i);
				}
			}

			else {
				if (lastOperation) {
					prevChar = str.charAt(i - 1);
					prev = romanToNumMapping.get(prevChar);
					if (prev < current) {

						System.out
								.println("Error:two subtractions performed in "
										+ str);
						return -1;
					}
				}
				nextChar = str.charAt(i + 1);
				next = romanToNumMapping.get(nextChar);

				charLeft -= 1;
				if (next > current) {
					i++;
					charLeft--;
					sum = performSubtraction(current, next, sum);
					if (sum == -1) {
						return sum;
					}

				} else if (next < current) {
					lastOperation = false;
					sum += current;
				} else if (next == current) {
					lastOperation = false;
					if (repeatableSymbols.containsKey(currentChar)) {
						int countOfRepeatedSymbols = repeatableSymbols
								.get(currentChar) + 1;
						repeatableSymbols.put(currentChar,
								countOfRepeatedSymbols);
						sum = checkForRepeatableCharacters(str, current, sum, i);

					} else {
						System.out
								.println("Error:Non repeatable digits repeated in "
										+ str);
						return -1;
					}
				}
			}
		}

		return sum;
	}

	private int checkForRepeatableCharacters(String str, int current, int sum,
			int i) {
		if ((repeatableSymbols.containsKey(str.charAt(i)))
				&& (repeatableSymbols.get(str.charAt(i)) <= timesCharactersCanBeRepeated)) {
			return sum + current;
		}
		return -1;
	}

	private int performSubtraction(int one, int two, int sum) {

		Integer[] temp = subtractionList.get(one);
		if (!lastOperation) {
			for (int j = 0; j < temp.length; j++) {
				if (temp[j] == two) {
					lastOperation = true;
					return sum + Math.abs(one - two);

				}
			}
		}
		System.out.println("Error:subtraction list violated in " + one + " & "
				+ two);
		return -1;

	}

	private void resetValues() {
		sum = 0;
		lastOperation = false;
		repeatableSymbols.put('I', 0);
		repeatableSymbols.put('X', 0);
		repeatableSymbols.put('C', 0);
		repeatableSymbols.put('M', 0);

	}
}
