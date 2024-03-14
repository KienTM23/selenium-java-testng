package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_17_Wait_Part_I_Element_Status {

    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    //3 bien cho file



    @BeforeClass
    public void initialBrowser() {
       driver = new ChromeDriver();
       explicitWait = new WebDriverWait(driver, Duration.ofMillis(15));
//       driver.manage().timeouts().implicitlyWait()
    }

    @Test
    public void TC_01_Visible() {
        driver.get("https://www.facebook.com/");

        //Visible: co tren UI va co trong DOM
        ///1 - wait cho no visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        ///2 - verify no visible / displayed
        Assert.assertTrue(driver.findElement(By.cssSelector("input#email")).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_In_DOM() {
        //invisible: ko dc co tren UI + co trong DOM/HTML
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleep(3);
        //1 - wait cho no invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        //2- verify no invisible / undisplayed
        Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    }
    @Test
    public void TC_02_01_Invisible_Not_In_DOM() {
        //invisible: ko dc co tren UI + co trong DOM/HTML
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleep(3);
        //1 - wait cho no invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        //2- verify no invisible / undisplayed
        Assert.assertFalse(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).isDisplayed());
    }
    //dung case visible and invisible nhieu con cac case khac it hon
    @Test
    public void TC_03_Presence() {
        driver.get("https://www.facebook.com/");
        //Presence: Bat buoc phai co trong DOM + ko quan tam co tren UI hay ko
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleep(3);
        //1 - wait cho no presence: co trong DOM nhung ko co tren UI (dung 1 case cua Invisible)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
        //2 - wait cho no presence: co trong DOM nhung  co tren UI (dung 1 case cua visible)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='firstname']")));
    }
    @Test
    public void TC_04_Staleness() {
        //Staleness
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleep(3);
        //step 1: dang co trong DOM/ hien thi tren UI
        WebElement confirmEmailTextbox = driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']"));
        //step 2 :actiom gi do va element ko con trong DOM
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        //step 3 : Element nay bi staleness (ko con trong DOM)
        explicitWait.until(ExpectedConditions.stalenessOf(confirmEmailTextbox));
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
