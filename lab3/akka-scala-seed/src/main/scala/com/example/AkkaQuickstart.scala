//#full-example
package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.GreeterMain.SayHello

//Объект-одиночка, представляющий актор, который отправляет приветственные сообщения.
object Greeter {
  //Запрос, содержащий имя (whom) и ссылку на актора (replyTo), которому нужно ответить.
  final case class Greet(whom: String, replyTo: ActorRef[Greeted])
  //Ответ с именем и ссылкой на отправителя (from).
  final case class Greeted(whom: String, from: ActorRef[Greet])

  //Возвращает Behavior[Greet], описывающее, как актор обрабатывает сообщения типа Greet.
  def apply(): Behavior[Greet] = Behaviors.receive { (context, message) =>
    //Логирует приветствие.
    context.log.info("Hello {}!", message.whom)
    //Отправляет ответ (Greeted) актору, указанному в replyTo.
    message.replyTo ! Greeted(message.whom, context.self)
    //поведение актора не меняется
    Behaviors.same
  }
}

// Актор, который отвечает на приветственные сообщения.
object GreeterBot {

  //Инициализирует поведение актора, начиная с 0 приветствий (greetingCounter).
  def apply(max: Int): Behavior[Greeter.Greeted] = {
    bot(0, max)
  }
  //Обрабатывает сообщения Greeter.Greeted
  private def bot(greetingCounter: Int, max: Int): Behavior[Greeter.Greeted] =
    Behaviors.receive { (context, message) =>
      //Логирует номер приветствия.
      val n = greetingCounter + 1
      context.log.info("Greeting {} for {}", n, message.whom)
      if (n == max) {   ///Если количество приветствий достигает max, актор завершает свою работу (Behaviors.stopped).
        Behaviors.stopped
      } else {  //Иначе отправляет новое сообщение Greet обратно в Greeter и увеличивает счётчик.
        message.from ! Greeter.Greet(message.whom, context.self)
        bot(n, max)
      }
    }
}

// Актор, который координирует работу системы.
object GreeterMain {
  //Содержит имя для отправки в Greeter
  final case class SayHello(name: String)

  def apply(): Behavior[SayHello] =
    Behaviors.setup { context =>  //Использует Behaviors.setup для создания актора Greeter.

      val greeter = context.spawn(Greeter(), "greeter")


      Behaviors.receiveMessage { message =>

        //Отправляет сообщение Greet в Greeter, указывая созданного бота в качестве получателя ответа.
        val replyTo = context.spawn(GreeterBot(max = 3), message.name)

        greeter ! Greeter.Greet(message.name, replyTo)
        Behaviors.same
      }
    }
}



object AkkaQuickstart extends App {
  //Создаёт систему акторов, начиная с GreeterMain.
  val greeterMain: ActorSystem[GreeterMain.SayHello] =
    ActorSystem(GreeterMain(), "AkkaQuickStart")
  //Отправляет сообщение SayHello("Charles") в GreeterMain, инициируя цепочку акторов
  greeterMain ! SayHello("Charles")

}
