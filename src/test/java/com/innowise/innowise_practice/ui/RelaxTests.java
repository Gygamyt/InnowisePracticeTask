package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.pageobjects.relax_by_page_objects.RelaxHeader;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.innowise.innowise_practice.ui.driver.Driver.*;

@Disabled
public class RelaxTests extends BaseTest {

    private final RelaxHeader relaxHeader = new RelaxHeader(getDriver(), getWaiter(), getActions());

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

        var resultPage = relaxHeader
                .openLunabarPage();

        ArrayList<String> resultNumbers = resultPage
                .clickPhoneNumbersButton()
                .getAllPhoneNumbers();

        String currentAddress = resultPage
                .clickOnCloseButton()
                .getCurrentAddress();

        ArrayList<String> resultHours = resultPage
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

    @Override
    @BeforeEach
    public void openPage() {
        openLink(LinksForTestsEnum.RELAX_BY.getLink());
    }
}
