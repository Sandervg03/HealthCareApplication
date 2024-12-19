package com.example.itm.repository

import com.example.itm.repository.interfaces.appointments.MedicalProfessionalAppointmentsRepositoryInterface
import com.example.itm.repository.interfaces.appointments.PatientAppointmentsRepositoryInterface
import com.example.itm.repository.interfaces.authorization.AuthorizationRepositoryInterface
import com.example.itm.repository.interfaces.helloworld.HelloWorldRepositoryInterface
import com.example.itm.repository.interfaces.insuranceplan.InsurancePlanRepositoryInterface
import com.example.itm.repository.interfaces.user.UserRepositoryInterface
import com.example.itm.repository.prod.appointments.MedicalProfessionalAppointmentsRepository
import com.example.itm.repository.prod.appointments.PatientAppointmentsRepository
import com.example.itm.repository.prod.authorization.AuthorizationRepository
import com.example.itm.repository.prod.helloworld.HelloWorldRepository
import com.example.itm.repository.prod.insuranceplan.InsurancePlanRepository
import com.example.itm.repository.prod.user.UserRepository
import com.example.itm.repository.test.appointments.TestMedicalProfessionalAppointmentRepository
import com.example.itm.repository.test.appointments.TestPatientAppointmentsRepository
import com.example.itm.repository.test.authorization.TestAuthorizationRepository
import com.example.itm.repository.test.helloworld.TestHelloWorldRepository
import com.example.itm.repository.test.insuranceplan.TestInsurancePlanRepository
import com.example.itm.repository.test.user.TestUserRepository

sealed class RepositoryObject<T>(
    val repositoryInterface: Class<T>,
    val testRepository: Class<out T>,
    val prodRepository: Class<out T>
) {

    data object patientAppointment:
        RepositoryObject<PatientAppointmentsRepositoryInterface>(
            PatientAppointmentsRepositoryInterface::class.java,
            TestPatientAppointmentsRepository::class.java,
            PatientAppointmentsRepository::class.java
        )

    data object medicalProfessionalAppointment :
        RepositoryObject<MedicalProfessionalAppointmentsRepositoryInterface>(
            MedicalProfessionalAppointmentsRepositoryInterface::class.java,
            TestMedicalProfessionalAppointmentRepository::class.java,
            MedicalProfessionalAppointmentsRepository::class.java
        )

    data object insurancePlan :
        RepositoryObject<InsurancePlanRepositoryInterface>(
            InsurancePlanRepositoryInterface::class.java,
            TestInsurancePlanRepository::class.java,
            InsurancePlanRepository::class.java
        )

    data object authorization:
            RepositoryObject<AuthorizationRepositoryInterface>(
                AuthorizationRepositoryInterface::class.java,
                TestAuthorizationRepository::class.java,
                AuthorizationRepository::class.java
            )

    data object user:
            RepositoryObject<UserRepositoryInterface>(
                UserRepositoryInterface::class.java,
                TestUserRepository::class.java,
                UserRepository::class.java
            )

    data object helloWorld:
            RepositoryObject<HelloWorldRepositoryInterface>(
                HelloWorldRepositoryInterface::class.java,
                TestHelloWorldRepository::class.java,
                HelloWorldRepository::class.java
            )

}
