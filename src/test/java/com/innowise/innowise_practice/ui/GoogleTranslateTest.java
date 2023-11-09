package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.google_page_objects.GooglePageObject;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.innowise.innowise_practice.ui.driver.Driver.getDriver;

public class GoogleTranslateTest extends BaseTest {

    private final GooglePageObject googlePageObject = new GooglePageObject(getDriver());

    private static final String textForTranslate = "Test";

    private static final String resultOfTranslate = "Тест";

    @Override
    @BeforeEach
    public void openPage() {
        Driver.openLink(LinksForTestsEnum.GOOGLE_TRANSLATE.getLink());
    }

    @Test
    public void googleTranslateTest() {
        googlePageObject
                .clickLeftEnglishButton()
                .clickExpandAllLanguagesButtonRight()
                .clickRussianLanguageRightButtonWhenExpanded()
                .clickOnFieldAndEnterText(textForTranslate);

        logger().info("start of assertions");

        Assertions.assertAll(
                () -> Assertions.assertEquals(resultOfTranslate, googlePageObject.getResultOfTranslate()),
                () -> Assertions.assertTrue(googlePageObject.isLeftEnglishButtonVisibleCheck()),
                () -> Assertions.assertTrue(googlePageObject.isExpandAllLanguagesButtonRightVisible()),
                () -> Assertions.assertFalse(googlePageObject.isRussianLanguageRightButtonWhenExpandedVisible())
        );

        logger().info("assertions ended");
    }
}
