package com.lirctek.loadboard.ui.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.lirctek.loadboard.ui.settings.password.PasswordUi
import com.lirctek.loadboard.ui.settings.profile.ProfileUi
import com.lirctek.loadboard.ui.theme.ToolBarBackColor
import com.lirctek.loadboard.ui.toolbar.HomeOtherToolBar
import com.lirctek.loadboard.ui.toolbar.SettingsToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SettingsUi(navController: NavController) {

    val tabItems = listOf("Profile", "Change Password", "Insurance")
    val pagerState = rememberPagerState()

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()
    var isEditButton by remember {
        mutableStateOf(true)
    }

    var isSaveButton by remember {
        mutableStateOf(true)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            SettingsToolBar(navController, isEditButton, isSaveButton)
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
                    if (pagerState.currentPage == 0){
                        isEditButton = true
                        isSaveButton = false
                    } else if (pagerState.currentPage == 1){
                        isEditButton = false
                        isSaveButton = true
                    } else if (pagerState.currentPage == 2){
                        isEditButton = false
                        isSaveButton = false
                    }

                    if (page == 0){
                        ProfileUi()
                    }else if (page == 1){
                        PasswordUi()
                    }else if (page == 2){
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