package com.example.itm.repository.test.insuranceplan

import android.content.Context
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.InsurancePlan
import com.example.itm.repository.interfaces.insuranceplan.InsurancePlanRepositoryInterface
import test.data.mapping.InsurancePlanTestData

class TestInsurancePlanRepository(
    context: Context
) : InsurancePlanRepositoryInterface {

    override suspend fun getInsurancePlanAsMedicalProfessional(bsn: Int): Resource<InsurancePlan> {
        val insurancePlan: InsurancePlan = InsurancePlanTestData.returnPatientInsurancePlan()
        if (bsn == insurancePlan.patientBsn) {
            return Resource.Success(insurancePlan)
        } else {
            return Resource.Error(message = "No insurance data found.")
        }
    }

    override suspend fun getInsurancePlanAsPatient(): Resource<InsurancePlan> {
        return Resource.Success(InsurancePlanTestData.returnPatientInsurancePlan())
    }
}