package com.example.itm.ui.screens

sealed class Screens(
    val route: String,
) {

    data object Template:
            Screens("template")

    data object LoginScreen:
            Screens("login")

    data object ForgotPasswordScreen:
            Screens("forgotPassword")

    data object OverviewScreen:
            Screens("overview")

    data object PatientSearchScreen:
            Screens("patientSearch")

    data object PatientRegisterScreen:
            Screens("patientRegister")

    data object InsurancePlanScreen:
            Screens("insurancePlan")

    data object AppointmentsScreen:
            Screens("appointments")

    data object ScreenNotExist:
            Screens("screenNotExist")
}