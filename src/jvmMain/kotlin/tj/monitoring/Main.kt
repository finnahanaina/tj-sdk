package tj.monitoring

class Main(private val name : String) {

    fun checkRunning():String{
        return "Hei $name, It's Working!";
    }

    fun booting():String{
        return "Booting Called"
    }

    fun heartbeat():String{
        return "Heartbeat Called"
    }

    fun transaction():String{
        return "Transaction Called"
    }

}
