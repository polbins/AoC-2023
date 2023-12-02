fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day00_test")
    check(part1(testInput) == 0)

    val input = readInput("Day00")
    part1(input).println()

    val testInput2 = readInput("Day00_part2_test")
    check(part2(testInput2) == 0)

    val input2 = readInput("Day00_part2")
    part2(input2).println()
}
