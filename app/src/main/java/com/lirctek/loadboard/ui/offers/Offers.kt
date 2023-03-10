package com.lirctek.loadboard.ui.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.lirctek.loadboard.ui.offers.active.ActiveOffers
import com.lirctek.loadboard.ui.offers.inactive.InActiveOffers
import com.lirctek.loadboard.ui.theme.ToolBarBackColor
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OffersUi(navController: NavController) {

    val tabItems = listOf("Active", "In-Active")
    val pagerState = rememberPagerState()

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeOtherToolBar("Offers")
        },
        scaffoldState = scaffoldState,
        backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surface
    ) { paddingValues ->

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(start = 15.dp, top = 2.dp, end = 15.dp, bottom = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant)
                ) {

                    TabText(tabItems = tabItems, currentPage = pagerState.currentPage) { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }

                }
            }

            Box(modifier = Modifier.fillMaxSize()){
                Column {
                    HorizontalPager(
                        count = tabItems.size,
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                    ) { page ->
                        if (page == 0){
                            ActiveOffers(navController)
                        }else if (page == 1){
                            InActiveOffers(navController)
                        }
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
                .background(if (currentPage == index) Color.White else androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant)
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