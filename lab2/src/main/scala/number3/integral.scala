import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

def integral(f: Double => Double, l: Double, r: Double, steps: Int): Double = {
  val step = (r - l) / steps
  (0 until steps)
    .map(i => l + step * i + step / 2)
    .map(f)
    .map(_ * step)
    .reduce(_ + _)
}
