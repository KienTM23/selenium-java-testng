package practice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
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

public class Practice_Button_Radio_Checkbox_Alert {

    WebDriver driver;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    Select select;
    Alert alert;
    Actions actions;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    //Browser
    @Test
    public void TC_01_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("ul#popup-login-tab_list a")).click();

        By buttonLoginBy = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(buttonLoginBy).isEnabled());

        Assert.assertEquals(driver.findElement(buttonLoginBy).getCssValue("background-color"), "rgba(0, 0, 0, 0)");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("");
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        Assert.assertTrue(driver.findElement(buttonLoginBy).isEnabled());
        sleep(1);
        Assert.assertEquals(driver.findElement(buttonLoginBy).getCssValue("background-color"), "rgba(201, 33, 39, 1)");
        System.out.println("Color rgb :" + driver.findElement(buttonLoginBy).getCssValue("background-color"));
        System.out.println("Color hex :" + Color.fromString(driver.findElement(buttonLoginBy).getCssValue("background-color")).asHex());
    }

    @Test
    public void TC_02_Default_Checkbox_Radio_Button() {
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement radioSummer = driver.findElement(By.xpath("//label[text()='Summer']/preceding-sibling::div/input"));
        radioSummer.click();

        Assert.assertTrue(radioSummer.isSelected());

        radioSummer.click();
    }

    @Test
    public void TC_04_Select_All_Checkboxes() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxesItem = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement itemCheckbox : allCheckboxesItem) {
            if (!itemCheckbox.isSelected()) {
                itemCheckbox.click();
                sleep(2);
            }
            Assert.assertTrue(itemCheckbox.isDisplayed());
        }
        for (WebElement itemCheckbox : allCheckboxesItem) {
            if (!itemCheckbox.isSelected()) {
                itemCheckbox.click();
                sleep(2);
            }
            Assert.assertFalse(itemCheckbox.isDisplayed());
        }
//

    }

    @Test
    public void TC_06_Custom_Radio_Button() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        sleep(2);
        WebElement radioButtonCanTho = driver.findElement(By.xpath("//span[@role='presentation']//div[@aria-label='Cần Thơ']"));
        Assert.assertFalse(radioButtonCanTho.isSelected());

        radioButtonCanTho.click();

        Assert.assertTrue(radioButtonCanTho.isDisplayed());

    }

    @Test
    public void TC_07_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //c1: switch alert
//        alert = driver.switchTo().alert();
        //c2: cho alert load va switch alert
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
    }
    @Test
    public void TC_07_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_07_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        String sendTextAlert = "my name automation";
        alert.sendKeys(sendTextAlert);
        sleep(2);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: "+sendTextAlert);
    }
    @Test
    public void TC_11_Authentication_Alert(){
        String username = "admin";
        String password = "admin";
        driver.get("http://"+username+":"+password+"@"+"the-internet.herokuapp.com/basic_auth");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example p")).getText(),"Congratulations! You must have the proper credentials.");
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
            Thread.sleep(second * 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
