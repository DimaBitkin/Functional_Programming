def testPassword(password: String): Either[List[String], Boolean] = {
  if (password == null) return Left(List("Password is null"))

  val specialChars = "!@#$%^&*()_+-=[]{}|;:',.<>/?`~"

  // Проверки для пароля
  def checkLength: Either[String, Boolean] =
    if (password.length >= 8) Right(true)
    else Left("Password must be at least 8 characters long")

  def checkUpperCase: Either[String, Boolean] =
    if (password.exists(_.isUpper)) Right(true)
    else Left("Password must contain at least one uppercase letter")

  def checkLowerCase: Either[String, Boolean] =
    if (password.exists(_.isLower)) Right(true)
    else Left("Password must contain at least one lowercase letter")

  def checkDigit: Either[String, Boolean] =
    if (password.exists(_.isDigit)) Right(true)
    else Left("Password must contain at least one digit")

  def checkSpecialChar: Either[String, Boolean] =
    if (password.exists(specialChars.contains)) Right(true)
    else Left("Password must contain at least one special character")

  // Список ошибок, если они есть
  val errors = List(
    checkLength,
    checkUpperCase,
    checkLowerCase,
    checkDigit,
    checkSpecialChar
  ).collect { case Left(error) => error } // Собираем только ошибки

  // Если есть ошибки, возвращаем их в Left
  if (errors.nonEmpty) Left(errors)
  else Right(true) // Пароль валидный, если ошибок нет
}
