package domain

class ValidationResult<T>(
    val t: T?,
    val exception: Exception?
) {

    constructor(t: T) : this(t = t, exception = null)

    constructor(exception: Exception) : this(t = null, exception = exception)

    val isValid = t != null && exception == null

}