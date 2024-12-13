import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import scala.concurrent.duration.Duration

// Сообщения для акторов
object Messages {
  case class ComputePartial(
      start: Double,
      end: Double,
      steps: Int,
      f: Double => Double,
      replyTo: ActorRef[Summation]
  )
  case class PartialResult(value: Double)
  case class Summation(value: Double)
  case object GetResult
}

import Messages._

// Актор для вычисления частичного интеграла
object PartialIntegrator {
  def apply(): Behavior[ComputePartial] = Behaviors.receive {
    (context, message) =>
      val step = (message.end - message.start) / message.steps
      val result = (0 until message.steps)
        .map(i => message.start + step * i + step / 2)
        .map(message.f)
        .map(_ * step)
        .sum

      message.replyTo ! Summation(result)
      Behaviors.same
  }
}

// Актор для суммирования результатов
object ResultAggregator {
  def apply(
      expectedResults: Int,
      replyTo: ActorRef[Double]
  ): Behavior[Summation] =
    Behaviors.setup { context =>
      def accumulate(results: List[Double]): Behavior[Summation] =
        Behaviors.receiveMessage { case Summation(value) =>
          val updatedResults = value :: results
          if (updatedResults.size == expectedResults) {
            val total = updatedResults.sum
            replyTo ! total
            Behaviors.stopped
          } else {
            accumulate(updatedResults)
          }
        }
      accumulate(Nil)
    }
}

// Главный актор
object IntegralCalculator {
  def apply(
      f: Double => Double,
      l: Double,
      r: Double,
      steps: Int,
      workers: Int,
      replyTo: ActorRef[Double]
  ): Behavior[GetResult.type] =
    Behaviors.setup { context =>
      val step = (r - l) / workers
      val aggregator =
        context.spawn(ResultAggregator(workers, replyTo), "aggregator")

      (0 until workers).foreach { i =>
        val start = l + i * step
        val end = if (i == workers - 1) r else start + step
        val partialWorker = context.spawn(PartialIntegrator(), s"worker-$i")
        partialWorker ! ComputePartial(
          start,
          end,
          steps / workers,
          f,
          aggregator
        )
      }

      Behaviors.receiveMessage { case GetResult =>
        Behaviors.same
      }
    }
}
def pow(base: Double, exp: Int): Double = {
  var result = 1.0
  var b = base
  var e = if (exp < 0) -exp else exp

  while (e > 0) {
    if (e % 2 == 1) result *= b
    b *= b
    e /= 2
  }

  if (exp < 0) 1 / result else result
}
@main def Main(): Unit = {
  val system = ActorSystem(
    Behaviors.setup[Double] { context =>
      val calculator = context.spawn(
        IntegralCalculator(
          base => pow(2.0, 2),
          0,
          2,
          1000000,
          1,
          context.self
        ),
        "calculator"
      )
      calculator ! GetResult

      Behaviors.receiveMessage { result =>
        println(s"Integral result: $result")
        Behaviors.stopped
      }
    },
    "integral-calculator"
  )
}
