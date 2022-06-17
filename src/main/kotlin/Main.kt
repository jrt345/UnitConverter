fun main(){
    println("Welcome to UnitConverter: " +
            "You can convert: temperature, length, area, volume, and mass, using commands.")
    while (true) {
        val inputString: String = readln()
        CommandRouter.commandRouter(inputString)
    }
}