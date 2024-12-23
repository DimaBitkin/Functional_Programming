// Определение типа Functor
trait Functor[F[_]] {
  // map принимает контейнер F[A] и функцию f: A => B,
  // а затем возвращает новый контейнер F[B],
  // где значения A в контейнере F были преобразованы в значения B
  def map[A, B](fu: F[A])(f: A => B): F[B]
}
