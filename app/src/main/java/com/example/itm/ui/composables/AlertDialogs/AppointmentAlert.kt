package com.example.itm.ui.composables.AlertDialogs

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.itm.data.model.Appointment

@Composable
fun AppointmentAlert(
    context: Context,
    onDismiss: () -> Unit,
    appointment: Appointment
) {

    AlertDialog(
        shape = RoundedCornerShape(10.dp),
        onDismissRequest = onDismiss,
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = appointment.appointmentDateTime.toLocaleString(), color = Color.Black)
                Spacer(modifier = Modifier.padding(20.dp))
                Text(text = "Address:")
                Text(text = appointment.clinicAddress, color = Color.Black)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Reason for visit:")
                Text(text = appointment.reasonForVisit, color = Color.Black)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Notes or observations:")
                Text(text = appointment.notesOrObservations, color = Color.Black)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Appointment status:")
                Text(text = appointment.status.name, color = Color.Black)
            }
        },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = onDismiss) {
                    Text(text = "Go back")
                }
            }
        }
    )
}