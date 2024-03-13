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

    val r1 = (0..9).random()
    val r2 = (0..9).random()
    val r1Range = (r1 - 1..r1 + 1)
    val r2Range = (r2 - 1..r2 + 1)

    for (r in r1Range) {
        for (l in r2Range) {
            if (r >= 0 && l >= 0 && r <= 9 && l <= 9) {
                if (myField[r][l] == 0) {
                    continue
                } else {
                    return false
                }
            } else {
                continue
            }
        }
    }

    for (r in r1Range) {
        for (l in r2Range) {
            if (r >= 0 && l >= 0 && r <= 9 && l <= 9) {
                if (r != r1 || l != r2) {
                    myField[r][l] = 2
                } else {
                    myField[r][l] = 1
                }
            } else {
                continue
            }
        }
    }
    return true
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
//fun topLine(letterArray: CharArray) {

//    print(letterArray[1])
//    print(" ")
//    print(letterArray[2])
//    print(" ")
//    print(letterArray[3])
//    print(" ")
//    print(letterArray[4])
//    print(" ")
//    print(letterArray[5])
//    print(" ")
//    print(letterArray[6])
//    print(" ")
//    print(letterArray[7])
//    print(" ")
//    print(letterArray[8])
//    print(" ")
//    print(letterArray[9])
//    println()
//}

//fun getRandomA(): Int {
//    var a = (0..9).random()
//    return a
//}
//
//fun getRandomB(): Int {
//    var b = (0..9).random()
//    return b
//}
//    if (myField[random1_1][random1_2] == 0) {
//        if (myField[random1_1 - 1][random1_2 - 1] == 0) {
//            if (myField[random1_1 - 1][random1_2] == 0) {
//                if (myField[random1_1 - 1][random1_2 + 1] == 0) {
//                    if (myField[random1_1][random1_2 + 1] == 0) {
//                        if (myField[random1_1 + 1][random1_2 + 1] == 0) {
//                            if (myField[random1_1 + 1][random1_2] == 0) {
//                                if (myField[random1_1 + 1][random1_2 - 1] == 0) {
//                                    if (myField[random1_1][random1_2 - 1] == 0) {
//                                        myField[random1_1][random1_2] = 1
//                                        myField[random1_1 - 1][random1_2 - 1] = 2
//                                        myField[random1_1 - 1][random1_2] = 2
//                                        myField[random1_1 - 1][random1_2 + 1] = 2
//                                        myField[random1_1][random1_2 + 1] = 2
//                                        myField[random1_1 + 1][random1_2 + 1] = 2
//                                        myField[random1_1 + 1][random1_2] = 2
//                                        myField[random1_1 + 1][random1_2 - 1] = 2
//                                        myField[random1_1][random1_2 - 1] = 2
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return true
//    } else return false

//fun createOneDeckShip(myField: Array<Array<Int>>): Boolean {
//
//    val r1 = (0..9).random()
//    val r2 = (0..9).random()
//
//    for (r in myField[r1 - 1][r2]..myField[r1 + 1][r2]) {
//        for (l in myField[r][r2 - 1]..myField[r][r2 + 1]) {
//            if (r1 - 1 >= 0 && r2 - 1 >= 0) {
//                if (l == 0) {
//                    continue
//                } else {
//                    return false
//                }
//            } else {
//                continue
//            }
//        }
//    }
//
//    for (r in myField[r1 - 1][r2]..myField[r1 + 1][r2]) {
//        for (l in myField[r][r2 - 1]..myField[r][r2 + 1]) {
//            if (r1 - 1 >= 0 && r2 - 1 >= 0) {
//                myField[r][l] = 2
//            }
//        }
//    }
//    return true
//}