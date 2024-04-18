import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase9 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void verifyDiscountCode(){
        WebElement mobileIcon = driver.findElement(By.cssSelector("ol[class='nav-primary'] > li:nth-child(1) > a"));
        mobileIcon.click();
        WebElement addToCartBtn = driver.findElement(By.xpath("(//button[@title='Add to Cart'])[1]"));
        addToCartBtn.click();
        String couponCode = "GURU50";
        WebElement discountInput = driver.findElement(By.id("coupon_code"));
        discountInput.sendKeys(couponCode);

    }


    @AfterTest
    public void closeBrowser(){
        //driver.quit();
    }
}
