package qa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LeftMenuTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void leftMenuTest() {
        String login = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));

        List<WebElement> categories = driver.findElements(By.cssSelector("#app-"));
        int catSize = categories.size();

        for (int i = 1; i <= catSize; i++) {
            driver.findElement(By.cssSelector("#app-:nth-child(" + i + ")")).click();
            assertTrue("Элемент с тэгом h1 не найден", driver.findElements(By.cssSelector("h1")).size() > 0);

            List<WebElement> subCategories = driver.findElements(By.cssSelector("[id ^= doc]"));
            int subCatSize = subCategories.size();

            for (int j = 1; j <= subCatSize; j++) {
                driver.findElement(By.cssSelector("[id ^= doc]:nth-child(" + j + ")")).click();
                assertTrue("Элемент с тэгом h1 не найден", driver.findElements(By.cssSelector("h1")).size() > 0);
            }
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
