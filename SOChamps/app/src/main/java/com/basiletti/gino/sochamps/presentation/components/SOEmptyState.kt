package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        iconRes?.let { icon ->
            Icon(
                modifier = Modifier.size(120.dp),
                painter = painterResource(id = icon),
                tint = Color.White,
                contentDescription = contentDescription
            )
        }

        Text(
            textAlign = TextAlign.Center,
            text = text,
            fontSize = 24.sp,
            modifier = Modifier.padding(20.dp)
        )

        buttonText?.let { text ->
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = {
                    onButtonClicked()
                }
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(
                            vertical = 10.dp,
                            horizontal = 30.dp
                        )
                )
            }
        }


    }
}