data class Number(
        var number: Int = 0,
        val coordinates: MutableList<Pair<Int, Int>> = mutableListOf()
) {
    fun append(digit: Char, i: Int, j: Int) {
        this.number = "$number$digit".toInt()
        coordinates.add(i to j)
    }
}

data class Symbol(
        val coordinate: Pair<Int, Int>,
        private val surrounding: List<Pair<Int, Int>> = buildSurrounding(coordinate)
) {
    companion object {
        fun buildSurrounding(coordinate: Pair<Int, Int>): List<Pair<Int, Int>> {
            val (i, j) = coordinate
            return setOf(
                    (i - 1).coerceAtLeast(0) to (j - 1).coerceAtLeast(0),
                    (i - 1).coerceAtLeast(0) to j,
                    (i - 1).coerceAtLeast(0) to j + 1,
                    (i).coerceAtLeast(0) to (j - 1).coerceAtLeast(0),
                    (i).coerceAtLeast(0) to j,
                    (i).coerceAtLeast(0) to (j + 1).coerceAtMost(width),
                    (i + 1).coerceAtMost(height) to (j - 1).coerceAtLeast(0),
                    (i + 1).coerceAtMost(height) to (j),
                    (i + 1).coerceAtMost(height) to (j + 1).coerceAtMost(width),
            ).toList()
        }
    }


    fun adjacent(coordinates: MutableList<Pair<Int, Int>>): Boolean {
        return coordinates.any { it in surrounding }
    }
}


var width = 0
var height = 0
fun main() {
    fun part1(input: List<String>): Int {
        height = input.size
        width = input.first().length
        val array = Array(height) { CharArray(width) }

        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()
        input.forEachIndexed { index, s ->
            array[index] = s.toCharArray()
        }
        array.forEachIndexed { i, line ->
            var number: Number? = null
            line.forEachIndexed { j, char ->
                if (char.isDigit()) {
                    if (number == null) number = Number()
                    number!!.append(char, i, j)
                } else {
                    if (char != '.') symbols.add(Symbol(i to j))
                    number?.let { numbers.add(it) }
                    number = null
                }
            }
            number?.let { numbers.add(it) }
        }

        var sum = 0
        numbers.forEach { number ->
            // find a symbol around coordinate, starting from top
            val found = symbols.any {
                it.adjacent(number.coordinates)
            }
            if (found) sum += number.number
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        height = input.size
        width = input.first().length
        val array = Array(height) { CharArray(width) }

        val numbers = mutableListOf<Number>()
        val symbols = mutableListOf<Symbol>()
        input.forEachIndexed { index, s ->
            array[index] = s.toCharArray()
        }
        array.forEachIndexed { i, line ->
            var number: Number? = null
            line.forEachIndexed { j, char ->
                if (char.isDigit()) {
                    if (number == null) number = Number()
                    number!!.append(char, i, j)
                } else {
                    if (char == '*') symbols.add(Symbol(i to j))
                    number?.let { numbers.add(it) }
                    number = null
                }
            }
            number?.let { numbers.add(it) }
        }

        var sum = 0

        symbols.forEach { symbol ->
            // find 2 numbers adjacent to symbol
            val adjacents = numbers.filter { symbol.adjacent(it.coordinates) }
            if (adjacents.size == 2) {
                sum += adjacents.map { it.number }.reduce(Int::times)
                numbers.removeAll(adjacents)
            }
        }

        return sum
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day03_test")
//    check(part1(testInput) == 4361)
//
//    val input = readInput("Day03")
//    part1(input).println()

//    val testInput2 = Path("src/Day03_part2_test.txt").readLines()
//    check(part2(testInput2) == 467835)

    val input2 = readInput("Day03_part2")
    part2(input2).println()
}
