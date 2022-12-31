package com.lirctek.loadboard.ui.loads

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
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
import com.lirctek.loadboard.ui.loads.inTransit.InTransitUi
import com.lirctek.loadboard.ui.theme.ToolBarBackColor
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LoadsUi(navController: NavController) {

    val tabItems = listOf("Available", "Dispatched", "In-Transit", "Delivered")
    val pagerState = rememberPagerState()

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var scrollToPosition  by remember { mutableStateOf(0F) }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeOtherToolBar("My Loads")
        },
        scaffoldState = scaffoldState
    ) { paddingValues ->

        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
                    .padding(paddingValues)
                    .horizontalScroll(scrollState)
                    .onGloballyPositioned { coordinates ->
                        scrollToPosition = coordinates.positionInParent().y
                    }
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

            Box(modifier = Modifier.fillMaxSize()){
                HorizontalPager(
                    count = tabItems.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                ) { page ->
                    if (page == 0){
                        AvailableUi(navController = navController)
                    }else if (page == 1){
                        DispatchedUi(navController = navController)
                    }else if (page == 2){
                        DeliveredUi(navController = navController)
                    }else if (page == 3){
                        InTransitUi(navController = navController)
                    }
                    if (pagerState.currentPage == 3 || pagerState.currentPage == 2){
                        LaunchedEffect(key1 = scrollState){
                            scrollState.scrollBy(100f)
                        }
                    }
                    if (pagerState.currentPage == 0 || pagerState.currentPage == 1){
                        LaunchedEffect(key1 = scrollState){
                            scrollState.scrollBy(-100f)
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