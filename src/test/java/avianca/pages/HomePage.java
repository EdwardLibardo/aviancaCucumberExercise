package avianca.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(css = ".airport_ida[data-msg='Selecciona un origen']")
    private WebElement fromCity;

    @FindBy(css = ".airport_regreso[data-msg='Selecciona un destino']")
    private WebElement toCity;

    @FindBy(css = ".new-container-flexible .errorIntegradas .material-icons")
    private WebElement calendarButton;

    @FindBy(css = ".boton-container .pull-btn")
    private WebElement searchFlightsButton;

    @FindBy(css=".icon-menu")
    private WebElement iconMenu;

    @FindBy(css = "[data-space='ida_regreso']")
    private WebElement calendar;

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public HomePage fromCity(String originCity) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(fromCity)).clear();
        driver.manage().window().maximize();
        fromCity.sendKeys(originCity);
        fromCity.sendKeys(Keys.DOWN);
        fromCity.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage toCity(String destinationCity) {
        toCity.sendKeys(destinationCity);
        toCity.sendKeys(Keys.DOWN);
        toCity.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage sinceDateAndUntilDate(String sinceDateAttribute, String untilDateAttribute) throws InterruptedException {
        String dateStr = "//div[@class='number-days']/table/tbody/tr/td[@aria-label='%1']";
        String sinceDate =  dateStr.replace("%1", sinceDateAttribute);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(calendarButton));
        calendarButton.click();
        driver.findElement(By.xpath(sinceDate)).click();
        String untilDate = dateStr.replace("%1", untilDateAttribute);
        driver.findElement(By.xpath(untilDate)).click();
        return this;
    }

    public PricesPage bookingFlight(){
        searchFlightsButton.click();
        return new PricesPage(driver);
    }

    public MenuPage clickIconMenu(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(fromCity)).clear();
        iconMenu.click();
        return new MenuPage(driver);
    }
}
