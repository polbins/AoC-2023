var seeds = mutableSetOf<Double>()
var maps = mutableListOf<MutableList<Triple<Double, Double, Double>>>()
fun main() {
    fun part1(input: List<String>): Int {
        var category: String? = null
        var mapping = mutableListOf<Triple<Double, Double, Double>>()
        input.forEach { line ->
            if (line.isEmpty()) {
                if (mapping.isNotEmpty()) {
                    maps.add(mapping)
                    mapping = mutableListOf()
                }
                category = null
                return@forEach
            }
            val split = line.split(':', ' ')
            if (category == null) {
                category = split[0]
                if (category == "seeds") {
                    for (i in 2 until split.size) {
                        seeds.add(split[i].toDouble())
                    }
                }
                return@forEach
            }

            val destination = split[0].toDouble()
            val source = split[1].toDouble()
            val range = split[2].toDouble()

            mapping.add(Triple(destination, source, range))
        }
        maps.add(mapping)

        var lowest = Double.MAX_VALUE
        seeds.forEach { seed ->
            var mappedValue = seed
            maps.forEach { sequences ->
                run loop@{
                    sequences.forEach { (destination, source, range) ->
                        if (mappedValue in source..source + (range - 1)) {
                            val old = mappedValue
                            mappedValue += destination - source
                            println("$old -> $mappedValue")
                            return@loop
                        }
                    }
                }
            }
            lowest = Math.min(mappedValue, lowest)
            println("end of seed $seed, lowest = $lowest")
        }
        return lowest.toInt()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35)

    val input = readInput("Day05")
    part1(input).println()
//
//    val testInput2 = readInput("Day05_part2_test")
//    check(part2(testInput2) == 0)
//
//    val input2 = readInput("Day05_part2")
//    part2(input2).println()
}
