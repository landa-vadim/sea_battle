fun main() {
    val myField = Array(10) { Array(10) { 0 } }
    val pcField = Array(10) { Array(10) { 0 } }
    var win = false


//    val predicate: (Int) -> Boolean = { it == 1 }

    for (i in 1..3) {
        createOneDeckShip(myField)
        createOneDeckShipPc(pcField)
        if (i == 1) {
            createThreeDeckShip(myField)
            createThreeDeckShipPc(pcField)
            createFourDeckShip(myField)
            createFourDeckShipPc(pcField)
        }
        if (i > 1) {
            createTwoDeckShip(myField)
            createTwoDeckShipPc(pcField)
        }
    }

    printMyField(myField)

    while (win == false) {




    }


}

fun createOneDeckShipPc(pcField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..9).random()

    if (pcField[rowIndex][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (pcField[x][y] == 0 || pcField[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x != rowIndex || y != columnIndex) {
                    pcField[x][y] = 2
                } else {
                    pcField[x][y] = 1
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createTwoDeckShipPc(pcField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..8).random()

    if (pcField[rowIndex][columnIndex] == 0 && pcField[rowIndex][columnIndex + 1] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 == 9) 9 else columnIndex + 2

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (pcField[x][y] == 0 || pcField[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1) {
                    pcField[x][y] = 1
                } else {
                    pcField[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createThreeDeckShipPc(pcField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..7).random()

    if (pcField[rowIndex][columnIndex] == 0 && pcField[rowIndex][columnIndex + 1] == 0 && pcField[rowIndex][columnIndex + 2] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 2 == 9) 9 else columnIndex + 3

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (pcField[x][y] == 0 || pcField[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2) {
                    pcField[x][y] = 1
                } else {
                    pcField[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createFourDeckShipPc(pcField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..6).random()

    if (pcField[rowIndex][columnIndex] == 0 && pcField[rowIndex][columnIndex + 1] == 0 && pcField[rowIndex][columnIndex + 2] == 0 && pcField[rowIndex][columnIndex + 3] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 3 == 9) 9 else columnIndex + 4

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (pcField[x][y] == 0 || pcField[x][y] == 2) {
                    continue
                } else {
                    return false
                }
            }
        }

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2 || x == rowIndex && y == columnIndex + 3) {
                    pcField[x][y] = 1
                } else {
                    pcField[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}


fun createOneDeckShip(myField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..9).random()

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


fun createTwoDeckShip(myField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..8).random()

    if (myField[rowIndex][columnIndex] == 0 && myField[rowIndex][columnIndex + 1] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 == 9) 9 else columnIndex + 2

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
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1) {
                    myField[x][y] = 1
                } else {
                    myField[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createThreeDeckShip(myField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..7).random()

    if (myField[rowIndex][columnIndex] == 0 && myField[rowIndex][columnIndex + 1] == 0 && myField[rowIndex][columnIndex + 2] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 2 == 9) 9 else columnIndex + 3

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
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2) {
                    myField[x][y] = 1
                } else {
                    myField[x][y] = 2
                }
            }
        }
        return true
    } else {
        return false
    }
}

fun createFourDeckShip(myField: Array<Array<Int>>): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..6).random()

    if (myField[rowIndex][columnIndex] == 0 && myField[rowIndex][columnIndex + 1] == 0 && myField[rowIndex][columnIndex + 2] == 0 && myField[rowIndex][columnIndex + 3] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 3 == 9) 9 else columnIndex + 4

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
                if (x == rowIndex && y == columnIndex || x == rowIndex && y == columnIndex + 1 || x == rowIndex && y == columnIndex + 2 || x == rowIndex && y == columnIndex + 3) {
                    myField[x][y] = 1
                } else {
                    myField[x][y] = 2
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

        for (column in row) {

            print("$column ")
        }
        println()
    }
}

fun printPcField(pcField: Array<Array<Int>>) {

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
    for (row in pcField) {

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