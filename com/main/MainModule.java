package com.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;

import com.processor.LineProcessor;

public class MainModule {
	final static int ARRAYINDEX = 100;

	public static void main(String[] args) {

		String[] inputLines = new String[ARRAYINDEX];
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src/resources/input.txt");
		} catch (FileNotFoundException e1) {
			System.out
					.println("Sorry, the file you are searching for could not be found! :(");

		}
		BufferedReader reader = new BufferedReader(fileReader);
		LineProcessor lineProcessor = new LineProcessor();
		String temp;

		int i = 0;

		try {
			while ((temp = reader.readLine()) != null) {

				inputLines[i] = temp;
				System.out.println(i + "] " + inputLines[i]);
				lineProcessor.identifyLineType(inputLines[i]);

				i++;
			}
		} catch (Exception e) {
			System.out.println("There was a problem in reading the file. :( ");
		}

	}
}
