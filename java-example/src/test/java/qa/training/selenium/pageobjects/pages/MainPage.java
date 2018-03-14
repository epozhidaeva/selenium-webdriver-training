package qa.training.selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Page{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open() {
        driver.get("http://localhost/litecart/en/");
        return this;
    }

    public void selectProduct() {
        WebElement product = driver.findElements(By.cssSelector("#box-most-popular .product")).get(0);
        product.click();
    }
}
