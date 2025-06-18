package creationalPatterns.builder

fun main() {
    val person1 = Person.Builder()
        .setFirstName("Leano")
        .setLastName("Baba")
        .setUserName("808kojisosa")
        .setEmail("kojisosa@gmail.com")
        .build()

    println(person1.toString())

}