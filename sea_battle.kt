fun main() {
    val myField = Array(10) { Array(10) { 0 } }
    val pcField = Array(10) { Array(10) { 0 } }

    val turn = 0..1
    val whatTurn = turn.random()

    var oneDeckShipsCounter = 0
    var twoDeckShipsCounter = 0
    var threeDeckShipsCounter = 0
    var fourDeckShipsCounter = 0

    val oneDeckShipAmount = 3
    val twoDeckShipAmount = 2
    val threeDeckShipAmount = 1
    val fourDeckShipAmount = 1

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
        if (turn.random() == 0) {
            if (createShipsColumn(myField, deckAmount = 2)) {
                twoDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(myField, deckAmount = 2)) {
                twoDeckShipsCounter++
            } else continue
        }
    }
    twoDeckShipsCounter = 0
    while (twoDeckShipsCounter < twoDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(pcField, deckAmount = 2)) {
                twoDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(pcField, deckAmount = 2)) {
                twoDeckShipsCounter++
            } else continue
        }
    }
    while (threeDeckShipsCounter < threeDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(myField, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(myField, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        }
    }
    threeDeckShipsCounter = 0
    while (threeDeckShipsCounter < threeDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(pcField, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(pcField, deckAmount = 3)) {
                threeDeckShipsCounter++
            } else continue
        }
    }

    while (fourDeckShipsCounter < fourDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(myField, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(myField, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        }
    }
    fourDeckShipsCounter = 0
    while (fourDeckShipsCounter < fourDeckShipAmount) {
        if (turn.random() == 0) {
            if (createShipsColumn(pcField, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        } else {
            if (createShipsRow(pcField, deckAmount = 4)) {
                fourDeckShipsCounter++
            } else continue
        }
    }

    printField(myField)

    printField(pcField)

//    ХОДЫ

    while (checkLastShip(myField)) {
        while (checkLastShip(pcField)) {
            if (whatTurn == 0) {
                while (myTurn(pcField)) {
                    continue
                }
                while (pcTurn(myField)) {
                    continue
                }
            } else {
                while (pcTurn(myField)) {
                    continue
                }
                while (myTurn(pcField)) {
                    continue
                }
            }
        }
        continue
    }
    if (!checkLastShip(myField)) {
        println("Вы проиграли!")
    }
    if (!checkLastShip(pcField)) {
        println("Вы выиграли!")
    }
}

// ФУНКЦИЯ МОЕГО ХОДА

fun myTurn(field: Array<Array<Int>>): Boolean {
    println("Введите координаты для выстрела:")
    val myTurn = readln()
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    if (checkReadLine(myTurn)) {
        if (myTurn.length == 2) {
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
        }
        if (myTurn.length == 3) {
            myTurn.toCharArray()
            val column = letterArray.indexOf(myTurn[0])
            val row = 9
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
        } else return false
    } else {
        return false
    }
}

// ФУНКЦИЯ ХОДА ПК

fun pcTurn(field: Array<Array<Int>>): Boolean {
    val rowIndex = (0..9).random()
    val columnIndex = (0..9).random()
    val letterArray = charArrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'К')
    val numbersArray = charArrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', ' ')
    if (field[rowIndex][columnIndex] == 8) {
        return false
    }
    val num = numbersArray[rowIndex]
    val let = letterArray[columnIndex]
    if (rowIndex == 9) {
        println()
        print("ПК бьет в $let")
        println("10")
        if (checkShoot(field, rowIndex, columnIndex)) {
            field[rowIndex][columnIndex] = 8
            return true
        } else return false
    } else {
        println("ПК бьет в $let$num")
        if (checkShoot(field, rowIndex, columnIndex)) {
            field[rowIndex][columnIndex] = 8
            return true
        } else return false
    }
}

// ФУНКЦИЯ ПРОВЕРКИ НА ПРОИГРЫШ

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
        val minRowIndex = if (row - 3 < 0) 0 else row - 3
        val maxRowIndex = if (row + 3 > 9) 9 else row + 3

        val minColumnIndex = if (column - 3 < 0) 0 else column - 3
        val maxColumIndex = if (column + 3 > 9) 9 else column + 3

        val rowIndexRange = minRowIndex..maxRowIndex
        val columnIndexRange = minColumnIndex..maxColumIndex

        for (x in rowIndexRange) {
            if (field[x][column] == 1) {
                if (x == row) {
                    continue
                }
                if (x == row - 1 || x == row + 1) {
                    println("Ранил!")
                    return true
                }
                if (x < row) {
                    for (x1 in x until row) {
                        if (field[x1][column] == 2) {
                            break
                        } else continue
                    }
                }
                if (x > row) {
                    for (x1 in row + 1..x) {
                        if (field[x1][column] == 2) {
                            break
                        } else continue
                    }
                }
            } else continue
        }
        for (y in columnIndexRange) {
            if (field[row][y] == 1) {
                if (y == column) {
                    continue
                }
                if (y == column - 1 || y == column + 1) {
                    println("Ранил!")
                    return true
                }
                if (y < column) {
                    for (y1 in y until column) {
                        if (field[row][y1] == 2) {
                            break
                        } else continue
                    }
                }
                if (y > column) {
                    for (y1 in column + 1..y) {
                        if (field[row][y1] == 2) {
                            break
                        } else continue
                    }
                }
            } else continue
        }
        println("Убил!")
        return true
    } else {
        println("Мимо!")
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

    if (deckAmount == 2) {
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
    if (deckAmount == 3) {
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
    if (deckAmount == 4) {
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
    } else {
        return false
    }
}

// ФУНКЦИЯ ПОСТРОЕНИЯ КОРАБЛЕЙ В ЛИНИЮ

fun createShipsRow(field: Array<Array<Int>>, deckAmount: Int): Boolean {

    if (deckAmount == 2) {
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
    if (deckAmount == 3) {
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
    if (deckAmount == 4) {
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
    } else {
        return false
    }
}