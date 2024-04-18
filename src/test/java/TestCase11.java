import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase11 {
    // step 11 : verify invoice is printed
    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/index.php/backendlogin");
    }

    @Test
    public void verifyInvoices(){
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("user01");
        WebElement password = driver.findElement(By.id("login"));
        password.sendKeys("guru99com");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@title='Login']"));
        loginBtn.click();
        //////
        WebElement closeBtn = driver.findElement(By.xpath("//a[@title='close']"));
        closeBtn.click();
        // step 3
        WebElement sales = driver.findElement(By.xpath("//*[text()='Sales']"));
        Actions action = new Actions(driver);
        action.moveToElement(sales).perform();
        WebElement orders = driver.findElement(By.cssSelector("ul > li[class='  level1'] > a"));
        orders.click();
        //step 4
        WebElement statusDropDown = driver.findElement(By.xpath("//select[@name='status']"));
        Select status = new Select(statusDropDown);
        status.selectByVisibleText("Canceled");
        WebElement searchBtn = driver.findElement(By.xpath("//button[@title='Search']"));
        searchBtn.click();
        // step 5
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));
        WebElement checkBox = driver.findElement(By.cssSelector("div[class='grid'] > div > table > tbody :nth-child(1) > td > input"));
        checkBox.click();
        // step 6
        WebElement actionsDropDown = driver.findElement(By.id("sales_order_grid_massaction-select"));
        Select selectActions = new Select(actionsDropDown);
        selectActions.selectByValue("pdfinvoices_order");
        WebElement submitBtn = driver.findElement(By.cssSelector("form[id='sales_order_grid_massaction-form'] > fieldset :nth-child(4) > button"));
        submitBtn.click();
        // step 7
        String expectedErrorMesg = "There are no printable documents related to selected orders.";
        WebElement errorMesg = driver.findElement(By.cssSelector("li[class='error-msg'] > ul > li > span"));
        String actualErrorMesg = errorMesg.getText();
        System.out.println(actualErrorMesg);
        Assert.assertEquals(actualErrorMesg,expectedErrorMesg);
        // step 8
        statusDropDown = driver.findElement(By.xpath("//select[@name='status']"));
        status = new Select(statusDropDown);
        status.selectByVisibleText("Complete");
        searchBtn = driver.findElement(By.xpath("//button[@title='Search']"));
        searchBtn.click();
        //step 9
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));
        checkBox = driver.findElement(By.cssSelector("div[class='grid'] > div > table > tbody :nth-child(1) > td > input"));
        checkBox.click();
        //step 10
        actionsDropDown = driver.findElement(By.id("sales_order_grid_massaction-select"));
        selectActions = new Select(actionsDropDown);
        selectActions.selectByValue("pdfinvoices_order");
        submitBtn = driver.findElement(By.cssSelector("form[id='sales_order_grid_massaction-form'] > fieldset :nth-child(4) > button"));
        submitBtn.click();


    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}