fun main() {
    val myField = Array(10) { Array(10) { 0 } }
    val pcField = Array(10) { Array(10) { 0 } }
    var win = false
    var myShipsCounter = 0
    var pcShipsCounter = 0
//    val oneDeckShipAmount = 3
//    val twoDeckShipAmount = 2
//    val threeDeckShipAmount = 1
//    val fourDeckShipAmount = 1

//    val predicate: (Int) -> Boolean = { it == 1 }


    while (myShipsCounter < 3) {
        if (createOneDeckShip(myField)) {
            myShipsCounter++
        } else continue
    }
    while (pcShipsCounter < 3) {
        if (createOneDeckShip(pcField)) {
            pcShipsCounter++
        } else continue
    }
    while (myShipsCounter < 5) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createTwoDeckShipColumn(myField)) {
                myShipsCounter++
            } else continue
        } else {
            if (createTwoDeckShipRow(myField)) {
                myShipsCounter++
            } else continue
        }
    }
    while (pcShipsCounter < 5) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createTwoDeckShipColumn(pcField)) {
                pcShipsCounter++
            } else continue
        } else {
            if (createTwoDeckShipRow(pcField)) {
                pcShipsCounter++
            } else continue
        }
    }
    while (myShipsCounter < 6) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createThreeDeckShipColumn(myField)) {
                myShipsCounter++
            } else continue
        } else {
            if (createThreeDeckShipRow(myField)) {
                myShipsCounter++
            } else continue
        }
    }
    while (pcShipsCounter < 6) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createThreeDeckShipColumn(pcField)) {
                pcShipsCounter++
            } else continue
        } else {
            if (createThreeDeckShipRow(pcField)) {
                pcShipsCounter++
            } else continue
        }
    }
    while (myShipsCounter < 7) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createFourDeckShipColumn(myField)) {
                myShipsCounter++
            } else continue
        } else {
            if (createFourDeckShipRow(myField)) {
                myShipsCounter++
            } else continue
        }
    }
    while (pcShipsCounter < 7) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createFourDeckShipColumn(pcField)) {
                pcShipsCounter++
            } else continue
        } else {
            if (createFourDeckShipRow(pcField)) {
                pcShipsCounter++
            } else continue
        }
    }

printField(myField)

//    while (!win) {
//
//
//
//
//    }


}


fun createOneDeckShip(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..9).random()

    if (field[rowIndex][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x != rowIndex || y != columnIndex) {
                    field[x][y] = 2
                } else {
                    field[x][y] = 1
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createTwoDeckShipColumn(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..8).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex][columnIndex + 1] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 == 9) 9 else columnIndex + 2

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createTwoDeckShipRow(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..8).random()
    val columnIndex = (0..9).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex + 1][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 == 9) 9 else rowIndex + 2

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex + 1 && y == columnIndex) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createThreeDeckShipColumn(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..7).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex][columnIndex + 1] == 0 && field[rowIndex][columnIndex + 2] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 2 == 9) 9 else columnIndex + 3

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createThreeDeckShipRow(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..7).random()
    val columnIndex = (0..9).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex + 1][columnIndex] == 0 && field[rowIndex + 2][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 2 == 9) 9 else rowIndex + 3

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex + 1 && y == columnIndex || x == rowIndex + 2 && y == columnIndex) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}


fun createFourDeckShipColumn(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..6).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex][columnIndex + 1] == 0 && field[rowIndex][columnIndex + 2] == 0 && field[rowIndex][columnIndex + 3] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 3 == 9) 9 else columnIndex + 4

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2 || x == rowIndex && y == columnIndex + 3) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createFourDeckShipRow(field: Array<Array<Int>>): Boolean {

    val rowIndex = (0..6).random()
    val columnIndex = (0..9).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex + 1][columnIndex] == 0 && field[rowIndex + 2][columnIndex] == 0 && field[rowIndex + 3][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 3 == 9) 9 else rowIndex + 4

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 0 || field[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex + 1 && y == columnIndex || x == rowIndex + 2 && y == columnIndex || x == rowIndex + 3 && y == columnIndex) {
                    field[x][y] = 1
                } else {
                    field[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}


fun printField(field: Array<Array<Int>>) {

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
    for (row in field) {

        if (number1 < 10) {
            print("${number1++} |")
        } else {
            print("$number1|")
        }
        print(" ")

        for (column in row) {

            print("$column ")
        }
        println()
    }
}

