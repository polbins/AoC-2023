fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val first = line.first { it.isDigit() }
            val last = line.last { it.isDigit() }
            println("$first$last".toInt())
            sum += "$first$last".toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
