import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL

class UpdateChecker {
    companion object{
        private const val stringCurrentVersion = "1.0.0"

        //Gets the latest version of UnitConverter (as a string) from the GitHub repository
        private fun getLatestVersion(): String? {
            return try {
                /*Link to README.md; containing a comment "<!--Version-MAJOR.MINOR.PATCH-->"
                 * in line 1; stating the latest version of the GitHub repository*/
                val latestVersion = URL("https://raw.githubusercontent.com/jrt345/UnitConverter/master/README.md")
                val gitHub = latestVersion.openConnection()
                val `in` = BufferedReader(InputStreamReader(gitHub.getInputStream()))

                //Converts the first line of README.md into a version string
                `in`.readLine().replace("<".toRegex(), "")
                    .replace("!".toRegex(), "")
                    .replace("-".toRegex(), "")
                    .replace("Version".toRegex(), "")
                    .replace(">".toRegex(), "")
                    .replace("\\s".toRegex(), "")
            } catch (e: IOException) {
                "0.0.0"
            }
        }

        //Returns if there is a new version available
        fun isUpdateAvailable(): Boolean {
            try {
                val stringLatestVersion = getLatestVersion()

                //Turn version strings into an array of version increments (MAJOR.MINOR.PATCH)
                val stringCurrentVersionIncrements: Array<String> =
                    stringCurrentVersion.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                val latestVersionStringIncrements =
                    stringLatestVersion!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()

                /*int arrays of the current and latest version increments
                 * Array index key: 0 = MAJOR, 1 = MINOR, 2 = PATCH*/
                val currentVersionNumericalIncrements = IntArray(3)
                val latestVersionNumericalIncrements = IntArray(3)

                //Convert version increments into int arrays
                for (i in 0..2) {
                    currentVersionNumericalIncrements[i] = stringCurrentVersionIncrements[i].toInt()
                    latestVersionNumericalIncrements[i] = latestVersionStringIncrements[i].toInt()
                }

                /*Compares current and latest MAJOR MINOR and PATCH versions and if any
                 * of the latest version increments are greater than the current version
                 * increments return true, otherwise return false*/
                return if (currentVersionNumericalIncrements[0] < latestVersionNumericalIncrements[0]) {
                    true
                } else if (currentVersionNumericalIncrements[1] < latestVersionNumericalIncrements[1]) {
                    true
                } else {
                    currentVersionNumericalIncrements[2] < latestVersionNumericalIncrements[2]
                }
            } catch (e: Exception) {
                return false
            }
        }
    }
}