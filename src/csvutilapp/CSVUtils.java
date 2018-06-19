package csvutilapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {
	private static final char DEFAULT_SEPARATOR = ',';
	private static final char DEFAULT_QUOTE = '"';
			

	public static void main(String[] args) throws Exception {
		
		String csvFile = "/home/user/Документи/Java/WorkingWithCSVFiles/src/csvutilapp/country2.csv";
		
		Scanner sc = new Scanner(new File(csvFile));
		while(sc.hasNext()) {
			List<String> line = parseLine(sc.nextLine());
			System.out.println("Country [id = " + line.get(0) + ", code = " + line.get(1) + ", name = " + line.get(3) + " ]");
		}
		sc.close();

	}
	
	// Method parseLine with one parameter
	public static List<String> parseLine(String csvLine){
		return parseLine(csvLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
	}
	
	// Method parseLine with two parameter
		public static List<String> parseLine(String csvLine, char separators){
			return parseLine(csvLine, separators, DEFAULT_QUOTE);
		}
		
		// Method parseLine with three parameter
		public static List<String> parseLine(String csvLine, char separators, char customQuote){
			List<String> res = new ArrayList<>();
			
			//if empty, return!
			if(csvLine == null && csvLine.isEmpty()) {
				return res;
			}
			
			if(customQuote == ' ') {
				customQuote = DEFAULT_QUOTE;
			}
			
			if(separators == ' ') {
				separators = DEFAULT_SEPARATOR;
			}
			
			StringBuffer curVal= new StringBuffer();
			boolean inQuotes = false;
			boolean startCollectChar = false;
			boolean doubleQuotesInColumn = false;
			
			char[] chars = csvLine.toCharArray();
			
			for(char ch : chars) {
				if(inQuotes) {
					startCollectChar = true;
					if(ch == customQuote) {
						inQuotes = false;
						doubleQuotesInColumn = false;
					} else {
						//Fixed: allow "" custom quote enclose
						if(ch == '\"') {
							if(!doubleQuotesInColumn) {
								curVal.append(ch);
								doubleQuotesInColumn = true;
							}
						}else {
							curVal.append(ch);
						}
					}
				}else {
					if(ch == customQuote) {
						
						inQuotes = true;
						
						// Fixed: allow "" in empty quote enclose
						if(chars[0] != '"' && customQuote == '\"') {
							curVal.append('"');
						}
						
						// Double quotes in column will hit this!
						if(startCollectChar) {
							curVal.append('"');
						}
					}else if(ch == separators) {
						
						res.add(curVal.toString());
						
						curVal = new StringBuffer();
						startCollectChar = false;
					}else if(ch == '\r') {
						//ignore LF characters
						continue;
					}else if(ch == '\n') {
						//the end, break!
						break;
					}else {
						curVal.append(ch);
					}
				}
			}
			
			res.add(curVal.toString());
			
			return res;
		
		}

}
