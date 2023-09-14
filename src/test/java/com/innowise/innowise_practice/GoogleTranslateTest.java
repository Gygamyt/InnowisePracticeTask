package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.GooglePageObject;
import com.innowise.innowise_practice.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class GoogleTranslateTest extends BaseTest {

    GooglePageObject googlePageObject = new GooglePageObject(Driver.getDriver());

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
