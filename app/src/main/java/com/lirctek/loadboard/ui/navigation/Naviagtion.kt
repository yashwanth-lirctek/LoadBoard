package com.lirctek.loadboard.ui.navigation

import android.net.Uri
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
import com.lirctek.loadboard.data.reqres.LoadBoardDataList
import com.lirctek.loadboard.data.reqres.LoadsList
import com.lirctek.loadboard.data.reqres.OfferDataList
import com.lirctek.loadboard.ui.home.HomeUi
import com.lirctek.loadboard.ui.home.homeDetails.HomeDetailsUi
import com.lirctek.loadboard.ui.loads.LoadsUi
import com.lirctek.loadboard.ui.loads.loadDetails.LoadDetailsUi
import com.lirctek.loadboard.ui.loads.loadDetails.documents.LoadDetailsDocumentUi
import com.lirctek.loadboard.ui.loads.loadDetails.main.LoadDetailsMainUi
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
                    type = AssetParamTypeOffers()
                }
            )){
            val offerItem = it.arguments?.getParcelable<OfferDataList>("offersList")
            offerItem?.let {
                OfferDetails(navController, it)
            }
        }
        composable("main/home/details/{dataList}",
            arguments = listOf(
                navArgument("dataList") {
                    type = AssetParamTypeHome()
                }
            )){
            val offerItem = it.arguments?.getParcelable<LoadBoardDataList>("dataList")
            offerItem?.let {
                HomeDetailsUi(navController, it)
            }
        }
        composable("main/loads/details/{loadData}",
            arguments = listOf(
                navArgument("loadData") {
                    type = AssetParamTypeLoads()
                }
            )){
            val offerItem = it.arguments?.getParcelable<LoadsList>("loadData")
            offerItem?.let {
                LoadDetailsUi(navController, it)
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

@Composable
fun LoadDetailsNavigation(
    navController: NavHostController,
    mainNavController: NavController,
    loadData: LoadsList
) {
    val json = Uri.encode(Gson().toJson(loadData))
    NavHost(navController = navController, startDestination = "main/loads/details/main"){
        composable("main/loads/details/main"){
            LoadDetailsMainUi(mainNavController, loadData)
        }
        composable("main/loads/details/main/{loadData}",
            arguments = listOf(
                navArgument("loadData") {
                    type = AssetParamTypeLoads()
                }
            )){
            val offerItem = it.arguments?.getParcelable<LoadsList>("loadData")
            offerItem?.let {
                LoadDetailsMainUi(mainNavController, it)
            }
        }
        composable("main/loads/details/documents/{loadData}",
            arguments = listOf(
                navArgument("loadData") {
                    type = AssetParamTypeLoads()
                }
            )){
            val offerItem = it.arguments?.getParcelable<LoadsList>("loadData")
            offerItem?.let {
                LoadDetailsDocumentUi(mainNavController, it)
            }
        }
    }
}

class AssetParamTypeOffers : NavType<OfferDataList>(isNullableAllowed = false) {
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

class AssetParamTypeHome : NavType<LoadBoardDataList>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): LoadBoardDataList? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): LoadBoardDataList {
        return Gson().fromJson(value, LoadBoardDataList::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: LoadBoardDataList) {
        bundle.putParcelable(key, value)
    }
}

class AssetParamTypeLoads : NavType<LoadsList>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): LoadsList? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): LoadsList {
        return Gson().fromJson(value, LoadsList::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: LoadsList) {
        bundle.putParcelable(key, value)
    }
}