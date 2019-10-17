package staticSolution;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class MyStoreSteps {

  static WebDriver driver;

  private static void waitFor(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void selectProductOnMainPage(int index) {
    WebElement productsSection = driver.findElement(By.className("products"));
    List<WebElement> productImages = productsSection.findElements(By.tagName("img"));
    productImages.get(index).click();
  }

  static void increaseItemQuantity(int amount) {
    WebElement addItem = driver.findElement(By.className("touchspin-up"));

    for (int i = 0; i < amount; i++) {
      addItem.click();
    }
  }

  static void addToCart() {
    driver.findElement(By.className("add-to-cart")).click();
  }

  static void assertModalHeader() {
    waitFor(1000);
    assertTrue(driver.findElement(By.className("modal-header")).getText().contains("Product successfully added to your shopping cart"));
  }

  static void goToHomePage() {
    driver.findElement(By.className("img-responsive")).click();
  }

  static void clickButtonInsidePopup(String buttonText) {
    WebElement popup = driver.findElement(By.className("modal-dialog"));
    popup.findElement(By.xpath("//*[contains(text(), '" + buttonText + "')]")).click();
    waitFor(2000);
  }

  static void assertCombinedAmountOfItemsInCheckout() {
    List<WebElement> amountInputs = driver.findElements(By.className("js-cart-line-product-quantity"));

    String firstItemAmount = amountInputs.get(0).getAttribute("value");
    String secondItemAmount = amountInputs.get(1).getAttribute("value");

    int productsSum = Integer.parseInt(firstItemAmount) + Integer.parseInt(secondItemAmount);

    String subTotal = driver.findElement(By.className("js-subtotal")).getText().replace(" items", "");

    assertEquals(productsSum, Integer.parseInt(subTotal));
  }
}
