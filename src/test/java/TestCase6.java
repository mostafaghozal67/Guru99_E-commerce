import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase6 {

    // step 17
    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void login(){
        WebElement myAccount = driver.findElement(By.xpath("(//a[@title='My Account'])[2]"));
        myAccount.click();
        WebElement email = driver.findElement(By.xpath("(//input[@type='email'])[1]"));
        email.sendKeys("mostafa12@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("123456");
        WebElement loginBtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginBtn.click();
    }

    @Test(dependsOnMethods = "login")
    public void addToCart(){
        WebElement myWishList = driver.findElement(By.cssSelector("div[class*='block-account'] > div[class='block-content'] > ul :nth-child(8) > a"));
        myWishList.click();
        WebElement addToCartBtn = driver.findElement(By.xpath("//button[@title='Add to Cart']"));
        addToCartBtn.click();
        WebElement checkout = driver.findElement(By.xpath("(//button[@title='Proceed to Checkout'])[1]"));
        checkout.click();
    }

    @Test(dependsOnMethods = "addToCart")
    public void shippingInfo(){
        WebElement address = driver.findElement(By.xpath("(//input[@title='Street Address'])[1]"));
        address.clear();
        address.sendKeys("ABC");
        WebElement city = driver.findElement(By.xpath("(//input[@title='City'])[1]"));
        city.clear();
        city.sendKeys("New York");
        WebElement stateDropDown = driver.findElement(By.xpath("(//select[@title='State/Province'])[1]"));
        Select stateDropDownSelect = new Select(stateDropDown);
        stateDropDownSelect.selectByVisibleText("New York");
        WebElement zip = driver.findElement(By.xpath("(//input[@title='Zip'])[1]"));
        zip.clear();
        zip.sendKeys("542896");
        WebElement countryDropDown = driver.findElement(By.xpath("(//select[@title='Country'])[1]"));
        Select countryDropDownSelect = new Select(countryDropDown);
        countryDropDownSelect.selectByVisibleText("United States");
        WebElement telephone = driver.findElement(By.xpath("(//input[@title='Telephone'])[1]"));
        telephone.clear();
        telephone.sendKeys("12345678");
        WebElement continueBtn = driver.findElement(By.xpath("(//button[@title='Continue'])[1]"));
        continueBtn.click();
    }

    @Test(dependsOnMethods = "shippingInfo")
    public void checkout(){

        String expectedFlatRate = "$5.00";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement FlatRate = driver.findElement(By.cssSelector("dl[class='sp-methods'] > dd > ul > li > label > span"));
        String  actualFlatRate = FlatRate.getText();
        Assert.assertTrue(actualFlatRate.contains(expectedFlatRate));

        WebElement checkoutContinueBtn = driver.findElement(By.cssSelector("div[id='shipping-method-buttons-container'] > button"));
        checkoutContinueBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement moneyOrderRadioBtn = driver.findElement(By.xpath("//input[@id='p_method_checkmo']"));
        moneyOrderRadioBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement paymentInfoContinueBtn = driver.findElement(By.cssSelector("div[id='payment-buttons-container'] > button"));
        paymentInfoContinueBtn.click();

        String expectedTotalCost = "$620.00";
        WebElement totalCost = driver.findElement(By.cssSelector("table[id='checkout-review-table'] > tfoot >tr[class='last'] :nth-child(2) > strong > span"));
        String actualTotalCost =totalCost.getText();
        Assert.assertTrue(actualTotalCost.contains(expectedTotalCost));

        WebElement placeOrderBtn = driver.findElement(By.xpath("//button[@title='Place Order']"));
        placeOrderBtn.click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String expectedMesg = "YOUR ORDER HAS BEEN RECEIVED";
        System.out.println(driver.getCurrentUrl());
        WebElement placeOrderMesg = driver.findElement(By.cssSelector("div[class='page-title'] > h1"));
        String actualPlaceOrderMesg = placeOrderMesg.getText();
        System.out.println(actualPlaceOrderMesg);
        //Assert.assertTrue(actualPlaceOrderMesg.contains(expectedMesg));
        WebElement orderNumber = driver.findElement(By.cssSelector("div[class='col-main'] :nth-child(3) > a"));
        //System.out.println(orderNumber.getText());
        //System.out.println(driver.getCurrentUrl());






    }


    @AfterTest
    public void closeBrowser(){
        //driver.quit();
    }
}
