package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class MesageErrorResponse(
        val message: String?,
        val reason:String
): Serializable

data class MesageRecoverResponse(
        val message: String
): Serializable
