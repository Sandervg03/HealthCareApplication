package com.example.itm

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.itm.repository.registerRepositories
import com.example.itm.session.Session
import com.example.itm.ui.screens.AppointmentsScreen.AppointmentsScreen
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import test.data.mapping.UserTestData

@RunWith(AndroidJUnit4::class)
class AppointmentsScreenAndroidTest {

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        registerRepositories()
        System.setProperty("TEST_MODE", "true")
    }

    @After
    fun after() {
        System.setProperty("TEST_MODE", "true")
    }

    @Test
    fun appointments_displayed_successfully() {

        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        val medicalProfessional = UserTestData.returnMedicalProfessional()
        val patient = UserTestData.returnPatient()

        Session.user = patient
        Session.role = patient.role
        Session.selectedUser = patient

        rule.setContent { AppointmentsScreen(context, navController).Create() }

        rule.onNodeWithText("Amsterdam").assertExists()
    }

    @Test
    fun appointments_displayed_unsuccessfully_due_to_no_appointments_existing() {

        val context: Context = ApplicationProvider.getApplicationContext<Context>()
        val navController: TestNavHostController = TestNavHostController(context)

        val medicalProfessional = UserTestData.returnMedicalProfessional()
        val patient = UserTestData.returnPatient()

        Session.user = medicalProfessional
        Session.role = medicalProfessional.role
        Session.selectedUser = patient

        rule.setContent { AppointmentsScreen(context, navController).Create() }

        rule.onNodeWithText("Not found").assertExists()
    }

}