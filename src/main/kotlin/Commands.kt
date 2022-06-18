class Commands {
    companion object {
        private fun stringInputToArray(stringInput:String):Array<String> {
            return stringInput.split(" ").toTypedArray()
        }

        fun runCommand(inputString:String) {
            val commandArguments:Array<String> = stringInputToArray(inputString)

            when (commandArguments[0].lowercase()) {
                "cvt" -> runUnitConverter(commandArguments)
                "help" -> runHelp()
                "about" -> runAboutSection()
                "chkupd" -> checkForUpdates()
                else -> {
                    println("'${commandArguments[0]}' is an invalid command\n")
                    println("Available commands:")
                    println("'cvt', 'help', 'about', 'chkupd'")
                }
            }
        }

        private fun runUnitConverter(arguments:Array<String>){
            if (arguments.size == 3) {
                UnitConverter.start(arguments)
            } else {
                println("Cvt command is invalid, contains ${arguments.size} arguments")
                println("Cvt command syntax: cvt <val><unit1> <unit2>")
            }

        }

        private fun runHelp() {

        }

        private fun runAboutSection() {
            println("About UnitConverter: Version 1.0")
            println("Built in Kotlin 1.7.0")
            println("Copyright © 2022 jrt345. All rights reserved.")
            println("UnitConverter is licensed under the GNU General Public License v3.0")
        }

        private fun checkForUpdates() {

        }
    }
}