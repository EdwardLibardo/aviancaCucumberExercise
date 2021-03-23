package avianca.steps;

import avianca.helpers.RunnerHelper;
import avianca.pages.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class aviancaSteps {
    HomePage homePage;
    PricesPage pricesPage;
    MenuPage menuPage;
    FlightSchedulePage flightSchedulePage;
    ConsultingTimePage consultingTimePage;
    String url = "https://www.avianca.com/co/es/?gclid=EAIaIQobChMI5fukj7PC7wIVBjKGCh3ezgUKEAAYASAAEgJmn_D_BwE&gclsrc=aw.ds";

    RunnerHelper runner = new RunnerHelper();

    @Given("the user is in the home page to book a flight")
    public void theUserIsInTheHomePageToBookAFlight() {
        homePage = new HomePage(runner.setUp(url));
    }

    @When("^user add the \"([^\"]*)\" as origen and \"([^\"]*)\" as destination$")
    public void userAddTheAsOrigenAndAsDestination(String origen, String destination) {
        homePage.fromCity(origen);
        System.setProperty("origen", origen);
        System.setProperty("destination", destination);
        homePage.toCity(destination);
    }

    @And("^user takes flight since \"([^\"]*)\" until \"([^\"]*)\"$")
    public void userTakesFlightSinceUntil(String sinceDateAttribute, String untilDateAttribute) throws InterruptedException {
        homePage.sinceDateAndUntilDate(sinceDateAttribute, untilDateAttribute);
    }

    @Then("^user confirms the flight$")
    public void userConfirmsTheFlight() {
        pricesPage = homePage.bookingFlight();
        pricesPage.resumeFlight(System.getProperty("origen"), System.getProperty("destination"));
        runner.tearDown();
    }

    @Given("^the user in the menu options$")
    public void theUserInTheMenuOptions() {
        homePage = new HomePage(runner.setUp(url));
        menuPage = homePage.clickIconMenu();
        flightSchedulePage = menuPage.clickOnFlightSchedules();
    }

    @And("^user adds the \"([^\"]*)\" as origen and \"([^\"]*)\" as destination from menu options$")
    public void userAddsTheAsOrigenAndAsDestinationFromMenuOptions(String originCity, String destinationCity) {
        flightSchedulePage.selectFromCity(originCity);
        flightSchedulePage.selectToCity(destinationCity);
    }

    @When("^user books the flight since \"([^\"]*)\" until \"([^\"]*)\"$")
    public void userBooksTheFlightSinceUntil(String sinceDate, String untilDate) throws InterruptedException {
        flightSchedulePage.selectDates(sinceDate,untilDate);
        consultingTimePage = flightSchedulePage.clickOnSearch();
    }

    @Then("^user can sort the flights by time$")
    public void userCanSortTheFlightsByTime() {
        consultingTimePage.sortFlights();
        consultingTimePage.verifyOrder();
        runner.tearDown();
    }
}
