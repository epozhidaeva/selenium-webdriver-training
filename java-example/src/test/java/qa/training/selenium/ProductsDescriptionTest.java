package qa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ProductsDescriptionTest extends BaseTest {

    @Before
    public void start() {
    }

    @Test
    public void checkProductChromeTest() {
        driver = new ChromeDriver();
        checkProductDescription();
    }

    @Test
    public void checkProductFFTest() {
        driver = new FirefoxDriver();
        checkProductDescription();
    }

    @Test
    public void checkProductIETest() {
        driver = new InternetExplorerDriver();
        checkProductDescription();
    }


    public void checkProductDescription() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

        driver.get("http://localhost/litecart/en/");

        WebElement product = driver.findElements(By.cssSelector("#box-campaigns .product")).get(0);

        String name = product.findElement(By.cssSelector(".name")).getAttribute("textContent");

        WebElement regularPriceWE = product.findElement(By.cssSelector(".price-wrapper .regular-price"));

        String regularPrice = regularPriceWE.getAttribute("textContent");

        assertTrue("Обычная цена не серая", isGrey(regularPriceWE.getCssValue("color")));
        assertTrue("Обычная цена не зачеркнута", isLineThrough(regularPriceWE.getCssValue("text-decoration")));

        WebElement salePriceWE = product.findElement(By.cssSelector(".price-wrapper .campaign-price"));

        String salePrice = salePriceWE.getAttribute("textContent");

        assertTrue("Акционная цена не красная", isRed(salePriceWE.getCssValue("color")));
        assertTrue("Акционная цена не жирная", isBold(salePriceWE.getCssValue("font-weight")));

        assertTrue("Обычная цена не меньше акционной",
                getFontSize(regularPriceWE.getCssValue("font-size")) <
                        getFontSize(salePriceWE.getCssValue("font-size")));

        //Переходим на страницу товара
        product.click();

        WebElement boxProduct = driver.findElement(By.cssSelector("#box-product"));

        String nameProduct = boxProduct.findElement(By.cssSelector("h1")).getAttribute("textContent");
        assertEquals("Название товара не совпадает", name, nameProduct);

        WebElement regularPriceProductWE = boxProduct.findElement(By.cssSelector(".price-wrapper .regular-price"));
        String regularPriceProduct = regularPriceProductWE.getAttribute("textContent");
        assertEquals("Обычная цена не совпадает", regularPrice, regularPriceProduct);

        WebElement salePriceProductWE = boxProduct.findElement(By.cssSelector(".price-wrapper .campaign-price"));

        String salePriceProduct = salePriceProductWE.getAttribute("textContent");
        assertEquals("Акционная цена не совпадает", salePrice, salePriceProduct);

        assertTrue("Обычная цена не серая (Страница товара)", isGrey(regularPriceProductWE.getCssValue("color")));
        assertTrue("Обычная цена не зачеркнута (Страница товара)", isLineThrough(regularPriceProductWE.getCssValue("text-decoration")));

        assertTrue("Акционная цена не красная (Страница товара)", isRed(salePriceProductWE.getCssValue("color")));
        assertTrue("Акционная цена не жирная (Страница товара)", isBold(salePriceProductWE.getCssValue("font-weight")));

        assertTrue("Обычная цена не меньше акционной (Страница товара)",
                getFontSize(regularPriceProductWE.getCssValue("font-size")) <
                        getFontSize(salePriceProductWE.getCssValue("font-size")));
    }

    public boolean isRed(String str) {
        Color color = Color.fromString(str);

        return color.getColor().getRed() > 0 && color.getColor().getGreen() == 0 && color.getColor().getBlue() == 0;
    }

    public boolean isGrey(String str) {
        Color color = Color.fromString(str);

        return color.getColor().getRed() == color.getColor().getGreen() && color.getColor().getGreen() == color.getColor().getBlue();
    }

    public boolean isBold(String str) {
        return Integer.parseInt(str) >= 700;
    }

    public boolean isLineThrough(String str) {
        return str.indexOf("line-through") != -1;
    }

    public float getFontSize(String str) {
        return Float.parseFloat(str.substring(0, str.indexOf("px")));
    }

}
