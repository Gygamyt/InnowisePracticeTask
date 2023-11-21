package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.google.GooglePageObject;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.ui.driver.Driver.*;

public class GoogleTranslateTest extends BaseTest {

    GooglePageObject googlePageObject = new GooglePageObject(getDriver(), getWaiter(), getActions());

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
