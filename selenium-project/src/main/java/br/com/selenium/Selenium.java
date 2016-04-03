package br.com.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mggoes\\Documents\\workspace - Financeiro\\selenium-project\\chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		try {
			webDriver.get("http://localhost:8081/app-project/");

			System.out.println(webDriver.getTitle());

			List<WebElement> elements = webDriver.findElements(By.tagName("input"));
			WebElement element = elements.get(0);
			element.click();

			System.out.println(webDriver.getTitle());

			element = webDriver.findElement(By.id("testJSON"));
			element.click();

			WebElement message = webDriver.findElement(By.tagName("li"));
			System.out.println(message.getText());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webDriver.close();
		}
	}
}
