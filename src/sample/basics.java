package sample;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import data.body;
import data.ReUsableMethods;

public class basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		body b = new body();
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//AddPlace
		 String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(ReUsableMethods.GenerateStringFromResource("C:\\Users\\pramod.a.ramesh\\Downloads\\addPlace.json"))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
	JsonPath js = ReUsableMethods.rawtojson(response);
	String placeID = js.getString("place_id");
	
	//UpdatePlace
	String NewAddress= "Kamakya";
	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
	.body("{\r\n" + 
			"\"place_id\":\""+placeID+"\",\r\n" + 
			"\"address\":\""+NewAddress+"\",\r\n" + 
			"\"key\":\"qaclick123\"\r\n" + 
			"}")
	.when().put("maps/api/place/update/json")
	.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	

	//GetPlace
		   String response1 = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
		  .when().get("maps/api/place/get/json")
		  .then().log().all().assertThat().statusCode(200).extract().response().asString();  
		   JsonPath js1=ReUsableMethods.rawtojson(response1);
		   String address = js1.getString("address");
		   System.out.println("Newly updated address is "+address);
		   Assert.assertEquals(address, NewAddress);
	
	}
}
