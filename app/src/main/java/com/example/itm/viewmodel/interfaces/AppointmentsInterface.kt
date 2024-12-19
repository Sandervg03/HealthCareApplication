package com.example.itm.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.repository.interfaces.appointments.AppointmentsRepositoryInterface

interface AppointmentsInterface {

    val repository: AppointmentsRepositoryInterface

    val appointments: LiveData<Resource<List<Appointment>>>

    fun getAppointments()
}