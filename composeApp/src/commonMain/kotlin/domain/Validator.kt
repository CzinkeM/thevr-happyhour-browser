package domain

import domain.model.OutOfRangeException

object Validator {

    fun isPartStringValid(partString: String, validRange: IntRange): ValidationResult<Int> {
        return try {
            val part = partString.toInt()
            if (part in validRange) {
                ValidationResult(t = part)
            } else {
                ValidationResult(exception = OutOfRangeException())
            }
        } catch (e: NumberFormatException) {
            ValidationResult(exception = e)
        }
    }
}