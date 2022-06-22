import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class UnitConverter {
    companion object {
        private fun getStringValueFromArgument(argument:String):String {
            var stringValue = ""
            for (character in argument) {
                if (character.isLetter()){
                    break
                }

                stringValue = stringValue.plus(character)
            }

            return stringValue
        }

        private fun getUnitsFromString(unitString:String): Units? {
            var unit: Units? = null
            for (i in Units.values()) {
                if (unitString == i.stringArgument){
                    unit = i
                }
            }

            return unit
        }

        private fun isCommandValid(stringValue:String, stringUnitFrom:String, stringUnitTo:String):Boolean {
            //Does valueFromString not contain number, if so the command is not valid
            if(!(stringValue.contains("(\\d)".toRegex()))) return false

            val unitFrom:Units = getUnitsFromString(stringUnitFrom) ?: return false

            val unitTo:Units = getUnitsFromString(stringUnitTo) ?: return false

            return (unitFrom.unitGroup == unitTo.unitGroup)
        }

        private fun runConverter(value:Double, unitFrom:Units?, unitTo:Units?) {
            if (unitFrom != null && unitTo != null && (unitFrom.unitGroup == unitTo.unitGroup)) {
                when (unitFrom.stringArgument) {
                    //Temperature
                    "F","C","K" -> temperatureConverter(value, unitFrom, unitTo)

                    //Length
                    "mm","cm","m","km","in","ft","yd","mi" -> unitConverter(value, unitFrom, unitTo)

                    //Area
                    "ac","mm2","cm2","m2","ha","km2","in2","ft2","yd2","mi2" -> unitConverter(value, unitFrom, unitTo)

                    //Volume
                    "mm3","ml","l","m3","km3","in3","ft3","yd3","mi3","gal","qt","pt","c","floz","tbsp","tsp",
                    "impgal","impqt","imppt","impc", "impfloz","imptbsp","imptsp" -> unitConverter(value, unitFrom, unitTo)

                    //Mass
                    "mg","g","kg","t","lb","oz","ton","impton","st","impst" -> unitConverter(value, unitFrom, unitTo)
                }
            }
        }

        private fun getUnitToSymbol(unitTo:Units?):String {
            var symbol = ""

            for (units in Units.values()) {
                if (unitTo == units) {
                    symbol = unitTo.stringArgument
                }
            }

            if (symbol.contains("(imp)".toRegex())) {
                symbol = symbol.replace("(imp)".toRegex(),"imp ")
            }

            return " $symbol"
        }

        private fun temperatureConverter(value:Double, unitFrom:Units?, unitTo:Units?){
            val df = DecimalFormat("0.###")

            var temperature: Double = when(unitFrom) {
                Units.FAHRENHEIT -> ((value - 32.0) * (5.0/9.0))
                Units.KELVIN -> (value - 273.15)
                else -> value
            }

            temperature = when(unitTo) {
                Units.FAHRENHEIT -> ((temperature * (9.0/5.0)) + 32.0)
                Units.KELVIN -> temperature + 273.15
                else -> temperature
            }
            println(df.format(temperature).toString() + getUnitToSymbol(unitTo))
        }

        private fun unitConverter(value:Double, unitFrom:Units?, unitTo:Units?){
            val valueToMetricBase:Double = value * (unitFrom?.toMetricBase ?: 0.0)
            val metricBaseValueToUnit:Double = valueToMetricBase * (unitTo?.fromMetricBase ?: 0.0)

            var df = DecimalFormat("0")
            var output = BigDecimal(0)

            if (metricBaseValueToUnit > 1000000.0){
                output = (metricBaseValueToUnit.toBigDecimal().setScale(0, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 100000.0){
                df = DecimalFormat("0.#")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(1, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 10000.0){
                df = DecimalFormat("0.##")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(2, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 1){
                df = DecimalFormat("0.###")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(3, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 0.1){
                df = DecimalFormat("0.####")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(4, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 0.01){
                df = DecimalFormat("0.#####")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(5, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 0.001){
                df = DecimalFormat("0.######")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(6, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 0.0001){
                df = DecimalFormat("0.#######")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(7, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit > 0.0000001){
                df = DecimalFormat("0.##########")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(10, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit >= 0.000000000001) {
                df = DecimalFormat("0.###############")
                output = (metricBaseValueToUnit.toBigDecimal().setScale(15, RoundingMode.HALF_UP))
            }
            else if (metricBaseValueToUnit < 0.000000000001) {
                println("Number is too small!")
            }

            println(df.format(output) + getUnitToSymbol(unitTo))
        }

        private fun printCommandErrors(stringValue:String, unitFrom:Units?, unitTo:Units?, arguments: Array<String>) {
            if(!(stringValue.contains("(\\d)".toRegex()))){
                println(("Error:'${arguments[1]}': is an invalid argument, unknown value."))
            }
            if (unitFrom == null) {
                println(("Error:'${arguments[1]}': is an invalid argument, unknown unit."))
            }
            if (unitTo == null) {
                println(("Error:'${arguments[2]}': is an invalid argument, unknown unit."))
            }
            if ((unitFrom?.unitGroup != unitTo?.unitGroup) && (unitFrom != null && unitTo != null)) {
                println(("Error: can not convert ${unitFrom.unitGroup.toString().lowercase()} '${unitFrom.stringArgument}' to ${unitTo.unitGroup.toString().lowercase()} '${unitTo.stringArgument}'."))
            }
        }

        fun start(arguments: Array<String>) {
            val stringValue = getStringValueFromArgument(arguments[1])

            val stringUnitFrom = arguments[1].drop(stringValue.length)

            val stringUnitTo = arguments[2]

            if (isCommandValid(stringValue, stringUnitFrom, stringUnitTo)){
                runConverter(stringValue.toDouble(), getUnitsFromString(stringUnitFrom), getUnitsFromString(stringUnitTo))
            } else {
                printCommandErrors(stringValue,  getUnitsFromString(stringUnitFrom), getUnitsFromString(stringUnitTo), arguments)
            }
        }
    }
}