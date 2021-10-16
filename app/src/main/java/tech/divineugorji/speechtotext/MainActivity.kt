package tech.divineugorji.speechtotext


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import tech.divineugorji.speechtotext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.voiceButton.setOnClickListener {
            val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speechIntent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "voice output")
            startForResult.launch(speechIntent)
        }
     setContentView(view)
    }

    var startForResult = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        if (result != null && result.resultCode == RESULT_OK) {
            assert(result.data != null)
            val matches = result.data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            binding.textOutput.text = matches!![0].toString()
        }
    }
}