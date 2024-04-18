import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCase1 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        //driver.get("http://live.techpanda.org/index.php/");
        driver.get("http://live.techpanda.org/");

    }

    @Test(groups = "TestCase1")
    public void verifyHomePageTitle(){
        String actual = driver.getTitle();
        String expected = "Home page";
        //Assert.assertTrue(actual.contains(expected));
        Assert.assertEquals(actual,expected,"Title doesn't match");
        WebElement mobileicon = driver.findElement(By.xpath("//*[text()='Mobile']"));
        mobileicon.click();
    }

    @Test(dependsOnMethods = "verifyHomePageTitle",groups = "TestCase1")
    public void verifyMobilePageTitle(){
        String actual = driver.getTitle();
        String expected = "Mobile";
        Assert.assertEquals(actual,expected,"Title doesn't match");
    }

    @Test(dependsOnMethods = "verifyMobilePageTitle",groups = "TestCase1")
    public void sortProductsByName(){
        // Css selector : li[class] > div[class='product-info'] > h2[class='product-name'] > a[title='IPhone']
        //List<WebElement> beforeFilter = driver.findElements(By.cssSelector("a[class='product-image']"));

        List<String> expectedElementTitles = new ArrayList<String>();
        expectedElementTitles.add("IPHONE");
        expectedElementTitles.add("SAMSUNG GALAXY");
        expectedElementTitles.add("SONY XPERIA");

        // Sort By Name
        WebElement DropDown = driver.findElement(By.xpath("(//select[@title='Sort By'])[1]"));
        Select DropDownSelect = new Select(DropDown);
        DropDownSelect.selectByVisibleText("Name");

        // Get Elements After Sorting Them
        List<WebElement> actualElement =  driver.findElements(By.cssSelector("li[class *='item'] > div[class='product-info'] > h2[class='product-name'] > a"));
        List<String> actualElementTitles = new ArrayList<String>();
        for (WebElement p : actualElement)
            actualElementTitles.add(p.getText());

        Assert.assertEquals(actualElementTitles,expectedElementTitles);

    }

    // Test Case 2
    @Test(groups = "TestCase2")
    public void verifyCost(){
        WebElement mobileicon = driver.findElement(By.xpath("//*[text()='Mobile']"));
        mobileicon.click();
        WebElement firstPagePriceElement = driver.findElement(By.cssSelector("span[id='product-price-1'] > span[class='price']"));
        String firstPagePrice = firstPagePriceElement.getText();
        WebElement sonyMobile = driver.findElement(By.id("product-collection-image-1"));
        sonyMobile.click();
        WebElement secondPagePriceElement = driver.findElement(By.className("price"));
        String secondPagePrice = secondPagePriceElement.getText();
        Assert.assertEquals(firstPagePrice,secondPagePrice);
    }

    // Test Case 3
    @Test(groups = "TestCase3")
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
