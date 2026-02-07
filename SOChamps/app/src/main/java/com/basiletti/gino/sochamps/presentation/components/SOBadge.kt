package com.basiletti.gino.sochamps.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.basiletti.gino.sochamps.R
import com.basiletti.gino.sochamps.ui.theme.iconSmall
import com.basiletti.gino.sochamps.ui.theme.spaceXSmall
import com.basiletti.gino.sochamps.ui.theme.spaceXXSmall

@Composable
fun SOBadge(
    modifier: Modifier = Modifier,
    badgeType: BadgeType,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = spaceXSmall),
        horizontalArrangement = Arrangement.spacedBy(spaceXXSmall)
    ) {
        Icon(
            modifier = Modifier.size(iconSmall),
            painter = painterResource(id = R.drawable.ic_badge),
            tint = getBadgeColour(badgeType),
            contentDescription = null
        )

        SODescription(text = text)
    }
}

private fun getBadgeColour(badgeType: BadgeType): Color {
    return when (badgeType) {
        BadgeType.BRONZE -> Color(color = 0xFFce8946)
        BadgeType.SILVER -> Color(color = 0xFFc0c0c0)
        BadgeType.GOLD -> Color(color = 0xFFd3af37)
    }
}

enum class BadgeType {
    BRONZE, SILVER, GOLD
}

