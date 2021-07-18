import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;


public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver getDriver(String browserName) {
        switch (browserName) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions setchrome = new ChromeOptions();
                setchrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                setchrome.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setchrome.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                setchrome.addArguments("--incognito");
                setchrome.addArguments("--start-fullscreen");
                logger.info("Драйвер для браузера Google Chrome");
                return new ChromeDriver(setchrome);

            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions setfirefox = new FirefoxOptions();
                setfirefox.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                setfirefox.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setfirefox.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                setfirefox.addArguments("--private");

                logger.info("Драйвер для браузера Mozilla Firefox");
                return new FirefoxDriver(setfirefox);

            default:
                throw new RuntimeException("Убедитесь, что данный браузер у Вас установлен");
        }
    }
}