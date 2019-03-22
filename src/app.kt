fun main(args: Array<String>) {
    val timeInt = 90001
    print(formatTime(timeInt))
}

fun formatTime (timeInSeconds: Int): String{
    //returns a correctly formatted sentence describing the time in years, days, hours, minutes & seconds
    return renderStringToSentence(createStrings(timeInSeconds))
}

fun createStrings (timeInSeconds:Int): List<String>{
    // calculate the number of seconds, minutes, hours and days
    // create a TimeThing object for each
    // then return a list of non-empty strings describing each of the time things
    val secText:String  = TimeThing(unit = "Second", amount = timeInSeconds.rem(60)).toString()
    val minText:String  = TimeThing(unit = "Minute", amount = (timeInSeconds/60).rem(60)).toString()
    val hourText:String = TimeThing(unit = "Hour",   amount = (timeInSeconds/(60*60)).rem(24)).toString()
    val dayText:String  = TimeThing(unit = "Day",    amount = (timeInSeconds/(24*60*60)).rem(365)).toString()
    val yearText:String = TimeThing(unit = "Year",   amount = (timeInSeconds/(365*24*60*60))).toString()

    return listOf(yearText,dayText,hourText,minText,secText).filterNot { it.toString() == "" }
}

// function to convert any list of strings into a sentence where they are separated with commas, and "and" before the last word
fun renderStringToSentence(args: List<String>): String{
    var i = 0
    var sentence = ""
    while (i < args.size){
            if (i < (args.size - 2)) sentence = sentence + (args[i]) + ", "
            if (i == (args.size - 2)) sentence = sentence + (args[i]) + " and "
            if (i == (args.size - 1)) sentence = sentence + (args[i])
        i++
    }
    return sentence
}

// data class to hold the time units, and to print them accordingly (with plurals)
class TimeThing(val unit: String, val amount: Int) {
    override fun toString(): String {
        when (amount) {
            0 -> return ("")
            1 -> return (amount.toString() + " " + unit)
            else -> {return (amount.toString() + " " + unit + "s")}
        }
    }
}