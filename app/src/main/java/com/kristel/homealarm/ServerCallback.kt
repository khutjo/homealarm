package com.kristel.homealarm


import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray
import org.json.JSONException

interface ServerCallback {
    fun onSuccess(result: JSONArray)
}