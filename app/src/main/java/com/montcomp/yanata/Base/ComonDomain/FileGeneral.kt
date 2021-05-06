package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class FileGeneral (
        val attachmentID: Int?,
        val name: String,
        val file: String,
        val size:Long
): Serializable

data class FileGeneralPost (
        val filename: String,
        val content: String
): Serializable