package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.basiletti.gino.sochamps.R

@Composable
fun SOAlertDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmText: String = stringResource(R.string.confirm_caps),
    cancelText: String = stringResource(R.string.cancel_caps),
) {
    AlertDialog(
        modifier = modifier,
        title = {
            SOTitle(text = dialogTitle)
        },
        text = {
            SODescription(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            SOMiniButton(text = confirmText) {
                onConfirmation()
            }
        },
        dismissButton = {
            SOMiniButton(text = cancelText) {
                onDismissRequest()
            }
        }
    )
}
