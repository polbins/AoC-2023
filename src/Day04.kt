import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { winningPoints(it) }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(input).println()
//
//    val testInput2 = readInput("Day04_part2_test")
//    check(part2(testInput2) == 0)
//
//    val input2 = readInput("Day04_part2")
//    part2(input2).println()
}

private val numberRegex = "\\d+".toRegex()

fun winningPoints(line: String): Int {
    val split = line.split(':', '|')
    val winningNumbers = numberRegex.findAll(split[1]).map { it.value }
    val scratchCardNumbers = numberRegex.findAll(split[2]).map { it.value }
    val match = scratchCardNumbers.count { it in winningNumbers }
    return 2.0.pow((match - 1.0)).toInt()
}
