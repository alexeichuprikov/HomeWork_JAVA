import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
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
                // Добавление свойств браузера Google Chrome (настройки сессии)
                // с помощью класса **ChromeOptions** (правильный способ) и констант перечисления CapabilityType
                ChromeOptions setchrome = new ChromeOptions();
                setchrome.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
                setchrome.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setchrome.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
                setchrome.setAcceptInsecureCerts(false);
                setchrome.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                // Добавление аргументов запуска Google Chrome
                setchrome.addArguments("--start-maximized");
                setchrome.addArguments("--incognito");

                logger.info("Драйвер для браузера Google Chrome");
                return new ChromeDriver(setchrome);

            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions setfirefox = new FirefoxOptions();
                setfirefox.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
                setfirefox.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                setfirefox.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
                setfirefox.setAcceptInsecureCerts(false);
                setfirefox.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                // Добавление аргументов запуска Google Chrome
                setfirefox.addArguments("--start-maximized");
                setfirefox.addArguments("--incognito");

                logger.info("Драйвер для браузера Mozilla Firefox");
                return new FirefoxDriver(setfirefox);

            default:
                throw new RuntimeException("Убедитесь, что данный браузер у Вас установлен");
        }
    }
}