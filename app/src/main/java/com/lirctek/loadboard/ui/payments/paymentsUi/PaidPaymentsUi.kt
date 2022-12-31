package com.lirctek.loadboard.ui.payments.paymentsUi

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.theme.CardBackgroundColor

@Composable
fun PaidPaymentsUi(
    onPaymentClick : () -> Unit
){
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
                .clickable {
                    onPaymentClick()
                }
        ) {

            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                PaidTopUi()
                Spacer(modifier = Modifier.height(6.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(6.dp))
                PaidMiddleUi("Invoiced", "200", "ADD")
                Spacer(modifier = Modifier.height(3.dp))
                PaidMiddleUi("Reimbursement", "250", "ADD")
                Spacer(modifier = Modifier.height(3.dp))
                PaidMiddleUi("Fees", "50", "SUB")
                Spacer(modifier = Modifier.height(3.dp))
                PaidMiddleUi("Advances", "100", "SUB")
                Spacer(modifier = Modifier.height(5.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(5.dp))
                PaidBottomUi("300")
            }
        }

    }
}

@Composable
fun PaidBottomUi(amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Total".uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 5.dp)
        )
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(end = 5.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${amount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            )
        }

    }
}

@Composable
fun PaidMiddleUi(text: String, amount : String, type: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 5.dp)
        )
        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(end = 5.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = if (type.equals("SUB", ignoreCase = true)) "- $ ${amount}" else "$ ${amount}",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
            )
        }

    }
}

@Composable
fun PaidTopUi() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column{
            Text(
                text = "Settlement : 4032".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Apr 21, 2000".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = "Paid".uppercase(),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = "$ 300",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}
