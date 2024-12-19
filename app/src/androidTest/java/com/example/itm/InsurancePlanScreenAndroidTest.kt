package com.example.itm

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.itm.session.Session
import com.example.itm.ui.screens.InsurancePlanScreen.InsurancePlanScreen
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.data.mapping.UserTestData

@RunWith(AndroidJUnit4::class)
class InsurancePlanScreenAndroidTest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        System.setProperty("TEST_MODE", "true")
    }

    @After
    fun after() {
        System.setProperty("TEST_MODE", "true")
    }

    @Test
    fun insurance_data_found_by_bsn() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        val medicalProfessional = UserTestData.returnMedicalProfessional()
        val patient = UserTestData.returnPatient()

        Session.user = medicalProfessional
        Session.role = medicalProfessional.role
        Session.selectedUser = patient

        rule.setContent { InsurancePlanScreen(context, navController).Create() }

        rule.onNodeWithText("Zilveren kruis").assertExists()
    }

    @Test
    fun insurance_data_not_found_due_to_non_existing_user() {
        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        val medicalProfessional = UserTestData.returnMedicalProfessional()
        val patient = UserTestData.returnPatient()

        Session.user = medicalProfessional
        Session.role = medicalProfessional.role
        Session.selectedUser = medicalProfessional

        rule.setContent { InsurancePlanScreen(context, navController).Create() }

        rule.onNodeWithText("No insurance data found.").assertExists()
    }
}