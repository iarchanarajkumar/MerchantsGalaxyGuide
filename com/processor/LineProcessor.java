package com.processor;

import java.util.HashMap;

import com.bean.MetalInfo;

public class LineProcessor {
	private HashMap<String, String> assignmentStatements = new HashMap<String, String>();
	private HashMap<String, MetalInfo> metalAssignments = new HashMap<String, MetalInfo>();

	private Converter converter = new Converter();

	String[] words;

	public void identifyLineType(String line) {
		String temp = new String();
		words = line.split("( )");

		if ((words.length == 3) && (line.toLowerCase().contains("is"))) {

			assignmentStatements.put(words[0], words[2]);

		}

		else if (line.contains("?")) {
			if (line.toLowerCase().contains("how much")) {
				temp = processGeneralQuestion(words);
			} else
				temp = solveMetalRelatedQuestion(words);
			if (temp.isEmpty()) {
				System.out
						.println("ANSWER:I have no idea what you are talking about");
			} else
				System.out.println(temp);
		}

		else if (line.toLowerCase().contains("credits")) {
			storeMetalRelatedData(words);

		}

	}

	private String processGeneralQuestion(String[] words) {
		if (!getValueToDecode(words).isEmpty())
			return getFullSentence(words)
					+ converter.decode(getValueToDecode(words));
		return "";

	}

	private void storeMetalRelatedData(String words[]) {
		MetalInfo metalInfo = new MetalInfo();
		metalInfo.setQuantity(converter.decode(getValueToDecode(words)));
		for (int i = 0; i < words.length; i++)
			if (words[i].equals("is")) {
				metalInfo.setMetalName(words[i - 1]);
				metalInfo.setCredits(Integer.parseInt(words[i + 1]));
				metalAssignments.put(words[i - 1], metalInfo);
			}

	}

	private String solveMetalRelatedQuestion(String[] words) {
		String temp = new String();
		for (int i = 0; i < words.length; i++) {
			if (metalAssignments.containsKey(words[i])) {

				temp = getFullSentence(words)
						+ (float) metalAssignments.get(words[i]).getCredits()
						/ metalAssignments.get(words[i]).getQuantity()
						* converter.decode(getValueToDecode(words));

			}

		}
		return temp;

	}

	// gets the value of the galaxy code that needs to be decoded
	private String getValueToDecode(String[] words) {
		String line = new String();

		for (int i = 0; i < words.length; i++) {

			if (assignmentStatements.containsKey(words[i])) {
				line += assignmentStatements.get(words[i]);

			}

		}
		return line;
	}

	// Sentence to print the answer
	private String getFullSentence(String[] words) {
		String line = "ANSWER:";

		for (int i = 0; i < words.length; i++) {

			if (assignmentStatements.containsKey(words[i])
					|| (metalAssignments.containsKey(words[i]))) {
				line += words[i] + " ";

			}

		}
		return line + "is ";
	}
}
