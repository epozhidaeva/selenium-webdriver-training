package qa.training.selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends Page{
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void removeProduct() {
        WebElement removeButton = driver.findElement(By.cssSelector("[name=remove_cart_item]"));
        WebElement table = driver.findElement(By.cssSelector(".dataTable"));

        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(table));
    }

    public int getProductsCount(){
        return driver.findElements(By.cssSelector(".dataTable tr:not(.header) .item")).size();
    }

    public boolean isCartEmpty() {
        return isElementPresent(By.xpath("//em[text()='There are no items in your cart.']"));
    }
}
