import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.Cookie;
import java.util.Set;

public class TwoTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TwoTest.class);

   String env = System.getProperty("browser", "chrome");

    @BeforeEach
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.getDriver(env.toLowerCase());
        logger.info("Драйвер стартовал!");
    }

    @Test
    public void openPage1() {
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.get("https://www.dns-shop.ru/");
        logger.info("Открыта страница DNS - " + "https://www.dns-shop.ru/");
        driver.manage().window().fullscreen();

        String title = driver.getTitle();
        logger.info("title - " + title);

        String currentUrl = driver.getCurrentUrl();
        logger.info("current URL - " + currentUrl);

        WebElement link2 = driver.findElement(By.xpath("//a[contains(text(), \"Да\")]"));
        link2.click();
        logger.info("Нажата кнопка \"Да\"");

        WebElement link1 = driver.findElement(By.xpath("//a[contains(text(), \"Бытовая техника\")]"));
        link1.click();
        logger.info("Нажата ссылка \"Бытовая техника\"");

        WebElement link3 = driver.findElement(By.xpath(".//input[@id='Техника для кухни']"));
        logger.info("WebElement:" + link3.getAttribute("id"));

        WebElement link4 = driver.findElement(By.xpath(".//input[@id='Техника для дома']"));
        logger.info("WebElement:" + link4.getAttribute("id"));

        WebElement link5 = driver.findElement(By.xpath(".//input[@id='Красота и здоровье']"));
        logger.info("WebElement:" + link5.getAttribute("id"));

        // Задержка sleep
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод логов браузера
        logger.info("Логи браузера");
        Logs logs = driver.manage().logs();
        LogEntries logEntries = logs.get(LogType.BROWSER);
        for (LogEntry logEntry : logEntries) {
            logger.info(String.format("%s", logEntry.getMessage()));
        }
        logger.info("--------------------------------------");

        // Создание куки Cookie 1 и вывод информации по нему
        logger.info("Куки, которое добавили мы");
        driver.manage().addCookie(new Cookie("Cookie 1", "This Is Cookie 1"));
        Cookie cookie1  = driver.manage().getCookieNamed("Cookie 1");
        logger.info(String.format("Domain: %s", cookie1.getDomain()));
        logger.info(String.format("Expiry: %s",cookie1.getExpiry()));
        logger.info(String.format("Name: %s",cookie1.getName()));
        logger.info(String.format("Path: %s",cookie1.getPath()));
        logger.info(String.format("Value: %s",cookie1.getValue()));
        logger.info("--------------------------------------");

        // Вывод информации по кукам dns
        logger.info("Куки, которое добавил ДНС");
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie cookie : cookies) {
            logger.info(String.format("Domain: %s", cookie.getDomain()));
            logger.info(String.format("Expiry: %s", cookie.getExpiry()));
            logger.info(String.format("Name: %s", cookie.getName()));
            logger.info(String.format("Path: %s", cookie.getPath()));
            logger.info(String.format("Value: %s", cookie.getValue()));
            logger.info("--------------------------------------");
        }
    }

    @AfterEach
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}