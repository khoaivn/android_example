package com.example.listview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        install_database(this)

        val imageId = arrayOf<Int>(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

    }

    private fun install_database(context: Activity) {

        val students_list = mutableListOf<Student>()
        val database = Firebase.database
        val myRef = database.getReference("/")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val students_data = dataSnapshot.child("students")
                val childIterator = students_data.children.iterator()
                while (childIterator.hasNext()) {
                    val childSnapshot = childIterator.next()
                    val name = childSnapshot.child("name").getValue(String::class.java)
                    val code = childSnapshot.child("code").getValue(String::class.java)
                    students_list.add(Student(name, code))
                }
                println(students_list.get(0).name)
                val listView = findViewById<ListView>(R.id.list_view)
                listView.adapter = MyAdapter(context, students_list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }
}
class MyAdapter(context: Context, private val students_list: List<Student>) :
    ArrayAdapter<Student>(context, 0, students_list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false)
        }

        val item = students_list[position]

        val tv_title = view?.findViewById<TextView>(R.id.title)
        val tv_description = view?.findViewById<TextView>(R.id.title)
        tv_title!!.text = item.name
        tv_description!!.text = item.code

        return view!!
    }
    override fun getCount(): Int {
        return students_list.size
    }
}


