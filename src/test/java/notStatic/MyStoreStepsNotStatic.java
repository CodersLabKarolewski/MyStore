package notStatic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class MyStoreStepsNotStatic {

  private WebDriver driver;

  MyStoreStepsNotStatic(WebDriver driver) {
    this.driver = driver;
  }

  private void waitForHalfASecond() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  void selectProductOnMainPage(int index) {
    WebElement productsSection = driver.findElement(By.className("products"));
    List<WebElement> productImages = productsSection.findElements(By.tagName("img"));
    productImages.get(index).click();
  }

  void increaseItemQuantity(int amount) {
    WebElement addItem = driver.findElement(By.className("touchspin-up"));

    for (int i = 0; i < amount; i++) {
      System.out.println(i);
      addItem.click();
    }
  }

  void addToCart() {
    driver.findElement(By.className("add-to-cart")).click();
  }

  void assertModalHeader() {
    waitForHalfASecond();
    assertTrue(driver.findElement(By.className("modal-header")).getText().contains("Product successfully added to your shopping cart"));
  }

  void goToHomePage() {
    driver.findElement(By.className("img-responsive")).click();
  }

  void clickButtonInsidePopup(String buttonText) {
    WebElement popup = driver.findElement(By.className("modal-dialog"));
    popup.findElement(By.xpath("//*[contains(text(), '" + buttonText + "')]")).click();
    waitForHalfASecond();
  }

  void assertCombinedAmountOfItemsInCheckout() {
    List<WebElement> amountInputs = driver.findElements(By.className("js-cart-line-product-quantity"));

    String firstItemAmount = amountInputs.get(0).getAttribute("value");
    String secondItemAmount = amountInputs.get(1).getAttribute("value");

    int productsSum = Integer.parseInt(firstItemAmount) + Integer.parseInt(secondItemAmount);

    String subTotal = driver.findElement(By.className("js-subtotal")).getText().replace(" items", "");

    assertEquals(productsSum, Integer.parseInt(subTotal));
  }
}
