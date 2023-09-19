package com.innowise.innowise_practice.pageobjects.relax_by_page_objects;

import com.innowise.innowise_practice.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static com.innowise.innowise_practice.driver.Driver.getDriver;

public class LunabarPageObject extends BasePage {
    public LunabarPageObject(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Телефоны']")
    private WebElement phoneNumbersButton;

    @FindBy(xpath = "//div[@class=\"TouchIcon Popup__close\"]")
    private WebElement closeButton;

    @FindBy(xpath = "//button[contains(@class, 'PersonalContacts__address')]")
    private WebElement addressElement;

    @FindBy(xpath = "//button[contains(@class, 'PersonalContacts__worktime')]")
    private WebElement workTimeButton;

    private By phoneNumberElements = By.xpath("//span[@class=\"PhoneLink__number\"]");

    private By workHoursElements = By.xpath("//div[contains(@class, 'ContactsPopupOpening__main')]");

    public String getCurrentAddress() {
        return getElementText(addressElement);
    }

    public LunabarPageObject clickPhoneNumbersButton() {
        clickElement(phoneNumbersButton);
        return this;
    }
    public ArrayList<String> getAllPhoneNumbers() {
        waiter.until(ExpectedConditions.visibilityOf(getDriver().findElement(phoneNumberElements)));
        List<WebElement> webElementsWithNumbers = getDriver().findElements(phoneNumberElements);
        ArrayList<String> resultNumbersArray = new ArrayList<>();
        webElementsWithNumbers.forEach(webElement -> resultNumbersArray.add(webElement.getText()));
        return resultNumbersArray;
    }

    public LunabarPageObject clickOnCloseButton() {
        clickElement(closeButton);
        return this;
    }

    public ArrayList<String> getCurrentWorkHours() {
        waitUntilElementVisible(workHoursElements);
        List<WebElement> webElementsWithWorkHours = getDriver().findElements(workHoursElements);
        ArrayList<String> resultHoursArray = new ArrayList<>();
        webElementsWithWorkHours.forEach(webElement -> resultHoursArray.add(webElement.getText()));
        return resultHoursArray;
    }

    public LunabarPageObject clickOnWorkHoursButton() {
        clickElement(workTimeButton);
        return this;
    }
}
