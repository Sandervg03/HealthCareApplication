package com.example.itm.repository.interfaces.appointments

import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment

interface PatientAppointmentsRepositoryInterface: AppointmentsRepositoryInterface {

    override suspend fun getAppointments(): Resource<List<Appointment>>
}