fun main() {
    fun spellsXmasInDirection(
        startPosition: Pair<Int, Int>,
        xStep: Int,
        yStep: Int,
        xmasInputMap: HashMap<Pair<Int, Int>, Char>
    ): Boolean {
        var currentPosition: Pair<Int, Int> = startPosition
        "XMAS".toCharArray().forEach { char ->
            if (char != xmasInputMap[currentPosition])
                return false
            currentPosition = Pair(currentPosition.first + xStep, currentPosition.second + yStep)
        }
        return true
    }

    fun spellsXmas(
        startPosition: Pair<Int, Int>,
        xmasInputMap: HashMap<Pair<Int, Int>, Char>
    ): Int {
        var spellsXmasXTimes = 0

        //up
        if (spellsXmasInDirection(startPosition, 0, -1, xmasInputMap)) spellsXmasXTimes++
        //right up
        if (spellsXmasInDirection(startPosition, 1, -1, xmasInputMap)) spellsXmasXTimes++
        //right
        if (spellsXmasInDirection(startPosition, 1, 0, xmasInputMap)) spellsXmasXTimes++
        //rightdown
        if (spellsXmasInDirection(startPosition, 1, 1, xmasInputMap)) spellsXmasXTimes++
        //down
        if (spellsXmasInDirection(startPosition, 0, 1, xmasInputMap)) spellsXmasXTimes++
        //left down
        if (spellsXmasInDirection(startPosition, -1, 1, xmasInputMap)) spellsXmasXTimes++
        //left
        if (spellsXmasInDirection(startPosition, -1, 0, xmasInputMap)) spellsXmasXTimes++
        //left up
        if (spellsXmasInDirection(startPosition, -1, -1, xmasInputMap)) spellsXmasXTimes++

        return spellsXmasXTimes
    }

    fun getXmasMap(input: List<String>): HashMap<Pair<Int, Int>, Char> {
        val xmasMap: HashMap<Pair<Int, Int>, Char> = hashMapOf<Pair<Int, Int>, Char>()
        for ((x, line) in input.withIndex()) {
            for ((y, char) in line.withIndex()) {
                xmasMap[Pair(x, y)] = char
            }
        }
        return xmasMap
    }

    fun part1(input: List<String>): Int {
        val xmasMap: HashMap<Pair<Int, Int>, Char> = getXmasMap(input)

        return xmasMap.keys.sumOf { pair -> spellsXmas(pair, xmasMap) }
    }

    fun is_X_Mas_Sequence(
        sequence: CharArray, startPosition: Pair<Int, Int>,
        xmasInputMap: HashMap<Pair<Int, Int>, Char>
    ): Boolean {
        return xmasInputMap[Pair(startPosition.first - 1, startPosition.second - 1)] == sequence[0] &&
                xmasInputMap[Pair(startPosition.first + 1, startPosition.second - 1)] == sequence[1] &&
                xmasInputMap[Pair(startPosition.first + 1, startPosition.second + 1)] == sequence[2] &&
                xmasInputMap[Pair(startPosition.first - 1, startPosition.second + 1)] == sequence[3]
    }

    fun isX_Mas(
        startPosition: Pair<Int, Int>,
        xmasInputMap: HashMap<Pair<Int, Int>, Char>
    ): Boolean {
        if (xmasInputMap[startPosition] == 'A') {
            return is_X_Mas_Sequence("MMSS".toCharArray(), startPosition, xmasInputMap) ||
                    is_X_Mas_Sequence("SMMS".toCharArray(), startPosition, xmasInputMap) ||
                    is_X_Mas_Sequence("SSMM".toCharArray(), startPosition, xmasInputMap) ||
                    is_X_Mas_Sequence("MSSM".toCharArray(), startPosition, xmasInputMap)
        }
        return false
    }

    fun Boolean.toInt() = if (this) 1 else 0

    fun part2(input: List<String>): Int {
        
        val xmasMap: HashMap<Pair<Int, Int>, Char> = getXmasMap(input)

        return xmasMap.keys.sumOf { pair -> isX_Mas(pair, xmasMap).toInt() }
    }

    val miniTestInput = readInput("Day04_test_mini")
    val testInput = readInput("Day04_test")
    val input = readInput("Day04")
    part1(miniTestInput).println()
    check(part1(miniTestInput) == 4)

    part1(testInput).println()
    check(part1(testInput) == 18)

    part1(input).println()


    part2(testInput).println()
    check(part2(testInput) == 9)

    part2(input).println()
}
