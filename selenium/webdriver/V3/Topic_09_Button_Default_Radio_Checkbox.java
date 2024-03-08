package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Default_Radio_Checkbox {


    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


//    @Test
    public void TC_01_Button(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButtonBy = By.cssSelector("button.fhs-btn-login");
        //verify login button disable
        Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
        //isEnable : neu 1 element enable -> true va nguoc lai
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("automationfc");
        sleep(1);
        //verify button enable
        Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
        //verify background color
        String loginButtonBackgroundColor = driver.findElement(loginButtonBy).getCssValue("background-color");
        //verify = rgb
        Assert.assertEquals(loginButtonBackgroundColor,"rgba(201, 33, 39, 1)");
        //convert rgb to Hexa
        String loginButtonBackgroundColorHexa = Color.fromString(loginButtonBackgroundColor).asHex();
        Assert.assertEquals(loginButtonBackgroundColorHexa.toUpperCase(),"#C92127");

        driver.navigate().refresh();
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        //remove disable attribute
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled');",driver.findElement(loginButtonBy));
        sleep(2);

        driver.findElement(loginButtonBy).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),
                "Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),
                "Thông tin này không thể để trống");
    }

//    @Test
    public void TC_02_Default_Radio(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        //default - the input:
        //action vs verify
        //Custom - the input:
        //action: ko click dc, verify dc
        By petrolRadio1 = By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input");
        By petrolRadio2 = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By petrolRadio3 = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");

        Assert.assertFalse(driver.findElement(petrolRadio1).isSelected());
        driver.findElement(petrolRadio1).click();
        sleep(2);

        Assert.assertTrue(driver.findElement(petrolRadio1).isSelected());
        driver.findElement(petrolRadio2).click();
        sleep(2);
        Assert.assertFalse(driver.findElement(petrolRadio1).isSelected());
        Assert.assertTrue(driver.findElement(petrolRadio2).isSelected());

        Assert.assertFalse(driver.findElement(petrolRadio3).isEnabled());
    }
    @Test
    public void TC_03_Default_Checkbox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By luggaeCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
        By heatedCheckbox = By.xpath("//label[text()='Heated front and rear seats\n']/preceding-sibling::input");
        By towbarCheckbox = By.xpath("//label[text()='Towbar preparation']/preceding-sibling::input");
        By leatherCheckbox = By.xpath("//label[text()='Leather trim']/preceding-sibling::input");

        //select
        checkToCheckbox(luggaeCheckbox);
        checkToCheckbox(heatedCheckbox);
        //selected
        Assert.assertTrue(isElementSelected(luggaeCheckbox));
        Assert.assertTrue(driver.findElement(heatedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(leatherCheckbox).isSelected());
        //disabled
        Assert.assertFalse(driver.findElement(towbarCheckbox).isEnabled());
        Assert.assertFalse(driver.findElement(leatherCheckbox).isEnabled());
        //de-select
       uncheckToCheckbox(luggaeCheckbox);
       uncheckToCheckbox(heatedCheckbox);
        //de-selected
        Assert.assertFalse(driver.findElement(luggaeCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(heatedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(towbarCheckbox).isSelected());
    }

    @Test
    public void TC_04_Multiple_checkbox(){
        List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
        for (WebElement checkbox: checkboxes){
            checkbox.click();
            sleep(1);
        }
        //verify
        for (WebElement checkbox: checkboxes){
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @Test
    public void TC_05_Custom_radio(){
        driver.get("https://material.angular.io/components/radio/examples");

        //case 1: dung the input: selenuim click(); -> ko work, isSelected() -> work
        //case 2: dung the span : selenuim click(); ->  work, isSelected() -> ko work
        //1 element phai define toi 2 cai locator =>
        // de nham lan, bao tri : code nhieu
        By winterCheckboxInput = By.cssSelector("input[value='Winter']");
        By winterCheckboxSpan = By.cssSelector("//input[@value='Winter']/preceding-sibling::span[@class='mat-radio-outer-circle");

        //case 3: dung span -click, input- verify

        //action
        driver.findElement(winterCheckboxSpan).click();
        //verify
        Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());

        //case 4: dung the input
        //Javascript - click (ko quan tam element an hay hien), isSelected - verify
        clickByJavascript(winterCheckboxInput);
        Assert.assertTrue(driver.findElement(winterCheckboxInput).isSelected());
    }

    @Test
    public void TC_06_Custom_checkbox(){
        driver.get("https://material.angular.io/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
        By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

        clickByJavascript(checkedCheckbox);
        sleep(1);
        clickByJavascript(indeterminateCheckbox);
        sleep(1);

        Assert.assertTrue(isElementSelected(checkedCheckbox));
        Assert.assertTrue(isElementSelected(indeterminateCheckbox));

        clickByJavascript(checkedCheckbox);
        sleep(1);
        clickByJavascript(indeterminateCheckbox);
        sleep(1);

        Assert.assertFalse(isElementSelected(checkedCheckbox));
        Assert.assertFalse(isElementSelected(indeterminateCheckbox));
    }
    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }
    public void clickByJavascript(By by){
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
    }
    public void sleep(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkToCheckbox(By by){
        if (!driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }
    public void uncheckToCheckbox(By by){
        if (driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }
    public boolean isElementSelected(By by){
        if (driver.findElement(by).isSelected()){
            return true;
        }else {
            return false;
        }
    }
}
