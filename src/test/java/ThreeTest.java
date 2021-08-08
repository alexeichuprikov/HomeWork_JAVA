import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WindowType;

public class ThreeTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(ThreeTest.class);

    String browser = System.getProperty("browser", "chrome").toLowerCase();
    String option = System.getProperty("option", "normal").toLowerCase();
    String link1Xpath = "//a[contains(text(), \"Да\")]";
    String link2Xpath = "(//a[contains(text(), \"Смартфоны и гаджеты\")])";
    String link6Xpath = "//span[contains(text(), \"Объем оперативной памяти\")]";


    @BeforeEach
    public void setUp() {
        logger.info("Переданные параметры запуска:");
        logger.info("- браузер: " + browser);
        logger.info("- стратегия загрузки страницы: " + option);
        driver = WebDriverFactory.getDriver(browser, option);
        logger.info("Драйвер стартовал!");
    }

    @Test
    public void openPage2() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1000));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        //1. Открыть страницу DNS
        //1.1. Открытие страницы DNS
        driver.get("https://www.dns-shop.ru/");
        logger.info("Открыта страница DNS - " + "https://www.dns-shop.ru/");

        //1.2. Закрытие всплывающего окна
        WebElement link1 = driver.findElement(By.xpath(link1Xpath));
        link1.click();
        logger.info("Нажата кнопка \"Да\"");

        //2. Нажать ссылку Смартфоны и гаджеты/Смарфтоны
        //2.1. Наведение на элемент "Смартфоны и гаджеты"
        WebElement link2 = driver.findElement(By.xpath(link2Xpath));
        Actions actions = new Actions(driver);
        actions
                .moveToElement(link2)
                .perform();
        logger.info("Наведение на элемент Смартфоны и гаджеты");

        //2.1 Переход по ссылке "Смартфоны" с использованием явного ожидания
        By link3Xpath = By.xpath("(//a[text()='Смартфоны'])");
        wait.until(ExpectedConditions.presenceOfElementLocated(link3Xpath));
        WebElement link3 = driver.findElement(link3Xpath);
        Actions actions1 = new Actions(driver);
        actions1
                .moveToElement(link3)
                .click()
                .perform();
        logger.info("Переход по сылке Смартфоны");

        //3. Создание скриншота
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage linkYesImage = ImageIO.read(file);
            ImageIO.write(linkYesImage, "png", new File("temp\\SmartphoneScreen.png"));
            logger.info("Скриншот сохранен в файле [temp\\SmartphoneScreen.png]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script1 = "window.scrollBy(0,1100);";
        js.executeScript(script1);
        logger.info("Проскроллено вниз на 1100px");

        //4. Выбрать значение в фильтре производитель: Samsung
        By link4Xpath = By.xpath("//span[contains(text(), \"Samsung\")]");
        wait.until(ExpectedConditions.presenceOfElementLocated(link4Xpath));
        WebElement link4 = driver.findElement(link4Xpath);
        Actions actions2 = new Actions(driver);
        actions2
                .moveToElement(link4)
                .click()
                .perform();

        //5. Выбрать значение в фильтре Объем оперативной памяти: 8 ГБ
        WebElement link6 = driver.findElement(By.xpath(link6Xpath));
        link6.click();

        By link5Xpath = By.xpath("//span[contains(text(), \"8 Гб\")]");
        wait.until(ExpectedConditions.presenceOfElementLocated(link5Xpath));
        WebElement link5 = driver.findElement(link5Xpath);
        Actions actions3 = new Actions(driver);
        actions3
                .moveToElement(link5)
                .click()
                .perform();


        String script2 = "window.scrollBy(0,300);";
        js.executeScript(script2);

        //6. Нажать кнопку Применить
        By link7Xpath = By.xpath("//button[contains(text(), \"Применить\")]");
        wait.until(ExpectedConditions.presenceOfElementLocated(link7Xpath));
        WebElement link7 = driver.findElement(link7Xpath);
        Actions actions4 = new Actions(driver);
        actions4
                .moveToElement(link7)
                .click()
                .perform();

          //7. Применить сортировку: Сначала дорогие
        By link8Xpath = By.xpath("//span[contains(text(), \"Сортировка\")]");
        wait.until(ExpectedConditions.presenceOfElementLocated(link8Xpath));
        WebElement link8 = driver.findElement(link8Xpath);
        Actions actions5 = new Actions(driver);
        actions5
                .moveToElement(link8)
                .click()
                .perform();
       // By link9Xpath = By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Сначала недорогие'])[2]/following::span[1]");
        By link9Xpath = By.xpath("//span[contains(text(), \"Сначала дорогие\")]");
        wait.until(ExpectedConditions.presenceOfElementLocated(link9Xpath));
        WebElement link9 = driver.findElement(link9Xpath);
        link9.click();

        //8. Сделать скриншот страницы
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage linkYesImage = ImageIO.read(file);
            ImageIO.write(linkYesImage, "png", new File("temp\\SmartphoneSortScreen.png"));
            logger.info("Скриншот сохранен в файле [temp\\SmartphoneSortScreen.png]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //9. Выбрать и перейти на страницу первого продукта в списке: НОВОЕ ОКНО+МАКСИМИЗМРОВАННЫЙ РЕЖИМ
        By link10Xpath = By.xpath  ("(.//*[normalize-space(text()) and normalize-space(.)='✕'])[2]/following::span[1]");
        WebElement link10 = driver.findElement(link10Xpath);
        Actions actions6 = new Actions(driver);
        actions6
                .moveToElement(link10)
                .click()
                .perform();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.dns-shop.ru/product/0650e776c5ac1b80/69-smartfon-samsung-galaxy-note-20-ultra-256-gb-belyj/");

        //10. Сделать скриншот страницы
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage linkYesImage = ImageIO.read(file);
            ImageIO.write(linkYesImage, "png", new File("temp\\NewpageScreen.png"));
            logger.info("Скриншот сохранен в файле [temp\\NewpagrScreen.png]");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //11. Выполнить проверки:
        //11.1 Проверить заголовок страницы
        String title = driver.getTitle();
        logger.info("title - " + title);

        //11.2 Проверить значение ОП в блоке Характеристики
  /*    By link11Xpath = By.xpath("//div[contains(text(), \"8 Гб\")]");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(link11Xpath));
        WebElement link11 = driver.findElement(link11Xpath);
        String attributeValue = link11.getAttribute("name");
        logger.info("Attribute Value: name = " + attributeValue);
        Assertions.assertTrue(attributeValue.equals("name"), "Значение attributeValue != name!");
*/

     //    ("//span[contains(text(),\"Смартфон Samsung Galaxy Note 20 Ultra 256 ГБ белый\")]");
     //   ("(.//*[normalize-space(text()) and normalize-space(.)='✕'])[2]/following::span[1]");


    }

    @AfterEach
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}