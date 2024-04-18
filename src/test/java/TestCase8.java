import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase8 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void verify(){
        WebElement myAccount = driver.findElement(By.xpath("(//a[@title='My Account'])[2]"));
        myAccount.click();
        WebElement email = driver.findElement(By.xpath("(//input[@type='email'])[1]"));
        email.sendKeys("mostafa11@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("123456");
        WebElement loginBtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginBtn.click();
        WebElement reOrder = driver.findElement(By.cssSelector("tbody > tr :nth-child(6) > span :nth-child(3)"));
        reOrder.click();
//        WebElement reOrder = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("tbody > tr :nth-child(6) > span :nth-child(2)")));
//        reOrder.click();

        WebElement quantity = driver.findElement(By.xpath("//input[@title='Qty']"));
        quantity.clear();
        quantity.sendKeys("10");
        WebElement updateBtn = driver.findElement(By.cssSelector("td[class='product-cart-actions'] > button[class *='btn-update']"));
        updateBtn.click();
        String expectedTotal = "$6,150.00";
        WebElement total = driver.findElement(By.cssSelector("strong > span[class='price']"));
        String actualTotal = total.getText();
        Assert.assertEquals(actualTotal,expectedTotal);
        WebElement checkoutBtn = driver.findElement(By.cssSelector("li[class='method-checkout-cart-methods-onepage-bottom'] > button"));
        checkoutBtn.click();
        WebElement continueBtn = driver.findElement(By.xpath("(//button[@title='Continue'])[1]"));
        continueBtn.click();
        WebElement shippingMethodContinueBtn = driver.findElement(By.cssSelector("div[id='shipping-method-buttons-container'] > button"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        shippingMethodContinueBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement moneyOrderRadioBtn = driver.findElement(By.xpath("//input[@id='p_method_checkmo']"));
        moneyOrderRadioBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement paymentInfoContinueBtn = driver.findElement(By.cssSelector("div[id='payment-buttons-container'] > button"));
        paymentInfoContinueBtn.click();
        WebElement placeOrderBtn = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderBtn.click();
        WebElement orderNumber = driver.findElement(By.cssSelector("div[class='col-main'] :nth-child(3) > a"));
        System.out.println(orderNumber.getText());

    }
}
