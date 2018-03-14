package qa.training.selenium.pageobjects.tests;

public class TestHelper {

    public static int getRandomIntFromInterval(Integer min, Integer max) {
        return (int)Math.floor (min + (Math.random() * ((max - min) + 1)));
    }
}
