package com.montcomp.yanata

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.montcomp.yanata.Base.NetworkLayer.DisposableActivity


class SplashScreenActivity : DisposableActivity() {


    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 5000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler()


        try{
            if (!intent.extras.isEmpty){
                //WebRTCallActivity.startIncomingCall(this, intent.extras);

                val mRunnableNotify: Runnable = Runnable {
                    if (!isFinishing) {
                        val intents = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intents)
                        finish()
                    }
                }
                mDelayHandler!!.postDelayed(mRunnableNotify, SPLASH_DELAY)
            }

        }catch (E: IllegalStateException){
            mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        }

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
