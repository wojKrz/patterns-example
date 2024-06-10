class Validator<T>(
    private val rules: List<ValidationRule<T, *>>
) {

    /**
     * First name cannot be empty
     * Second name cannot be empty
     * Tax id number starts with 9 and is not empty
     */
    fun validate(validatedObject: T): ValidationResult {
        val result = rules.mapNotNull { it.check(validatedObject) }

//        if(invoice.firstName.isEmpty()) {
//            result.add(ValidationError("firstName", "Cannot be empty"))
//        }
//        if(invoice.secondName.isEmpty()) {
//            result.add(ValidationError("secondName", "Cannot be empty"))
//        }
//        if(invoice.taxIdNumber.isEmpty()){
//            result.add(ValidationError("taxIdNumber", "CannotBeEmpty"))
//        }
//        if(invoice.taxIdNumber.startsWith("9").not()){
//            result.add(ValidationError("taxIdNumber", "Needs to start with 9"))
//        }

        return if (result.isEmpty()) {
            ValidationResult.Success
        } else {
            ValidationResult.Failure(result)
        }
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Failure(
        val list: List<ValidationError>
    ) : ValidationResult()
}

data class ValidationError(
    val fieldName: String, val error: String
)

interface ValidationRule<T, S> {
    val fieldGetter: (T) -> S
    fun check(validatedObject: T): ValidationError?
}

class FieldIsNotEmptyRule(
    override val fieldGetter: (Invoice) -> String,
    private val verboseFieldName: String,
    private val nextRule: ValidationRule<Invoice, *>? = null
) : ValidationRule<Invoice, String> {

    override fun check(validatedObject: Invoice): ValidationError? =
        if (fieldGetter(validatedObject).isEmpty()) {
            ValidationError(verboseFieldName, "Cannot be empty")
        } else {
            nextRule?.check(validatedObject)
        }
}

class ObeysTaxIdFormatRule(
    override val fieldGetter: (Invoice) -> String
) : ValidationRule<Invoice, String> {

    override fun check(validatedObject: Invoice): ValidationError? =
        if (fieldGetter(validatedObject).startsWith("9").not()) {
            ValidationError("taxIdNumber", "Must start with 9")
        } else {
            null
        }
}
