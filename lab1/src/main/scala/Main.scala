import scala.annotation.meta.setter
@main def main() = {
  
val selekt = 7

selekt match{

  case 1 =>{
    println(" hello world")
  }
  case 2 => {
    val n = 5
    printIterator(n)
  }

  case 3 =>{
    val collection = Seq(10, 20, 30, 40, 50)
    val (even, odd) = splitByIndex(collection)
    println(s"Even indexed elements: $even") // Вывод: Even indexed elements: List(10, 30, 50)
    println(s"Odd indexed elements: $odd")   // Вывод: Odd indexed elements: List(20, 40)
  
  }
  case 4 =>{
    val collection1 = Seq(110, 240, 310, 140, 50)
    println(findMax(collection1))
  }
  
  case 5 =>{
    val maxFunction = (a: Int, b: Int) => if (a > b) a else b
    val result = maxFunction(5, 2)
    println(result)
  }

  case 6 =>{
    val greetFunction = greet _
    println(greetFunction)
  }

  case 7 => {
    val f0 = compose(f1,f2)
      println(f0(5))
  }
  case 8 =>{
    def multiply(a: Int, b: Int): Int = a * b

    val multiplyFunction: (Int, Int) => Int = multiply
    println(multiplyFunction)
  } 
  
}
  
}
