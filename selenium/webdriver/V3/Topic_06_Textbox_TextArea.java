package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_06_Textbox_TextArea {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    String loginPageUrl;
    String customerId;
    String userId;
    String userPassword;
    String customerName;
    String gender;
    String dateOfBirth, addressInput, addressOutput, city, state, pinNumber, phoneNumber, email, password;

    String editAdressInput, editAdressOutput, editCity, editState, editPin, editPhone, editEmail;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        loginPageUrl = "https://demo.guru99.com/v4/";
        customerName = "kevink";
        gender = "male";
        dateOfBirth = "2024-03-07";
        addressInput = "123 Los Angeles\nUnited States";
        addressOutput = "123 Los Angeles United States";
        city = "New York";
        state = "California";
        pinNumber = "654213";
        phoneNumber = "0987654321";
        email = "kevink" + getRandomNumber() + "@gmail.com";
        password = "123465";
        //data edit
        editAdressInput = "123 Tracy\nColombioa";
        editAdressOutput = "123 Tracy Colombioa";
        editCity = "Ha Noi";
        editState = "New Deley";
        editPin = "587965";
        editPhone = "0123654789";
        editEmail = "kevink" + getRandomNumber() + "@gmail.vn";

        driver.get(loginPageUrl);
    }

    @Test
    public void TC_Register() {
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);

        driver.findElement(By.name("uid")).sendKeys(userId);
        driver.findElement(By.name("password")).sendKeys(userPassword);
        driver.findElement(By.name("btnLogin")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(), "Welcome To Manager's Page of Guru99 Bank");
        Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText(), "Manger Id : " + userId);
    }

    @Test
    public void TC_03_New_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.name("name")).sendKeys(customerName);

        WebElement dobTextbox = driver.findElement(By.name("dob"));
        jsExecutor.executeScript("arguments[0].removeAttribute('type')", dobTextbox);
        dobTextbox.sendKeys(dateOfBirth); //note

        driver.findElement(By.name("addr")).sendKeys(addressInput);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("state")).sendKeys(state);
        driver.findElement(By.name("pinno")).sendKeys(pinNumber);
        driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
        driver.findElement(By.name("emailid")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("sub")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

        customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
    }

    @Test
    public void TC_04_Edit_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();

        driver.findElement(By.id("cusid")).sendKeys(customerId);
        driver.findElement(By.id("AccSubmit")).click();

        //verify new customer displayed at edit customer correctly
        Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
        Assert.assertEquals(driver.findElement(By.name("gender")).getAttribute("value"), gender);
        Assert.assertEquals(driver.findElement(By.name("dob")).getAttribute("value"), dateOfBirth);
        Assert.assertEquals(driver.findElement(By.name("addr")).getText(), addressInput);
        Assert.assertEquals(driver.findElement(By.name("city")).getAttribute("value"), city);
        Assert.assertEquals(driver.findElement(By.name("state")).getAttribute("value"), state);
        Assert.assertEquals(driver.findElement(By.name("pinno")).getAttribute("value"), pinNumber);
        Assert.assertEquals(driver.findElement(By.name("telephoneno")).getAttribute("value"), phoneNumber);
        Assert.assertEquals(driver.findElement(By.name("emailid")).getAttribute("value"), email);
        //edit
        driver.findElement(By.name("addr")).clear();
        driver.findElement(By.name("addr")).sendKeys(editAdressInput);
        driver.findElement(By.name("city")).clear();
        driver.findElement(By.name("city")).sendKeys(editCity);
        driver.findElement(By.name("state")).clear();
        driver.findElement(By.name("state")).sendKeys(editState);
        driver.findElement(By.name("pinno")).clear();
        driver.findElement(By.name("pinno")).sendKeys(editPin);
        driver.findElement(By.name("telephoneno")).clear();
        driver.findElement(By.name("telephoneno")).sendKeys(editPhone);
        driver.findElement(By.name("emailid")).clear();
        driver.findElement(By.name("emailid")).sendKeys(editEmail);
        driver.findElement(By.name("sub")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(), "Customer Registered Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAdressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(99999);
    }
}
