fun main() {
    fun part1(input: List<String>): Int {
        // get the game ID
        return input.sumOf { playable(it) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day02_test")
//    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
//
//    val testInput2 = readInput("Day02_part2_test")
//    check(part2(testInput2) == 0)
//
//    val input2 = readInput("Day02_part2")
//    part2(input2).println()
}

private val numberRegex = "\\d+".toRegex()
private val countAndColorRegex = "\\s+(\\d+) (red|green|blue)".toRegex()
private const val red = 12
private const val green = 13
private const val blue = 14

private fun playable(line: String): Int {
    val gameStrings = line.split(':', ';')
    val gameId = numberRegex.find(gameStrings[0])!!.value.toInt()
    for (i in 1 until gameStrings.size) {
        val countAndColorString = gameStrings[i].split(',')
        countAndColorString.forEach {
            val countAndColor = countAndColorRegex.find(it)
            val count = countAndColor!!.groupValues[1].toInt()
            val max = when (val color = countAndColor.groupValues[2]) {
                "red" -> red
                "green" -> green
                "blue" -> blue
                else -> error("unknown color: $color!")
            }
            if (count > max) {
                return 0
            }
        }
    }
    return gameId
}
