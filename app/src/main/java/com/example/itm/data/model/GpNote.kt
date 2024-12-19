package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GpNote (
    @SerializedName("noteId")
    val noteId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("generalPractitionerBsn")
    val generalPractitionerBsn: Int,

    @SerializedName("dateOfNote")
    val dateOfNote: Date,

    @SerializedName("observationOrComment")
    val observationOrComment: String,

    @SerializedName("nextSteps")
    val nextSteps: String
)