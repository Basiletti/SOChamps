package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.basiletti.gino.sochamps.ui.theme.iconLarge
import com.basiletti.gino.sochamps.ui.theme.spaceRegular
import com.basiletti.gino.sochamps.ui.theme.spaceXXXlarge
import com.basiletti.gino.sochamps.ui.theme.textLarge

@Composable
fun SOSpinner(
    modifier: Modifier = Modifier,
    text: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = spaceRegular,
                vertical = spaceXXXlarge
            ),
        verticalArrangement = Arrangement.spacedBy(spaceRegular)
    ) {
//        CircularProgressIndicator(
//            modifier = Modifier.width(iconLarge),
//            color = Color.White,
//            trackColor = Color.DarkGray
//        )

        Text(
            textAlign = TextAlign.Center,
            text = text,
            fontSize = textLarge,
            modifier = Modifier.padding(spaceRegular)
        )


    }
}