package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.basiletti.gino.sochamps.ui.theme.textLarge
import com.basiletti.gino.sochamps.ui.theme.textRegular
import com.basiletti.gino.sochamps.ui.theme.textSmall

@Composable
fun SOTitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        fontWeight = FontWeight.SemiBold,
        text = text,
        fontSize = textLarge,
        modifier = modifier
    )
}

@Composable
fun SOSubtitle(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        fontSize = textRegular,
        modifier = modifier
    )
}

@Composable
fun SODescription(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        text = text,
        fontSize = textSmall,
        modifier = modifier
    )
}