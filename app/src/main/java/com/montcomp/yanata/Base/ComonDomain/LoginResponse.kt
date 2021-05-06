package com.montcomp.yanata.Base.ComonDomain

import java.io.Serializable

data class LoginResponse(
        val resultCode:String?,//passtemporal
        val resultDescription: String?,
        val userID: Int,
        val token: String,
        val name: String?,
        val surname1: String?,
        val surname2: String?,
        val profile: String?,
        val listTut: ArrayList<LoginUsers>?,
        val video:String,
        val enabledChatbotTriage:Boolean,
        val videoGrupal :Boolean
):Serializable////aqui estaba :BaseDomain pero lo cambien para probar el resultcode

data class LoginTutResponse(    //queda por definir que va a devolver el servicio
        val resultDescription: String?,
        val userID: Int,
        val token: String,
        val name: String?,
        val surname1: String?,
        val surname2: String?,
        val profile: String?
):BaseDomain()

data class EntityResponse(
        val resultDescription: String?,
        val entity: ArrayList<EntityList>?
):BaseDomain()

data class EntityList(
        val id: Int?,
        val code: String?,
        val name: String?,
        val appEntityCode : String?
)
data class EntityResponse2(
        val version_android: String,
        val version_ios: String,
        val entities: ArrayList<EntityList2>?
):BaseDomain()

data class EntityList2(
        val appEntityCode : String?,
        val entity: String?,
        val name: String?,
        val appServer: String?,
        val videoServer: String?,
        val videoStun: String?,
        val videoTurn: String?,
        val videoNN: String?,
        var state : Boolean = false
)

data class LoginUsers(
        val userID: Int?,
        val name: String?,
        val surname1: String?,
        val surname2: String?
)
data class LoginRequest(
        val username: String,
        val password: String,
        val language: String
)

data class LoginEntityRequest(
        val username: String,
        val password: String,
        val language: String,
        val entity: String
)


data class LoginResponseZentros(
        val token:String,
        val user: UserLogin
):Serializable////aqui estaba :BaseDomain pero lo cambien para probar el resultcode

data class UserLogin(
        val codigoUsuario: Int,
        val nombreUsuario: String,
        val salt: Int?,
        val password: String,
        val email: String,
        val estado: String?,
        val fechaAlta: String?,
        val fechaBaja: String?,
        val codRolUsuario: Int,
        val pestanias: String?,
        val nombreRol: String?,
        val codPersona: Int,
        val nombrePersona:String,
        val activo: String
):Serializable

data class LoginRequestZentros(
        val username: String,
        val password: String
)