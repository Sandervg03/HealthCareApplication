package com.example.itm.viewmodel.insuranceplan

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.InsurancePlan
import com.example.itm.data.model.Roles
import com.example.itm.repository.RepositoryObject
import com.example.itm.repository.interfaces.insuranceplan.InsurancePlanRepositoryInterface
import com.example.itm.session.Session
import com.example.itm.util.provider.RepositoryProvider
import kotlinx.coroutines.launch

class InsurancePlanViewModel(context: Context): ViewModel() {

    private val _repository: InsurancePlanRepositoryInterface =
        RepositoryProvider.provideMultipleImplementationsRepository(
            context, RepositoryObject.insurancePlan.repositoryInterface
        )

    private val _getInsurancePlanResult: MutableLiveData<Resource<InsurancePlan>> =
        MutableLiveData(Resource.Empty())

    val getInsurancePlanResult: LiveData<Resource<InsurancePlan>>
        get() = _getInsurancePlanResult

    fun getInsurancePlan() {
        try {
            _getInsurancePlanResult.value = Resource.Fetching()

            viewModelScope.launch {
                if (Session.role == Roles.MedicalProfessional) {
                    _getInsurancePlanResult.value =
                        _repository.getInsurancePlanAsMedicalProfessional(
                            Session.selectedUser?.bsn ?:
                            throw Exception("No selected user found.")
                        )
                } else {
                    _getInsurancePlanResult.value = _repository.getInsurancePlanAsPatient()
                }
            }
        } catch (e: Exception) {
            Log.e("getInsurancePlan", e.message ?: "No exception message found")
            _getInsurancePlanResult.value =
                Resource.Error(message = e.message ?: "Something went wrong...")
        }
    }

    init {
        getInsurancePlan()
    }
}