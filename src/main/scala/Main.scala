import model.{Pizza, Size, Topping}
import console.{UserChoice, offerUserChoice}
import scala.io.Source

@main def main(): Unit = {
  println("Welcome to our pizzeria!")

  val userChoice = UserChoice(
    "What would you like to do?",
    List(
      ("1: Order pizza", chooseSize),
      ("2: Exit", exit)
    ))

  val choiceAction = offerUserChoice(userChoice)

  val pizza = choiceAction(Pizza(Size.Undefined, List()))
  println(s"Your pizza: ${pizza.size}, ${pizza.toppings.map({ topping => topping.name }).mkString(", ")}")
  val toppingPrice = pizza.toppings.map({ topping => topping.price }).sum
  val price = pizza.size.price + toppingPrice
  println(s"Your pizza is: $price €")
}

def exit(pizza: Pizza): Pizza = {
  println("bye")
  pizza
}

def chooseSize(pizza: Pizza): Pizza = {
  val userChoice = UserChoice(
    "Which size?",
    List(
      ("1: Small", makeSmallPizza),
      ("2: Medium", makeMediumPizza),
      ("3: Large", makeLargePizza)
    )
  )
  val choiceAction = offerUserChoice(userChoice)

  choiceAction(pizza)
}

def makeSmallPizza(pizza: Pizza): Pizza = {
  println("Making small pizza")
  val smallPizza = pizza.copy(size = Size.Small)
  chooseToppings(smallPizza)
}

def makeMediumPizza(pizza: Pizza): Pizza = {
  println("Making medium pizza")
  val mediumPizza = pizza.copy(size = Size.Medium)
  chooseToppings(mediumPizza)
}

def makeLargePizza(pizza: Pizza): Pizza = {
  println("Making large pizza")
  val largePizza = pizza.copy(size = Size.Large)
  chooseToppings(largePizza)
}

def chooseToppings(pizza: Pizza): Pizza = {
  val toppings: List[Topping] = readToppings("toppings.csv")
  val toppingsWithIndices: List[(Topping, Int)] = toppings.zipWithIndex
  val choices: List[(String, Pizza => Pizza)] = toppingsWithIndices
    .map({ (topping, index) => (s"${index + 1}: ${topping.name}", addTopping(topping)) })
  val userChoice = UserChoice(
    "What toppings would you like?",
    choices :+ (s"${choices.size + 1}: finish", finishToppings)
  )
  val choiceAction = offerUserChoice(userChoice)

  choiceAction(pizza)
}

def addTopping(topping: Topping)(pizza: Pizza): Pizza = {
  println(s"Adding ${topping.name}")
  val newPizza = pizza.copy(toppings = pizza.toppings :+ topping)
  chooseToppings(newPizza)
}

def finishToppings(pizza: Pizza): Pizza = {
  pizza
}

def readToppings(sourceFile: String): List[Topping] = {
  val source = Source.fromFile(sourceFile)
  val lines = source.getLines()
  val toppings = lines.map({ line =>
    val split = line.split(",")
    val name = split(0)
    val price = split(1)
    Topping(name, price.toInt)
  }).toList
  source.close()
  toppings
}