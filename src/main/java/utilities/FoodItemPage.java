package utilities;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public class FoodItemPage extends WebDriverConfig{
	
	private String foodList = "html/body/div[1]/span";
	
	public void printAllFood(){
		List<WebElement> foodListElements = getElements("xpath", foodList);
		
		LinkedHashMap<String, String> foodNameServings = new LinkedHashMap<String, String>();
		
		for(int index = 0 ; index < foodListElements.size(); index += 2){
			String foodName = foodListElements.get(index).getText();
			String servings = foodListElements.get( index + 1).getText();
			foodNameServings.put(foodName, servings);
		}
		
		for(Map.Entry<String, String> temp: foodNameServings.entrySet()){
			System.out.println(temp.getKey() + " : " + temp.getValue());
		}
	}
	
	public void printSpecific(int index){
		index--;
		List<WebElement> foodListElements = getElements("xpath", foodList);
		String foodName = foodListElements.get(index * 2).getText();
		System.err.println(foodName);
			
	}

}
