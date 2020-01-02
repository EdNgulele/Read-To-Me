package com.example.edblack.readtome

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var textToSpeech: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this, this)

        btn_speak.setOnClickListener {
            speakOut()
        }
    }


    public override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = textToSpeech!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("textToSpeech", "This Language is not supported")
            } else {
                btn_speak.isEnabled = true
                speakOut()
            }

        } else {
            Log.e("textToSpeech", "Initilization Failed!")
        }

    }


    private fun speakOut() {
        var text = edit_text!!.text.toString();
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")

    }


}
