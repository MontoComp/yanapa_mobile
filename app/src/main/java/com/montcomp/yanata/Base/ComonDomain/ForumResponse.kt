package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class ForumResponse(
        val id: String,
        val title: String,
        val name:String,
        val description:String,
        val hour:String,
        val date:String
): Serializable

data class ForumRequest(
        val title: String,
        val name:String,
        val description:String,
        val hour:String,
        val date:String
): Serializable

