package com.example.itm.repository.interfaces.appointments

import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment

interface AppointmentsRepositoryInterface {

    suspend fun getAppointments(): Resource<List<Appointment>>
}