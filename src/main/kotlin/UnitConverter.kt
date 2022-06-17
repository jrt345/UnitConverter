import java.math.RoundingMode
import java.text.DecimalFormat


class UnitConverter {
    companion object {
        //Returns boolean is the inputted command is valid or not
        private fun isCommandValid(valueFromString:String, unitFromString:String, unitToString:String):Boolean {
            //Does valueFromString not contain number, if so the command is not valid
            if(!(valueFromString.contains("([\\d])".toRegex()))) return false

            val unitFrom:Units = stringToUnits(unitFromString) ?: return false

            val unitTo:Units = stringToUnits(unitToString) ?: return false

            return (unitFrom.unitGroup == unitTo.unitGroup)
        }

        fun conversionRouter(arguments: Array<String>) {
            //The inputted value as a string
            var valueString = ""

            //Separates the inputted value from the original unit
            for (i in arguments[1]) {
                if (i.isLetter()){
                    break
                }

                valueString = valueString.plus(i)
            }

            //The unit of the inputted value
            val fromUnit = arguments[1].drop(valueString.length)

            //The unit of the desired outcome from converting the inputted value
            val toUnit = arguments[2]

            if (isCommandValid(valueString, fromUnit, toUnit)){
                when (fromUnit) {
                    //Temperature
                    "f","c","k" -> temperatureConverter(valueString, fromUnit, toUnit)

                    //Length
                    "mm","cm","m","km","in","ft","yd","mi" -> unitConverter(valueString, fromUnit, toUnit)

                    //Area
                    "ac","mm2","cm2","m2","ha","km2","in2","ft2","yd2","mi2" -> unitConverter(valueString, fromUnit, toUnit)

                    //Volume
                    "mm3","ml","l","m3","km3","in3","ft3","yd3","mi3",
                    "gal","qt","pt","cup","floz","tbsp","tsp",
                    "impgal","impqt","imppt","impcup",
                    "impfloz","imptbsp","imptsp" -> unitConverter(valueString, fromUnit, toUnit)

                    //Mass
                    "mg","g","kg","t","lb","oz","ton","impton","st","impst" -> unitConverter(valueString, fromUnit, toUnit)
                }
            }
        }

        private fun stringToUnits(unitString:String): Units? {
            var unit: Units? = null
            for (i in Units.values()) {
                if (unitString == i.stringArgument){
                    unit = i
                }
            }

            return unit
        }

        private fun temperatureConverter(valueFromString:String, unitFromString:String, unitToString:String){
            val unitFrom:Units? = stringToUnits(unitFromString)
            val unitTo:Units? = stringToUnits(unitToString)
            val valueUnput:Double = valueFromString.toDouble()

            val df = DecimalFormat("0.###")

            var temperature: Double = when(unitFrom) {
                Units.FAHRENHEIT -> ((valueUnput - 32.0) * (5.0/9.0))
                Units.KELVIN -> (valueUnput - 273.15)
                else -> valueUnput
            }

            temperature = when(unitTo) {
                Units.FAHRENHEIT -> ((temperature * (9.0/5.0)) + 32.0)
                Units.KELVIN -> temperature + 273.15
                else -> temperature
            }
            println(df.format(temperature))
        }

        private fun unitConverter(valueFromString:String, unitFromString:String, unitToString:String){
            val unitFrom:Units? = stringToUnits(unitFromString)
            val unitTo:Units? = stringToUnits(unitToString)

            val valueToMetricBase:Double = valueFromString.toDouble() * (unitFrom?.toMetricBase ?: 0.0)
            val metricBaseValueToUnit:Double = valueToMetricBase * (unitTo?.fromMetricBase ?: 0.0)

            val df: DecimalFormat
            if (metricBaseValueToUnit > 1000000.0){
                println(metricBaseValueToUnit.toBigDecimal().setScale(0, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 100000.0){
                df = DecimalFormat("0.#")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(1, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 10000.0){
                df = DecimalFormat("0.##")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(2, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 1){
                df = DecimalFormat("0.###")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(3, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 0.1){
                df = DecimalFormat("0.####")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(4, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 0.01){
                df = DecimalFormat("0.#####")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(5, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 0.001){
                df = DecimalFormat("0.######")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(6, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 0.0001){
                df = DecimalFormat("0.#######")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(7, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit > 0.0000001){
                df = DecimalFormat("0.##########")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(10, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit >= 0.000000000001) {
                df = DecimalFormat("0.###############")
                println(df.format(metricBaseValueToUnit.toBigDecimal().setScale(15, RoundingMode.HALF_UP)))
            }
            else if (metricBaseValueToUnit < 0.000000000001) {
                println("Number is too small!")
            }
        }
    }
}