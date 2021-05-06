package com.montcomp.yanata.Base.ComonDomain

data class FormGarageResponse(
    val roadTypes: List<ProvMunResponse>,
    val plants: List<String>,
    val provinces:List<ProvMunResponse>
)

data class ProvMunResponse(
    val code: Int,
    val name: String
)

data class PostFormGarage(
    val roadTypeCode: Int,
    val roadName: String,
    val number:String,
    val qualifier:String?,
    val stairs:String?,
    val plant:String,
    val gateway :String,
    val places:Int,
    val postalCode:String?,
    val provinceCode:Int,
    val municipalityCode:Int,
    val observations:ArrayList<String>?,
    val attachments:ArrayList<FileGeneralPost>

)

data class ErrorResponse(
    val message: String?
)
