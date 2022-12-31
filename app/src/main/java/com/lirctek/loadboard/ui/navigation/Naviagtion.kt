package com.lirctek.loadboard.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.ui.home.HomeUi
import com.lirctek.loadboard.ui.loads.LoadsUi
import com.lirctek.loadboard.ui.login.LoginScreen
import com.lirctek.loadboard.ui.main.MainUi
import com.lirctek.loadboard.ui.offers.OffersUi
import com.lirctek.loadboard.ui.offers.offerDetails.OfferDetails
import com.lirctek.loadboard.ui.payments.PaymentsUi
import com.lirctek.loadboard.ui.payments.paid.details.PaidDetailsUi
import com.lirctek.loadboard.ui.settings.SettingsUi
import com.lirctek.loadboard.ui.splash.SplashScreen

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
        composable("main/offers/details/{offersList}",
            arguments = listOf(
                navArgument("offersList") {
                    type = AssetParamType()
                }
            )){
            val offerItem = it.arguments?.getParcelable<OfferDataList>("offersList")
            offerItem?.let {
                OfferDetails(navController, it)
            }
        }
        composable("main/payments/paid/details"){
            PaidDetailsUi()
        }
    }
}

@Composable
fun HomeNavigation(navController: NavHostController, mainNavController: NavController) {
    NavHost(navController = navController, startDestination = "main/home"){
        composable("main/home"){
            HomeUi(navController = mainNavController)
        }
        composable("main/settings"){
            SettingsUi(navController = navController)
        }
        composable("main/offers"){
            OffersUi(navController = mainNavController)
        }
        composable("main/loads"){
            LoadsUi(navController = mainNavController)
        }
        composable("main/payments"){
            PaymentsUi(navController = mainNavController)
        }
    }
}

class AssetParamType : NavType<OfferDataList>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): OfferDataList? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): OfferDataList {
        return Gson().fromJson(value, OfferDataList::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: OfferDataList) {
        bundle.putParcelable(key, value)
    }
}