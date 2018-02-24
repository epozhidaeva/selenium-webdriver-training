package qa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public boolean isElemetPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areElemetsPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (InvalidSelectorException e) {
            return false;
        }
    }

    public void loginAdmin() {
        String login = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));
    }

    public void assertSortedList(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        assertEquals("Список не отсортирован по алфавиту", sortedList, list);
    }
}
