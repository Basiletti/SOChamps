package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.basiletti.gino.sochamps.ui.theme.iconXXXLarge
import com.basiletti.gino.sochamps.ui.theme.spaceLarge
import com.basiletti.gino.sochamps.ui.theme.spaceRegular
import com.basiletti.gino.sochamps.ui.theme.spaceXXXlarge
import com.basiletti.gino.sochamps.ui.theme.textLarge

@Composable
fun SOEmptyState(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: Int? = null,
    contentDescription: String? = null,
    buttonText: String? = null,
    onButtonClicked: () -> Unit = {},
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = spaceRegular,
                vertical = spaceXXXlarge
            ),
    ) {
        iconRes?.let { icon ->
            Icon(
                modifier = Modifier.size(iconXXXLarge),
                painter = painterResource(id = icon),
                tint = Color.White,
                contentDescription = contentDescription
            )
        }

        Text(
            textAlign = TextAlign.Center,
            text = text,
            fontSize = textLarge,
            modifier = Modifier.padding(spaceRegular)
        )

        buttonText?.let { text ->
            Spacer(Modifier.height(spaceLarge))

            SOButton(
                text = text,
                onButtonClicked = onButtonClicked,

            )
        }


    }
}