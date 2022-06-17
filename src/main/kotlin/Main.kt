fun main(){
    println("Welcome to UnitConverter: " +
            "You can convert: temperature, length, area, volume, and mass, using commands.")
    while (true) {
        val input: String = readln()
        val typed = input.split(" ").toTypedArray();
        if (typed.size == 3 && typed[0] == "cvt"){
            UnitConverter.conversionRouter(typed)
        } else {
            println("Proper usage: <cvt> <xu1> <u2>, x = any number, u1 = unit 1, u2 = unit 2")
        }
    }
}