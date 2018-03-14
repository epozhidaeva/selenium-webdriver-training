package qa.training.selenium.pageobjects.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import qa.training.selenium.pageobjects.pages.CheckoutPage;
import qa.training.selenium.pageobjects.pages.MainPage;
import qa.training.selenium.pageobjects.pages.ProductPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class Application {

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;


    public Application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductToCart() {
        mainPage.open().selectProduct();
        productPage.addToCart();
    }

    public void checkout() {
        productPage.checkout();
    }

    public void clearCart() {
        int rowsCount = checkoutPage.getProductsCount();

        for (int i = 0; i < rowsCount; i++) {
            checkoutPage.removeProduct();
        }
        assertTrue(checkoutPage.isCartEmpty());
    }
}
