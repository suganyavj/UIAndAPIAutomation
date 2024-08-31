package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageFiles.webAutomationPage;

public class webAutomationSteps {
    webAutomationPage page = new webAutomationPage();

    @Given("launch the browser")
    public void launch() throws InterruptedException {
        page.launch();
    }
    @When("get the cookie data")
    public void get_cookie_data() throws InterruptedException {
        page.get_cookie_data();
    }

    @And("login to the site")
    public void login() throws InterruptedException {
        page.login();
    }
    @And("get names in list")
    public void getNames(){
        page.getNames();
    }
    @When("get salary in web table")
    public void getSalary() throws InterruptedException {
        page.getSalary();
    }
    @When("get alert")
    public void alerts() throws InterruptedException {
        page.alerts();
    }
    @When("drag and drop")
    public void dragAndDrop() throws InterruptedException {
        page.dragAndDrop();
    }
}
