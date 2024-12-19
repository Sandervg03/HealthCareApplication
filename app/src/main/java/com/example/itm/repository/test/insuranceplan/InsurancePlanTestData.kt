package test.data.mapping

import com.example.itm.data.model.InsurancePlan
import java.util.*

class InsurancePlanTestData {

    companion object {

        fun returnPatientInsurancePlan(): InsurancePlan {
            return InsurancePlan(
                insurancePlanId = 1,
                insuranceCompany = "Zilveren kruis",
                planType = "Basis",
                coverageDetails = "100% ziekenhuis Nederland",
                patientBsn = 123456789,
                expirationDate = Date(),
                premiumAmount = 123
            )
        }
    }
}