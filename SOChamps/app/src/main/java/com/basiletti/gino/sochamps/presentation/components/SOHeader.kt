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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                    horizontal = 30.dp,
                    vertical = 20.dp
                )
        ) {
            Text(
                text = headerText,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f)
            )

            iconEndRes?.let { icon ->
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = icon),
                    tint = Color.White,
                    contentDescription = contentDescription
                )
            }
        }

        HorizontalDivider()
    }
}