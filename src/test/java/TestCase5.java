import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase5 {

    WebDriver driver;

    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void createAccount(){
        WebElement myAccount = driver.findElement(By.xpath("(//a[@title='My Account'] )[2]"));
        myAccount.click();
        WebElement createAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        createAccount.click();
        // Create Account
        WebElement firstName = driver.findElement(By.id("firstname"));
        firstName.sendKeys("Mostafaa");

        WebElement lastName = driver.findElement(By.id("lastname"));
        lastName.sendKeys("ghozal");

        WebElement email = driver.findElement(By.id("email_address"));
        email.sendKeys("mostafa12@gmail.com");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement confirmPassword = driver.findElement(By.id("confirmation"));
        confirmPassword.sendKeys("123456");

        WebElement registerBtn = driver.findElement(By.xpath("//button[@title='Register']"));
        registerBtn.click();



    }

    @Test(dependsOnMethods = "createAccount")
    public void shareWishlist(){
        //driver.get("http://live.techpanda.org/index.php/tv.html");
        WebElement tvElement = driver.findElement(By.xpath("//a[text()='TV']"));
        tvElement.click();
        WebElement addToWishlistBtn = driver.findElement(By.xpath("(//a[text()='Add to Wishlist'])[1]"));
        addToWishlistBtn.click();
        //System.out.println("geeeeee");
        // share the wishlist
        WebElement shareBtn = driver.findElement(By.cssSelector("button[class *='btn-share']"));
        shareBtn.click();

        WebElement email = driver.findElement(By.cssSelector("#email_address"));
        email.sendKeys("ghozal@gmail.com");
        WebElement mesg = driver.findElement(By.id("message"));
        mesg.sendKeys("hello");
        WebElement shareWishlistBtn = driver.findElement(By.xpath("//button[@title='Share Wishlist']"));
        shareWishlistBtn.click();

        String expectedMesg = "Your Wishlist has been shared.";
        String actualMesg = driver.findElement(By.xpath("//span[text()='Your Wishlist has been shared.']")).getText();
        Assert.assertEquals(actualMesg,expectedMesg);


    }




    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
}
