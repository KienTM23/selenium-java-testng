package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Element_Homework {
    WebDriver driver;

    By fullNameTextboxBy = By.id("txtFirstname");
    By emailTextboxBy = By.id("txtEmail");
    By confirmEmailTextboxBy = By.id("txtCEmail");
    By passwordTextboxBy = By.id("txtPassword");
    By confirmPasswordTextboxBy = By.id("txtCPassword");
    By phoneTextboxBy = By.id("txtPhone");
    By registerButtonBy = By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']");
    By firstNameMessageError = By.id("txtFirstname-error");
    By emailMessageError = By.id("txtEmail-error");
    By confirmEmailMessageError = By.id("txtCEmail-error");
    By passwordMessageError = By.id("txtPassword-error");
    By confirmPasswordMessageError = By.id("txtCPassword-error");
    By phoneMessageError = By.id("txtPhone-error");

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

//    @BeforeMethod
//    public void beforeMethod(){
//        driver = new ChromeDriver();
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
//    }
    @Test
    public void TC_01_Register_with_empty_data() {
        //dam bao cac field luon empty tran truong hop case data co san
        driver.findElement(fullNameTextboxBy).sendKeys("");
        driver.findElement(emailTextboxBy).sendKeys("");
        driver.findElement(confirmEmailTextboxBy).sendKeys("");
        driver.findElement(passwordTextboxBy).sendKeys("");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("");
        driver.findElement(phoneTextboxBy).sendKeys("");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(firstNameMessageError).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(emailMessageError).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(confirmEmailMessageError).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(passwordMessageError).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(confirmPasswordMessageError).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(phoneMessageError).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_with_invalid_email() {
        driver.findElement(fullNameTextboxBy).sendKeys("kien tm");
        driver.findElement(emailTextboxBy).sendKeys("kientm@1231@12331");
        driver.findElement(confirmEmailTextboxBy).sendKeys("kientm@1231@12331");
        driver.findElement(passwordTextboxBy).sendKeys("123456");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
        driver.findElement(phoneTextboxBy).sendKeys("0987654321");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(emailMessageError).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(confirmEmailMessageError).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_with_incorrect_confirm_email() {
        driver.findElement(fullNameTextboxBy).sendKeys("kien tm");
        driver.findElement(emailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(confirmEmailTextboxBy).sendKeys("kientm@1231@12331");
        driver.findElement(passwordTextboxBy).sendKeys("123456");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
        driver.findElement(phoneTextboxBy).sendKeys("0987654321");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(confirmEmailMessageError).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_with_password_6_characters() {
        driver.findElement(fullNameTextboxBy).sendKeys("kientm");
        driver.findElement(emailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(confirmEmailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(passwordTextboxBy).sendKeys("123");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("123");
        driver.findElement(phoneTextboxBy).sendKeys("0987654321");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(passwordMessageError).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(confirmPasswordMessageError).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_with_incorrect_confirm_password() {
        driver.findElement(fullNameTextboxBy).sendKeys("kien tm");
        driver.findElement(emailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(confirmEmailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(passwordTextboxBy).sendKeys("123456");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("123457");
        driver.findElement(phoneTextboxBy).sendKeys("0987654321");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(confirmPasswordMessageError).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_with_invalid_phone_number() {
        driver.findElement(fullNameTextboxBy).sendKeys("kien tm");
        driver.findElement(emailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(confirmEmailTextboxBy).sendKeys("kientm@1231");
        driver.findElement(passwordTextboxBy).sendKeys("123456");
        driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
        driver.findElement(phoneTextboxBy).sendKeys("098765");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(phoneMessageError).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(phoneTextboxBy).clear();
        driver.findElement(phoneTextboxBy).sendKeys("98765");
        driver.findElement(registerButtonBy).click();

        Assert.assertEquals(driver.findElement(phoneMessageError).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void cleanBrowser() {

//        driver.quit();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
