package com.wasim.firbasedemoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
// database referance
    var database: FirebaseDatabase? = null
    private var userId: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database?.setPersistenceEnabled(true)
        database = FirebaseDatabase.getInstance()
        val referece = database!!.getReference("data")


// addd data
        buttonSave.setOnClickListener {
            userId = referece.push().key.toString()
            val x = et.text.toString().trim()
            val y = et2.text.toString().trim()
            val newUser = referece.child(userId)
            newUser.child("first_name").setValue(x)
            newUser.child("second_name").setValue(y)
        }
//        update data from database
        btn_update.setOnClickListener{
            val x = et.text.toString().trim()
            val yMVQvmqfCPqlNgEPf5eS = et2.text.toString().trim()
            val newUser = referece.child("-")
            newUser.child("first_name").setValue(x)
            newUser.child("second_name").setValue(y)
        }
        btn_delete.setOnClickListener{
            referece.child("-MVQvmqfCPqlNgEPf5eS").removeValue()
        }

btn_move.setOnClickListener{
//   navigation on one activity to anorther
    startActivity(Intent(this,MainActivity2::class.java))
}
//        show data from databa in mobile
        btn_fetch.setOnClickListener {
                referece.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        //  println(snapshot)
                        snapshot.children.forEach {
                            val user = it.getValue(UserInfo::class.java)
                            println("single user : ${user?.first_name.toString()} ${user?.secondName.toString()}")
                            tv_output.append("${user?.first_name.toString()} ${user?.secondName.toString()}\n")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("error", error.toString())
                    }

                })
        }
    }
}