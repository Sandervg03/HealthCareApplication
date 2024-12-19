package com.example.itm.viewmodel.appointments

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.Appointment
import com.example.itm.data.model.Roles
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.appointments.AppointmentsRepositoryInterface
import com.example.itm.session.Session
import com.example.itm.util.provider.RepositoryProvider
import com.example.itm.viewmodel.interfaces.AppointmentsInterface
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    context: Context
): ViewModel(), AppointmentsInterface {

    override val repository: AppointmentsRepositoryInterface =
        RepositoryProvider.provideMultipleImplementationsRepository(
            context,
            if (Session.role == Roles.Patient) {
                RepositoryObject.patientAppointment.repositoryInterface
            } else {
                RepositoryObject.medicalProfessionalAppointment.repositoryInterface
            }
        )

    private val _appointments: MutableLiveData<Resource<List<Appointment>>> =
        MutableLiveData(Resource.Empty())

    override val appointments: LiveData<Resource<List<Appointment>>>
        get() = _appointments

    override fun getAppointments() {
        try {
            _appointments.value = Resource.Fetching()

            viewModelScope.launch {
                _appointments.value = repository.getAppointments()
            }
        } catch (e: Exception) {

            Log.e("Appointments", e.message ?: "No exception message found...")
            _appointments.value = Resource.Error(
                message = "Something went wrong, please try again later"
            )
        }
    }

    init {
        getAppointments()
    }
}