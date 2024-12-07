trait Monad[M[_]] extends Functor[M] {
  // flatMap принимает контейнер M[A] и функцию f: A => M[B],
  // а затем возвращает новый контейнер M[B], который может быть вложенным.
  def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]

  // unit позволяет обернуть значение типа A в контейнер M[A]
  def unit[A](a: A): M[A]
}
