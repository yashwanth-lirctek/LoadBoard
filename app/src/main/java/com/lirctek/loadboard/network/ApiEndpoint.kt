package com.lirctek.loadboard.network

object ApiEndpoint {

    const val AUTHORIZATION = "Authorization"

    const val LOGIN = "login"
    const val GET_USER_INFO_BY_DRIVER_CARRIER = "mobile/api/carrier/getCarrierInfoByUserId"
    const val GET_OFFERS = "workorder/api/cooffers/getCoOfferedLoads"
    const val LOAD_BOARD_LIST = "customerorder/getLoadboardList"
    const val GET_TRIP_LOAD = "mobile/api/dispatchorder/getTripLoads"
    const val ADD_EDIT_DESCRIPTION = "description/addEditDescription_app"
    const val GET_DESCRIPTION = "Description/getDescriptionsByType_App"
    const val ADD_EDIT_OFFER ="cooffer/AddEditOffer"
    const val GET_OFFER_DETAIL ="cooffer/getcoofferdetailsbyId"
}