package qa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class AddProductTest extends BaseTest {

    @Test
    public void addProductTest(){
        loginAdmin();
        driver.findElement(By.cssSelector("#app- a[href*=catalog]")).click();
        int count = getProductsCount();

        driver.findElement(By.cssSelector("a[href*=edit_product]")).click();

        //Заполняем информацию о товаре
        String name = "Donald duck " + produceRandomNumberString(3);

        driver.findElement(By.cssSelector("[name='status'][value='1']")).click();
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(name);
        driver.findElement(By.cssSelector("[name='code']")).sendKeys("DonDck01");
        changeCheckBoxState(driver.findElement(By.cssSelector("[name='categories[]'][value='0']")), true);
        changeCheckBoxState(driver.findElement(By.cssSelector("[name='categories[]'][value='1']")), true);
        changeCheckBoxState(driver.findElement(By.cssSelector("[name='categories[]'][value='2']")), true);
        Select select = new Select( driver.findElement(By.cssSelector("select[name=default_category_id]")));
        select.selectByVisibleText("Rubber Ducks");
        changeCheckBoxState(driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-3']")), true);
        driver.findElement(By.cssSelector("[name='quantity']")).sendKeys(Keys.chord(Keys.CONTROL, "a") + "9");
        select = new Select( driver.findElement(By.cssSelector("select[name='sold_out_status_id']")));
        select.selectByVisibleText("Temporary sold out");
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys(Keys.HOME + "11.02.2018");
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys(Keys.HOME + "11.04.2018");

        File image = new File("src/test/resources/image.png");
        driver.findElement(By.cssSelector("input[name='new_images[]']")).sendKeys(image.getAbsolutePath());

        //Вкладка Information
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        select = new Select( driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        select.selectByVisibleText("ACME Corp.");
        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys(produceRandomString(7));
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys(produceRandomString(7));
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys(produceRandomString(17));
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys(produceRandomString(7));
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys(produceRandomString(7));

        //Вкладка Prices
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        driver.findElement(By.cssSelector("[name='purchase_price']")).sendKeys(Keys.chord(Keys.CONTROL, "a") + "15");
        select = new Select( driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        select.selectByVisibleText("Euros");
        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("25");
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys("20");

        driver.findElement(By.cssSelector("[name='save']")).click();

        //Проверяем, что товар появился в каталоге
        assertTrue("Количество товаров не увеличилось", getProductsCount() == count + 1);

        boolean found = false;
        List<WebElement> elements = driver.findElements(By.cssSelector(".dataTable .row a"));
        for (int i = 0; i < elements.size(); i++) {
            if (name.equals(elements.get(i).getText())) {
                found = true;
            }
        }

        assertTrue("Добавленный товар не найден", found);
    }

    public int getProductsCount() {
        WebElement footer = driver.findElement(By.cssSelector(".dataTable .footer td"));
        String pr = "Products: ";
        int count = Integer.parseInt(footer.getText().substring(footer.getText().indexOf(pr) + pr.length()));
        System.out.println("Количество товаров: " + count);
        return count;
    }
}


