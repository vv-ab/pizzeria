class UserChoice(
  val text: String,
  val choices: List[(String, (Pizza) => Pizza)]
)

class Pizza(
  val size: String,
  val topping: String
)

def offerUserChoice(userChoice: UserChoice): (Pizza) => Pizza = {
  printUserChoice(userChoice)

  val input = Console.in.readLine().toInt
  val tuple = userChoice.choices(input - 1)
  val (_, choiceAction) = tuple
  choiceAction
}


def printUserChoice(userChoice: UserChoice): Unit = {
  println(userChoice.text)
  for ((choice, _) <- userChoice.choices) {
    println(choice)
  }
}

@main def main(): Unit = {
  println("Welcome to our pizzeria!")

  val userChoice = UserChoice(
    "What would you like to do?",
    List(
      ("1: Order pizza", chooseSize),
      ("2: Exit", exit)
    ))

  val choiceAction = offerUserChoice(userChoice)

  val pizza = choiceAction(Pizza("", ""))
  println(s"Your pizza: ${pizza.size}, ${pizza.topping}")
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
  val smallPizza = Pizza("Small", "")
  chooseToppings(smallPizza)
}

def makeMediumPizza(pizza: Pizza): Pizza = {
  println("Making medium pizza")
  val mediumPizza = Pizza("Medium", "")
  chooseToppings(mediumPizza)
}

def makeLargePizza(pizza: Pizza): Pizza = {
  println("Making large pizza")
  val largePizza = Pizza("Large", "")
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
      ("5: Mushrooms", addMushrooms)
    )
  )
  val choiceAction = offerUserChoice(userChoice)

  choiceAction(pizza)
}

def addCorn(pizza: Pizza): Pizza = {
  println("Adding Corn")
  val pizzaWithCorn = Pizza(pizza.size, "Corn")
  pizzaWithCorn
}

def addPineapple(pizza: Pizza): Pizza = {
  println("Adding Pineapple")
  val pizzaWithPineapple = Pizza(pizza.size, "Pineapple")
  pizzaWithPineapple
}

def addOnion(pizza: Pizza): Pizza = {
  println("Adding Onion")
  val pizzaWithOnion = Pizza(pizza.size, "Onion")
  pizzaWithOnion
}

def addHam(pizza: Pizza): Pizza = {
  println("Adding Ham")
  val pizzaWithHam = Pizza(pizza.size, "Ham")
  pizzaWithHam
}

def addMushrooms(pizza: Pizza): Pizza = {
  println("Adding Mushrooms")
  val pizzaWithMushrooms = Pizza(pizza.size, "Mushrooms")
  pizzaWithMushrooms
}