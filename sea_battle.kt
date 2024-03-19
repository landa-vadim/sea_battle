fun main() {
    var myField = Array(10) { Array(10) { 0 } }
    var pcField = Array(10) { Array(10) { 0 } }
    var countPlayersShipDecks = 0

//    val predicate: (Int) -> Boolean = { it == 1 }

    while (countPlayersShipDecks < 3) {
        if (createOneDeckShip(myField)) {
            countPlayersShipDecks++
        } else {
            continue
        }
    }
    printMyField(myField)
}

fun createOneDeckShip(myField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..9).random()

    println("Try: [$rowIndex][$columnIndex]")

    if (myField[rowIndex][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (myField[x][y] == 0 || myField[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x != rowIndex || y != columnIndex) {
                    myField[x][y] = 2
                } else {
                    myField[x][y] = 1
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun printMyField(myField: Array<Array<Int>>) {

    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    var number0 = 0
    var number1 = 1

    println()
    print("    ")
    for (i in letterArray) {
        print(letterArray[number0++])
        print(" ")
    }
    println()
    for (row in myField) {

        if (number1 < 10) {
            print("${number1++} |")
        } else {
            print("$number1|")
        }
        print(" ")

        for (line in row) {

            print("$line ")
        }
        println()
    }
}