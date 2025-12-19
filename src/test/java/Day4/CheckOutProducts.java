package Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutProducts {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        //Step1 : Login vào https://www.saucedemo.com/
        WebElement userName = driver.findElement(By.xpath("//input[@placeholder=\"Username\"]"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@data-test=\"login-button\"]"));
        loginBtn.click();

        //Step 2: Chọn tìm kiếm droplist Price (low to high)
        WebElement dropList = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropList);
        select.selectByIndex(2);

        //Step 3: Add to cart 2 sản phẩm bất kì
        WebElement product1 = driver.findElement(By.xpath("//div[contains(text(),'Backpack')]/ancestor::div[@class='inventory_item_description']/descendant::button[text()='Add to cart']"));
        product1.click();

        WebElement product2 = driver.findElement(By.xpath("//div[contains(text(),'Light')]/ancestor::div[@class='inventory_item_description']/descendant::button[text()='Add to cart']"));
        product2.click();

        System.out.println("=======================Shopping Cart======================");

        WebElement noOfProducts = driver.findElement(By.xpath("//span[@class=\"shopping_cart_badge\"]"));
        System.out.println("Số lượng sản phẩm trong giỏ hàng: " + noOfProducts.getText());

        //Step 4: Click vào giỏ hàng

        WebElement shoppingCart = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        shoppingCart.click();

        List<WebElement> itemNames = driver.findElements(By.className("inventory_item_name"));
        String product1Name = itemNames.get(0).getText();
        String product2Name = itemNames.get(1).getText();

        List<WebElement> itemPrice = driver.findElements(By.className("inventory_item_price"));
        String product1Price = itemPrice.get(0).getText();
        String product2Price = itemPrice.get(1).getText();

        System.out.println("Sản phẩm 1: " + product1Name + " có giá " + product1Price);
        System.out.println("Sản phẩm 2: " + product2Name + " có giá " + product2Price);

        List<WebElement> removeBtn = driver.findElements(By.xpath("//button[text()=\"Remove\"]"));
        String button1 = removeBtn.get(0).getText();
        String button2 = removeBtn.get(1).getText();
        System.out.println("Button 1: " + button1);
        System.out.println("Button 2: " + button2);

        //Step 5: Click continute và nhập các thông tin Firts name, Last name, Zip code
        WebElement checkOutBtn = driver.findElement(By.xpath("//button[text()=\"Checkout\"]"));
        checkOutBtn.click();

        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"firstName\"]"));
        firstName.sendKeys("Quynh Anh");

        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"lastName\"]"));
        lastName.sendKeys("Nguyen");

        WebElement zipCode = driver.findElement(By.xpath("//input[@name=\"postalCode\"]"));
        zipCode.sendKeys("10000");

        //Step 6: Click continute
        WebElement continueBtn = driver.findElement(By.xpath("//input[@name=\"continue\"]"));
        continueBtn.click();

        List<WebElement> checkOutItems = driver.findElements(By.className("inventory_item_name"));
        List<WebElement> checkOutPrices = driver.findElements(By.className("inventory_item_price"));

        System.out.println("=====================Checkout: Overview====================");
        System.out.println("Số lượng sản phẩm: " + checkOutItems.size());
        double itemTotal = 0;
        for (int i = 0; i < checkOutItems.size(); i++) {
            String name = checkOutItems.get(i).getText();
            String priceText = checkOutPrices.get(i).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            itemTotal += price;
            System.out.println("Sản phẩm " + (i+1) + ": " + name + "| Giá: " + priceText);
        }

        WebElement shippingInfo = driver.findElement(By.xpath("//div[@data-test=\"shipping-info-value\"]"));
        System.out.println("Shipping Information: " + shippingInfo.getText());

        WebElement itemTotalText = driver.findElement(By.className("summary_subtotal_label"));
        double uiItemTotal = Double.parseDouble(itemTotalText.getText().replace("Item total: $",""));
        System.out.println("Price Total hiển thị: " + uiItemTotal);

        System.out.println("Price Total tính toán: " + itemTotal);
        System.out.println("\tTổng tiền các sản phẩm có chính xác: " + (uiItemTotal == itemTotal));

        WebElement taxText = driver.findElement(By.xpath("//div[@class=\"summary_tax_label\"]"));
        double tax = Double.parseDouble(taxText.getText().replace("Tax: $",""));

        WebElement totalText = driver.findElement(By.xpath("//div[@class=\"summary_total_label\"]"));
        double total = Double.parseDouble(totalText.getText().replace("Total: $",""));

        System.out.println("Tax: $" + tax);
        System.out.println("Tổng tiền các sản phẩm: $" +itemTotal);
        System.out.println("\tTổng tiền sau thuế: " + (total == (itemTotal + tax )));

        WebElement finishBtn = driver.findElement(By.xpath("//button[@data-test=\"finish\"]"));
        System.out.println("Finish button có hiển thị? " + finishBtn.isDisplayed());

        System.out.println("=====================Checkout: Complete!====================");

        //Step 7: Click Finish
        finishBtn.click();

        WebElement completeTitle = driver.findElement(By.className("title"));
        System.out.println("Hiển thị \"Checkout: Complete!\": " + completeTitle.getText().equals("Checkout: Complete!"));

        WebElement completeHeader = driver.findElement(By.className("complete-header"));
        System.out.println("Hiển thị \"Thank you for your order!\": " + completeHeader.getText().equals("Thank you for your order!"));

        WebElement completeText = driver.findElement(By.className("complete-text"));
        System.out.println("Hiển thị \"Your order has been dispatched, and will arrive " +
                "just as fast as the pony can get there!\": " + completeText.getText().equals("Your order has been " +
                "dispatched, and will arrive just as fast as the pony can get there!"));

        WebElement backHomeBtn = driver.findElement(By.id("back-to-products"));
        System.out.println("Hiển thị Back Home button: " + backHomeBtn.isDisplayed());

        Thread.sleep(2000);
        driver.quit();
    }
}


