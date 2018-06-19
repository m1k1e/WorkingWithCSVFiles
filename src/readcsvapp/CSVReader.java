package readcsvapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

	public static void main(String[] args) throws IOException {
		// Work 
		String csvFile = "/home/user/Документи/Java/WorkingWithCSVFiles/src/readcsvapp/country.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		
		try {
			// Open csv file
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				//use comma separator
				String[] country = line.split(csvSplitBy);
				
				System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}

}
