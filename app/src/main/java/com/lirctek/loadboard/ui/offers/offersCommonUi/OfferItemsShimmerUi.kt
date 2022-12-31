package com.lirctek.loadboard.ui.offers.offersCommonUi

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.shimmer.shimmerEffect
import com.lirctek.loadboard.ui.shimmer.shimmerEffectTwo
import com.lirctek.loadboard.ui.theme.CardBackgroundColor
import com.lirctek.loadboard.ui.theme.Gray

@Composable
fun OfferItemsShimmerUi(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {

        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 2.dp,
            backgroundColor = CardBackgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxSize().shimmerEffectTwo().padding(10.dp)
            ){}
        }

    }
}

