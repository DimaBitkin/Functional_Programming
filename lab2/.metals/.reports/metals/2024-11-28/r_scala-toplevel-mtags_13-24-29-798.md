error id: file:///C:/Users/dima-/OneDrive/Desktop/ФП/Functional_Programming/lab2/src/main/scala/goodEnoughPassword.scala:[4..5) in Input.VirtualFile("file:///C:/Users/dima-/OneDrive/Desktop/ФП/Functional_Programming/lab2/src/main/scala/goodEnoughPassword.scala", "def (password: String): Boolean = {
  val specialSymbol = "!@#$%^&*()_+-=[]{}|;:',.<>/?`~"

  val criteria: Seq[Boolean] = Seq(
    password.length >= 8,
    password.exists(_.isLower),
    password.exists(_.isUpper),
    password.exists(_.isDigit),
    password.exists(specialSymbol.contains)
  )
  criteria.reduce(_ && _) // Вернёт true, если все хорошо, иначе false
}")
file:///C:/Users/dima-/OneDrive/Desktop/ФП/Functional_Programming/lab2/src/main/scala/goodEnoughPassword.scala:1: error: expected identifier; obtained lparen
def (password: String): Boolean = {
    ^
#### Short summary: 

expected identifier; obtained lparen