import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase3 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void verifyCart(){
        WebElement mobileIcon = driver.findElement(By.xpath("//*[text()='Mobile']"));
        mobileIcon.click();
        //Add to Cart
        WebElement addCartElement = driver.findElement(By.cssSelector("ul[class*='products-grid']>li:nth-child(3)>div[class='product-info']>div[class='actions']>button[class*='btn-cart']"));
        addCartElement.click();
        //Changing the quantity
        WebElement quantityElement = driver.findElement(By.xpath("//input[@type='text']"));
        quantityElement.clear();
        quantityElement.sendKeys("1000");
        //Clicking on Update button
        WebElement updateBtn = driver.findElement(By.cssSelector("button[class*='button btn-update']"));
        updateBtn.click();
        String expectedMesg = "* The maximum quantity allowed for purchase is 500.";
        WebElement errorElement = driver.findElement(By.cssSelector("p[class*='error']"));
        String actualMesg= errorElement.getText();
        Assert.assertEquals(actualMesg,expectedMesg);

        // Empty the cart
        WebElement emptyBtn = driver.findElement(By.cssSelector("button[class*='btn-empty']"));
        emptyBtn.click();

        // Check the cart message
        String expectedCartMesg = "SHOPPING CART IS EMPTY";
        WebElement cartMesgElement = driver.findElement(By.tagName("h1"));
        String actualCartMesg = cartMesgElement.getText();
        Assert.assertEquals(actualCartMesg,expectedCartMesg);

        //li[class *='item'] > div[class='product-info'] > div[class='actions']

    }

    @AfterTest
    public void closeBrowser(){
        //driver.quit();
    }
}
