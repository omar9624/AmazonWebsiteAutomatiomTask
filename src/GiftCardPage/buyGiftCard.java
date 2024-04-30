package GiftCardPage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class buyGiftCard extends Parameters {
	@BeforeTest
	public void setup() throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		WebElement popup = driver
				.findElement(By.cssSelector("input[data-action-params='{\"toasterType\":\"AIS_INGRESS\"}']"));
		popup.click();
	}

	@Test
	public void buyGiftCardProcess() throws InterruptedException, IOException {

		WebElement giftCardTab = driver
				.findElement(By.cssSelector(".nav-a[href='/gift-cards/b/?ie=UTF8&node=2238192011&ref_=nav_cs_gc']"));
		giftCardTab.click();

		WebElement amazonGiftCard = driver.findElement(By.cssSelector("a[aria-label='Amazon Gift Cards']"));
		amazonGiftCard.click();

		WebElement GiftCardPage = driver.findElement(By.cssSelector("a[aria-label='Photo or video gift cards']"));
		GiftCardPage.click();

		WebElement cardsContainer = driver
				.findElement(By.cssSelector(".s-main-slot.s-result-list.s-search-results.sg-row"));
		List<WebElement> cards = cardsContainer.findElements(By.className("sg-col-inner"));
		System.out.println("Number of Cards : " + cards.size());

		int randomNumber = random.nextInt(cards.size()) - 1;
		cards.get(randomNumber).click();
		
		WebElement amountConatiner = driver.findElement(By.id("gc-amount-mini-picker"));
		List<WebElement> amounts = amountConatiner.findElements(By.tagName("li"));
		System.out.println("Number of Amounts : " + amounts.size());

		int randomAmount = random.nextInt(6);
		amounts.get(randomAmount).click();
		System.out.println(amounts.get(randomAmount).getText());

		

		boolean actualResultAmount = driver.findElement(By.id("gc-live-preview-amount")).getText()
				.contains(amounts.get(randomAmount).getText());
		// Assertion For Amount
		myAssert.assertEquals(actualResultAmount, true);
		
		WebElement standardCardDesign = driver.findElement(By.id("gc-customization-type-button-Designs"));
		WebElement emailInput = driver.findElement(By.id("gc-order-form-recipients"));
		WebElement senderInput = driver.findElement(By.id("gc-order-form-senderName"));
		WebElement msgInput = driver.findElement(By.id("gc-order-form-message"));
		WebElement addToCartButton = driver.findElement(By.id("gc-buy-box-atc"));

		standardCardDesign.click();
		emailInput.sendKeys("omaralsayyed24@gmail.com");
		senderInput.sendKeys("Omar Alsayyed");

		//Choose Message From Msg List
		int randomMsg = random.nextInt(msgs.length);
		msgInput.clear();
		msgInput.sendKeys(msgs[randomMsg]);
		
		Thread.sleep(2000);
		addToCartButton.click();

		Thread.sleep(2000);
		TakeScreenshot();
		
		Thread.sleep(2000);
		driver.quit();
	}
}
