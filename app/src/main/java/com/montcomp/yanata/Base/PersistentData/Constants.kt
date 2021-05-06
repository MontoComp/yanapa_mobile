package com.hcr.hcr_android.Base.PersistentData


class Constants {
    class Service {
        class BaseURL {
            companion object {
                val pre = "https://pre-ehe.everincloud.com/"
                val pro = "https://remotehs.es/"
            }
        }
    }

    enum class PersonType(val type:String) {
        professional("professionals"),
        patients("patients"),
        group("group")

    }

    enum class CuestionnaireState(val type: String){

        finished("finished"),
        pending("pending"),
        current("current")

    }


}

