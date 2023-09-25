package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.google_page_objects.GooglePageObject;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoogleTranslateTest extends BaseTest {

    private final GooglePageObject googlePageObject = new GooglePageObject(Driver.getDriver());

    private static final String textForTranslate = "Test";

    @Test
    public void googleTranslateTest() {
        Driver.getDriver().get(LinksForTestsEnum.GOOGLE_TRANSLATE.getLink());

        logger().info("site is opened");

        googlePageObject
                .clickLeftEnglishButton()
                .clickExpandAllLanguagesButtonRight()
                .clickRussianLanguageRightButtonWhenExpanded()
                .clickOnFieldAndEnterText(textForTranslate);

        logger().info("start of assertions");

        Assertions.assertAll(
                () -> Assertions.assertEquals("Тест", googlePageObject.getResultOfTranslate()),
                () -> Assertions.assertTrue(googlePageObject.isLeftEnglishButtonVisibleCheck()),
                () -> Assertions.assertTrue(googlePageObject.isExpandAllLanguagesButtonRightVisible()),
                () -> Assertions.assertFalse(googlePageObject.isRussianLanguageRightButtonWhenExpandedVisible())
        );

        logger().info("assertions ended");
    }
}
