import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class TestCase4 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void compare(){
        WebElement mobileIcon = driver.findElement(By.xpath("//*[text()='Mobile']"));
        mobileIcon.click();
        WebElement SonyCompareBtn= driver.findElement(By.cssSelector("ul[class*='products-grid'] > li:nth-child(3) > div[class='product-info'] > div[class='actions'] > ul[class='add-to-links'] :nth-child(2) >a"));
        SonyCompareBtn.click();
        WebElement iphoneCompareBtn= driver.findElement(By.cssSelector("ul[class*='products-grid'] > li:nth-child(1) > div[class='product-info'] > div[class='actions'] > ul[class='add-to-links'] :nth-child(2) >a"));
        iphoneCompareBtn.click();
        String mainWindow = driver.getWindowHandle();
        WebElement compareBtn = driver.findElement(By.cssSelector("button[title='Compare']"));
        compareBtn.click();
        Set<String> allWindows = driver.getWindowHandles(); // Main + Popup
        for (String s : allWindows){
            if(!s.equals(mainWindow)){
                driver.switchTo().window(s);

            }
        }
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        //driver.close();


    }

    @AfterTest
    public void closeBrowser(){
        //driver.quit();
    }
}

