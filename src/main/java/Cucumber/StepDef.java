package Cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.io.IOException;

import static Code.DailyStatusReport.*;

public class StepDef {

    DSR_Methods dsr = new DSR_Methods();

    @Given("user drafts the dsr mail and send the email")
    public void draftEmail() throws IOException {
//        SendDsrFromOutlook();
    }

    @When("user access the web form")
    public void userAccessTheWebform() {
        dsr.GetWebForm();
    }

    @Then("check whether user have multiple emailId's")
    public void checkWhetherUserHaveMultipleEmailIdS() throws InterruptedException, IOException {
        dsr.CheckMultipleEmail(ExtractFromTextFile("email"));
    }

    @And("user click on Start now")
    public void userClickOnStartNow() {
        dsr.StartNow();
    }

    @Then("user select the current data")
    public void userSelectTheCurrentData() throws InterruptedException {
        Thread.sleep(2000);
        dsr.driver.findElement(By.xpath("//input[@id='DatePicker0-label']")).sendKeys(dsr.DateFormatter("MM/dd/yyyy"));
    }

    @Then("user enter the EmployeeID")
    public void userEnterTheEmployeeID() {
        dsr.Fill("Employee ID",
                dsr.ExtractFromDsrPropertyFile("EmployeeID"));
    }

    @Then("user enter the In-Time")
    public void userEnterTheInTime() {
        dsr.Fill("In-Time",
                dsr.ExtractFromDsrPropertyFile("In-Time"));
    }

    @Then("user enter the Out-Time")
    public void userEnterTheOutTime() {
        dsr.Fill("Out-Time", dsr.ExtractFromDsrPropertyFile("Out-Time"));
    }

    @Then("user enter note")
    public void userEnterNote() {
        dsr.Fill("Note", dsr.ExtractFromDsrPropertyFile("Note"));
    }

    @Then("user select the check box of Working")
    public void userSelectTheCheckBoxOfWorking() {
        dsr.SelectWorkingDay(dsr.ExtractFromDsrPropertyFile("Work/Leave/Holiday"));
    }

    @And("user select on Next")
    public void userSelectOnNext() {
        dsr.MoveToElement(dsr.findElement_By(By.xpath("//button[text()='Next']")));
        dsr.ClickElement(dsr.findElement_By(By.xpath("//button[text()='Next']")));
    }

    @When("user select the project")
    public void userSelectTheProject() throws InterruptedException {
        Thread.sleep(1000);
        dsr.FillDropDown("Project Name", dsr.ExtractFromDsrPropertyFile("ProjectName"));
    }

    @Then("user select Full time or Support")
    public void userSelectFullTimeOrSupport() {
        dsr.FillDropDown("Full-Time", dsr.ExtractFromDsrPropertyFile("FullTime/Support"));
    }

    @Then("user enter the number of hours assigned")
    public void userEnterTheNumberOfHoursAssigned() {
        dsr.FillDropDown("Hours Assigned", "8");
    }

    @Then("user enter the number of hours worked")
    public void userEnterTheNumberOfHoursWorked() throws InterruptedException {
        dsr.findElement_By(By.xpath("//span[contains(text(),'Hours Worked')]/../../../../../div//div[@aria-haspopup='listbox']")).click();
        Thread.sleep(1500);
        if(dsr.ExtractFromDsrPropertyFile("HoursWorked").equalsIgnoreCase("8")){
            dsr.findElement_By(By.xpath("(//span[text()='8'])[2]")).click();
        } else  dsr.findElement_By(By.xpath("(//span[text()='4'])[1]")).click();

    }

    @Then("user enter the Task accomplished today")
    public void userEnterTheTaskAccomplishedToday() throws IOException {
        String tempToday = "";
        for (int i = 0; i < dsr.getTodayTask("WhatIDidToday").size(); i++) {
            tempToday = tempToday + dsr.getTodayTask("WhatIDidToday").get(i) + "\n";
        }
        dsr.FillTextArea("Task 1", tempToday);
    }

    @Then("user select the check box of status")
    public void userSelectTheCheckBoxOfStatus() {
        dsr.SelectStatus("Task 1 Status", dsr.ExtractFromDsrPropertyFile("Status"));
    }

    @Then("user enter the tomarrow task")
    public void userEnterTheTomarrowTask() throws IOException {
        String tempTomarrow = "";
        for (int i = 0; i < dsr.getTomarrowTask("WhatIPlannedForTomorrow").size(); i++) {
            tempTomarrow = tempTomarrow + dsr.getTomarrowTask("WhatIPlannedForTomorrow").get(i) + "\n";
        }
        dsr.FillTextArea("Tasks for tomorrow", tempTomarrow);
    }


    @Then("user select the check box of send a copy to email")
    public void userSelectTheCheckBoxOfSendACopyToEmail() {
        dsr.findElement_By(By.xpath("//input[@type='checkbox']")).click();
    }

    @And("user submit the web form")
    public void userSubmitTheWebForm() {
        dsr.findElement_By(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("user fills the details of project")
    public void userFillsTheDetailsOfProject() {
        if (dsr.ExtractFromDsrPropertyFile("MultipleProject").equalsIgnoreCase("Yes")){
//            will work on scripting if any one is required.
        }else System.out.println("Working on Single project !!!");
    }
}
