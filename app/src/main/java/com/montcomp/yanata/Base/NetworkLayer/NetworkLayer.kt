package com.montcomp.yanata.Base.NetworkLayer

import com.hcr.hcr_android.Base.PersistentData.PersistentData
import com.montcomp.yanata.Base.ComonDomain.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


var apiService=APIService.create()
var disposable: Disposable? = null


interface APIService {


    companion object {
        fun create(): APIService {

            if(PersistentData.instance.urlBase.value == null || PersistentData.instance.urlBase.value == ""){
                PersistentData.instance.urlBase.value = "https://remotehs.es/"
            }

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(PersistentData.instance.urlBase.value)
                    .client(getUnsafeOkHttpClient())
                    .build()
            return retrofit.create(APIService::class.java)

        }

        private fun getUnsafeOkHttpClient(): OkHttpClient? {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                        object : X509TrustManager {
                            @Throws(CertificateException::class)
                            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                            }

                            @Throws(CertificateException::class)
                            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                            }

                            override fun getAcceptedIssuers(): Array<X509Certificate> {
                                return arrayOf()
                            }
                        }
                )
                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.sslSocketFactory(sslSocketFactory)
                builder.hostnameVerifier(object : HostnameVerifier {
                    override fun verify(hostname: String?, session: SSLSession?): Boolean {
                        return true
                    }
                })
                val okHttpClient = builder.build()
                okHttpClient.newBuilder()
                        .connectTimeout(180, TimeUnit.SECONDS)
                        .readTimeout(180, TimeUnit.SECONDS)
                        .writeTimeout(180, TimeUnit.SECONDS)
                        .build()
                okHttpClient

            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    }



    @POST("user/login")
    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )
    fun loginEntity(@Body request: LoginRequestZentros): Observable<LoginResponseZentros>

    @GET("people")
    fun mypeople(): Observable<List<PeopleResponse>>

    @POST("people")
    @Headers(
            "Accept: application/json",
            "Content-type:application/json"
    )
    fun mypostpeople(@Body request: PeopleResponse): Observable<Any>

    @GET("posts")
    fun myposts(): Observable<List<ProjectsResponse>>

    @POST("posts")
    @Headers(
            "Accept: application/json",
            "Content-type:application/json"
    )
    fun mypostssend(@Body request: ProjectsRequest): Observable<Any>

    @GET("forum")
    fun myforum(): Observable<List<ForumResponse>>

    @POST("forum")
    @Headers(
            "Accept: application/json",
            "Content-type:application/json"
    )
    fun mypostforum(@Body request: ForumRequest): Observable<Any>

    @GET("posts")
    fun myprojectsnames(): Observable<List<ProjectsResponse>>

    @GET("people/{district}")
    fun mydistrict(@Path("district") district: String): Observable<List<PeopleResponse>>

}