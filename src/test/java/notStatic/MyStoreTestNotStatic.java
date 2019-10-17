package notStatic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStoreTestNotStatic {

  private WebDriver driver;
  private MyStoreStepsNotStatic myStoreStepsNotStatic;

  @Before
  public void anOpenBrowserWithGoogleCom() {
    System.setProperty("webdriver.chrome.driver",
            "src/test/resources/chromedriver");

    driver = new ChromeDriver();
    myStoreStepsNotStatic = new MyStoreStepsNotStatic(driver);
    driver.manage().window().maximize();
    driver.get("https://Prod-kurs.coderslab.pl");
  }

  @Test
  public void wikiTest() {
    myStoreStepsNotStatic.selectProductOnMainPage(2);
    myStoreStepsNotStatic.increaseItemQuantity(3);
    myStoreStepsNotStatic.addToCart();
    myStoreStepsNotStatic.assertModalHeader();
    myStoreStepsNotStatic.clickButtonInsidePopup("Continue shopping");
    myStoreStepsNotStatic.goToHomePage();

    myStoreStepsNotStatic.selectProductOnMainPage(4);
    myStoreStepsNotStatic.increaseItemQuantity(2);
    myStoreStepsNotStatic.addToCart();
    myStoreStepsNotStatic.assertModalHeader();
    myStoreStepsNotStatic.clickButtonInsidePopup("Proceed to checkout");

    myStoreStepsNotStatic.assertCombinedAmountOfItemsInCheckout();

    driver.findElement(By.xpath("//*[contains(text(), 'Proceed to checkout')]")).click();
  }

  @After
  public void closeBrowser() {
    driver.quit();
  }
}
