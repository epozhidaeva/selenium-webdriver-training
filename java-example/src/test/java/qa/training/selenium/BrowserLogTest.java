package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BrowserLogTest extends BaseTest {

    @Test
    public void browserLogTest() {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> rows = driver.findElements(By.xpath("//input[contains(@name, 'products')]/../.."));

        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            cells.get(2).findElement(By.cssSelector("a")).click();

            assertEquals(0, gerBrowserLogsCount());

            driver.findElement(By.cssSelector("button[name='cancel']")).click();

            rows = driver.findElements(By.xpath("//input[contains(@name, 'products')]/../.."));
        }
    }

    public int gerBrowserLogsCount() {
        List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();

        for (LogEntry l : logEntries) {
            System.out.println(l);
        }

        return logEntries.size();
    }
}
