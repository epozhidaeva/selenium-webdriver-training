package qa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.junit.Assert.*;

public class CheckoutCartTest extends BaseTest {

    @Test
    public void checkoutCartTest() {
        for (int i = 0; i < 3; i++) {
            openMainPage();
            selectProduct();
            addProductToCart();
        }

        checkoutCart();

        int rowsCount = driver.findElements(By.cssSelector(".dataTable tr:not(.header) .item")).size();

        for (int i = 0; i < rowsCount; i++) {
            removeProductFromCart();
        }
        assertTrue(isElementPresent(By.xpath("//em[text()='There are no items in your cart.']")));
    }

    public void openMainPage() {
        driver.get("http://localhost/litecart/en/");
    }

    public void selectProduct() {
        WebElement product = driver.findElements(By.cssSelector("#box-most-popular .product")).get(0);
        product.click();
    }

    public void addProductToCart() {
        WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));

        if (isElementPresent(By.cssSelector("select[name='options[Size]']"))) {
            Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
            select.selectByIndex(getRandomIntFromInterval(1, select.getOptions().size() - 1));
        }

        WebElement addProduct = driver.findElement(By.cssSelector("[name=add_cart_product]"));
        addProduct.click();
        int newQuantity  = Integer.parseInt(quantity.getText()) + 1;
        wait.until(textToBePresentInElement(quantity, "" + newQuantity));
    }

    public void checkoutCart() {
        driver.findElement(By.cssSelector("a[href*=checkout]")).click();
    }

    public void removeProductFromCart() {
        WebElement removeButton = driver.findElement(By.cssSelector("[name=remove_cart_item]"));
        WebElement table = driver.findElement(By.cssSelector(".dataTable"));

        removeButton.click();
        wait.until(ExpectedConditions.stalenessOf(table));
    }
}
