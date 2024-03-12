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

import java.util.Set;

public class Topic_15_Javascript_Executor {

    WebDriver driver;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_() {
        //code JS define element:
//        var subcribleButton = $$("button[id='send2']")[0];
//        subcribleButton.click();
        //code selenium:
//        driver.findElement(By.cssSelector("button[id='send2']")).click();
        navigateToUrlByJS("http://live.techpanda.org/");
//        executeForBrowser("window.location = 'http://live.techpanda.org/'");
        String homePageDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(homePageDomain,"live.techpanda.org");

        String homePageUrl = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(homePageUrl,"http://live.techpanda.org/");

        clickToElementByJS("//a[text()='Mobile']");
        sleep(5);

        String shoppingCartText = getInnerText();
        Assert.assertTrue(shoppingCartText.contains("Iphone was added to cart"));

        String customerPageTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(customerPageTitle,"Customer Service");

        scrollToElementOnDown("//input[@id='newsletter']");
    }

    @Test
    public void TC_02_() {
        driver.get("");
        By firstName = By.id("user_first_name");
        By email = By.id("email");
        By createButton = By.xpath("//button[contains(text(),'Tao tai khoan')]");
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

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleep(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleep(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleep(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
