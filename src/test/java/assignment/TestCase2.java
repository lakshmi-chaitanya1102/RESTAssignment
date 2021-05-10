package assignment;
import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestCase2 {
	
	@Test
	public void multiplyWSDL() throws Exception{
		FileInputStream fis=new FileInputStream(".\\JSONFiles\\multiply.xml");
		RestAssured.baseURI="http://www.dneonline.com";
		Response response=given().header("content-type","text/xml").
		       body(IOUtils.toString(fis,"UTF-8"))
		       .post("/calculator.asmx").
		then(). 
				statusCode(200).assertThat().statusCode(200).log().all().extract().response();
		
		XmlPath xmlPath=new XmlPath(response.asString());
		String result=xmlPath.getString("MultiplyResult");
		Assert.assertEquals("6", result);
	}

}
