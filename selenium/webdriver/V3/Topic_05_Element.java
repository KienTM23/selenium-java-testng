package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_05_Element {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01() {
        //các hàm / câu lệnh(command):
        // tuương tac vs browser -> driver

//        WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
//        emailTextbox.clear();
//        emailTextbox.sendKeys("kientm@gmail.com");
//        emailTextbox.getCssValue("background-color");
        WebElement element = driver.findElement(By.xpath(""));
        //xoa du lieu trong textbox/ textarea/ editable dropdown (cho phep edit/nhap lieu)
        element.clear(); //*
        //nhap du lieu trong textbox/ textarea/ editable dropdown (cho phep edit/nhap lieu)
        element.sendKeys(""); //**

        element.click(); //**


        element.getAttribute("placeholder"); //*
        //ko nam trong attribute thi dung gettext()
        element.getText(); //**
        //test GUI
        element.getCssValue(""); //*
        //rat it use
        element.getLocation();
        element.getRect();
        element.getSize();
        //chup hinh cua element bang format nao do
        element.getScreenshotAs(OutputType.BASE64);//*
        //lay ra ten the cua element, khi lay locator ko co ten the
        // dau ra cua step nay là dau vào cuủa step kia
        WebElement loginButton = driver.findElement(By.name("login"));
        String loginButtonTagname = loginButton.getTagName();

        loginButton = driver.findElement(By.xpath("//"+loginButtonTagname+"[@id='loginBtn']"));
        element.getTagName();

        //kiem tra 1 element co hien thi hay ko
        //mong doi no hien thi
        element.isDisplayed();
        Assert.assertTrue(element.isDisplayed());//**
        //mong doi no ko hien thi
        Assert.assertFalse(element.isDisplayed());
        //kiem tra element thao tac dc ko
        //read-only/disable
        element.isEnabled();
        Assert.assertFalse(element.isEnabled());//*
        //kiem tra 1 element da dc chon hay chua
        //radio/checkbox/dropdown
        element.isSelected();//*

        //
        element.submit();
    }

    @Test
    public void TC_02() {
    }

    public void afterClass() {
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
