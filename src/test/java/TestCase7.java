import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCase7 {
    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");

    }

    @Test
    public void saveAsPdf(){
        //Login
        WebElement myAccount = driver.findElement(By.xpath("(//a[@title='My Account'])[2]"));
        myAccount.click();
        WebElement email = driver.findElement(By.xpath("(//input[@type='email'])[1]"));
        email.sendKeys("mostafa12@gmail.com");
        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys("123456");
        WebElement loginBtn = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginBtn.click();

        WebElement myOrders = driver.findElement(By.cssSelector("div[class='block-content'] > ul :nth-child(4) >a"));
        myOrders.click();
        WebElement viewOrder = driver.findElement(By.cssSelector("tbody > tr :nth-child(6) > span :nth-child(1)"));
        viewOrder.click();

        WebElement printOrder = driver.findElement(By.className("link-print"));
        printOrder.click();




    }

    @AfterTest
    public void closeBrowser(){
        //driver.quit();
    }
}
