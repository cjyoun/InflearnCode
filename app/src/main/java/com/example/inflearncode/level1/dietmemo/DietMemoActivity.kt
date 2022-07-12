package com.example.inflearncode.level1.dietmemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.inflearncode.R
import com.example.inflearncode.level1.dietmemo.model.DataModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.DayOfWeek
import java.time.Year
import java.util.*

class DietMemoActivity : AppCompatActivity() {

    private val dataModelList = mutableListOf<DataModel>()  // adapter에 넣어줄 모델

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_memo)

        // firebase database
        val database = Firebase.database
        val myRef = database.getReference("myMemo")

//      myRef.setValue("~~~~")  // database에 데이터 넣기 (중복된 데이터는 더 쌓이지 않음)
//      myRef.push().setValue("!!!!!")  // database에 중복되더라도 입력되는대로 데이터 넣기

        val listView = findViewById<ListView>(R.id.memoListView)

        val memoAdapter = MemoListViewAdapter(dataModelList)

        listView.adapter = memoAdapter

        // database 값 가져오기 (.child(Firebase.auth.currentUser!!.uid) 를 붙여줌으로 현재 유저에 해당하는 값을 가져오기)
        myRef.child(Firebase.auth.currentUser!!.uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                dataModelList.clear()   // 이전에 저장된 값들 초기화화

               for (dataModel in snapshot.children){
                    Log.d("Data", dataModel.toString())
                    dataModelList.add(dataModel.getValue(DataModel::class.java)!!) // dataModelList에 firebase에서 가져온 값 넣기
                }
                memoAdapter.notifyDataSetChanged() // 데이터 모델리스트가 다 넣어지면 adapter를 새롭게 생성하라는 기능.
                Log.d("DataModel", dataModelList.toString())
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


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
                val myRef = database.getReference("myMemo").child(Firebase.auth.currentUser!!.uid) // 데이터베이스에 값이 쌓일 때 현재 유저의 uid값에 데이터가 쌓이도록

                val model = DataModel(dateText, healthMemo)
                myRef.push().setValue(model)    // 모델값 database에 저장하기

                mAlertDialog.dismiss() // dialog 끄기

            }



        }

    }
}