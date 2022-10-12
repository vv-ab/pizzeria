import model.{Pizza, Size, Topping}
import console.{UserChoice, offerUserChoice}

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
  println(s"Your pizza: ${pizza.size} ${pizza.toppings.map({ topping => topping.name }).mkString(", ")}")
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
  val userChoice = UserChoice(
    "What toppings would you like?",
    List(
      ("1: Corn", addCorn),
      ("2: Pineapple", addPineapple),
      ("3: Onion", addOnion),
      ("4: Ham", addHam),
      ("5: Mushrooms", addMushrooms),
      ("6: Finish", finish)
    )
  )
  val choiceAction = offerUserChoice(userChoice)

  choiceAction(pizza)
}

def addCorn(pizza: Pizza): Pizza = {
  println("Adding Corn")
  val pizzaWithCorn = pizza.copy(toppings = pizza.toppings :+ Topping("Corn", 1))
  chooseToppings(pizzaWithCorn)
}

def addPineapple(pizza: Pizza): Pizza = {
  println("Adding Pineapple")
  val pizzaWithPineapple = pizza.copy(toppings = pizza.toppings :+ Topping("Pineapple", 2))
  chooseToppings(pizzaWithPineapple)
}

def addOnion(pizza: Pizza): Pizza = {
  println("Adding Onion")
  val pizzaWithOnion = pizza.copy(toppings = pizza.toppings :+ Topping("Onion", 1))
  chooseToppings(pizzaWithOnion)
}

def addHam(pizza: Pizza): Pizza = {
  println("Adding Ham")
  val pizzaWithHam = pizza.copy(toppings = pizza.toppings :+ Topping("Ham", 3))
  chooseToppings(pizzaWithHam)
}

def addMushrooms(pizza: Pizza): Pizza = {
  println("Adding Mushrooms")
  val pizzaWithMushrooms = pizza.copy(toppings = pizza.toppings :+ Topping("Mushrooms", 2))
  chooseToppings(pizzaWithMushrooms)
}

def finish(pizza: Pizza): Pizza = {
  pizza
}