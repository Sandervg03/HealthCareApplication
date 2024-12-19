package com.example.itm.repository.interfaces.insuranceplan

import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.InsurancePlan

interface InsurancePlanRepositoryInterface {

    suspend fun getInsurancePlanAsMedicalProfessional(bsn: Int): Resource<InsurancePlan>
    suspend fun getInsurancePlanAsPatient(): Resource<InsurancePlan>
}