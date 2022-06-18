fun main(){
    println("Welcome to UnitConverter v1.0 \n" +
            "You can convert: temperature, length, area, volume, and mass, using commands. \n" +
            "Available commands: 'cvt', 'help', 'about', 'chkupd'")
    while (true) {
        val stringInput: String = readln()
        Commands.runCommand(stringInput)
    }
}