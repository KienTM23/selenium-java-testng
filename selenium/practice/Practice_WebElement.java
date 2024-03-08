package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Practice_WebElement {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Browser
    @Test
    public void TC_01_Verify_Url() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//div[@class='buttons-set']//span/span")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        Assert.assertEquals(driver.getTitle(),"Customer Login");

        driver.findElement(By.xpath("//div[@class='buttons-set']//span/span")).click();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//div[@class='buttons-set']//span/span")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();

        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        Assert.assertTrue(driver.getPageSource().contains(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText()));

        driver.findElement(By.xpath("//div[@class='buttons-set']//span/span")).click();

        Assert.assertTrue(driver.getPageSource().contains(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText()));
    }

    //Element
    @Test
    public void TC_01_Displayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        Assert.assertTrue(driver.findElement(By.xpath("//label[@for='mail']")).isDisplayed());
        driver.findElement(By.id("mail")).sendKeys("automation@gmail.com");
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed());
        driver.findElement(By.id("under_18")).click();
        sleep(2);
        Assert.assertTrue(driver.findElement(By.xpath("//label[@for='edu']")).isDisplayed());
        driver.findElement(By.id("edu")).sendKeys("bachelor");
        sleep(2);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@class='figcaption']//h5[text()='Name: User5']")).isDisplayed());



    }
    @Test
    public void TC_02_Enabled(){}
    @Test
    public void TC_03_Selected(){}
    @Test
    public void TC_04_All(){}

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleep(long second){
        try {
            Thread.sleep(second * 1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
