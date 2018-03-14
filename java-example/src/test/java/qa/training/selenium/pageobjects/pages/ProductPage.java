package qa.training.selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import qa.training.selenium.pageobjects.tests.TestHelper;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends Page{
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));

        if (isElementPresent(By.cssSelector("select[name='options[Size]']"))) {
            Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            select.selectByIndex(TestHelper.getRandomIntFromInterval(1, select.getOptions().size() - 1));
        }

        WebElement addProduct = driver.findElement(By.cssSelector("[name=add_cart_product]"));
        addProduct.click();
        int newQuantity  = Integer.parseInt(quantity.getText()) + 1;
        wait.until(textToBePresentInElement(quantity, "" + newQuantity));
    }

    public void checkout() {
        driver.findElement(By.cssSelector("a[href*=checkout]")).click();
    }
}
