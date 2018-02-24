package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SortedCountriesTest extends BaseTest {

    @Test
    public void sortedCountriesTest() {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable .row"));
        System.out.println(rows.size());

        List<String> countries = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            countries.add(cells.get(4).getText());

            if(!cells.get(5).getText().equals("0")) {
                cells.get(4).findElement(By.cssSelector("a")).click();

                List<WebElement> tableZonesRows = driver.findElements(By.cssSelector("#table-zones tr"));

                List<String> zones = new ArrayList<>();

                for (int j = 1; j < tableZonesRows.size() - 1 ; j++) {
                    List<WebElement> cellsZones = tableZonesRows.get(j).findElements(By.cssSelector("td input"));
                    zones.add(cellsZones.get(2).getAttribute("value"));
                }

                assertSortedList(zones);
                System.out.println("Список зон для страны: " + zones);
                driver.findElement(By.cssSelector("button[name='cancel']")).click();

                rows = driver.findElements(By.cssSelector(".dataTable .row"));
            }
        }
        System.out.println("Список стран: " + countries);
        assertSortedList(countries);
    }

    @Test
    public void sortedZonesTest() {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable .row"));

        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            cells.get(2).findElement(By.cssSelector("a")).click();

            List<WebElement> tableZonesRows = driver.findElements(By.cssSelector("#table-zones tr"));

            List<String> zones = new ArrayList<>();

            for (int j = 1; j < tableZonesRows.size() - 1 ; j++) {
                List<WebElement> cellsZones = tableZonesRows.get(j).findElements(By.cssSelector("td"));
                String index = cellsZones.get(2).findElement(By.cssSelector("select")).getAttribute("selectedIndex");

                zones.add(cellsZones.get(2).findElements(By.cssSelector("option")).get(Integer.parseInt(index)).getText());
            }

            assertSortedList(zones);
            System.out.println("Список зон для страны: " + zones);
            driver.findElement(By.cssSelector("button[name='cancel']")).click();

            rows = driver.findElements(By.cssSelector(".dataTable .row"));
        }
    }
}
