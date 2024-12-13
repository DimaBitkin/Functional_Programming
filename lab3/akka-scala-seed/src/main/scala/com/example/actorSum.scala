import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors

object AddingServer {
  // Определим тип суммируемого значения
  type Addable = Int | Double | String

  // Опишем сообщение, которое сервер принимает
  case class AddMessage(a: Addable, b: Addable, replyTo: ActorRef[Addable])

  // Поведение сервера
  def apply(): Behavior[AddMessage] = Behaviors.receive { (context, message) =>
    val result = (message.a, message.b) match {
      case (a: String, b: Addable) => a + b
      case (a: Addable, b: String) => b.toString.prependedAll(a.toString())
      case (a: Int, b: Int)        => a + b
      case (a: Double, b: Double)  => a + b
      case (_: Int, _: Double) => ???
      case (_: Double, _: Int) => ???
    }
    message.replyTo ! result // Отправляем результат обратно отправителю
    Behaviors.same
  }
}

object Client {
  import AddingServer.Addable
  import AddingServer.AddMessage

  // Поведение клиента
  def apply(server: ActorRef[AddMessage]): Behavior[Addable] = Behaviors.setup {
    context =>
      // Генерация и отправка сообщения серверу
      server ! AddMessage(5, 10, context.self)
      server ! AddMessage(3.14, 2.71, context.self)
      server ! AddMessage("Hello, ", "World!", context.self)

      // Обработка ответа от сервера
      Behaviors.receiveMessage { result =>
        context.log.info(s"Received result: $result")
        Behaviors.same
      }
  }
}

object AddingSystem {
  import AddingServer.AddMessage

  // Основное поведение системы
  def apply(): Behavior[AddMessage] = Behaviors.setup { context =>
    // Создаём актор-сервер
    val server = context.spawn(AddingServer(), "adding-server")

    // Создаём клиентов
    context.spawn(Client(server), "client-1")
    context.spawn(Client(server), "client-2")

    // Система сама ничего не делает, поэтому её поведение - пустое
    Behaviors.empty
  }
}

@main def AddingMain(): Unit = {
  // Запуск ActorSystem
  ActorSystem(AddingSystem(), "adding-system")
}
