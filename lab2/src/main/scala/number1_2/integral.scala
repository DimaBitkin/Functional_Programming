import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

def integral(
    f: Double => Double,
    l: Double,
    r: Double,
    steps: Int,
    threads: Int
): Double = {

  val step = (r - l) / steps

  val threadInterval = scala.math.ceil((steps + 0.0) / threads).toInt

  val futures =
    (0 until steps)
      .grouped(threadInterval)
      .map(interval =>
        Future {

          interval
            .map(i => l + step * i + step / 2)
            .map(f)
            .map(_ * step)
            .reduce(_ + _)

        }(global)
      )

  val resultFuture = Future.reduce(futures)(_ + _)(global)

  val result = Await.result(resultFuture, Duration.Inf)

  result

}
