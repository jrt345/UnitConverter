class UnitConverter {
    companion object {
        fun conversionRouter(arguments: Array<String>) {
            var fromValue = ""

            for (i in arguments[1]) {
                if (i.isLetter()){
                    break
                }

                fromValue = fromValue.plus(i)
            }

            val fromUnit = arguments[1].drop(fromValue.length)

            val toUnit = arguments[2]

            when (fromValue) {
                "degf","degc","f","c" -> temperatureConverter()

                "mm","cm","m","km","in","ft","yd","mi" -> lengthConverter()

                "ac","mm2","cm2","m2","ha","km2","in2","ft2","yd2","mi2" -> areaConverter()

                "mm3","ml","l","m3","km3","in3","ft3","yd3","mi3",
                "gal","qt","pt","cup","cups","floz","tbsp","tsp",
                "impgal","impqt","imppt","impcup","impcups",
                "impfloz","imptbsp","imptsp", -> volumeConverter()

                "mg","g","kg","t","lb","lbs","oz","ton","impton","st","impst" -> massConverter()
            }


        }

        fun temperatureConverter(){

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