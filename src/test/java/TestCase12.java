import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;

public class TestCase12 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void makeReview(){
        driver.navigate().to("http://live.techpanda.org/index.php/review/product/list/id/1/");
        WebElement rateRadioBtn = driver.findElement(By.xpath("(//input[@name='ratings[1]'])[2]"));
        rateRadioBtn.click();
        WebElement reviewTextArea = driver.findElement(By.tagName("textarea"));
        reviewTextArea.sendKeys("good product");
        WebElement reviewSummary = driver.findElement(By.id("summary_field"));
        reviewSummary.sendKeys("good");
        WebElement nickName = driver.findElement(By.id("nickname_field"));
        nickName.sendKeys("ghozal");
        WebElement submitBtn = driver.findElement(By.xpath("//button[@title='Submit Review']"));
        submitBtn.click();



    }

    @Test(dependsOnMethods = "makeReview")
    public void approveReview(){
        // step 5
        driver.navigate().to("http://live.techpanda.org/index.php/backendlogin");
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("user01");
        WebElement password = driver.findElement(By.id("login"));
        password.sendKeys("guru99com");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@title='Login']"));
        loginBtn.click();
        WebElement closeBtn = driver.findElement(By.xpath("//a[@title='close']"));
        closeBtn.click();
        // step 6
        WebElement catalog = driver.findElement(By.xpath("//span[text()='Catalog']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(catalog).perform();
        WebElement ratings = driver.findElement(By.xpath("//span[text()='Reviews and Ratings']"));
        actions.moveToElement(ratings).perform();
        WebElement customerReviews = driver.findElement(By.xpath("//span[text()='Customer Reviews']"));
        actions.moveToElement(customerReviews).perform();
        WebElement pendingReviews = driver.findElement(By.xpath("//span[text()='Pending Reviews']"));
        pendingReviews.click();
        // step 7
        WebElement ID = driver.findElement(By.xpath("//span[text()='ID']"));
        ID.click();
        ID = driver.findElement(By.xpath("//span[text()='ID']"));
        ID.click();
        WebElement editBtn = driver.findElement(By.xpath("(//a[text()='Edit'])[1]"));
        editBtn.click();
        // step 8
        WebElement status = driver.findElement(By.id("status_id"));
        Select statusDropDown = new Select(status);
        statusDropDown.selectByValue("1");
        //WebElement reviewSummary = driver.findElement(By.cssSelector("input[id='title']"));
        //_nickName = reviewSummary.getText();
        WebElement saveBtn = driver.findElement(By.xpath("(//button[@title='Save Review'])[1]"));
        saveBtn.click();





    }

    @Test(dependsOnMethods = "approveReview")
    public void verifyReview(){
        driver.navigate().to("http://live.techpanda.org/");
        WebElement mobile = driver.findElement(By.xpath("//a[text()='Mobile']"));
        mobile.click();
        WebElement sonyImage = driver.findElement(By.id("product-collection-image-1"));
        sonyImage.click();
        WebElement reviews = driver.findElement(By.xpath("(//span[text()='Reviews'])[1]"));
        reviews.click();
        String expectedReviewSummary = "good";
        WebElement ReviewSummary = driver.findElement(By.cssSelector("div[class*='box-reviews'] > dl :nth-child(1) > a"));
        String actualReviewSummary = ReviewSummary.getText();
        Assert.assertEquals(actualReviewSummary,expectedReviewSummary.toUpperCase(Locale.ROOT));


    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
