package sample;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.ReUsableMethods;
import data.body;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	@Test
	public static void JsonParse() {
		// TODO Auto-generated method stub
		
		JsonPath js = ReUsableMethods.rawtojson(body.coursePrice());
		int CourseSize=js.get("courses.size()");
		System.out.println(CourseSize);
		
		int PurchasePrice=js.get("dashboard.purchaseAmount");
		System.out.println(PurchasePrice);
		
		String FirstCourseName=js.get("courses[0].title");
		System.out.println(FirstCourseName);
		
		int CoursePriceSum=0;
		for(int i=0; i<CourseSize; i++) {
			String CourseName=js.get("courses["+i+"].title");
			int CoursePrice=js.get("courses["+i+"].price");
			int CourseCopies=js.get("courses["+i+"].copies");
			int soldValue=CoursePrice*CourseCopies;
			System.out.println(CourseName+":"+CoursePrice);
			
			if(CourseName.equalsIgnoreCase("Appium")) {
				int AppiumCopies=js.get("courses["+i+"].copies");
				System.out.println(AppiumCopies);
			}
			CoursePriceSum=CoursePriceSum+soldValue;	
		}
		
		System.out.println(CoursePriceSum);
		Assert.assertEquals(CoursePriceSum, PurchasePrice);
	}

}
