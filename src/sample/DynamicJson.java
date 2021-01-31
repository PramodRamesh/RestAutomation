package sample;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ReUsableMethods;
import data.body;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider = "BookData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type","application/json").body(body.addBook(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = ReUsableMethods.rawtojson(response);
		String ID = js.get("ID");
		System.out.println(ID);
	}
	
	@DataProvider(name="BookData")
	public Object[][] bookData() {
	
		return new Object[][] {{"hmmm","111"},{"hmmm","222"},{"hmmm","333"}};
		
		
	}
	
}
