package com.example.itm.repository.test.appointments

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.repository.interfaces.appointments.PatientAppointmentsRepositoryInterface

class TestPatientAppointmentsRepository(
    context: Context
): PatientAppointmentsRepositoryInterface {

    override suspend fun getAppointments(): Resource<List<Appointment>> {
        return Resource.Success(listOf(AppointmentsTestData.returnAppointment()))
    }
}