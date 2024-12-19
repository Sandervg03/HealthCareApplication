package com.example.itm

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.itm.ui.screens.PatientSearchScreen.PatientSearchScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PatientSearchScreenAndroidTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun user_is_found_by_bsn() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        rule.setContent { PatientSearchScreen(context, navController).Create() }

        rule.onNodeWithTag("input").performTextInput("123456789")

        rule.onNodeWithTag("search").performClick()

        rule.onNodeWithText("John Doe").assertExists()
    }

    @Test
    fun user_is_not_found_by_bsn() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        rule.setContent { PatientSearchScreen(context, navController).Create() }

        rule.onNodeWithTag("input").performTextInput("987654321")

        rule.onNodeWithTag("search").performClick()

        rule.onNodeWithText("John Doe").assertDoesNotExist()
    }
}