package com.hcr.hcr_android.Base.PersistentData

import android.app.Application
import java.util.ArrayList

enum class Keys {
    nothing;

    enum class UserData(var value: String) {
        TOKEN(""),
        PROFILE(""),
        NAME(""),
        NOTREAD("0"),
        origin(""),
        VIDEO(""),
        ENTITYCODE(""),
        ENTITYCODE2(""),
        SURNAME(""),
        SURNAME1(""),
        MYNAME("")
    }

    enum class FormEnabled(var value: Boolean){
        FORMENABLED(false)
    }
    enum class Alerts (var value: Int){
        PENDINGFORMS(0),
        FINISHEDFORMS(0)
    }

    enum class MyhomeEneable(var value: Boolean){
        MYHOMEENEABLE(true)
    }

    enum class LoginSession(var value: Boolean){
        SESSION(false)
    }
    enum class VersionUpdate(var value: Boolean){
        VERSIONUPDATE(true)
    }
    enum class  UserIdData(var value: Int){
        USERID(0)

    }
    enum class  CodId(var value: Int){
        CODID(0)

    }
    enum class UserMailData(var value: Long){
        MAILBOX(0)
    }

    /*enum class MenuElement(var value: ArrayList<MenuElements>){
        MENUELEMENT(ArrayList<MenuElements>())
    }*/

    enum class ApiEndpointStun(var value: String){
        URLSTUN("")
    }

    enum class ApiEndpointNN(var value: String){
        URLNN("")
    }

    enum class BaseURL(val value:String) {
        DEV("LOL"),
        PRO("https://remotehs.es/"),
        PRE("https://pre-ehe.everincloud.com/"),
        QA("https://remotehs-ar.ehcos.com/"),
        //QA("http://10.115.88.69:8080/"),
        LOCAL("LOL")
    }
    // Nueva ruta QA Agregando para pruebas
    //https://remotehs-ar.ehcos.com/remotehs-web/pages/public/main/index.zul?entity=defaultEntity //QA

    enum class UrlBase(var value: String){
        URL("http://192.168.1.4:3000/")
        //URL("http://10.0.2.2:3000/")
        //URL("http://192.168.243.20:6083/api/v1/")
    }
    enum class Parasarchivos(var value:String) {
        PAS("")
    }
}


class PersistentData: Application() {
    init { println("This ($this) is a singleton") }

    private object Holder {
        val INSTANCE = PersistentData()
    }

    companion object {
        val instance: PersistentData by lazy {
            Holder.INSTANCE
        }
    }

    val baseURL: String by lazy {
        Keys.BaseURL.QA.value
    }

    val parapasararchivos=Keys.Parasarchivos.PAS

    var urlBase = Keys.UrlBase.URL
    var codid=Keys.CodId.CODID
    var myname = Keys.UserData.MYNAME
    var userToken = Keys.UserData.TOKEN
    var userID = Keys.UserIdData.USERID


    var userName = Keys.UserData.NAME

    var userProfile = Keys.UserData.PROFILE
    var notRead = Keys.UserData.NOTREAD
    var pendingForms = Keys.Alerts.PENDINGFORMS
    var finishedForms = Keys.Alerts.FINISHEDFORMS
    var mailbox = Keys.UserMailData.MAILBOX
    var origin = Keys.UserData.origin
    var video = Keys.UserData.VIDEO
    //var menuElement = Keys.MenuElement.MENUELEMENT

    var loginSession = Keys.LoginSession.SESSION
    var versionUpdate = Keys.VersionUpdate.VERSIONUPDATE
    var surname = Keys.UserData.SURNAME
    var surname1 = Keys.UserData.SURNAME1
    var entityCode= Keys.UserData.ENTITYCODE
    var entityCode2= Keys.UserData.ENTITYCODE2
    var apiEndpointStun = Keys.ApiEndpointStun.URLSTUN
    var apiEndpointNN = Keys.ApiEndpointNN.URLNN

    var formEnabled = Keys.FormEnabled.FORMENABLED

    var myhome = Keys.MyhomeEneable.MYHOMEENEABLE
}