fun main() {
    val myField = Array(10) { Array(10) { 0 } }
    val pcField = Array(10) { Array(10) { 0 } }
    val enemyField = Array(10) { Array(10) { 0 } }
    val playerField = Array(10) { Array(10) { 0 } }

    val turn = 0..1
    val whatTurn = turn.random()

    initField(myField)
    initField(pcField)

    printField(myField)
    printField(pcField)


//    ХОДЫ

    var isMyShipExist = checkAnyShipExist(myField)
    var isPcShipExist = checkAnyShipExist(pcField)

    while (isMyShipExist && isPcShipExist) {
        if (whatTurn == 0) {
            while (myTurn(pcField, enemyField) && checkAnyShipExist(pcField)) {
                printField(enemyField)
                printField(pcField)
            }
            while (pcTurn(playerField, myField) && checkAnyShipExist(myField)) {
                printField(myField)
                printField(playerField)
            }
        } else {
            while (pcTurn(playerField, myField) && checkAnyShipExist(pcField)) {
                printField(myField)
                printField(playerField)
            }
            while (myTurn(pcField, enemyField) && checkAnyShipExist(myField)) {
                printField(enemyField)
                printField(pcField)
            }
        }

        isMyShipExist = checkAnyShipExist(myField)
        isPcShipExist = checkAnyShipExist(pcField)
    }

    if (isMyShipExist) {
        println("Вы проиграли!")
    }

    if (isPcShipExist) {
        println("Вы выиграли!")
    }
}

const val LIVE_DECK = 1
const val NO_DECK = 2
const val DAMAGED_DECK = 8
const val UNKNOWN = 0

fun initField(field: Array<Array<Int>>) {
    var oneDeckShipsCounter = 0
    var twoDeckShipsCounter = 0
    var threeDeckShipsCounter = 0
    var fourDeckShipsCounter = 0

    val oneDeckShipAmount = 3
    val twoDeckShipAmount = 2
    val threeDeckShipAmount = 1
    val fourDeckShipAmount = 1
    val turn = 0..1

    for (i in 0 until oneDeckShipAmount) {
        while (!createOneDeckShip(field)) { }
    }

    for (i in 0 until twoDeckShipAmount) {
        if (turn.random() == 0) {
            while (!createShipsColumn(field, 2)) {}
        } else {
            while (!createShipsRow(field, 2)) {}
        }
    }

    while (threeDeckShipsCounter < threeDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(field, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(field, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        }
    }
    while (fourDeckShipsCounter < fourDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(field, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(field, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        }
    }
}

// ФУНКЦИЯ МОЕГО ХОДА

fun myTurn(pcField: Array<Array<Int>>, enemyField: Array<Array<Int>>): Boolean {
    println("Введите координаты для выстрела:")
    val myTurn = readln()
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    if (checkReadLine(myTurn)) {
        if (myTurn.length == 2) {
            myTurn.toCharArray()
            val column = letterArray.indexOf(myTurn[0])
            val row = myTurn[1].digitToInt() - 1
            if (enemyField[row][column] != DAMAGED_DECK) {
                if (checkShoot(pcField, row, column, enemyField)) {
                    pcField[row][column] = 8
                    enemyField[row][column] = 8
                    return true
                } else {
                    println("Мимо!")
                    return false
                }
            } else {
                println("Вы уже сюда стреляли!")
                return true
            }
        }
        if (myTurn.length == 3) {
            myTurn.toCharArray()
            val column = letterArray.indexOf(myTurn[0])
            val row = 9
            if (enemyField[row][column] != 8) {
                if (checkShoot(pcField, row, column, enemyField)) {
                    pcField[row][column] = 8
                    enemyField[row][column] = 8
                    return true
                } else {
                    println("Мимо!")
                    return false
                }
            } else {
                println("Вы уже сюда стреляли!")
                return true
            }
        } else return false
    } else {
        return false
    }
}

// ФУНКЦИЯ НАЧАЛЬНОГО ХОДА ПК

fun pcTurn(playerField: Array<Array<Int>>, myField: Array<Array<Int>>): Boolean {
    var rowIndex = 0
    var columnIndex = 0
    if (pcMustExecute(myField, playerField)) {
        for (x in 0..9) {
            for (y in 0..9) {
                if (playerField[x][y] == 8) {
                    for (c in x - 1..x - 2) {
                        if (playerField[c][y] == 8) {
                            rowIndex = c - 1
                            columnIndex = y
                            continue
                        }
                    }
                    for (c in x + 1..x + 2) {
                        if (playerField[c][y] == 8) {
                            rowIndex = c + 1
                            columnIndex = y
                            continue
                        }
                    }
                    for (v in y - 1..y - 2) {
                        if (playerField[x][v] == 8) {
                            rowIndex = x
                            columnIndex = v - 1
                            continue
                        }
                    }
                    for (v in y + 1..y + 2) {
                        if (playerField[x][v] == 8) {
                            rowIndex = x
                            columnIndex = v + 1
                            continue
                        }
                    }
                }
            }
        }
    } else {
        rowIndex = (0..9).random()
        columnIndex = (0..9).random()
    }
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    val numbersArray = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', ' ')
    while (playerField[rowIndex][columnIndex] == 8) {
        rowIndex = (0..9).random()
        columnIndex = (0..9).random()

        println("playerField[$rowIndex][$columnIndex] = ${playerField[rowIndex][columnIndex]}")
    }
    val num = numbersArray[rowIndex]
    val let = letterArray[columnIndex]
    if (rowIndex == 9) {
        println()
        println("ПК бьет в ${let}10")
    } else {
        println("ПК бьет в $let$num")
    }
    if (checkShoot(myField, rowIndex, columnIndex, playerField)) {
//        if (!pcMustExecute(myField, playerField)) {
//            for (x in rowIndex - 3..rowIndex + 3) {
//                for (y in columnIndex - 3..columnIndex + 3) {
//                    if (playerField[x][y] == 8) {
//                        for (c in x - 1..x + 1) {
//                            for (v in y - 1..y + 1) {
//                                if (myField[c][v] != 8) {
//                                    playerField[c][v] = 2
//                                } else continue
//                            }
//                        }
//                    } else continue
//                }
//            }
//        }
        return true
    } else return false
}

// ФУНКЦИЯ ПРОВЕРКИ ПОЛЯ ИГРОКА НА РАНЕНЫЙ КОРАБЛЬ

fun pcMustExecute(myField: Array<Array<Int>>, playerField: Array<Array<Int>>): Boolean {
    for (x in 0..9) {
        for (y in 0..9) {
            val xMin = if (x - 1 < 0) 0 else x - 1
            val xMax = if (x + 1 > 9) 9 else x + 1
            val yMin = if (y - 1 < 0) 0 else y - 1
            val yMax = if (y + 1 > 9) 9 else y + 1
            if (myField[x][y] == 8) {
                if (myField[xMin][y] != 2) {
                    if (myField[xMin][y] == 1) {
                        return true
                    }
                }
                if (myField[xMax][y] != 2) {
                    if (myField[xMax][y] == 1) {
                        return true
                    }
                }
                if (myField[x][yMin] != 2) {
                    if (myField[x][yMin] == 1) {
                        return true
                    }
                }
                if (myField[x][yMax] != 2) {
                    if (myField[x][yMax] == 1) {
                        return true
                    }
                }
//                else {
//                    for (c in xMin..xMax) {
//                        for (v in yMin..yMax) {
//                            if ((c != x || v != y) && playerField[c][v] != 8) {
//                                playerField[c][v] = 2
//                            } else continue
//                        }
//                    }
//                }
            }
        }
    }
    return false
}

// ФУНКЦИЯ ПРОВЕРКИ НА ПРОИГРЫШ

fun checkAnyShipExist(field: Array<Array<Int>>): Boolean {
    for (x in 0..9) {
        for (y in 0..9) {
            if (field[x][y] == 1) {
                return true
            }
        }
    }
    return false
}

// ФУНКЦИЯ ПРОВЕРКИ ТОЧНОСТИ ВЫСТРЕЛА

fun checkShoot(field: Array<Array<Int>>, row: Int, column: Int, field2: Array<Array<Int>>): Boolean {
    if (field[row][column] == 1) {
        field[row][column] = 8
        field2[row][column] = 8
        val leftBorder = if (row - 1 < 0) 0 else row - 1
        val maxLeftBorder = if (row - 3 < 0) 0 else row - 3
        val rowMax1 = if (row + 1 > 9) 9 else row + 1
        val rowMax2 = if (row + 3 > 9) 9 else row + 3
        val columnMin1 = if (column - 1 < 0) 0 else column - 1
        val columnMin2 = if (column - 3 < 0) 0 else column - 3
        val columnMax1 = if (column + 1 > 9) 9 else column + 1
        val columnMax2 = if (column + 3 > 9) 9 else column + 3

        for (x in leftBorder..maxLeftBorder) {
            if (field[x][column] != 2) {
                if (field[x][column] == 1) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (x in rowMax1..rowMax2) {
            if (field[x][column] != 2) {
                if (field[x][column] == 1) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (y in columnMin1..columnMin2) {
            if (field[row][y] != 2) {
                if (field[row][y] == 1) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (y in columnMax1..columnMax2) {
            if (field[row][y] != 2) {
                if (field[row][y] == 1) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (x in leftBorder..maxLeftBorder) {
            if (field2[x][column] == 8) {
                for (y in columnMin1..columnMax1) {
                    if (field2[x][y] != 8) {
                        field2[x][y] = 2
                    }
                }
            } else {
                for (y in columnMin1..columnMax1) {
                    field2[x][y] = 2
                }
                break
            }
        }
        for (x in rowMax1..rowMax2) {
            if (field2[x][column] == 8) {
                for (y in columnMin1..columnMax1) {
                    if (field2[x][y] != 8) {
                        field2[x][y] = 2
                    }
                }
            } else {
                for (y in columnMin1..columnMax1) {
                    field2[x][y] = 2
                }
                break
            }
        }
        for (y in columnMin1..columnMin2) {
            if (field2[row][y] == 8) {
                for (x in leftBorder..rowMax1) {
                    if (field2[x][y] != 8) {
                        field2[x][y] = 2
                    }
                }
            } else {
                for (x in leftBorder..rowMax1) {
                    field2[x][y] = 2
                }
                break
            }
        }
        for (y in columnMax1..columnMax2) {
            if (field2[row][y] == 8) {
                for (x in leftBorder..rowMax1) {
                    if (field2[x][y] != 8) {
                        field2[x][y] = 2
                    }
                }
            } else {
                for (x in leftBorder..rowMax1) {
                    field2[x][y] = 2
                }
                break
            }
        }
        println("Убил!")
        return true
    } else {
        println("Мимо!")
        field[row][column] = 2
        field2[row][column] = 2
        return false
    }
}

// ФУНКЦИЯ ПРОВЕРКИ КОРРЕКТНОСТИ ВВЕДЕННЫХ КООРДИНАТ

fun checkReadLine(myTurn: String): Boolean {
    if (myTurn.length == 2) {
        myTurn.toCharArray()
        val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
        val numbersArray = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
        if (myTurn[0] in letterArray) {
            if (myTurn[1] in numbersArray) {
                return true
            } else {
                println("Второй символ не соответсвует формату \"Цифра от 0 до 9\"")
                return false
            }
        } else {
            println("Первый символ не соответсвует формату \"Буква от 'А' до 'К'\"")
            return false
        }
    }
    if (myTurn.length == 3) {
        myTurn.toCharArray()
        val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
        val charZero = '0'
        val charOne = '1'
        if (myTurn[0] in letterArray) {
            if (myTurn[1] == charOne && myTurn[2] == charZero) {
                return true
            } else {
                println("Введенный номер строки не соответствует существующему")
                return false
            }
        } else {
            println("Нужно ввести два или три символа в формате \"Буква колонки номер строки\"")
            return false
        }
    } else {
        println("Нужно ввести два или три символа в формате \"Буква колонки номер строки\"")
        return false
    }
}

// ФУНКЦИЯ ПЕЧАТИ ПОЛЯ

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

// ФУНКЦИЯ ПОСТРОЕНИЯ 1-ПАЛУБНЫХ КОРАБЛЕЙ

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

// ФУНКЦИЯ ПОСТРОЕНИЯ КОРАБЛЕЙ В КОЛОНКУ
fun createShipsColumn(field: Array<Array<Int>>, deckAmount: Int): Boolean {

    val rowIndex = (0..9).random()
    val columnIndex = (0..10 - deckAmount).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex][columnIndex + deckAmount - 1] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + deckAmount - 1 == 9) 9 else columnIndex + deckAmount

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
                field[x][y] = 2
                for (i in 1..deckAmount) {
                    if (x == rowIndex && y == columnIndex + i - 1) {
                        field[x][y] = 1
                    }
                }
            }
        }
        return true
    } else {
        return false
    }
}

// ФУНКЦИЯ ПОСТРОЕНИЯ КОРАБЛЕЙ В ЛИНИЮ

fun createShipsRow(field: Array<Array<Int>>, deckAmount: Int): Boolean {

    val rowIndex = (0..10 - deckAmount).random()
    val columnIndex = (0..9).random()

    if (field[rowIndex][columnIndex] == 0 && field[rowIndex + deckAmount - 1][columnIndex] == 0) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + deckAmount - 1 == 9) 9 else rowIndex + deckAmount

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
                field[x][y] = 2
                for (i in 1..deckAmount) {
                    if (x == rowIndex + i - 1 && y == columnIndex) {
                        field[x][y] = 1
                    }
                }
            }
        }
        return true
    } else {
        return false
    }
}

