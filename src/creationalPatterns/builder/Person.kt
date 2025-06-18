package creationalPatterns.builder

class Person private constructor(
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val email: String?,
) {
    class Builder {
        private var firstName: String? = null
        private var lastName: String? = null
        private var username: String? = null
        private var email: String? = null

        fun setFirstName(firstName: String?) = apply { this.firstName = firstName }

        fun setLastName(lastName: String?) = apply {this.lastName = lastName }

        fun setUserName(username: String?) = apply { this.username = username }

        fun setEmail(email: String?) = apply {this.email = email}

        fun build(): Person {
            return Person(firstName, lastName, username, email)
        }

    }

    override fun toString(): String {
        return "Person(\n\tfirstName=$firstName, " +
                "\n\tlastName=$lastName, " +
                "\n\tusername=$username, " +
                "\n\temail=$email)"
    }

}