class HelpCommand {
    companion object {
        private fun runCvtCommandHelp() {
            println("Cvt command:\n" +
                    "The 'cvt' command stands for convert, and is the main feature of UnitConverter.\n" +
                    "UnitConverter can convert common units of temperature, length, area, volume, and mass.\n\n" +
                    "The syntax of the command is: cvt <<val><unit1>> <unit2>\n" +
                    "val indicates the value of the unit you want to convert.\n" +
                    "unit1 is the abbreviation of val's unit.\n" +
                    "unit2 is the abbreviation of the unit to convert val's unit to.\n\n" +
                    "Examples:\n" +
                    "'cvt 95F C' -> 35 C\n" +
                    "'cvt 1ft cm' -> 30.48 cm\n" +
                    "'cvt 1yd2 ft2' -> 9 ft2\n" +
                    "'cvt 500ml l' -> 0.5 l\n" +
                    "'cvt 1lb kg' -> 0.4536 kg\n\n" +
                    "Available units:\n" +
                    "Temperature: 'F', 'C', 'K;\n\n" +
                    "Length: 'mm', 'cm', 'm', 'km', 'in', 'ft', 'yd', 'mi'\n\n" +
                    "Area: 'ac', 'mm2', 'cm2', 'm2', 'ha', 'km2', 'in2', 'ft2', 'yd2', 'mi2'\n\n" +
                    "Volume: 'mm3', 'ml', 'l', 'm3', 'km3', 'in3', 'ft3', 'yd3', 'mi3',\n" +
                    "'gal', 'qt', 'pt', 'c', 'floz', 'tbsp', 'tsp', 'impgal', 'impqt',\n" +
                    "'imppt', 'impc', 'impfloz', 'imptbsp', 'imptsp'\n\n" +
                    "Area: 'mg', 'g', 'kg', 't', 'lb', 'oz', 'ton', 'impton', 'st', 'impst'\n")
        }

        private fun runChkupdCommandHelp() {
            println("Chkupd command:\n" +
                    "The 'chkudp' command stands for check update, and will check for any available updates for UnitConverter.\n")
        }

        fun start() {
            while (true) {
                println("Type the number of one of the following options to learn more about:\n" +
                        "[1]Cvt command\n" +
                        "[2]Chkupd command\n" +
                        "[3]Exit help menu")

                val stringInput:String = readln()

                try {
                    when (val intInput = stringInput.toInt()) {
                        1 -> runCvtCommandHelp()
                        2 -> runChkupdCommandHelp()
                        3 -> {
                            println("Exited help menu, you may type a command now:")
                            break
                        }
                        else -> {
                            println("'$intInput' is out of range, try again.")
                        }
                    }
                } catch (e: NumberFormatException) {
                    println("'$stringInput' is an invalid option, try again.")
                }
            }
        }
    }
}