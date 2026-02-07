package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SOErrorBanner(
    modifier: Modifier = Modifier,
    errorText: String,
) {
    Text(
        modifier = modifier
            .background(Color.Red)
            .padding(vertical = 20.dp),
        text = errorText
    )
}