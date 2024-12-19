package test.data.mapping

import com.example.itm.data.model.MaritalStatus
import com.example.itm.data.model.Roles
import com.example.itm.data.model.User
import java.util.*

class UserTestData {

    companion object {
        fun returnMedicalProfessional(): User {
            return User(
                bsn = 1234567890,
                firstName = "Jane",
                lastName = "Doe",
                dateOfBirth = Date(),
                userName = "janedoe",
                password = "Test.123",
                role = Roles.MedicalProfessional,
                emergencyContactName = "John Doe",
                emergencyContactPhoneNumber = "+1234567890",
                profilePicture = ByteArray(0),
                accountCreationDate = Date(),
                lastLogin = Date(),
                phoneNumber = "+1234567890",
                address = "123 Main St",
                countryOfResidence = "USA",
                insurancePolicy = "ABC123456",
                maritalStatus = MaritalStatus.Single
            )
        }

        fun returnPatient(): User {
            return User(
                bsn = 123456789,
                firstName = "John",
                lastName = "Doe",
                dateOfBirth = Date(),
                userName = "johndoe",
                password = "Test.123",
                role = Roles.Patient,
                emergencyContactName = "Jane Doe",
                emergencyContactPhoneNumber = "+1234567890",
                profilePicture = ByteArray(0),
                accountCreationDate = Date(),
                lastLogin = Date(),
                phoneNumber = "+1234567890",
                address = "123 Main St",
                countryOfResidence = "USA",
                insurancePolicy = "ABC123456",
                maritalStatus = MaritalStatus.Single
            )
        }
    }
}