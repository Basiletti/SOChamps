package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.basiletti.gino.sochamps.ui.theme.textLarge
import com.basiletti.gino.sochamps.ui.theme.textRegular
import com.basiletti.gino.sochamps.ui.theme.textSmall

@Composable
fun SOTitle(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
) {
    Text(
        fontWeight = FontWeight.SemiBold,
        text = text,
        fontSize = textLarge,
        modifier = modifier,
        color = textColor,
    )
}

@Composable
fun SOSubtitle(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
) {
    Text(
        text = text,
        fontSize = textRegular,
        modifier = modifier,
        color = textColor,
    )
}

@Composable
fun SODescription(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
) {
    Text(
        text = text,
        fontSize = textSmall,
        modifier = modifier,
        color = textColor,
    )
}