class Commands {
    companion object {
        private fun stringInputToArray(stringInput:String):Array<String> {
            return stringInput.split(" ").toTypedArray()
        }

        fun runCommand(inputString:String) {
            val commandArguments:Array<String> = stringInputToArray(inputString)

            when (commandArguments[0].lowercase()) {
                "cvt" -> runUnitConverter(inputString, commandArguments)
                "help" -> runHelp(inputString, commandArguments)
                "about" -> runAboutSection(inputString, commandArguments)
                "chkupd" -> checkForUpdates(inputString, commandArguments)
                else -> {
                    println("'${commandArguments[0]}' is an invalid command.\n\n" +
                            "Available commands:\n" +
                            "'cvt', 'help', 'about', 'chkupd'")
                }
            }
        }

        private fun runUnitConverter(inputString:String, arguments:Array<String>){
            if (arguments.size == 3) {
                UnitConverter.start(arguments)
            } else {
                println("Error: '$inputString' command invalid.\n" +
                        "cvt command syntax: cvt <<val><unit1>> <unit2>")
            }
        }

        private fun runHelp(inputString:String, arguments:Array<String>) {
            if (arguments.size == 1) {
                HelpCommand.start()
            } else {
                println("Error: '$inputString' command invalid.\n" +
                        "Type 'help'")
            }
        }

        private fun runAboutSection(inputString:String, arguments:Array<String>) {
            if (arguments.size == 1) {
                println("About UnitConverter: Version 1.0\n" +
                        "Built in Kotlin 1.7.0\n" +
                        "Copyright © 2022 jrt345. All rights reserved.\n" +
                        "UnitConverter is licensed under the GNU General Public License v3.0")
            } else {
                println("Error: '$inputString' command invalid.\n" +
                        "Type 'about'")
            }
        }

        private fun checkForUpdates(inputString:String, arguments:Array<String>) {
            if (arguments.size == 1) {
                if (UpdateChecker.isUpdateAvailable()) {
                    println("A new version of UnitConverter has been released.\n" +
                            "Version ${UpdateChecker.getLatestVersion()} is available at:\n" +
                            "https://github.com/jrt345/UnitConverter/releases/latest")
                } else {
                    println("There are no new updates.")
                }
            } else {
                println("Error: '$inputString' command invalid.\n" +
                        "Type 'chkupd'")
            }
        }
    }
}