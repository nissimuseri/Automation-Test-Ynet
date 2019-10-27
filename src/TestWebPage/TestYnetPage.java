package TestWebPage;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class TestYnetPage
{
	public static void main(String[] args) throws InterruptedException
	{
		/* Create a new object of page, using Chrome,
		   go to Google and search for Ynet website.
		*/
		WebDriver driver=null;
		String website = "https://www.google.com";
		Page page= new Page(driver,website);// Open page.
		String xPathOfElement = "//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input";
		String stringToFill = "Ynet";
		page.FillElement(xPathOfElement, stringToFill);
		xPathOfElement = "//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]";
		page.OpenElement(xPathOfElement);// Search "Ynet" in Google.
		xPathOfElement = "//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a/h3";
		page.OpenElement(xPathOfElement);// Open the first result --> Open "Ynet" page.
		// Verify the correct webpage is opened.
		String expectedTitle = "ynet - çãùåú, ëìëìä, ñôåøè, áøéàåú";
		String expectedUrl = "https://www.ynet.co.il/home/0,7340,L-8,00.html";
		String currentTitle = page.getDriver().getTitle();
		String currentUrl = page.getDriver().getCurrentUrl();
		page.VerifyCorrectWebsite(expectedTitle, expectedUrl, currentTitle, currentUrl);
		// Print the current weather in the homepage.
		page.PrintWeather("Tel-Aviv");
		// Change the city to Eilat and print the weather.
		String xPathOfEilat = "//*[@id=\"cdanwmansrch_weathercitieselect\"]/option[1]";
		page.ChangeWeatherCity(xPathOfEilat);
		page.PrintWeather("Eilat");
		// Open the page in resolution 1920*1080.
		page.getDriver().manage().window().setSize(new Dimension(1920, 1080));
		// Open the top article.
		xPathOfElement = "//*[@id=\"top-story-1\"]/div[1]/div[2]/a[1]/span";
		page.OpenElement(xPathOfElement);
		Thread.sleep(10000);// Wait for the ads to disappear.
		// Verify “send to a friend” link exists in the article.
		String elementName = "Send to Friend";
		xPathOfElement = "//*[@id=\"F_Content\"]/div/div[2]/div/div[2]/div[1]/div[2]/div/ul/li[5]/a";
		page.VerifyElementIsExist(elementName, xPathOfElement,eObjectType.Button);
		// Open "send to friend".
		page.ScrollPage();
		xPathOfElement = "//*[@id=\"F_Content\"]/div/div[2]/div/div[2]/div[1]/div[2]/div/ul/li[5]";
		page.OpenElement(xPathOfElement);
		// Fill The form.
		page.ChangeWindows();
        String recipientEmail = "israel_israeli@domaim.com";
        String yourName = "Israel Israeli";
        String yourEmail = "my_email@domain.com";
        String comments = "Some Comments.";
        page.FillAForm(recipientEmail, yourName, yourEmail, comments);
		// * Verify there is a validation on the empty “captcha".
        elementName = "Captcha";
        xPathOfElement = "//*[@id=\"recaptcha-anchor\"]/div[5]";
        page.VerifyElementIsExist(elementName, xPathOfElement, eObjectType.Checkbox);
	}


}
