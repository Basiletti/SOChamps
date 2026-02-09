package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.basiletti.gino.sochamps.ui.theme.textLarge
import com.basiletti.gino.sochamps.ui.theme.textRegular
import com.basiletti.gino.sochamps.ui.theme.textSmall

@Composable
fun SOTitle(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = if (isSystemInDarkTheme()) Color.White else Color.Black
) {
    SOText(
        fontWeight = FontWeight.SemiBold,
        text = text,
        fontSize = textLarge,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun SOSubtitle(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = if (isSystemInDarkTheme()) Color.White else Color.Black
) {
    SOText(
        text = text,
        fontSize = textRegular,
        modifier = modifier,
        color = color,
    )

}

@Composable
fun SODescription(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = if (isSystemInDarkTheme()) Color.White else Color.Black
) {
    SOText(
        text = text,
        fontSize = textSmall,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun SOText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    color: Color,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = modifier,
        color = color,
    )
}