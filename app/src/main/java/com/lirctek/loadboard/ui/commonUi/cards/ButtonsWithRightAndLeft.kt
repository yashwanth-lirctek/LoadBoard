package com.lirctek.loadboard.ui.commonUi.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdsClick
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.R
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun ButtonsWithRightAndLeftUI(
    offeredAmount: Double = 0.0,
    onAcceptOffer : () -> Unit,
    onPlaceOffer : () -> Unit,
    onYourOffer : () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (offeredAmount == 0.0){
            Column (
            ){
                OfferButtonUI(
                    text = stringResource(R.string.place_offer),
                    icon = Icons.Outlined.AdsClick,
                    modifier = Modifier
                        .clickable {
                            onPlaceOffer()
                        }
                )
            }
        }else {
            Column {
                OfferButtonUI(text =
                stringResource(R.string.your_offer),
                    value = offeredAmount.toString(),
                    Icons.Outlined.EditNote,
                    modifier = Modifier
                        .clickable {
                            onYourOffer()
                        }
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxWidth()
        ){
            OfferButtonUI(
                text = stringResource(R.string.accept_offer),
                icon = Icons.Outlined.AdsClick,
                modifier = Modifier
                    .clickable {
                        onAcceptOffer()
                    }
            )
        }
    }
}

@Composable
fun OfferButtonUI(
    text: String,
    value: String? = null,
    icon: ImageVector? = null,
    modifier: Modifier
){

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 10.dp, vertical = 3.dp)
    ) {
        if(icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp)
            )
        }
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = if (value != null) " : $ $value" else "",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
        )
    }

}