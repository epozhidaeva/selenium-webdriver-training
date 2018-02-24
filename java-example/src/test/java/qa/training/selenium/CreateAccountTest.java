package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import java.lang.Math;

public class CreateAccountTest extends BaseTest {

    @Test
    public void createAccountTest() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("a[href*=create_account]")).click();

        String email = produceRandomString(5) + "@" + produceRandomString(3) + ".com";
        String password = "Password1";

        driver.findElement(By.cssSelector("[name=tax_id]")).sendKeys(produceRandomNumberString(5));
        driver.findElement(By.cssSelector("[name=company]")).sendKeys(produceRandomString(5));
        driver.findElement(By.cssSelector("[name=firstname")).sendKeys(produceRandomString(5));
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys(produceRandomString(5));
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys(produceRandomString(5));
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys(produceRandomNumberString(5));
        driver.findElement(By.cssSelector("[name=city]")).sendKeys(produceRandomString(5));

        Select select = new Select(driver.findElement(By.cssSelector("[name=country_code]")));
        select.selectByVisibleText("United States");

//        driver.findElement(By.cssSelector("[id^=select2-country_code]")).click();
//        driver.findElement(By.cssSelector("li[id^=select2-country_code][id $=US]")).click();

        select = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        Integer ind = getRandomIntFromInterval(0, select.getOptions().size() - 1);
        select.selectByIndex(ind);

//        driver.findElement(By.cssSelector("select[name='zone_code']")).click();
//        String index = driver.findElement(By.cssSelector("select[name='zone_code']")).getAttribute("length");
//        Integer ind = getRandomIntFromInterval(0, Integer.parseInt(index) - 1);
//        driver.findElements(By.cssSelector("select[name='zone_code'] option")).get(ind).click();

        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys(produceRandomNumberString(7));
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=create_account]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));

        //Разлогиниваемся
        driver.findElement(By.cssSelector("a[href*=logout]")).click();

        //Логинимся и разлогиниваемся снова
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=login]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));
        driver.findElement(By.cssSelector("a[href*=logout]")).click();
    }

    public String produceRandomString(int length) {
        String possible = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        String str = "";

        for( int i = 0; i < length; i++ ) {
            //str += possible.charAt((int) Math.floor(Math.random() * possible.length()));
            str += possible.charAt(rnd.nextInt(possible.length()));
        }

        return str;
    }

    public String produceRandomNumberString(int length) {
        String possible = "0123456789";
        Random rnd = new Random();
        String str = "";

        for( int i = 0; i < length; i++ ) {
            //str += possible.charAt((int) Math.floor(Math.random() * possible.length()));
            str += possible.charAt(rnd.nextInt(possible.length()));
        }

        return str;
    }

    public int getRandomIntFromInterval(Integer min, Integer max) {
        return (int)Math.floor (min + (Math.random() * ((max - min) + 1)));
    }
}
