package qa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.HasCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class DifferentBrowsersTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        //WebDriver firefoxDriver = new FirefoxDriver();

        //--Start Capabilities
        /*DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("unexpectedAlertBehaviour", "dismiss");
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true); //for IE
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); //for IE
        //driver = new InternetExplorerDriver(caps);
        //driver = new FirefoxDriver(caps);
        driver = new ChromeDriver(caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());*/
        //--End Capabilities

        //--Start Options
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        //options.addArguments("start-fullscreen");
        //driver = new ChromeDriver(options);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(caps);*/
        //--End Options

        //--Start FF ESR
        /*FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox ESR\\firefox.exe")));
        driver = new FirefoxDriver(options);*/
        //--End FF ESR

        //--Start FF Nihgtly
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);

        //--End FF Nihgtly

        System.out.println(((HasCapabilities) driver).getCapabilities());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    /*@Test
    public void firstTest() {
        driver.navigate().to("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        //driver.findElement(By.name("btnG")).click();
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
        wait.until(titleIs("webdriver - Поиск в Google"));
    }*/

    @Test
    public void loginTest() {
        String login = "admin";
        String password = "admin";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*=logout]")));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
