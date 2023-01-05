package com.lirctek.loadboard.ui.commonUi.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.WhereToVote
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.R
import com.lirctek.loadboard.extensions.fontFamily

@Composable
fun StartEndLocationUI(
    pickUpLocation: String? = null,
    pickUpDate: String? = null,
    pickUpTime: String? = null,
    endLocation: String? = null,
    endDate: String? = null,
    endTime: String? = null
){
    Row(
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier.height(100.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 2.dp, vertical = 5.dp)) {
                LocationIconsUI()
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        LocationWithDateTimeUI(
            pickUpLocation = pickUpLocation,
            pickUpDate = pickUpDate,
            pickUpTime = pickUpTime,
            endLocation = endLocation,
            endDate = endDate,
            endTime = endTime
        )
    }
}

@Composable
fun LocationIconsUI() {
    Column() {
        Icon(
            imageVector = Icons.Filled.Pin,
            contentDescription = null,
            tint = Color.White,
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.White
        )
        Icon(
            imageVector = Icons.Filled.WhereToVote,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun LocationWithDateTimeUI(
    pickUpLocation: String? = null,
    pickUpDate: String? = null,
    pickUpTime: String? = null,
    endLocation: String? = null,
    endDate: String? = null,
    endTime: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        StopLayoutUi(title = stringResource(R.string.pick_up), location = pickUpLocation, date = pickUpDate, time = pickUpTime)
        StopLayoutUi(title = stringResource(R.string.delivery), location = endLocation, date = endDate, time = endTime)
    }
}

@Composable
fun StopLayoutUi(
    title: String,
    location: String? = null,
    date: String? = null,
    time: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = title.uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Text(
                text = location ?: "-",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ){
            Text(
                text = (date ?: "-").uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            time?.let {
                Text(
                    text = it,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}
