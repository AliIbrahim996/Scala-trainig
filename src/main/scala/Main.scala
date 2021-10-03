@main def hello: Unit =
  val fruits = List("apple", "banana", "lime", "orange")

  val fruitLength = for {
  f <- fruits if f.length > 4
  } yield f.length