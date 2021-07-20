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

    public static WebDriver getDriver(String browserName, String PageLoadStrategyName) {
        switch (browserName) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions setchrome = new ChromeOptions();
                setchrome.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setchrome.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
                setchrome.addArguments("--incognito");
                setchrome.addArguments("--start-fullscreen");
                switch (PageLoadStrategyName) {
                    case "normal":
                        setchrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                        break;
                    case "none":
                        setchrome.setPageLoadStrategy(PageLoadStrategy.NONE);
                        break;
                    case "eager":
                        setchrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        break;
                }
                logger.info("Драйвер для браузера Google Chrome");
                return new ChromeDriver(setchrome);
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions setfirefox = new FirefoxOptions();
                setfirefox.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setfirefox.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
                setfirefox.addArguments("--private");
                switch (PageLoadStrategyName) {
                    case "normal":
                        setfirefox.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                        break;
                    case "none":
                        setfirefox.setPageLoadStrategy(PageLoadStrategy.NONE);
                        break;
                    case "eager":
                        setfirefox.setPageLoadStrategy(PageLoadStrategy.EAGER);
                        break;
                }
                logger.info("Драйвер для браузера Mozilla Firefox");
                return new FirefoxDriver(setfirefox);

            default:
                throw new RuntimeException("Некорректный ввод");
        }
    }
}