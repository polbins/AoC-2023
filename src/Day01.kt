fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }
            "$first$last".toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { findSum(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()

    val testInput2 = readInput("Day01_part2_test")
    check(part2(testInput2) == 281)

    val input2 = readInput("Day01_part2")
    part2(input2).println()
}

private val map = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
)

private val mapKeys = map.keys

private fun findSum(line: String): Int {
    val firstResult = line.findAnyOf(mapKeys)
    val lastResult = line.findLastAnyOf(mapKeys)
    val firstResultIndex = firstResult?.first ?: -1
    val lastResultIndex = lastResult?.first ?: -1
    val firstDigitIndex = line.indexOfFirst { it.isDigit() }
    val lastDigitIndex = line.indexOfLast { it.isDigit() }

    val first = if (
            (firstDigitIndex != -1 && firstDigitIndex < firstResultIndex) ||
            firstResultIndex == -1
    ) {
        line[firstDigitIndex].digitToInt()
    } else {
        map[firstResult!!.second]
    }
    val last = if (
            (lastDigitIndex != -1 && lastDigitIndex > lastResultIndex) ||
            lastResultIndex == -1
    ) {
        line[lastDigitIndex].digitToInt()
    } else {
        map[lastResult!!.second]
    }
    return "$first$last".toInt()
}
