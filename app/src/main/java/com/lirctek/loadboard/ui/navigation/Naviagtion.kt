package com.lirctek.loadboard.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lirctek.loadboard.ui.home.HomeUi
import com.lirctek.loadboard.ui.loads.LoadsUi
import com.lirctek.loadboard.ui.login.LoginScreen
import com.lirctek.loadboard.ui.main.MainUi
import com.lirctek.loadboard.ui.offers.OffersUi
import com.lirctek.loadboard.ui.payments.PaymentsUi
import com.lirctek.loadboard.ui.settings.SettingsUi
import com.lirctek.loadboard.ui.splash.SplashScreen
import com.lirctek.loadboard.ui.toolbar.HomeToolBar

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash"){
        composable("splash"){
            SplashScreen(navController = navController)
        }
        composable("login"){
            LoginScreen(navController = navController)
        }
        composable("main"){
            MainUi(mainNavController = navController)
        }
    }
}

@Composable
fun HomeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main/home"){
        composable("main/home"){
            HomeUi(navController = navController)
        }
        composable("main/settings"){
            SettingsUi(navController = navController)
        }
        composable("main/offers"){
            OffersUi(navController = navController)
        }
        composable("main/loads"){
            LoadsUi(navController = navController)
        }
        composable("main/payments"){
            PaymentsUi(navController = navController)
        }
    }
}