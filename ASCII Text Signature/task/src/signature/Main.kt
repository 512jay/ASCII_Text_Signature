package signature

import java.io.File
import java.util.Scanner

fun main() {
    val romanWidth = mutableMapOf("space" to 10)
    val roman = mutableMapOf("" to arrayOf(""))
    loadFont(romanWidth, roman, "/Users/jay/Desktop/roman.txt")

    val medWidth = mutableMapOf<String, Int>("space" to 5)
    val med = mutableMapOf<String, Array<String>>("" to arrayOf(""))
    loadFont(medWidth, med, "/Users/jay/Desktop/medium.txt")

    val scanner = Scanner(System.`in`)
    print("Enter name and surname: ")
    val name = scanner.nextLine()
    print("Enter person's status: ")
    val status = scanner.nextLine()

    val length1 = getLength(name, romanWidth)
    val length2 = getLength(status, medWidth)
    val border = 8

    if (length1 > length2) {
        println(filler(length1 + border,"8"))
        printString(name, roman, "", "")
        printString(status, med, filler(preFill(length1, length2)), filler(postFill(length1, length2)))
        println(filler(length1 + border,"8"))
    } else {
        println(filler(length2 + border,"8"))
        printString(name, roman, filler(preFill(length1, length2)), filler(postFill(length1, length2)))
        printString(status, med, "", "")
        println(filler(length2 + border,"8"))
    }
//
//    println("length1 $length1, and length2 $length2")
//    println("filler(preFill(length1, length2))  = ${preFill(length1, length2)}")
//    println("filler(postFill(length1, length2)) = ${postFill(length1, length2)}")
}

fun loadFont(fontWidth: MutableMap<String, Int>, font: MutableMap<String, Array<String>>,
             path: String) {
    val romanScanner = Scanner(File(path))
    val romanFontSize = romanScanner.nextInt() // 10
    val charsInRoman = romanScanner.nextInt() // 52

    for (i1 in 0 until charsInRoman) {
        val temp = romanScanner.next()
        val width = romanScanner.nextInt()
        romanScanner.nextLine()
        fontWidth[temp] = width
        // println("letter $temp width ${romanWidth[temp]}")
        val letter: Array<String> = Array(romanFontSize) { "" }
        for (i2 in 0 until romanFontSize) letter[i2] = romanScanner.nextLine()
        font[temp] = letter
    }
}

fun printString(c: String, font: MutableMap<String, Array<String>>, preFill: String, postFill: String) {
    val array = font.getValue(c.first().toString())
    val size = array.size
    val str =  Array(size) {"88  $preFill"}

    for (char in c) {
        if (char == ' ') {
            if (size == 10){
                for (line in 0 until size) {
                    str[line] += filler(10," ")
                }
            } else {
                for (line in 0 until size) {
                    str[line] += filler(5, " ")
                }
            }
        }else {
            for (line in 0 until size) {
                str[line] += font.getValue(char.toString())[line]
            }
        }
    }

    for (line in str) {
        println("$line$postFill  88")
    }
}

fun getLength(str: String, fontWidth: MutableMap<String, Int>): Int {
    var length = 0
    for ( c in str) {
        length += if (c == ' ') {
            fontWidth.getValue("space")
        } else {
            fontWidth.getValue(c.toString())
        }
    }
    return length
}

fun preFill(length1: Int, length2: Int): Int {
    val longest = if (length1 >= length2) length1 else length2
    val shortest = if(length1 < length2) length1 else length2
    if (isEven(longest) && isEven(shortest)){
        return (longest - shortest) / 2
    } else if (isOdd(longest) && isOdd(shortest)){
        return (longest - shortest) / 2
    } else if (isEven(longest) && isOdd(shortest)){
        return (longest - shortest) / 2
    } else if (isOdd(longest) && isEven(shortest)){
        return (longest - shortest) / 2
    }
    return (longest - shortest) / 2
}

fun postFill (length1: Int, length2: Int): Int {
    val longest = if (length1 >= length2) length1 else length2
    val shortest = if (length1 < length2) length1 else length2
    if (isEven(longest) && isEven(shortest)){
        return (longest - shortest) / 2
    } else if (isOdd(longest) && isOdd(shortest)){
        return (longest - shortest) / 2
    } else if (isEven(longest) && isOdd(shortest)){
        return (longest - shortest) / 2 + 1
    } else if (isOdd(longest) && isEven(shortest)){
        return (longest - shortest) / 2 + 1
    }
    return (longest - shortest) / 2
}

fun filler(num: Int, char: String = " "): String {
    var str = ""
    repeat(num) {
        str += char
    }
    return str
}

fun isEven(num: Int): Boolean {
    if (num % 2 == 0 ) {
        return true
    }
    return false
}

fun isOdd(num: Int): Boolean {
        if (num % 2 == 1 ) {
            return true
        }
        return false
}
