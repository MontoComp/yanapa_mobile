package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class PermitsResponse (
        val code: Int,
        val authorizingCondition: Int,
        val name:String,
        val type:String
): Serializable

data class PermitsRequest (
        val id: Int?,
        val token: String
): Serializable
