package com.example.itm.data.api.service.InsurancePlan

import com.example.itm.data.model.InsurancePlan
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InsurancePlanApiService {

    @GET("/medicalprofessional/insurance")
    suspend fun getInsurancePlanAsMedicalprofessional(
        @Query("bsn")
        bsn: Int
    ): Response<InsurancePlan>

    @GET("/patient/insurance")
    suspend fun getInsurancePlanAsPatient(): Response<InsurancePlan>
}