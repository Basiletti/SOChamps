package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.basiletti.gino.sochamps.ui.theme.iconRegular
import com.basiletti.gino.sochamps.ui.theme.spaceLarge
import com.basiletti.gino.sochamps.ui.theme.spaceRegular
import com.basiletti.gino.sochamps.ui.theme.textLarge

@Composable
fun SOHeader(
    modifier: Modifier = Modifier,
    headerText: String,
    iconEndRes: Int? = null,
    contentDescription: String? = null
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = spaceLarge,
                    vertical = spaceRegular
                )
        ) {
            Text(
                text = headerText,
                fontSize = textLarge,
                modifier = Modifier.weight(1f)
            )

            iconEndRes?.let { icon ->
                Icon(
                    modifier = Modifier.size(iconRegular),
                    painter = painterResource(id = icon),
                    tint = Color.White,
                    contentDescription = contentDescription
                )
            }
        }

        HorizontalDivider()
    }
}