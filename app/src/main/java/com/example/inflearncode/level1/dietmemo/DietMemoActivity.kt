package com.example.inflearncode.level1.dietmemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.inflearncode.R
import com.example.inflearncode.level1.dietmemo.model.DataModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.DayOfWeek
import java.time.Year
import java.util.*

class DietMemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_memo)


        val writeBtn = findViewById<ImageView>(R.id.writeBtn)
        writeBtn.setOnClickListener {

            // 다이얼로그 출력하기
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.cutom_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView).setTitle("운동 메모 다이얼로그")

            val mAlertDialog = mBuilder.show()

            var dateText = ""
            // custom dialog에 있는 id값에 해당되는 것 가져오기
            val dateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)
            // date picker 보이기
            dateSelectBtn?.setOnClickListener {

                // 날짜 선택하기
                val today = GregorianCalendar()
                val year : Int = today.get(Calendar.YEAR)
                val month : Int = today.get(Calendar.MONTH)
                val day : Int = today.get(Calendar.DATE)

                val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfWeek: Int) {
                        Log.d("DietMemo-cutomDialog", "$year , ${month+1} , $dayOfWeek")

                        dateSelectBtn.text = "$year - ${month+1} - $dayOfWeek"  // 버튼의 text 값 변경

                        dateText = "$year - ${month+1} - $dayOfWeek"    // 날짜 값

                    }
                }, year, month, day)
                dlg.show()

            }


            // firebase database 저장
            val saveBtn = mAlertDialog.findViewById<Button>(R.id.dateSaveBtn)

            saveBtn?.setOnClickListener {

                val healthMemo = mAlertDialog.findViewById<EditText>(R.id.healthMemo).toString()   // 메모 내용

                // firebase database
                val database = Firebase.database
                val myRef = database.getReference("message")

//                myRef.setValue("~~~~")  // database에 데이터 넣기 (중복된 데이터는 더 쌓이지 않음)
//                myRef.push().setValue("!!!!!")  // database에 중복되더라도 입력되는대로 데이터 넣기

                val model = DataModel(dateText, healthMemo)
                myRef.push().setValue(model)    // 모델값 database에 저장하기

            }



        }

    }
}