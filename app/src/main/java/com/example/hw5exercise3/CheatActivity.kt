package com.example.hw5exercise3

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hw5exercise3.databinding.ActivityCheatBinding


const val EXTRA_ANSWER_SHOWN =
    "com.example.hw5exercise1.answer_shown"

private const val EXTRA_ANSWER_IS_TRUE =
    "com.example.hw5exercise1.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCheatBinding
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//Binding and layout Inflator stayed the same as the last few activities
//Replaces set content view
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)


//Presenting the answer onClick and putting it in main activity
        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown:Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }

        setResult(Activity.RESULT_OK, data)

    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }




}