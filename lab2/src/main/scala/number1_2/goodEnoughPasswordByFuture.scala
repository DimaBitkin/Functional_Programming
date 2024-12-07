import scala.concurrent.{Future, ExecutionContext}
import scala.io.StdIn
import scala.util.Try
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn.readLine

def readingPassword(): Future[String] = {
  Future {
    print("Введите пароль: ")
    readLine()
  }.map { password =>
    testPassword(password) match {
      case Right(_) => password
      case Left(error) =>
        println(s"Ошибка: $error")
        throw new Exception() // Исключение
    }
  }.recoverWith { case _ => readingPassword() } // Перехватывает все исключения
}
