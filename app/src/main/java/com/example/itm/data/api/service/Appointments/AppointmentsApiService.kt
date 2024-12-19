package com.example.itm.data.api.service.Appointments

import com.example.itm.data.model.Appointment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppointmentsApiService {

    @GET("/patient/appointments")
    suspend fun getAppointmentsAsPatient(): Response<List<Appointment>>

    @GET("/medicalprofessional/appointments")
    suspend fun getAppointmentsAsMedicalProfessional(
        @Query("bsn")
        bsn: Int
    ): Response<List<Appointment>>
}