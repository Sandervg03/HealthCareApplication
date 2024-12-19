package com.example.itm.ui.screens

import com.example.itm.R

sealed class NavItem(
    val icon: Int,
    val description: String,
    val clickDestination: String
) {
    data object Appointments:
            NavItem(R.drawable.agenda, "Appointments", "appointments")

    data object Records:
            NavItem(R.drawable.medical_record, "Records", "records")

    data object Medication:
        NavItem(R.drawable.pills_icon, "Medication", "medication")

    data object Insurance:
            NavItem(R.drawable.safety, "InsurancePlan", "insurancePlan")

    data object Invoice:
            NavItem(R.drawable.invoice_icon, "Invoice", "invoice")

}