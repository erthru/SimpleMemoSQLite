package online.erthru.simplememosqlite.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import online.erthru.simplememosqlite.R
import online.erthru.simplememosqlite.adapter.RecyclerViewMain
import online.erthru.simplememosqlite.persistence.DatabaseHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgAddMain.setOnClickListener {
            val i = Intent(this,ActionMemoActivity::class.java)
            i.putExtra("mode","1")
            startActivity(i)
        }

        rvMain.layoutManager = LinearLayoutManager(this)

    }

    override fun onResume() {
        super.onResume()
        val adapter = RecyclerViewMain(this,DatabaseHelper(this).allMemo())
        adapter.notifyDataSetChanged()
        rvMain.adapter = adapter
    }

}
