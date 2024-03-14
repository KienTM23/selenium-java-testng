package webdriver.V3;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

public class Topic_20_PVII_Fluent_Wait {

    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    private long sunTime = 30;
    private long pollTime = 1;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        //find element vs tong thoi gian la 15s
        //tan so lap lai de tim neu ko thay la 1s/1 lan
        fluentDriver = new FluentWait<>(driver);
        //tong thoi gian cho cho dieu kien
        fluentDriver.withTimeout(Duration.ofSeconds(15))
                //tan so moi 1s check 1 lan
                .pollingEvery(Duration.ofSeconds(1))
                //neu gap exception la find ko thay element se bo qua
                .ignoring(NoSuchElementException.class)
                //dieu kien cua fluent wait
                .until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        return driver.findElement(By.xpath("//input[@name='btnI-fail']"));
                    }
                });
        ///withTimeout <=> sum time
        //pollingEvery <=> thoi gian tim lai : polling time
        //
        WebElement loginButton = driver.findElement(By.xpath(""));
        fluentElement = new FluentWait<WebElement>(loginButton);

        fluentElement.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class);
        //apply dieu kien va tra ve du lieu
        String loginButtonText = fluentElement.until(new Function<WebElement, String>() {
            @Override
            public String apply(WebElement element) {
                return element.getText();
            }
        });
        //new Function<WebElement, Boolean>() => ko nhan kieu du lieu nguyen thuy
        Boolean loginButtonStatus = fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.isDisplayed();
            }
        });
        //du lieu de dung cho 1 step khac
        Assert.assertEquals(loginButtonText, "");
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        fluentDriver = new FluentWait<WebDriver>(driver);

        driver.findElement(By.cssSelector("div#start>button")).click();
        //sau khi bam loading icon xuat hien va mat di sau 5s
        //icon loading bien mat = helloworld text xuat hien
        fluentDriver.withTimeout(Duration.ofSeconds(7))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver webDriver) {
                        return driver.findElement(By.cssSelector("div#finish>h4")).getText();
                    }
                });
    }


    public WebElement findElement(By locator){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sunTime))
                .pollingEvery(Duration.ofSeconds(pollTime))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(locator);
            }
        });
        return element;
    }

    public void clickToElement(By locator){
        WebElement element = findElement(locator);
        element.click();
    }
    public boolean isElementDisplayed(By locator){
        WebElement element= findElement(locator);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(sunTime))
                .pollingEvery(Duration.ofSeconds(pollTime))
                .ignoring(NoSuchElementException.class);
        boolean isDisplayed = wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                boolean flag = element.isDisplayed();
                return flag;
            }
        });
        return isDisplayed;
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public String getTimeNow() {
        Date date = new Date();
        return date.toString();
    }
}
