package presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun DisclaimerDialog(
    modifier: Modifier = Modifier,
    onOkButtonClicked: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {},
        title = {
            Text("Disclaimer")
        },
        text = {
            Text("This is a hobby project from fans and not officially related to TheVR.")
        },
        buttons = {
            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = onOkButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = null,
                )
                Text(
                    text = "Acknowledged"
                )
            }
        }
    )
}