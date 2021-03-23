package avianca.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class ConsultingTimePage extends BasePage{

    @FindBy(xpath = "//h1[contains(text(),'Consulta de itinerarios')]")
    private WebElement title;

    @FindBy(id = "mainContent_GrillaItinerariosIda_GridVuelos_LinkSortHoraSale_0")
    private WebElement sortByTime;

    public ConsultingTimePage(WebDriver driver) {
        super(driver);
    }

    public void sortFlights(){
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        //getDriver().close();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(title));
        sortByTime.click();
    }

    public void verifyOrder(){
        String time1= driver.findElement(By.xpath("//tr[@id='ctl00_mainContent_GrillaItinerariosIda_GridVuelos_ctl10']/td[1]/span[2]")).getText();
        String value2 = driver.findElement(By.xpath("//tr[@id='ctl00_mainContent_GrillaItinerariosIda_GridVuelos_ctl10']/td[2]/span")).getText();
        String[] firstFlight = time1.split(":");
        int hourFirstTime = Integer.parseInt(firstFlight[0]);
        String[] secondFlight = value2.split(":");
        int hourSecondTime = Integer.parseInt(secondFlight[0]);
        Assert.assertTrue(hourFirstTime>hourSecondTime);
    }
}
