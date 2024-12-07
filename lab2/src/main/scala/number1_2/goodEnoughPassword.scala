def goodEnoughPassword(password: String): Boolean = {
  val specialSymbol = "!@#$%^&*()_+-=[]{}|;:',.<>/?`~"

  val criteria: Seq[Boolean] = Seq(
    password.length >= 8,
    password.exists(_.isLower),
    password.exists(_.isUpper),
    password.exists(_.isDigit),
    password.exists(specialSymbol.contains)
  )
  criteria.reduce(_ && _) // Вернёт true, если все хорошо, иначе false
}
