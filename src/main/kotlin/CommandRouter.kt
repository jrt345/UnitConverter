class CommandRouter {
    companion object {
        private fun inputToArguments(inputString:String):Array<String> {
            return inputString.split(" ").toTypedArray()
        }
        fun commandRouter(inputString:String) {
            val arguments:Array<String> = inputToArguments(inputString)

            when (arguments[0].lowercase()) {
                "cvt" -> conversionRouter(arguments)
                "help" -> helpRouter()
                "about" -> aboutSection()
                "chkupd" -> checkForUpdate()
                else -> {
                    println("'${arguments[0]}' is an invalid command\n")
                    println("Available commands:")
                    println("'cvt', 'help', 'about', 'chkupd'")
                }
            }
        }

        private fun conversionRouter(arguments:Array<String>){
            UnitConverter.conversionRouter(arguments)
        }

        private fun helpRouter() {
            TODO("Not yet implemented")
        }

        private fun aboutSection() {
            println("About UnitConverter: Version 1.0")
            println("Built in Kotlin 1.7.0")
            println("Copyright © 2022 jrt345. All rights reserved.")
            println("UnitConverter is licensed under the GNU General Public License v3.0")
        }

        private fun checkForUpdate() {
            TODO("Not yet implemented")
        }
    }
}