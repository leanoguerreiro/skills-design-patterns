package creationalPatterns.builder

fun main() {
    val person1 = Person.Builder()
        .setFirstName("jhon")
        .setLastName("Joe")
        .setUserName("jhonjoe123")
        .setEmail("jhonjoe123@gmail.com")
        .build()

    println(person1.toString())

}