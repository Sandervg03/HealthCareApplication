package com.example.itm

import com.example.itm.data.model.MaritalStatus
import com.example.itm.data.model.Roles
import com.example.itm.data.model.User
import com.example.itm.data.model.validateRegister
import org.junit.Test
import org.junit.Assert.*
import test.data.mapping.UserTestData
import java.util.Date

class PatientRegisterUnitTest {

    @Test
    fun validate_registration_inputs_successfully() {
        val user: User = UserTestData.returnPatient()
        assert(user.validateRegister() == Unit)
    }

    @Test
    fun validate_registration_inputs_unsuccessfully() {
        val user: User = User(
            bsn = 1234567890,
            firstName = "",
            lastName = "",
            dateOfBirth = Date(),
            userName = "",
            password = "",
            role = Roles.Patient,
            emergencyContactName = "",
            emergencyContactPhoneNumber = "",
            profilePicture = ByteArray(0),
            accountCreationDate = Date(),
            lastLogin = Date(),
            phoneNumber = "",
            address = "",
            countryOfResidence = "",
            insurancePolicy = "",
            maritalStatus = MaritalStatus.Single
        )
        assertThrows(Exception::class.java) {
            user.validateRegister()
        }
    }
}