package Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Exercise02 {
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

        WebElement state = driver.findElement(By.id("State"));
        Select select1 = new Select(state);
        select1.selectByValue("Florida");

        WebElement comment = driver.findElement(By.xpath("//textarea[@id=\"Sales_Contact_Comments__c\"]"));
        comment.sendKeys("I want to know more about your products.");

        WebElement button = driver.findElement(By.xpath("//button[@class=\"mktoButton\"]"));
        button.click();

        WebElement errorMsg = driver.findElement((By.xpath("//div[@class='mktoErrorMsg']")));
        errorMsg.isEnabled();

        System.out.println("Nội dung message là: " + errorMsg.getText());

        Thread.sleep(3000);
        driver.quit();
    }
}
