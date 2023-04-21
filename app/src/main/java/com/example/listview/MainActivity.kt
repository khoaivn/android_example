package com.example.listview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.database.Cursor
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        install_datebase()
        get_data()

//        val listView = findViewById<ListView>(R.id.list_view)
//
//        val language = arrayOf<String>("C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
//        val description = arrayOf<String>(
//            "C programming is considered as the base for other programming languages",
//            "C++ is an object-oriented programming language.",
//            "Java is a programming language and a platform.",
//            ".NET is a framework which is used to develop software applications.",
//            "Kotlin is a open-source programming language, used to develop Android apps and much more.",
//            "Ruby is an open-source and fully object-oriented programming language.",
//            "Ruby on Rails is a server-side web application development framework written in Ruby language.",
//            "Python is interpreted scripting  and object-oriented programming language.",
//            "JavaScript is an object-based scripting language.",
//            "PHP is an interpreted language, i.e., there is no need for compilation.",
//            "AJAX allows you to send and receive data asynchronously without reloading the web page.",
//            "Perl is a cross-platform environment used to create network and server-side applications.",
//            "Hadoop is an open source framework from Apache written in Java."
//        )
//
//        val imageId = arrayOf<Int>(
//            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
//            R.drawable.ic_launcher_background
//        )
//
////        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
//        listView.adapter =  MyListAdapter(this, language, description, imageId)
    }
    fun install_datebase(){
//        val dbHelper = MyDatabaseHelper(this)
//        val db = dbHelper.writableDatabase
//
//        val values = ContentValues()
//        values.put("name", "Pham Trung Dung")
//        values.put("email", "dung.phamtrung@phenikaa-uni.edu.vn")
//
//        db.insert("users", null, values)

        val database = Firebase.database
        val myRef = database.getReference("/")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue<String>()
                Log.d("TAG", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }
    fun get_data(){
//        val dbHelper = MyDatabaseHelper(this)
//        val db = dbHelper.readableDatabase
//
//        val cursor: Cursor = db.rawQuery("SELECT * FROM users", null)
//
//        while (cursor.moveToNext()) {
//            val id: Int = cursor.getInt(cursor.getColumnIndex("id"))
//            val name: String = cursor.getString(cursor.getColumnIndex("name"))
//            val email: String = cursor.getString(cursor.getColumnIndex("email"))
//            Log.d("MyApp", "id: $id, name: $name, email: $email")
//        }
//
//        cursor.close()
//        db.close()

    }
}

class MyListAdapter(private val context: Activity, private val title: Array<String>, private val description: Array<String>, private val imgid: Array<Int>)
    : ArrayAdapter<String>(context, R.layout.item_listview, title) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.item_listview, null, true)


        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = title[position]
        imageView.setImageResource(imgid[position])
        subtitleText.text = description[position]

        return rowView
    }
}
//class MyListAdapter(private val context: Context, private val items: List<String>) : BaseAdapter() {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val view: View
//        val viewHolder: ViewHolder
//
//        if (convertView == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false)
//            viewHolder = ViewHolder(view)
//            view.tag = viewHolder
//        } else {
//            view = convertView
//            viewHolder = convertView.tag as ViewHolder
//        }
//
//        viewHolder.itemTextView.text = items[position]
//
//        return view
//    }
//
//    override fun getItem(position: Int): Any {
//        return items[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getCount(): Int {
//        return items.size
//    }
//
//    private class ViewHolder(view: View) {
//        val itemTextView: TextView = view.findViewById(R.id.text1)
//    }
//}