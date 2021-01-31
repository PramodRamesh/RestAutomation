package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath rawtojson(String response) {
		
		   JsonPath JS = new JsonPath(response);
		   return JS;
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		
	    return new String(Files.readAllBytes(Paths.get(path)));
	}

}
