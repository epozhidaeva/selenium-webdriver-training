package qa.training.selenium.pageobjects.tests;

import org.junit.Test;

public class CartTests extends TestBase {

    @Test
    public void checkoutCart() {
        for (int i = 0; i < 3; i++) {
            app.addProductToCart();
        }

        app.checkout();
        app.clearCart();
    }
}
