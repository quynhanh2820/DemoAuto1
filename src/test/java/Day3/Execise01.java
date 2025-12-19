package Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Execise01 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebElement email = driver.findElement(By.name("Email"));
        email.sendKeys("email@mail.com");

        WebElement company = driver.findElement(By.name("Company"));
        company.sendKeys("FPT Software");
        Thread.sleep(1000);

        WebElement phoneNumber = driver.findElement(By.xpath("//input[@id=\"Phone\"]"));
        phoneNumber.click();
        phoneNumber.sendKeys("0912345678");
        Thread.sleep(2000);

        WebElement state = driver.findElement(By.id("State"));
        Select select1 = new Select(state);
        select1.selectByValue("Arizona");
        System.out.println("State: " + select1.getFirstSelectedOption().getText());

        WebElement interest = driver.findElement(By.name("Solution_Interest__c"));
        Select select = new Select(interest);
        select.selectByIndex(3);
        System.out.println("Interest selected successfully.");
        Thread.sleep(3000);

        WebElement comments = driver.findElement(By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"));
        comments.sendKeys("I want to know more about your products.");

        WebElement letstalk = driver.findElement(By.xpath("//button[@class=\"mktoButton\"]"));
        letstalk.click();

        Thread.sleep(3000);
        driver.quit();
    }
}
