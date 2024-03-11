package webdriver.V3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Fix_Popup {

    WebDriver driver;
    Actions action;


    @BeforeClass
    public void initialBrowser(){
        driver = new ChromeDriver();

        action = new Actions(driver);
        driver.get("https://ngoaingu24h.vn/");
    }


    @Test
    public void TC_01_NgoaiNgu24h_By(){
        driver.get("https://ngoaingu24h.vn/");
        By loginPopupBy = By.cssSelector("div#modal-login-v1");
        //verify login popup is not displayed
        Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());

        driver.findElement(By.cssSelector("button.login_")).click();

        Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());

        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tai khoan nay khong ton tai");

        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();

        Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
     }

    @Test
    public void TC_02_NgoaiNgu24h_Webelement() {
        driver.get("https://ngoaingu24h.vn/");
        WebElement loginPopupBy = driver.findElement(By.cssSelector("div#modal-login-v1"));
        //verify login popup is not displayed
        Assert.assertFalse(loginPopupBy.isDisplayed());

        driver.findElement(By.cssSelector("button.login_")).click();

        Assert.assertTrue(loginPopupBy.isDisplayed());

        driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationtest");
        driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationtest");
        driver.findElement(By.cssSelector("button.btn-login-v1")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tai khoan nay khong ton tai");

        driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();

        Assert.assertFalse(loginPopupBy.isDisplayed());
    }

    //neu 1 cai element thao tac trong nhieu ngu canh khac nhau thi ko dung Webelement ma dung By
    //Webelement la da tim roi : find 1 lan
    //By chi la String no se de cac ngu canh tu tim find element
    // => Neu dung By: element nay dong/ co the bi refresh lai sau cac step tiep theo(cung 1 trang)
    // => Neu dung Webelement: Element nay luon luon co dinh/ ko bi thay doi status/ ko bi update lai HTML
    @AfterClass
    public void cleanBrowser()
    {
        driver.quit();
    }

}
