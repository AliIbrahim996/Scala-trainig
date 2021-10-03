val names = List("chris","ed","murice")
var capNames = names.map(_.capitalize)
//=====================================
val list = (10 to 12).map(i => i*2)
//=====================================
val namesWithUnderscore = List("_olivia", "_walter", "_peter")
capNames = for name <- namesWithUnderscore yield
  val nameWithoutUnderscore = name.drop(1)
  val capName = nameWithoutUnderscore.capitalize
  capName

//=============Using match as the body of a method (common)
def isTruthy(a:Matchable) = a match
  case 0 | "" | false => false
  case _              => true

isTruthy(0)
isTruthy(false)
isTruthy("")
isTruthy(55)
isTruthy("11")
//=======Domain modeling
/*Traits(simple interface)*/
trait Speaker:
  def speak(): String

trait TailWagger:
  def startTail(): Unit = println("tail is wagging")
  def stopTail(): Unit = println("tail is stopped")

trait Runner():
  def startRunning(): Unit = println("I'm running")
  def stopRunning(): Unit = println("Stop running")

class Dog(name:String) extends Speaker, TailWagger, Runner:
  def speak(): String = "Woof!"

class Cat(name: String) extends Speaker, TailWagger, Runner:
  def speak(): String = "Meow"
  override def startRunning(): Unit = println("Yeah..I don't run")
  override def stopRunning(): Unit = println("No need to stop")

val d =Dog("Rover")
d.speak()

val c = Cat("Morris")
c.speak()
c.startRunning()
c.stopRunning()()

/*Classes*/
class Person(var firstName:String, var lastName: String):
  def printFullName() = println(s"$firstName $lastName")

val p = Person("Ali","Ibrahim")
p.printFullName()

p.firstName = "hh"
p.printFullName()
//===========================

class Demo(private var f_name:String):
  println("Initialize begins")
  val full_name = f_name
  def printF_Name: Unit =
    println(full_name)

  printF_Name
  println("initialization ends")


val ali = Demo("Ali")

//
class Socket(val timeout: Int =  5000, val linger: Int = 1000):
  override def toString = s"time out: $timeout, Linger: $linger"


val s = Socket()
val s1 = Socket(2500)
val s2 = Socket(2500,300)
val s3 = Socket(linger = 1600)


//Objects has only one instance
object StringUtils:
  def truncate(s: String, length: Int): String = s.take(length)
  def containsWhitespace(s: String): Boolean = s.matches(".*\\s.*")
  def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty


val  str = StringUtils.truncate("aaa",2)

//Companion objects
import scala.math.*

case class Circle(radius: Double):
  def area: Double = Circle.calculateArea(radius)

object Circle:
  private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)

val circle1 = Circle(5.0)
circle1.area

//Lambda functions

//all these functions are equivalent
val aa = List(1,2,3).map(i=>i*2)
val bb = List(1,2,3).map(_*2)

def double(i:Int):Int = i*2

val cc = List(1,2,3).map(i=>double(i))
val dd = List(1,2,3).map(double)
val ee = List(1,2,3).map(double(_))

//HOF
def sayHello(f: () => Unit) : Unit = f()

def helloAli () :Unit = println("Hello, Ali")

sayHello(helloAli)

def bonjourJulien(): Unit = println("Bonjour, Julien")

sayHello(bonjourJulien)

//HOF with params

def executeNTimes(f: () => Unit, n: Int): Unit =
  for i <- 1 to n do f()

executeNTimes(helloAli,3)

def executeAndPrint(f: (Int, Int) => Int, i: Int, j: Int): Unit =
  println(f(i, j))

def sum(x: Int, y: Int) = x + y
def multiply(x: Int, y: Int) = x * y

executeAndPrint(sum, 3, 11)       // prints 14
executeAndPrint(multiply, 3, 9)   // prints 27

//Write your own map method

//first step
"def map(f:(Int) => A)"

//second step
"def map[A](f:(Int) => A)"

//third step

"def map[A](f:(Int) => A,xs:List[A])"

//fourth step

"def map[A](f:(Int)=> A, xs:List[A]) : List[A]"

//fifth step
"def map[A](f: (Int) => A, xs: List[Int]): List[A] ="
  "for x <- xs yield f(x)"

//Make it generic
def map[A,B](f:(B)=>A,xs:List[B]): List[A] =
  for x <- xs yield f(x)

map(double,List(1,2,3)) // List(2, 4, 6)

def strlen(s: String) = s.length
map(strlen, List("a", "bb", "ccc"))   // List(1, 2, 3)

//a method that  return a function takes a parameter as String and println that string param
def greet():String  => Unit =
  (name:String) => println(s"Hello, $name")

val greet_function = greet()

greet_function("Joe")

//improving the method

def greet2(greet_str:String):String => Unit =
  (inner_str:String) => println(s"$greet_str, $inner_str")

val greeting = greet2("hello")

greeting("Ali")

//a method that returns functions that greet people in different languages
def createGreetingFunction(desiredLanguage: String): String => Unit =
  val englishGreeting = (name: String) => println(s"Hello, $name")
  val frenchGreeting = (name: String) => println(s"Bonjour, $name")
  val spanishGreeting = (name: String) => println(s"Hola, $name")
  val germanyGreeting = (name: String) => println(s"Hallo, $name")
  val turkyGreeting = (name: String) => println(s"Merhaba, $name")
  val arabicGreeting = (name: String) => println(s"مرحباً, $name")

  desiredLanguage match
    case "english" => englishGreeting
    case "french" => frenchGreeting
    case "spanish" => spanishGreeting
    case "germany" => germanyGreeting
    case "turky" => turkyGreeting
    case "arabic" => arabicGreeting

val greet_in_franch = createGreetingFunction("french")
greet_in_franch("Jhon")

val greet_int_arabic= createGreetingFunction("arabic")

greet_int_arabic("Ali")

val greet_int_turky = createGreetingFunction("turky")
greet_int_turky("Ali")
