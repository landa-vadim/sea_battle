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

//    ХОДЫ

    var isMyShipExist = checkAnyShipExist(myField)
    var isPcShipExist = checkAnyShipExist(pcField)

    while (isMyShipExist && isPcShipExist) {
        if (whatTurn == 0) {
            while (myTurn(pcField, enemyField) && checkAnyShipExist(pcField)) {
                printField(enemyField)
            }
            printField(enemyField)
            while (checkAnyShipExist(pcField) && pcTurn(playerField, myField) && checkAnyShipExist(myField)) {
                printField(myField)
            }
            printField(myField)
        } else {
            while (pcTurn(playerField, myField) && checkAnyShipExist(myField)) {
                printField(myField)
            }
            printField(myField)
            while (checkAnyShipExist(myField) && myTurn(pcField, enemyField) && checkAnyShipExist(pcField)) {
                printField(enemyField)
            }
            printField(enemyField)
        }
        isMyShipExist = checkAnyShipExist(myField)
        isPcShipExist = checkAnyShipExist(pcField)
    }
    if (isMyShipExist) {
        println("Вы выиграли!")
    }
    if (isPcShipExist) {
        println("Вы проиграли!")
    }
}

// КОНСТАНТЫ СОСТОЯНИЯ КЛЕТКИ ПОЛЯ

const val LIVE_DECK = 1
const val NO_DECK = 2
const val DAMAGED_DECK = 8
const val UNKNOWN = 0

// ФУНКЦИЯ ЗАПОЛНЕНИЯ ПОЛЕЙ

fun initField(field: Array<Array<Int>>) {

    val oneDeckShipAmount = 3
    val twoDeckShipAmount = 2
    val threeDeckShipAmount = 1
    val fourDeckShipAmount = 1
    val turn = 0..1

    for (i in 0 until oneDeckShipAmount) {
        while (!createOneDeckShip(field)) {
        }
    }
    for (i in 0 until twoDeckShipAmount) {
        if (turn.random() == 0) {
            while (!createShipsColumn(field, 2)) {
            }
        } else {
            while (!createShipsRow(field, 2)) {
            }
        }
    }
    for (i in 0 until threeDeckShipAmount) {
        if (turn.random() == 0) {
            while (!createShipsColumn(field, 3)) {
            }
        } else {
            while (!createShipsRow(field, 3)) {
            }
        }
    }
    for (i in 0 until fourDeckShipAmount) {
        if (turn.random() == 0) {
            while (!createShipsColumn(field, 4)) {
            }
        } else {
            while (!createShipsRow(field, 4)) {
            }
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
            if (enemyField[row][column] != DAMAGED_DECK && enemyField[row][column] != NO_DECK) {
                if (checkShoot(pcField, row, column, enemyField)) {
                    pcField[row][column] = DAMAGED_DECK
                    enemyField[row][column] = DAMAGED_DECK
                    return true
                } else {
                    return false
                }
            } else {
                if (enemyField[row][column] != DAMAGED_DECK) {
                    println("Вы уже сюда стреляли!")
                    return true
                }
                if (enemyField[row][column] != NO_DECK) {
                    println("Вы уже знаете, что здесь не может быть корабля!")
                    return true
                }
            }
        }
        if (myTurn.length == 3) {
            myTurn.toCharArray()
            val column = letterArray.indexOf(myTurn[0])
            val row = 9
            if (enemyField[row][column] != DAMAGED_DECK) {
                if (checkShoot(pcField, row, column, enemyField)) {
                    pcField[row][column] = DAMAGED_DECK
                    enemyField[row][column] = DAMAGED_DECK
                    return true
                } else {
                    return false
                }
            } else {
                println("Вы уже сюда стреляли!")
                return true
            }
        } else return false
    } else {
        return true
    }
}

// ФУНКЦИЯ ХОДА ПК

fun pcTurn(playerField: Array<Array<Int>>, myField: Array<Array<Int>>): Boolean {
    var rowIndex = 11
    var columnIndex = 11
    if (pcMustExecute(myField, playerField)) {
        for (x in 0..9) {
            for (y in 0..9) {
                val bottomBoard = if (x + 1 > 9) 9 else x + 1
                val topBoard = if (x - 1 < 0) 0 else x - 1
                val rightBoard = if (y + 1 > 9) 9 else y + 1
                val leftBoard = if (y - 1 < 0) 0 else y - 1
                val bottomBoardMax = if (bottomBoard + 1 > 9) 9 else bottomBoard + 1
                val rightBoardMax = if (rightBoard + 1 > 9) 9 else rightBoard + 1
                if (playerField[x][y] == DAMAGED_DECK) {
                    if (playerField[bottomBoard][y] == DAMAGED_DECK) {
                        for (c in topBoard..bottomBoardMax) {
                            if (playerField[c][y] != NO_DECK && playerField[c][y] != DAMAGED_DECK) {
                                rowIndex = c
                                columnIndex = y
                                break
                            }
                        }
                    }
                    if (playerField[x][rightBoard] == DAMAGED_DECK) {
                        for (v in leftBoard..rightBoardMax) {
                            if (playerField[x][v] != NO_DECK && playerField[x][v] != DAMAGED_DECK) {
                                rowIndex = x
                                columnIndex = v
                                break
                            }
                        }
                    }
                    if (rowIndex > 10 && columnIndex > 10 && (playerField[bottomBoard][y] == UNKNOWN || playerField[topBoard][y] == UNKNOWN || playerField[x][rightBoard] == UNKNOWN || playerField[x][leftBoard] == UNKNOWN)) {
                        if (playerField[bottomBoard][y] == UNKNOWN) {
                            rowIndex = bottomBoard
                            columnIndex = y
                            break
                        }
                        if (playerField[topBoard][y] == UNKNOWN) {
                            rowIndex = topBoard
                            columnIndex = y
                            break
                        }
                        if (playerField[x][rightBoard] == UNKNOWN) {
                            rowIndex = x
                            columnIndex = rightBoard
                            break
                        }
                        if (playerField[x][leftBoard] == UNKNOWN) {
                            rowIndex = x
                            columnIndex = leftBoard
                            break
                        }
                    }
                }
            }
            if (rowIndex < 11 && columnIndex < 11) break
        }
    } else {
        rowIndex = (0..9).random()
        columnIndex = (0..9).random()
    }
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    val numbersArray = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', ' ')
    while (playerField[rowIndex][columnIndex] == DAMAGED_DECK || playerField[rowIndex][columnIndex] == NO_DECK) {
        rowIndex = (0..9).random()
        columnIndex = (0..9).random()
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
        return true
    } else return false
}

// ФУНКЦИЯ ПРОВЕРКИ ПОЛЯ ИГРОКА НА РАНЕНЫЙ КОРАБЛЬ

fun pcMustExecute(myField: Array<Array<Int>>, playerField: Array<Array<Int>>): Boolean {
    for (x in 0..9) {
        for (y in 0..9) {
            val topBoard = if (x - 1 < 0) 0 else x - 1
            val bottomBoard = if (x + 1 > 9) 9 else x + 1
            val leftBoard = if (y - 1 < 0) 0 else y - 1
            val rightBoard = if (y + 1 > 9) 9 else y + 1
            if (myField[x][y] == DAMAGED_DECK) {
                if (myField[topBoard][y] != NO_DECK) {
                    if (myField[topBoard][y] == LIVE_DECK) {
                        return true
                    }
                }
                if (myField[bottomBoard][y] != NO_DECK) {
                    if (myField[bottomBoard][y] == LIVE_DECK) {
                        return true
                    }
                }
                if (myField[x][leftBoard] != NO_DECK) {
                    if (myField[x][leftBoard] == LIVE_DECK) {
                        return true
                    }
                }
                if (myField[x][rightBoard] != NO_DECK) {
                    if (myField[x][rightBoard] == LIVE_DECK) {
                        return true
                    }
                }
            }
        }
    }
    return false
}

// ФУНКЦИЯ ПРОВЕРКИ НА ПРОИГРЫШ

fun checkAnyShipExist(field: Array<Array<Int>>): Boolean {
    for (x in 0..9) {
        for (y in 0..9) {
            if (field[x][y] == LIVE_DECK) {
                return true
            }
        }
    }
    return false
}

// ФУНКЦИЯ ПРОВЕРКИ ТОЧНОСТИ ВЫСТРЕЛА

fun checkShoot(field: Array<Array<Int>>, row: Int, column: Int, field2: Array<Array<Int>>): Boolean {
    if (field[row][column] == LIVE_DECK) {
        field[row][column] = DAMAGED_DECK
        field2[row][column] = DAMAGED_DECK
        val topBorder = if (row - 1 < 0) 0 else row - 1
        val maxTopBorder = if (row - 4 < 0) 0 else row - 4
        val bottomBorder = if (row + 1 > 9) 9 else row + 1
        val maxBottomBorder = if (row + 4 > 9) 9 else row + 4
        val leftBorder = if (column - 1 < 0) 0 else column - 1
        val maxLeftBorder = if (column - 4 < 0) 0 else column - 4
        val rightBorder = if (column + 1 > 9) 9 else column + 1
        val maxRightBorder = if (column + 4 > 9) 9 else column + 4

        for (x in topBorder downTo maxTopBorder) {
            if (field[x][column] != NO_DECK) {
                if (field[x][column] == LIVE_DECK) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (x in bottomBorder..maxBottomBorder) {
            if (field[x][column] != NO_DECK) {
                if (field[x][column] == LIVE_DECK) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (y in leftBorder downTo maxLeftBorder) {
            if (field[row][y] != NO_DECK) {
                if (field[row][y] == LIVE_DECK) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (y in rightBorder..maxRightBorder) {
            if (field[row][y] != NO_DECK) {
                if (field[row][y] == LIVE_DECK) {
                    println("Ранил!")
                    return true
                }
            } else {
                break
            }
        }
        for (x in topBorder downTo maxTopBorder) {
            if (field2[x][column] == DAMAGED_DECK) {
                for (y in leftBorder..rightBorder) {
                    if (field2[x][y] != DAMAGED_DECK) {
                        field2[x][y] = NO_DECK
                    }
                }
            } else {
                for (y in leftBorder..rightBorder) {
                    field2[x][y] = NO_DECK
                }
                break
            }
        }
        for (x in bottomBorder..maxBottomBorder) {
            if (field2[x][column] == DAMAGED_DECK) {
                for (y in leftBorder..rightBorder) {
                    if (field2[x][y] != DAMAGED_DECK) {
                        field2[x][y] = NO_DECK
                    }
                }
            } else {
                for (y in leftBorder..rightBorder) {
                    field2[x][y] = NO_DECK
                }
                break
            }
        }
        for (y in leftBorder downTo maxLeftBorder) {
            if (field2[row][y] == DAMAGED_DECK) {
                for (x in topBorder..bottomBorder) {
                    if (field2[x][y] != DAMAGED_DECK) {
                        field2[x][y] = NO_DECK
                    }
                }
            } else {
                for (x in topBorder..bottomBorder) {
                    field2[x][y] = NO_DECK
                }
                break
            }
        }
        for (y in rightBorder..maxRightBorder) {
            if (field2[row][y] == DAMAGED_DECK) {
                for (x in topBorder..bottomBorder) {
                    if (field2[x][y] != DAMAGED_DECK) {
                        field2[x][y] = NO_DECK
                    }
                }
            } else {
                for (x in topBorder..bottomBorder) {
                    field2[x][y] = NO_DECK
                }
                break
            }
        }
        println("Убил!")
        return true
    } else {
        print("Мимо!")
        field[row][column] = NO_DECK
        field2[row][column] = NO_DECK
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

    if (field[rowIndex][columnIndex] == UNKNOWN) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == UNKNOWN || field[x][y] == NO_DECK) {
                    continue
                } else {
                    return false
                }
            }
        }
        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (x != rowIndex || y != columnIndex) {
                    field[x][y] = NO_DECK
                } else {
                    field[x][y] = LIVE_DECK
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

    if (field[rowIndex][columnIndex] == UNKNOWN && field[rowIndex][columnIndex + deckAmount - 1] == UNKNOWN) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + 1 > 9) 9 else rowIndex + 1

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + deckAmount - 1 == 9) 9 else columnIndex + deckAmount

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == UNKNOWN || field[x][y] == NO_DECK) {
                    continue
                } else {
                    return false
                }
            }
        }
        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                field[x][y] = NO_DECK
                for (i in 1..deckAmount) {
                    if (x == rowIndex && y == columnIndex + i - 1) {
                        field[x][y] = LIVE_DECK
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

    if (field[rowIndex][columnIndex] == UNKNOWN && field[rowIndex + deckAmount - 1][columnIndex] == UNKNOWN) {

        val minRowIndex = if (rowIndex - 1 < 0) 0 else rowIndex - 1
        val maxRowIndex = if (rowIndex + deckAmount - 1 == 9) 9 else rowIndex + deckAmount

        val minColumnIndex = if (columnIndex - 1 < 0) 0 else columnIndex - 1
        val maxColumIndex = if (columnIndex + 1 > 9) 9 else columnIndex + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == UNKNOWN || field[x][y] == NO_DECK) {
                    continue
                } else {
                    return false
                }
            }
        }
        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                field[x][y] = NO_DECK
                for (i in 1..deckAmount) {
                    if (x == rowIndex + i - 1 && y == columnIndex) {
                        field[x][y] = LIVE_DECK
                    }
                }
            }
        }
        return true
    } else {
        return false
    }
}

