package TestWebPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import TestWebPage.eObjectType;

public class Page {
	private WebDriver m_Driver;
	private String m_URL;
	
	public void setDriver(WebDriver m_Driver)
	{
	   this.m_Driver = m_Driver;
	}

	public WebDriver getDriver()
	{
	   return this.m_Driver;
	}
	
	public void setURL(String m_URL)
	{
	   this.m_URL = m_URL;
	}

	public String getURL()
	{
	   return this.m_URL;
	}
		
	public Page(WebDriver i_Driver, String i_URL)
	{
		m_Driver=i_Driver;
		m_URL=i_URL;
		m_Driver=openWebsite(m_URL);
	}
	
	private WebDriver openWebsite(String i_WebsiteURL)
	{
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new ChromeDriver(options);
		driver.get(i_WebsiteURL);
		return driver;
	}
	
	public void FillElement(String i_XPathOfElement, String i_StringToFill)
	{
		WebElement inputBox = m_Driver.findElement(By.xpath(i_XPathOfElement));
		inputBox.sendKeys(i_StringToFill);
	}
	
	public void OpenElement(String i_ElementXPath)
	{
		WebElement elementToClick = m_Driver.findElement(By.xpath(i_ElementXPath));
		elementToClick.click();
	}
	
	public void VerifyCorrectWebsite(String i_ExpectedTitle,String i_ExpectedUrl,
			String i_CurrentTitle, String i_CurrentUrl)
	{
		if(i_CurrentTitle.equals(i_ExpectedTitle) && i_CurrentUrl.equals(i_ExpectedUrl))
		{
			System.out.println("You open the right page.");
		}
		else
		{
			m_Driver.quit();
			System.out.println("Error: You open the wrong page.");
		}
	}
	
	public void PrintWeather(String i_City)
	{
		WebElement weatherTemperature = m_Driver.findElement(By.xpath("//*[@id=\"cdanwmansrch_weathertemps\"]"));
		String CurrentWeather = weatherTemperature.getText();
		System.out.println("The currunt weather in " + i_City + " is:"+CurrentWeather);
	}

	public void ChangeWeatherCity(String i_XPathOfNewCity)
	{
		WebElement chooseCityOfWeather = m_Driver.findElement(By.xpath("//*[@id=\"mainWeatherSelect\"]"));
		ScrollPage();
		chooseCityOfWeather.click();
		chooseCityOfWeather = m_Driver.findElement(By.xpath(i_XPathOfNewCity));
		chooseCityOfWeather.click();
	}
	
	public void VerifyElementIsExist(String i_ElementName, String i_ElementXPath, eObjectType i_ElementType)
	{
		boolean verifyButton = m_Driver.findElements(By.xpath(i_ElementXPath)).size() != 0;
		if (verifyButton == false)
		{
			System.out.println("Error: The " + i_ElementName + ' ' + i_ElementType +" is not exists on this page.");
			m_Driver.quit();
		}
		else {
			System.out.println("ok");

		}
	}
	
	public void ScrollPage()
	{
		JavascriptExecutor jsx = (JavascriptExecutor)m_Driver;
		jsx.executeScript("window.scrollBy(0,450)", "");
	}
	
	public void ChangeWindows()
	{
        String MainWindow = m_Driver.getWindowHandle();

        for (String activeHandle : m_Driver.getWindowHandles()) {
            if (!activeHandle.equals(MainWindow)) {
            	m_Driver.switchTo().window(activeHandle);
            }
        }
	}
	
	public void FillAForm(String i_RecipientEmail, String i_YourName,
						  String i_YourEmail, String i_Comments)
	{
		// Input "Recipient's email".
		FillElement("//*[@id=\"txtTo\"]", "israel_israeli@domaim.com");
		// Input "Your name".
		FillElement("//*[@id=\"txtFromName\"]", "Israel Israeli");
		// Input "Your email".
		FillElement("//*[@id=\"txtFromAddress\"]", "my_email@domain.com");
		// Input "Comments".
		FillElement("//*[@id=\"txtRemarks\"]", "Some Comments.");
	}
}

