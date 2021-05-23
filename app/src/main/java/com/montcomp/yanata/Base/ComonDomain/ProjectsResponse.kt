package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class ProjectsResponse(
        val id: String,
        val title: String,
        val manager:String,
        val dni:Int,
        val address: String,
        val email:String,
        val phone:Int
): Serializable

data class ProjectsRequest(
        val title: String,
        val manager:String,
        val dni:Int,
        val address: String,
        val email:String,
        val phone:Int
): Serializable


