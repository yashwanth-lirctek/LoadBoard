package com.lirctek.loadboard.ui.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lirctek.loadboard.extensions.fontFamily
import com.lirctek.loadboard.ui.loads.available.AvailableUi
import com.lirctek.loadboard.ui.loads.delivered.DeliveredUi
import com.lirctek.loadboard.ui.loads.dispatched.DispatchedUi
import com.lirctek.loadboard.ui.payments.invoiced.InvoiceUi
import com.lirctek.loadboard.ui.payments.notBilled.NotBilledUi
import com.lirctek.loadboard.ui.payments.paid.PaidUi
import com.lirctek.loadboard.ui.theme.ToolBarBackColor
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PaymentsUi(navController: NavController) {

    val tabItems = listOf("Not Billed", "Invoiced", "Paid")
    val pagerState = rememberPagerState()

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeOtherToolBar("Payments")
        },
        scaffoldState = scaffoldState,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) { paddingValues ->

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(paddingValues)
                    .padding(start = 15.dp, top = 2.dp, end = 15.dp, bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(ToolBarBackColor)
                ) {

                    TabText(tabItems = tabItems, currentPage = pagerState.currentPage) { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }

                }
            }

            Column {
                HorizontalPager(
                    count = tabItems.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) { page ->
                    if (page == 0){
                        NotBilledUi(navController)
                    }else if (page == 1){
                        InvoiceUi(navController = navController)
                    }else if (page == 2){
                        PaidUi(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TabText(tabItems: List<String>, currentPage: Int, onClick:(index: Int) -> Unit){
    tabItems.forEachIndexed { index, s ->
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(if (currentPage == index) Color.White else ToolBarBackColor)
                .clickable(onClick = { onClick(index) })
                .padding(horizontal = 15.dp, vertical = 6.dp),
        ) {
            Text(
                text = tabItems[index].uppercase(),
                fontFamily = fontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = if (currentPage == index) Color.Black else Color.White
            )
        }

    }
}