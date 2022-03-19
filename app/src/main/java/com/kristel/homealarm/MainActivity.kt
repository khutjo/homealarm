package com.kristel.homealarm



import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.kristel.homealarm.databinding.ActivityMainBinding
import org.json.JSONArray


class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var parentLinearLayout: LinearLayout? = null
    private var htppreq: HttpRequestHendler? = null
    var pb: ProgressBar? = null
    val ends = mutableMapOf<Int, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parentLinearLayout = findViewById(R.id.parent_linear_layout)

        pb = findViewById(R.id.pgressbar)
        ends.clear()
        val htppreq = HttpRequestHendler(this)


        htppreq.sendrequest("posttest", callback = object : ServerCallback {
                override fun onSuccess(result: JSONArray) {
                    for (i in 0 until result.length()) {

                        pb?.visibility = ProgressBar.INVISIBLE;
                        val employee = result.getJSONObject(i)
                        addLinearContent(employee.getString("exfirstName"), i)
                    }
                }
            })



    }

    fun addLinearContent(massage: String, buttonID: Int){
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
        ends.put(buttonID, massage)
        Log.i("button id","test " + massage + " "+buttonID )
        btnTag.width = 1100
        btnTag.setText(massage)
        btnTag.setId(buttonID)
        btnTag.setOnClickListener(this);
//        row.addView(btnTag)


        parentLinearLayout!!.addView(btnTag)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {

            Log.i("button idb","test " + ends + " "+ends[p0.id ] +p0.id )

        }

    }


//    override fun onClick(p0: View?) {
//        Log.i("button idb","$p0")
//    }
//
//    override fun onClick(p0: DialogInterface?, p1: Int) {
//        Log.i("button ida","$p1")
//    }
}




