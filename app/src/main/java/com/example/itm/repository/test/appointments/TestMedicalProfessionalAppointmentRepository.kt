package com.example.itm.repository.test.appointments

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.repository.interfaces.appointments.MedicalProfessionalAppointmentsRepositoryInterface
import com.example.itm.repository.interfaces.appointments.PatientAppointmentsRepositoryInterface

class TestMedicalProfessionalAppointmentRepository(
  context: Context
) : MedicalProfessionalAppointmentsRepositoryInterface {

    override suspend fun getAppointments(): Resource<List<Appointment>> {
        return Resource.Error(message = "Not found")
    }
}