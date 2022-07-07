package com.example.inflearncode.level1.dice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.inflearncode.R
import com.example.inflearncode.databinding.ActivityDiceBinding
import kotlin.random.Random

class DiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiceBinding   // 바인딩 하기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dice)  // 바인딩 하기

        val diceImage1 = binding.dice1
        val diceImage2 = binding.dice2

        binding.diceStartBtn.setOnClickListener{

            val number1 = Random.nextInt(1, 6)
            val number2 = Random.nextInt(1, 6)

            when(number1){
                1 -> diceImage1.setImageResource(R.drawable.dice_1)
                2 -> diceImage1.setImageResource(R.drawable.dice_2)
                3 -> diceImage1.setImageResource(R.drawable.dice_3)
                4 -> diceImage1.setImageResource(R.drawable.dice_4)
                5 -> diceImage1.setImageResource(R.drawable.dice_5)
                else -> diceImage1.setImageResource(R.drawable.dice_6)
            }

            when(number2){
                1 -> diceImage2.setImageResource(R.drawable.dice_1)
                2 -> diceImage2.setImageResource(R.drawable.dice_2)
                3 -> diceImage2.setImageResource(R.drawable.dice_3)
                4 -> diceImage2.setImageResource(R.drawable.dice_4)
                5 -> diceImage2.setImageResource(R.drawable.dice_5)
                else -> diceImage2.setImageResource(R.drawable.dice_6)
            }

            Toast.makeText(this, "주사위 돌리기", Toast.LENGTH_SHORT).show()
        }

    }
}