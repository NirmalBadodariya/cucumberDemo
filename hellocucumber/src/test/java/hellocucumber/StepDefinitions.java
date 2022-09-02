package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

class IsItFriday {
    static String isItFriday(String today) {
        return null;
    }
}

public class StepDefinitions {
    private String today;
    private String actualAnswer;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }
    @Given("today is Friday")
    public void today_is_Friday() {
        today = "Friday";
    }
//    static String isItFriday(String today) {
//        return "Nope";
//    }

    @Then("I should be told {string}")
    public String i_should_be_told(String expectedAnswer) {
        return "Nope";
    }
    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }


}