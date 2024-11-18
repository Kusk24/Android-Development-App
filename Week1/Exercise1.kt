class Exercise1 {
    /**
     * You can edit, run, and share this code.
     * play.kotlinlang.org

    Exercise 1
    fun main() {
    println("Hello, world!!!")
    var number = 5

    //val numberIsOdd = number % 2 != 0
    val numberIsOdd = if (number % 2 != 0) true else false

    if (numberIsOdd){
    print("odd")
    }else{
    print("even")
    }

    print(if (number % 2 != 0) "odd" else "even")
    }

     */

    /*
    //Exercise 2
    fun main() {

        val work = 20

        when {
            (work <= 2) -> print(work*20)
            (3 <= work && work <= 5) -> print(work*30)
            else -> print(work*40)
        }

    }
    */

    /*
    fun main() {
        val hour = 20
          if (hour >= 1 && hour <=2){
            print("$hour: Hour: ${hour * 20} Baht")
        } else if (hour >= 3 && hour <5) {
            print("${hour * 30} Baht")
        } else if (hour > 5) {
            print("${hour * 40} Baht")
        }
    }
    */

    /*
    fun main() {
        val fruits = listOf("apple", "banana", "peach", "kiwi")

        for (fruit in fruits) {
            println(fruit)
        }

        //Kotlin
        for (i in 1..10){
            //println(i)
        }

        //while loop
        var index = 0
        while (index < fruits){
            print(fruits[index])
            index++
        }
    }
    */
    /*
    fun main() {
        val num = 9


        fun isPrime(number: Int):Boolean {
            if (number == 1){
                return true
            } else {
                if (num % number == 0 && number != num) {
                    return false
                } else {
                    return isPrime(number - 1)
                }
            }
        }

        if (isPrime(num)) {
            print("It is Prime")
        } else {
            print("It is not prime")
        }
    }
    */
    /*
    fun main() {
        fun fact(num: Int): Int {
            if (num == 1) {
                return 1
            } else {
                return num * fact(num-1)
            }
        }
        print(fact(5))
    }
    */

    /*
    //Example 5
    fun main() {
        val person1 = Person("Michael", "Jiem", 33)
        //println("${person1.firstName} ${person1.lastName} ${person1}.age}")
        print(person1)
        val person2 = person1.copy()
        val person3 = person1

        person1
        print(person2)
        print(person3)
    }

    data class Person(var firstName: String, var lastName: String, var age: Int){
        constructor (firstName: String, age: Int): this(firstName, "DEFAULT", age)

        fun setFirstName(firstName: String){

        }
    }
    */

    fun main() {
        val a: String? = null
        print(a?.length)
    }
}