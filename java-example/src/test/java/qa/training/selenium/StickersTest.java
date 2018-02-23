package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import static org.junit.Assert.*;

public class StickersTest  extends BaseTest {

    @Test
    public void checkStickersTest() {

        driver.get("http://localhost/litecart/en/");

        List<WebElement> products = driver.findElements(By.cssSelector(".product"));
        int productsCount = products.size();
        System.out.println(productsCount);

        for (int i = 0; i < productsCount; i++) {
            WebElement product = products.get(i);
            assertEquals(1, product.findElements(By.cssSelector(".sticker")).size());
        }
    }
}
