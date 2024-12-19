package com.example.itm.repository.test.appointments

import com.example.itm.data.model.Appointment
import com.example.itm.data.model.AppointmentStatus
import java.util.Date

class AppointmentsTestData {

    companion object {

        fun returnAppointment(): Appointment {
            return Appointment(
                1,
                123456789,
                12345678,
                Date(),
                "Amsterdam",
                "Pain",
                "Torso pain",
                AppointmentStatus.Completed
            )
        }
    }
}