package com.example.karmika.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.karmika.core.ui.designsystem.KarmikaColors

@Composable
fun <T> KarmikaRoleSelector(
    roles: List<Pair<T, String>>,
    selectedRole: T,
    onRoleSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        roles.forEach { (role, label) ->

            val selected = role == selectedRole

            if (selected) {
                Button(
                    onClick = {
                        onRoleSelected(role)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KarmikaColors.Primary
                    )
                ) {
                    Text(label)
                }
            } else {
                OutlinedButton(
                    onClick = {
                        onRoleSelected(role)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(label)
                }
            }
        }
    }
}