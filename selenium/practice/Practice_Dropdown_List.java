package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Practice_Dropdown_List {

    WebDriver driver;

    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;
    Select select;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Test
    public void TC_01_Dropdown_List_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");

        driver.findElement(By.xpath("//div[@class='header-links']//a[@class='ico-register']")).click();
        //data
        String firstName = "John";
        String lastName = "Wick";
        String email = "johnwick" + getRandomNumber() + "@gmail.com";
        String password = "123456";
        String confirmPassword = "123456";
        //chon gia tri
        driver.findElement(By.xpath("//div[@class='inputs']//input[@id='gender-male']")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.xpath("//select[@name='DateOfBirthDay']//option[text()='1']")).click();
        int countItemDate =driver.findElements(By.xpath("//select[@name='DateOfBirthDay']//option")).size();
        Assert.assertEquals(countItemDate,32);
        driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']//option[text()='May']")).click();
        int countItemMonth =driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']//option")).size();
        Assert.assertEquals(countItemMonth,13);
        driver.findElement(By.xpath("//select[@name='DateOfBirthYear']//option[text()='1980']")).click();
        int countItemYear =driver.findElements(By.xpath("//select[@name='DateOfBirthYear']//option")).size();
        Assert.assertEquals(countItemYear,112);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);

        ///click register
        driver.findElement(By.xpath("//button[@name='register-button']")).click();
        sleep(3);
        //verify register success
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
    }

    @Test
    public void TC_02_Dropdown_List_Where_To_Buy(){
        driver.get("https://www.rode.com/wheretobuy");

        select = new Select(driver.findElement(By.cssSelector("select#country")));
        //
        Assert.assertFalse(select.isMultiple());
        //
        select.selectByVisibleText("Vietnam");
        //
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");
        //click button search
        driver.findElement(By.xpath("//div[@id='where_to_buy_map']//button[text()='Search']")).click();
    }
    @Test
    public void TC_03_Dropdown_List_JQuery(){
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-icon")).click();
        sleep(2);
        By selectSpeedBy = By.xpath("//ul[@id='speed-menu']//div");
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectSpeedBy));
        List<WebElement> allItems = driver.findElements(selectSpeedBy);
        for (WebElement item: allItems){
            if (item.getText().equals("Fast")){
                item.click();
                sleep(2);
                break;
            }
        }
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),"Fast");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(99999);
    }

    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
