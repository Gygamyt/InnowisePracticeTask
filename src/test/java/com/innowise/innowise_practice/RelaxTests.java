package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.relax_by_page_objects.LunabarPageObject;
import com.innowise.innowise_practice.pageobjects.relax_by_page_objects.RelaxHeader;
import com.innowise.innowise_practice.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.innowise.innowise_practice.driver.Driver.openLink;

public class RelaxTests extends BaseTest {

    private final WebDriver driver = Driver.getDriver();

    private final RelaxHeader relaxHeader = new RelaxHeader(driver);

    private final LunabarPageObject lunabarPageObject = new LunabarPageObject(driver);

    @Test
    public void lunabarTest() {
        final String expectedAddress = "Минск, Я. Купалы, 25";
        final ArrayList<String> expectedNumbers = new ArrayList<>() {{
            add("+375 33 333-33-29");
            add("+375 33 333-33-49");
        }};
        final ArrayList<String> expectedWorkHours = new ArrayList<>() {{
            add("Выходной");
            add("Выходной");
            add("""
                    12:00—00:00
                    Клуб
                    12:00—00:00
                    Ресторан""");
            add("""
                    12:00—00:00
                    Клуб
                    12:00—00:00
                    Ресторан""");
            add("""
                    12:00—06:00
                    Клуб
                    12:00—06:00
                    Ресторан""");
            add("""
                    12:00—06:00
                    Клуб
                    12:00—06:00
                    Ресторан""");
            add("Выходной");
        }};

        openLink(LinksForTestsEnum.RELAX_BY.getLink());

        ArrayList<String> resultNumbers = relaxHeader
                .openLunabarPage()
                .clickPhoneNumbersButton()
                .getAllPhoneNumbers();

        String currentAddress = lunabarPageObject
                .clickOnCloseButton()
                .getCurrentAddress();

        ArrayList<String> resultHours = lunabarPageObject
                .clickOnWorkHoursButton()
                .getCurrentWorkHours();

        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(expectedNumbers, resultNumbers),
                () -> Assertions.assertEquals(expectedAddress, currentAddress),
                () -> Assertions.assertIterableEquals(expectedWorkHours, resultHours)
        );
    }

    @Test
    public void restaurantsTest() {
        openLink(LinksForTestsEnum.RELAX_BY.getLink());
        List<WebElement> selectedElements = relaxHeader
                .clickFoodButton()
                .clickRestaurantButton()
                .openFilters()
                .applyDistrictFilter()
                .applyKitchenVariant()
                .applyTakeAwayFilter()
                .applyTakeAwayVariant()
                .takeAllSelectedElements();
    }


}
