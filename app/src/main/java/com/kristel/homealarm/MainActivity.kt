package com.kristel.homealarm



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.kristel.homealarm.databinding.ActivityMainBinding

import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var parentLinearLayout: LinearLayout? = null
    var pb: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parentLinearLayout = findViewById(R.id.parent_linear_layout)

        pb = findViewById(R.id.pgressbar)

        val htppshit = HttpRequestHendler(this)


            htppshit.sendrequest("", callback = object : ServerCallback {
                override fun onSuccess(result: JSONArray) {
                    for (i in 0 until result.length()) {

                        pb?.visibility = ProgressBar.INVISIBLE;
                        val employee = result.getJSONObject(i)
                        addLinearContent(employee.getString("exfirstName"))
                    }
                }
            })



    }

    fun addLinearContent(massage: String){
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.field, null)

        val btnTag = Button(this)
        btnTag.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
//                1.0f
            )


        )

        btnTag.width = 1100
        btnTag.setText(massage)
        btnTag.setId(parentLinearLayout!!.childCount)
//        row.addView(btnTag)


        parentLinearLayout!!.addView(btnTag, parentLinearLayout!!.childCount - 1)
    }


}