import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase2 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void verifyCost(){
        WebElement mobileIcon = driver.findElement(By.xpath("//*[text()='Mobile']"));
        mobileIcon.click();
        WebElement firstPagePriceElement = driver.findElement(By.cssSelector("span[id='product-price-1'] > span[class='price']"));
        String firstPagePrice = firstPagePriceElement.getText();
        WebElement sonyMobile = driver.findElement(By.id("product-collection-image-1"));
        sonyMobile.click();
        WebElement secondPagePriceElement = driver.findElement(By.className("price"));
        String secondPagePrice = secondPagePriceElement.getText();
        Assert.assertEquals(firstPagePrice,secondPagePrice);
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
