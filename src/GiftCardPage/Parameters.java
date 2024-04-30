package GiftCardPage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {
	String url = "https://www.amazon.com/";

	WebDriver driver = new ChromeDriver();

	Assertion myAssert = new Assertion();

	Random random = new Random();
	
	String[] msgs = {"enjoy your gift" , "this is from me to you" , "hope you like it"};

	// Screenshot Function
	public void TakeScreenshot() throws IOException {

		TakesScreenshot ts = ((TakesScreenshot) driver);

		LocalDateTime date = LocalDateTime.now();
		String filename = date.toString().replace(":", "-");

		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./src/images/" + filename + ".jpg");

		srcFile.renameTo(destFile);

	}

}
