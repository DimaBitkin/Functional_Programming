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
