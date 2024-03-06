package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Run_On_Multiple_Browser {
    WebDriver webDriver;
    String projectPath = System.getProperty("user.dir");

    @Test
    public void TC_01_Firefox(){
        //Executable File: chromedriver /geckodriver...
        System.setProperty("webdriver.gecko.driver",projectPath + "\\browserDrivers\\geckodriver.exe");

        //class: firefoxdriver,chromeDriver,...
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.facebook.com/login/");
        webDriver.quit();
    }
    @Test
    public void TC_02_Chrome(){
        //Executable File: chromedriver /geckodriver...
        System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");

        //class: firefoxdriver,chromeDriver,...
        webDriver = new ChromeDriver();
        webDriver.get("https://www.facebook.com/login/");
        webDriver.quit();
    }
}
