package avianca.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricesPage extends BasePage{

    @FindBy(xpath="//span[contains(text(),'Salida de')]")
    private WebElement resumeLabel;

    public PricesPage(WebDriver driver) {
        super(driver);
    }

    public void resumeFlight(String origenCity, String destinyCity){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(resumeLabel));
        Assert.assertTrue(resumeLabel.getText().contains(origenCity) );
        Assert.assertTrue(resumeLabel.getText().contains(destinyCity) );
    }
}
