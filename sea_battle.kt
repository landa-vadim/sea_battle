fun main() {
    val myField = Array(10) { Array(10) { 0 } }
    val pcField = Array(10) { Array(10) { 0 } }

    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    val turn = 0..1

    var oneDeckShipsCounter = 0
    var twoDeckShipsCounter = 0
    var threeDeckShipsCounter = 0
    var fourDeckShipsCounter = 0

    val oneDeckShipAmount = 3
    val twoDeckShipAmount = 2
    val threeDeckShipAmount = 1
    val fourDeckShipAmount = 1

//    val predicate: (Int) -> Boolean = { it == 1 }

//    ЦИКЛЫ ПОСТРОЕНИЯ ПОЛЕЙ С КОРОБЛЯМИ

    while (oneDeckShipsCounter < oneDeckShipAmount) {
        if (createOneDeckShip(myField)) {
            oneDeckShipsCounter++
        } else continue
    }
    oneDeckShipsCounter = 0
    while (oneDeckShipsCounter < oneDeckShipAmount) {
        if (createOneDeckShip(pcField)) {
            oneDeckShipsCounter++
        } else continue
    }

    while (twoDeckShipsCounter < twoDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createTwoDeckShipColumn(myField)) {
                twoDeckShipsCounter++
            } else continue
        } else {
            if (createTwoDeckShipRow(myField)) {
                twoDeckShipsCounter++
            } else continue
        }
    }
    twoDeckShipsCounter = 0
    while (twoDeckShipsCounter < twoDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createTwoDeckShipColumn(pcField)) {
                twoDeckShipsCounter++
            } else continue
        } else {
            if (createTwoDeckShipRow(pcField)) {
                twoDeckShipsCounter++
            } else continue
        }
    }

    while (threeDeckShipsCounter < threeDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createThreeDeckShipColumn(myField)) {
                threeDeckShipsCounter++
            } else continue
        } else {
            if (createThreeDeckShipRow(myField)) {
                threeDeckShipsCounter++
            } else continue
        }
    }
    threeDeckShipsCounter = 0
    while (threeDeckShipsCounter < threeDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createThreeDeckShipColumn(pcField)) {
                threeDeckShipsCounter++
            } else continue
        } else {
            if (createThreeDeckShipRow(pcField)) {
                threeDeckShipsCounter++
            } else continue
        }
    }

    while (fourDeckShipsCounter < fourDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createFourDeckShipColumn(myField)) {
                fourDeckShipsCounter++
            } else continue
        } else {
            if (createFourDeckShipRow(myField)) {
                fourDeckShipsCounter++
            } else continue
        }
    }
    fourDeckShipsCounter = 0
    while (fourDeckShipsCounter < fourDeckShipAmount) {
        val shipSide = 0..1
        if (shipSide.random() == 0) {
            if (createFourDeckShipColumn(pcField)) {
                fourDeckShipsCounter++
            } else continue
        } else {
            if (createFourDeckShipRow(pcField)) {
                fourDeckShipsCounter++
            } else continue
        }
    }

    printField(myField)

//    ХОДЫ

    while (checkLastShip(myField)) {
        if (turn.random() == 0) {
            while (myTurn(pcField)) {
                continue
            }
//            while (pcTurn(myField)) {
//                continue
//            }
        } else {
//            while (pcTurn(myField)) {
//                continue
//            }
            while (myTurn(pcField)) {
                continue
            }
        }
        continue
    }
}

// ФУНКЦИЯ МОЕГО ХОДА

fun myTurn(field: Array<Array<Int>>): Boolean {
    println("Введите координаты для выстрела:")
    val myTurn = readln()
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    if (checkReadLine(myTurn)) {
        myTurn.toCharArray()
        val column = letterArray.indexOf(myTurn[0])
        val row = myTurn[1].digitToInt() - 1
        if (field[row][column] != 8) {
            if (checkShoot(field, row, column)) {
                field[row][column] = 8
                return true
            } else {
                println("Мимо!")
                return false
            }
        } else {
            println("Вы уже сюда стреляли!")
            return false
        }
    } else {
        return false
    }
}

// ФУНКЦИЯ ХОДА ПК

//fun pcTurn(field: Array<Array<Int>>): Boolean {
//    val rowIndex = (0..9).random()
//    val columnIndex = (0..9).random()
//
//    if () {
//
//
//        if (checkMyShoot(field, row, column)) {
//            field[row][column] = 8
//            return true
//        } else {
//            println("Мимо!")
//            return false
//        }
//    } else {
//        return false
//    }
//}

// ФУНКЦИЯ ПРОВЕРКИ НА МОЙ ПРОИГРЫШ

fun checkLastShip(field: Array<Array<Int>>): Boolean {
    for (x in 0..9) {
        for (y in 0..9) {
            if (field[x][y] == 1) {
                return true
            } else continue
        }
    }
    return false
}

// ФУНКЦИЯ ПРОВЕРКИ ТОЧНОСТИ ВЫСТРЕЛА

fun checkShoot(field: Array<Array<Int>>, row: Int, column: Int): Boolean {
    if (field[row][column] == 1) {
        val minRowIndex = if (row - 1 < 0) 0 else row - 1
        val maxRowIndex = if (row + 1 > 9) 9 else row + 1

        val minColumnIndex = if (column - 1 < 0) 0 else column - 1
        val maxColumIndex = if (column + 1 > 9) 9 else column + 1

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            for (y in columnIndexRange) {
                if (field[x][y] == 1) {
                    if (x == row && y == column) {
                        continue
                    } else {
                        println("Ранил!")
                        break
                    }
                } else continue
            }
        }
        return true
    } else {
        return false
    }
}

// ФУНКЦИЯ ПРОВЕРКИ КОРРЕКТНОСТИ ВВЕДЕННЫХ КООРДИНАТ

fun checkReadLine(myTurn: String): Boolean {
    if (myTurn.length == 2) {
        myTurn.toCharArray()
        val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
        val numbersArray = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
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
    } else {
        println("Нужно ввести два символа в формате \"Буква колонки номер строки\"")
        return false
    }
}

// ФУНКЦИЯ ПОСТОРОЕНИЯ 1-ПАЛУБНЫХ КОРАБЛЕЙ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 2-ПАЛУБНЫХ КОРАБЛЕЙ В КОЛОНКУ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 2-ПАЛУБНЫХ КОРАБЛЕЙ В ЛИНИЮ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 3-ПАЛУБНЫХ КОРАБЛЕЙ В КОЛОНКУ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 3-ПАЛУБНЫХ КОРАБЛЕЙ В ЛИНИЮ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 4-ПАЛУБНЫХ КОРАБЛЕЙ В КОЛОНКУ

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

// ФУНКЦИЯ ПОСТОРОЕНИЯ 4-ПАЛУБНЫХ КОРАБЛЕЙ В ЛИНИЮ

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

