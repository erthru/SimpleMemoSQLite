package online.erthru.simplememosqlite.persistence

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import online.erthru.simplememosqlite.model.Memo

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"db_memo",null,1){

    companion object {
        val TABLE_MEMO = "tb_memo"
        val MEMO_ID = "memo_id"
        val MEMO_TITLE = "memo_title"
        val MEMO_BODY = "memo_body"
        val MEMO_CREATED_AT = "memo_created_at"
        val MEMO_UPDATED_AT = "memo_updated_at"
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val addTableMemo = "CREATE TABLE $TABLE_MEMO (" +
                "$MEMO_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "$MEMO_TITLE TEXT," +
                "$MEMO_BODY TEXT," +
                "$MEMO_CREATED_AT TIMESTAMP," +
                "$MEMO_UPDATED_AT TIMESTAMP" +
                ")"

        p0?.execSQL(addTableMemo)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun newMemo(title:String?, body:String?){

        val db = writableDatabase
        db.execSQL("INSERT INTO $TABLE_MEMO ($MEMO_TITLE,$MEMO_BODY,$MEMO_CREATED_AT,$MEMO_UPDATED_AT) VALUES ('$title','$body',datetime('now'),datetime('now'))")

    }

    fun updateMemo(id:String?, title:String?, body:String?){

        val db = writableDatabase
        db.execSQL("UPDATE $TABLE_MEMO SET $MEMO_TITLE='$title', $MEMO_BODY='$body', $MEMO_UPDATED_AT=datetime('now') WHERE $MEMO_ID=$id")

    }

    fun deleteMemo(id:String?){

        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_MEMO WHERE $MEMO_ID=$id")

    }

    fun allMemo() : ArrayList<Memo>{

        var arrayList = ArrayList<Memo>()

        val db = writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_MEMO ORDER BY $MEMO_ID DESC",null)

        if(cursor.moveToFirst()){

            for(i in 0 until cursor.count){
                arrayList.add(Memo(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)))
                cursor.moveToNext()
            }

        }

        return arrayList

    }

}
