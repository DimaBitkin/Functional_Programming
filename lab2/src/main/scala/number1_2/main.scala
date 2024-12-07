import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure, Try}
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@main def hello(): Unit = {
  val base = 2.0
  val exp = 2
  val selekt = 4

  selekt match {
    case _: 1 => {
      val result = integral(base => pow(base, exp), 0, 2, 1000000)
      println(result) // Интеграл x^3 от 0 до 2

      val result2 = integral(base => rectilinear_function(base), 0, 2, 10000)
      println(result2)
    }

    case _: 2 => {
      val passwords = List(
        "Short1!", // Недопустимый: короткий
        "lowercase1!", // Недопустимый: нет заглавных букв
        "UPPERCASE1!", // Недопустимый: нет строчных букв
        "NoDigits!", // Недопустимый: нет цифры
        "ValidPassword1!", // Допустимый
        "NoSpecialChar1" // Недопустимый: нет спецсимвола
      )

      passwords.foreach { password =>
        println(
          s"Passvord\t '$password\t' is valid: ${goodEnoughPassword(password)}"
        )
      }
    }

    case _: 3 => {
      val passwords = List(
        "Short1!", // Недопустимый: короткий
        "lowercase1!", // Недопустимый: нет заглавных букв
        "UPPERCASE1!", // Недопустимый: нет строчных букв
        "NoDigits!", // Недопустимый: нет цифры
        "ValidPassword1!", // Допустимый
        "NoSpecialChar1", // Недопустимый: нет спецсимвола
        null // Ошибка: null
      )

      passwords.foreach { password =>
        testPassword(password) match {
          case Left(errors) =>
            println(
              s"Password '$password' has errors: ${errors.mkString(", ")}"
            )
          case Right(valid) =>
            println(s"Password '$password' is valid!")
        }
      }
    }

    case _: 4 =>
      // Future
      println("Запрос с использованием Future")
      val firstPassword = readingPassword()
      val finalPassword = Await.result(firstPassword, Duration.Inf)
      println(s"Пароль верен: $finalPassword \n")
      println()

  }

}
