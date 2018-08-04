package online.erthru.simplememosqlite.util

class FixString{

    companion object {

        fun fixDate(date:String?) : String?{

            val month = HashMap<String, String>()
            month.put("01","January")
            month.put("02","February")
            month.put("03","March")
            month.put("04","April")
            month.put("05","May")
            month.put("06","June")
            month.put("07","July")
            month.put("08","August")
            month.put("09","September")
            month.put("10","October")
            month.put("11","November")
            month.put("12","December")

            return date?.substring(8,10) +" "+month[date?.substring(5,7)] +" "+date?.substring(0,4)+" | "+date?.substring(11)

        }

    }

}