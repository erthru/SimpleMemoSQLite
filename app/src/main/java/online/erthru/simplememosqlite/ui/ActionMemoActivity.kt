package online.erthru.simplememosqlite.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_action_memo.*
import online.erthru.simplememosqlite.R
import online.erthru.simplememosqlite.persistence.DatabaseHelper

class ActionMemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_memo)

        val i = intent
        var id:String? = null
        if(i.getStringExtra("mode").equals("2")){
            layoutEditAM.visibility = View.VISIBLE
            btnSaveAM.visibility = View.GONE
            edTitleAM.setText(i.getStringExtra("title"))
            edBodyAM.setText(i.getStringExtra("body"))
            id = i.getStringExtra("id")
            tvTitleAM.setText("Update Memo")
        }

        imgCloseAM.setOnClickListener { this.finish() }

        btnSaveAM.setOnClickListener {

            if(edTitleAM.text.toString().isNullOrBlank()){
                Toast.makeText(applicationContext,"Empty title not allowed",Toast.LENGTH_SHORT).show()
                edTitleAM.setError("Required field")
            }else if(edBodyAM.text.toString().isNullOrBlank()){
                Toast.makeText(applicationContext,"Empty content not allowed",Toast.LENGTH_SHORT).show()
                edBodyAM.setError("Required field")
            }else{
                DatabaseHelper(this).newMemo(edTitleAM.text.toString(),edBodyAM.text.toString())
                Toast.makeText(applicationContext,"New memo created",Toast.LENGTH_SHORT).show()
                this.finish()
            }

        }

        btnUpdateAM.setOnClickListener {

            if(edTitleAM.text.toString().isNullOrBlank()){
                Toast.makeText(applicationContext,"Empty title not allowed",Toast.LENGTH_SHORT).show()
                edTitleAM.setError("Required field")
            }else if(edBodyAM.text.toString().isNullOrBlank()){
                Toast.makeText(applicationContext,"Empty content not allowed",Toast.LENGTH_SHORT).show()
                edBodyAM.setError("Required field")
            }else{
                DatabaseHelper(this).updateMemo(id,edTitleAM.text.toString(),edBodyAM.text.toString())
                Toast.makeText(applicationContext,"Memo updated",Toast.LENGTH_SHORT).show()
                this.finish()
            }

        }

        btnDeleteAM.setOnClickListener {

            AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Delete this memo ?")
                    .setPositiveButton("DELETE",DialogInterface.OnClickListener { dialogInterface, i ->
                        DatabaseHelper(this).deleteMemo(id)
                    })
                    .setNegativeButton("CANCEL",DialogInterface.OnClickListener { dialogInterface, i ->
                        dialogInterface.dismiss()
                    })
                    .show()

        }

    }
}
