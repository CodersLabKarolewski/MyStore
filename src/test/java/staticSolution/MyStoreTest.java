package staticSolution;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static staticSolution.MyStoreSteps.addToCart;
import static staticSolution.MyStoreSteps.assertCombinedAmountOfItemsInCheckout;
import static staticSolution.MyStoreSteps.assertModalHeader;
import static staticSolution.MyStoreSteps.clickButtonInsidePopup;
import static staticSolution.MyStoreSteps.driver;
import static staticSolution.MyStoreSteps.goToHomePage;
import static staticSolution.MyStoreSteps.increaseItemQuantity;
import static staticSolution.MyStoreSteps.selectProductOnMainPage;

public class MyStoreTest {

  @Before
  public void anOpenBrowserWithGoogleCom() {
    System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver");

    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://Prod-kurs.coderslab.pl");
  }

  @Test
  public void wikiTest() {
    selectProductOnMainPage(2);
    increaseItemQuantity(3);
    addToCart();
    assertModalHeader();
    clickButtonInsidePopup("Continue shopping");
    goToHomePage();

    selectProductOnMainPage(4);
    increaseItemQuantity(2);
    addToCart();
    assertModalHeader();
    clickButtonInsidePopup("Proceed to checkout");

    assertCombinedAmountOfItemsInCheckout();

    driver.findElement(By.xpath("//*[contains(text(), 'Proceed to checkout')]")).click();
  }

  @After
  public void closeBrowser() {
    driver.quit();
  }
}
