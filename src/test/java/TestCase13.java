import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCase13 {

    WebDriver driver;
    @BeforeTest
    public void openBrowser(){
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/index.php/backendlogin");
    }

    @Test
    public void login(){
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("user01");
        WebElement password = driver.findElement(By.id("login"));
        password.sendKeys("guru99com");
        WebElement loginBtn = driver.findElement(By.xpath("//input[@title='Login']"));
        loginBtn.click();
        WebElement closeBtn = driver.findElement(By.xpath("//a[@title='close']"));
        closeBtn.click();
        WebElement sales = driver.findElement(By.xpath("//*[text()='Sales']"));
        Actions action = new Actions(driver);
        action.moveToElement(sales).perform();
        WebElement invoices = driver.findElement(By.xpath("//*[@id='nav']/li[1]/ul/li[2]/a"));
        invoices.click();
    }



    @Test(dependsOnMethods = "login")
    public void sort(){
        // elements are sorted descendingly by default
        List<WebElement> datesElements = driver.findElements(By.cssSelector("table[id='sales_invoice_grid_table'] > tbody> tr> :nth-child(3)"));
        //System.out.println(dates.get(1).getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        List<LocalDateTime> dates = new ArrayList<>() ;
        for(WebElement element :datesElements){
            String dateString = element.getText();
            LocalDateTime date = LocalDateTime.parse(dateString, formatter);
            dates.add(date);
        }
        List<LocalDateTime> sortedDates = new ArrayList<>(dates);
        Collections.sort(sortedDates,Collections.reverseOrder());
        Assert.assertEquals(sortedDates,dates);

        //Sort Ascendingly
        WebElement sort = driver.findElement(By.xpath("//span[text()='Invoice Date']"));
        sort.click();
        datesElements.clear();
        dates.clear();
        new WebDriverWait(driver,Duration.ofSeconds(15)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-mask")));
        datesElements = driver.findElements(By.cssSelector("table[id='sales_invoice_grid_table'] > tbody> tr> :nth-child(3)"));
        for(WebElement element :datesElements){
            String dateString = element.getText();
            LocalDateTime date = LocalDateTime.parse(dateString, formatter);
            dates.add(date);
        }
        sortedDates = new ArrayList<>(dates);
        Collections.sort(sortedDates);
        Assert.assertEquals(sortedDates,dates);

    }
}
