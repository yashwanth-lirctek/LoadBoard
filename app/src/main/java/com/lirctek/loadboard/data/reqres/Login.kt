package com.lirctek.loadboard.data.reqres

import java.io.Serializable

data class LoginRequest (
    val identifier: String,
    val password: String
)

class LoginResponse {
    var user: LoginResponseData? = null
    var token: String? = null
    var isAppEnabled: Int = 0
    var IsDeleted: Int = 0
    var IsBOLRequired: Int = 0
    var IsDate24HrFormat: Int = 0
    var IsEld: Int = 0
    var SplitFullLoad: Int = 0
    var IsLTL: Int = 0
    var IsFullLoad: Int = 0
}

class LoginResponseData : Serializable {

    var Id: Int = 0
    var Username: String? = null
    var Email: String? = null
    var FirstName: String? = null
    var LastName: String? = null
    var Role_Id: Int = 0
    var Company_Id: Int = 0
    var IsDeleted: Boolean = false

}