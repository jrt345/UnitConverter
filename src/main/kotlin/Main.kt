fun main(){
    println("Welcome to UnitConverter: " +
            "You can convert: temperature, length, area, volume, and mass, using commands.")
    while (true) {
        val stringInput: String = readln()
        Commands.runCommand(stringInput)
    }
}