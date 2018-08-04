package online.erthru.simplememosqlite.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_main.view.*
import online.erthru.simplememosqlite.R
import online.erthru.simplememosqlite.model.Memo
import online.erthru.simplememosqlite.ui.ActionMemoActivity
import online.erthru.simplememosqlite.util.FixString
import java.util.*

class RecyclerViewMain(val context:Context, val arrayList: ArrayList<Memo>?) : RecyclerView.Adapter<RecyclerViewMain.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.list_main,parent,false))
    }

    override fun getItemCount(): Int = arrayList?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val memo = arrayList?.get(position)
        holder.v.tvTitleLM.text = memo?.title
        holder.v.tvBodyLM.text = memo?.body
        holder.v.tvDateLM.text = FixString.fixDate(memo?.updated_at)
        holder.v.setOnClickListener {
            val i = Intent(context,ActionMemoActivity::class.java)
            i.putExtra("mode","2")
            i.putExtra("id",memo?.id)
            i.putExtra("title",memo?.title)
            i.putExtra("body",memo?.body)
            context.startActivity(i)
        }

        holder.v.imgReminderLM.setOnClickListener {

            val calStartTime = Calendar.getInstance()
            val year = memo?.updated_at?.substring(0,4)?.toInt()
            val month = memo?.updated_at?.substring(5,7)?.toInt()
            val day = memo?.updated_at?.substring(8,10)?.toInt()
            val hour = memo?.updated_at?.substring(11,13)?.toInt()
            val minutes = memo?.updated_at?.substring(14,16)?.toInt()
            calStartTime.set(year!!,month!!-1,day!!,hour!!,minutes!!)

            val calEndTime = Calendar.getInstance()
            calEndTime.set(year!!,month!!-1,day!!,hour!!+2,minutes!!)

            val i = Intent(Intent.ACTION_EDIT)
            i.setType("vnd.android.cursor.item/event")
            i.putExtra("allDay", false)
            i.putExtra("rrule", "FREQ=YEARLY")
            i.putExtra("title", memo?.title)
            context?.startActivity(i)

        }

    }

    class Holder(val v:View) : RecyclerView.ViewHolder(v)

}