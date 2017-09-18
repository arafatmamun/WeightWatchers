package Assignment;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import utilities.FoodItemPage;
import utilities.WebDriverConfig;

public class TestGeneralScripting  {
	
	
	
	// Reverse Given String
	private static void reverseEverything(String s){
		String reverse = "";
		
		for(int index = s.length() -1 ; index >= 0; index--){
			reverse += s.charAt(index);
		}
		
		System.out.println(reverse);
	}
	
	
	// Verifying if a file is Exists
	private static void doesFileExist(String path){
		File temp = new File(path);
		if(temp.exists())
			System.out.println("File exists: " + path);
		else
			System.out.println("File Does not Exist" + path);
	}
	
	
	@Test(priority=1)
	public static void QuestionOne(){
		String testString = "hope you are doing well";
		reverseEverything(testString);
	}
	
	@Test(priority=2)
	public static void QuestionTwoA(){
		FoodItemPage foodItemPage = new FoodItemPage();
		foodItemPage.printSpecific(3);
		foodItemPage.printSpecific(5);
	}
	
	@Test(priority=3)
	public static void QuestionTwoB(){
		FoodItemPage foodItemPage = new FoodItemPage();
		foodItemPage.printAllFood();
	}
	
	@Test(priority=4)
	public static void QuestionThreeA(){
		String currentDir = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator"); 
		String filePath = currentDir + fileSeparator + "dictionary" + fileSeparator + "dictionary.txt";
		doesFileExist(filePath);
	}
	
	@Test(priority=5)
	public static void QuestionThreeB(){
		String currentDir = System.getProperty("user.dir");
		String fileSeparator = System.getProperty("file.separator"); 
		String filePath = currentDir + fileSeparator + "dictionary" + fileSeparator + "dictionary.txt";
		
		
		BufferedReader bufferReader = null;
		FileReader fileReadrer = null;

		try {

			fileReadrer = new FileReader(filePath);
			bufferReader = new BufferedReader(fileReadrer);

			String currentLine;
			LinkedHashMap<String, ArrayList<String>> dictionary = new LinkedHashMap<String, ArrayList<String>>();
			
			// reading data and processing for expected print
			while ((currentLine = bufferReader.readLine()) != null) {
				String[] fullString = currentLine.split("-");
				String[] meaning = fullString[1].split(",");
				
				ArrayList<String> tempMeaning = new ArrayList<String>();
				for(int index = 0; index < meaning.length; index++)
					tempMeaning.add(meaning[index]);
				
				dictionary.put(fullString[0], tempMeaning);	
			}
			
			// print dictionary
			for(Map.Entry<String, ArrayList<String>> temp: dictionary.entrySet()){
				System.out.println(temp.getKey());
				ArrayList<String> meaning = temp.getValue();
				for(int index = 0; index < meaning.size(); index++)
					System.out.println(meaning.get(index));
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bufferReader != null)
					bufferReader.close();

				if (fileReadrer != null)
					fileReadrer.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
}
