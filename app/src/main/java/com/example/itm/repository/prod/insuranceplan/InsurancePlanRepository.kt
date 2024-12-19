package com.example.itm.repository.prod.insuranceplan

import android.content.Context
import android.util.Log
import com.example.itm.data.api.Api
import com.example.itm.data.api.service.InsurancePlan.InsurancePlanApiService
import com.example.itm.data.api.util.Resource
import com.example.itm.data.model.InsurancePlan
import com.example.itm.repository.interfaces.insuranceplan.InsurancePlanRepositoryInterface
import retrofit2.Response

class InsurancePlanRepository(context: Context): InsurancePlanRepositoryInterface {

    private val _api: InsurancePlanApiService =
        Api.createApi(
            InsurancePlanApiService::class.java,
            context
        )

    override suspend fun getInsurancePlanAsMedicalProfessional(bsn: Int): Resource<InsurancePlan> {
        val returnValue: InsurancePlan = try {
            val response: Response<InsurancePlan> = _api.getInsurancePlanAsMedicalprofessional(bsn)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            Log.e("getInsurancePlan", e. message ?: "No exception message found.")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(returnValue)
    }

    override suspend fun getInsurancePlanAsPatient(): Resource<InsurancePlan> {
        val returnValue: InsurancePlan = try {
            val response: Response<InsurancePlan> = _api.getInsurancePlanAsPatient()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            Log.e("getInsurancePlan", e. message ?: "No exception message found.")
            return Resource.Error(message = e.message)
        }
        return Resource.Success(returnValue)
    }
}