class HelpCommand {
    companion object {
        fun start() {
            while (true) {
                println("Type the number of one of the following options to learn more about:\n" +
                        "[1]Cvt command\n" +
                        "[2]About command\n" +
                        "[3]Chkupd command\n" +
                        "[4]Exit help menu")

                val stringInput:String = readln()

                try {
                    val intInput = stringInput.toInt()

                    if (intInput == 4){
                        println("Exited help menu, you may type a command now:")
                        break
                    } else if (intInput in 1..3) {

                    } else {
                        println("'$intInput' is out of range, try again")
                    }
                } catch (e: NumberFormatException) {
                    println("'$stringInput' is an invalid option, try again")
                }
            }
        }
    }
}