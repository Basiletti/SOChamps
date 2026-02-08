package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.basiletti.gino.sochamps.ui.theme.spaceLarge
import com.basiletti.gino.sochamps.ui.theme.spaceSmall

@Composable
fun SOButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color.DarkGray,
    onButtonClicked: () -> Unit,
) {
    Button(
        onClick = {
            onButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        SOSubtitle(
            text = text,
            modifier = modifier
                .padding(
                    vertical = spaceSmall,
                    horizontal = spaceLarge
                )

        )
    }
}

@Composable
fun SOMiniButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color.DarkGray,
    onButtonClicked: () -> Unit,
) {
    Button(
        onClick = {
            onButtonClicked()
        },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        SODescription(
            text = text,
            modifier = modifier
        )
    }
}