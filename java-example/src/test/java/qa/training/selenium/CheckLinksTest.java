package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CheckLinksTest extends BaseTest {

    @Test
    public void checkLinksTest() {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a[href*=edit_country]")).click();

        List<WebElement> links = driver.findElements(By.xpath("//*[@class='fa fa-external-link']/.."));

        for (int i = 0; i < links.size(); i++) {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();

            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));

            Set<String> newWindows = driver.getWindowHandles();
            List<String> newWindowsList = new ArrayList<>(newWindows);
            newWindowsList.removeAll(oldWindows);
            assertEquals(1, newWindowsList.size());
            String newWindow = newWindowsList.get(0);
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
}
