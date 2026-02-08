package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.basiletti.gino.sochamps.R
import com.basiletti.gino.sochamps.ui.theme.iconXXXLarge

@Composable
fun AsyncImageLoader(
    imageUrl: String
) {
    val showLoadingSpinner = remember { mutableStateOf(false) }
    val showImageLoadFailure = remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {

        AsyncImage(
            placeholder = if (showImageLoadFailure.value) painterResource(id = R.drawable.ic_user) else null,
            onLoading = { showLoadingSpinner.value = true },
            onSuccess = { showLoadingSpinner.value = false },
            onError = { showImageLoadFailure.value = true },
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.size(iconXXXLarge)
        )

        if (showLoadingSpinner.value) {
            CircularProgressIndicator(color = Color.Black)
        }
    }
}