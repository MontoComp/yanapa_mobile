package com.montcomp.yanata.Base.ComonDomain

import com.hcr.hcr_android.Base.PersistentData.PersistentData
import java.io.Serializable


data class LogoutResponse2(
        val resultCode: String,
        val resultDescription: Any? = null
)
data class LogoutResponse(
        val resultCode: String
)

data class LogoutDrawerResponse (
        val resultCode: String
): Serializable

data class LogoutRequest(
        val userID: Int,
        val token: String
)

data class LogoutDrawerRequest (
        val userID: Int = PersistentData.instance.userID.value,
        val token: String = PersistentData.instance.userToken.value
): Serializable