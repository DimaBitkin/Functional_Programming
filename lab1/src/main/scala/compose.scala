import scala.compiletime.ops.double
def compose[A, B, C](fl:A=>B, f:B=>C):A=>C = x=>f(fl(x))

def f1(a: Int):Double =  a*1.0

def f2(a: Double): String = s"a = $a"