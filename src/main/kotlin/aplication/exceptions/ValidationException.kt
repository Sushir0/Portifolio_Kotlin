package aplication.exceptions

data class ValidationException(
    val errors: List<String>
) : Exception() {

    override val message: String
        get() = errors.joinToString(",")
}
