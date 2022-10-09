@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import kotlinx.html.I
import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.revert
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (i in v) {
        abs += sqr(i)
    }
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    if (list.isEmpty()) return result
    else {
        for (i in list) {
            result += i
        }
        return result / list.size
    }
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var sr = mean(list)
    if (list.isEmpty()) return list
    else {
        for ((i) in list.withIndex()) {
            list[i] = list[i] - sr
        }
        return list
    }
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    if (a.isEmpty() || b.isEmpty()) return 0
    else {
        for ((i) in a.withIndex()) {
            for ((j) in b.withIndex()) {
                if (i == j) {
                    c += a[i] * b[j]
                }
            }
        }
    };return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var summ = 0
    if (p.isEmpty()) return 0
    for ((i) in p.withIndex())
        summ += p[i] * Math.pow(x.toDouble(), i.toDouble()).toInt()
    return summ
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    else {
        for ((i) in list.withIndex()) {
            if (i != 0) {
                list[i] += list[i - 1]
            }
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    var result = mutableListOf<Int>()
    var del = 2
    while (del <= number) {
        if (number % del == 0) {
            result.add(del)
            number /= del
        } else del += 1
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")


/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var result = mutableListOf<Int>()
    var number = n
    if (n == 0) result.add(0)
    else{
    while (number > 0) {
        result.add(number % base)
        number /= base }
    }
    return result.reversed()
}


/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var list = convert(n, base)
    var result: String = ""
    for ((i) in list.withIndex()) {
        if (list[i] <= 9) {
            result += list[i]

        } else {
            result += ('\u0061' + list[i] - 10)
        }
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var len = digits.size - 1
    for (element in digits) {
        result += Math.pow(base.toDouble(), len.toDouble()).toInt() * element
        len -= 1
    }
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = mutableListOf<Int>()
    for (char in str) {
        if (char in '\u0030'..'\u0039') {
            result.add(char - '\u0030')
        } else {
            result.add((char - '\u0061') + 10)
        }
    }
    return decimal(result, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var list = mutableListOf<Int>()
    var result = mutableListOf<String>()
    var number = n
    while (number > 0) {
        list.add(number % 10)
        number /= 10
    }
    for ((i) in list.withIndex()) {
        when {
            i == 0 && list[i] in 1..3 -> result += "I".repeat(list[i] % 10)
            i == 0 && list[i] == 4 -> result += "IV"
            i == 0 && list[i] == 5 -> result += "V"
            i == 0 && list[i] in 6..8 -> result += "V" + "I".repeat(list[i] % 10 - 5)
            i == 0 && list[i] == 9 -> result += "IX"
            i == 1 && list[i] in 1..3 -> result += "X".repeat(list[i] % 10)
            i == 1 && list[i] == 4 -> result += "XL"
            i == 1 && list[i] == 5 -> result += "L"
            i == 1 && list[i] in 6..8 -> result += "L" + "X".repeat(list[i] % 10 - 5)
            i == 1 && list[i] == 9 -> result += "XC"
            i == 2 && list[i] in 1..3 -> result += "C".repeat(list[i] % 10)
            i == 2 && list[i] == 4 -> result += "CD"
            i == 2 && list[i] == 5 -> result += "D"
            i == 2 && list[i] in 6..8 -> result += "D" + "C".repeat(list[i] % 10 - 5)
            i == 2 && list[i] == 9 -> result += "CM"
            i == 3 && list[i] in 1..3 -> result += "M".repeat(list[i] % 10)
            list[i] == 0 -> result = result

        }
    }
    return result.reversed().joinToString(separator = "")
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var numbers = arrayOf(
        "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять",
        "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать",
        "восемнадцать", "девятнадцать", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
        "восемьдесят", "девяносто", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
        "восемьсот", "девятьсот", "одна", "две", "тысяча", "тысячи", "тысяч"
    )
    var number = n
    var list = mutableListOf<String>()
    var count = digitNumber(number) - 1
    var flag3 = 0
    var flag1 = 0
    while (number > 0) {
        var x = number / Math.pow(10.0, count.toDouble()).toInt()
        when {
            x == 0 -> list = list
            count == 5 -> {
                list.add(numbers[26 + x])
                if (number / 1000 % 100 == 0) {
                    list.add(numbers[40])
                }
            }

            count == 4 && x == 1 -> {
                list.add(numbers[9 + number / Math.pow(10.0, count - 1.toDouble()).toInt() % 10])
                list.add(numbers[40])
                flag3 = 1
            }

            count == 4 && x != 1 -> {
                list.add(numbers[17 + x])
                if (number / 1000 % 10 == 0) {
                    list.add(numbers[40])
                }
            }

            count == 3 && x == 0 -> list.add(numbers[40])
            count == 3 && flag3 == 0 && x == 1 -> {
                list.add(numbers[36])
                list.add(numbers[38])
            }

            count == 3 && flag3 == 0 && x == 2 -> {
                list.add(numbers[37])
                list.add(numbers[39])
            }

            count == 3 && flag3 == 0 && x in 3..4 -> {
                list.add(numbers[2 + x - 3])
                list.add(numbers[39])
            }

            count == 3 && flag3 == 0 && x in 5..9 -> {
                list.add(numbers[4 + x - 5])
                list.add(numbers[40])
            }

            count == 3 && flag3 == 0 && x == 0 -> {
                list.add(numbers[40])
            }

            count == 2 -> list.add(numbers[26 + x])
            count == 1 && x != 1 -> list.add(numbers[17 + x])
            count == 1 && x == 1 -> {
                list.add(numbers[9 + number / Math.pow(10.0, count - 1.toDouble()).toInt() % 10])
                flag1 = 1
            }

            count == 0 && flag1 == 0 -> list.add(numbers[-1 + x])
        }
        count -= 1
        number = number % Math.pow(10.0, count + 1.toDouble()).toInt()
    }
    return list.joinToString(" ")
}


