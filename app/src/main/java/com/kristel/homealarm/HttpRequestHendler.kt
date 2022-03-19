package com.kristel.homealarm


import android.content.Context
import android.util.Log
import android.view.View
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import com.kristel.homealarm.ServerCallback

class HttpRequestHendler {
    private var requestQueue: RequestQueue? = null
    private var context: Context? = null
    private val url = "https://espwebapi.azurewebsites.net/posttest"

    constructor(context: Context){
        this.context = context
        requestQueue = Volley.newRequestQueue(context)
    }

    fun sendrequest(massage: String,callback: ServerCallback){
        val request = object : JsonObjectRequest(Method.POST, url, null, Response.Listener {
                response ->try {
            callback.onSuccess(response.getJSONArray("extra"))

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("JSONException",e.printStackTrace().toString())
        }
        }, Response.ErrorListener {
                error -> error.printStackTrace()
            Log.e("ErrorListener",error.printStackTrace().toString())
        }){
            // Providing Request Headers

            override fun getHeaders(): Map<String, String> {
                // Create HashMap of your Headers as the example provided below

                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                headers["app_key"] = "codekey"

                return headers
            }
        }
        requestQueue?.add(request)
    }

}