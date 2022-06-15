class UnitConverter {
    companion object {
        fun conversionRouter(arguments: Array<String>) {
            if (arguments[0].lowercase() == "convt" || arguments[0].lowercase() == "cvt" || arguments[0].lowercase() == "convert") {
                when(arguments[1].lowercase()) {
                    "temp","temperature" -> temperatureConverter(arguments[2].toDouble(), arguments[3], arguments[5])
                    "len","length"-> lengthConverter()
                    "a","area" -> areaConverter()
                    "v", "vol", "volume" -> volumeConverter()
                    "m", "mass" -> massConverter()
                }
            }
        }

        fun temperatureConverter(value:Double, unitFrom:String, unitTo:String){

        }

        fun lengthConverter(){

        }

        fun areaConverter(){

        }

        fun volumeConverter(){

        }

        fun massConverter(){

        }
    }
}