package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class PeopleResponse(
        val area: Int,
        val district:String,
        val year:Int,
        val male: Int,
        val female:Int,
        val total:Int
): Serializable
