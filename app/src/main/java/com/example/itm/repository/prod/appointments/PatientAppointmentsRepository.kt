package com.example.itm.repository.prod.appointments

import android.content.Context
import android.util.Log
import com.example.itm.data.api.Api
import com.example.itm.data.api.service.Appointments.AppointmentsApiService
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.repository.interfaces.appointments.PatientAppointmentsRepositoryInterface
import retrofit2.Response

class PatientAppointmentsRepository(
    context: Context
) : PatientAppointmentsRepositoryInterface {

    private val _api: AppointmentsApiService =
        Api.createApi(AppointmentsApiService::class.java, context)

    override suspend fun getAppointments(): Resource<List<Appointment>> {

        val returnValue: List<Appointment> = try {
            val response: Response<List<Appointment>> = _api.getAppointmentsAsPatient()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message() ?: "No exception message found.")
            }
        } catch (e: Exception) {
            Log.e(
                "PatientAppointmentsRepository",
                e.message ?: "No exception message found..."
            )
            return Resource.Error(message = e.message ?: "No exception message found.")
        }
        return Resource.Success(returnValue)
    }
}