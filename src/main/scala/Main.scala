class UserChoice(
  val text: String,
  val choices: List[(String, (Pizza) => Unit)]
)

class Pizza(
  val size: String,
  val topping: String
)

def offerUserChoice(userChoice: UserChoice): (Pizza) => Unit = {
  printUserChoice(userChoice)

  val input = Console.in.readLine().toInt
  val (_, choiceAction) = userChoice.choices(input - 1)
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

  choiceAction(Pizza("", ""))
}

def exit(pizza: Pizza): Unit = {
  println("bye")
}

def chooseSize(pizza: Pizza): Unit = {
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

def makeSmallPizza(pizza: Pizza): Unit = {
  println("Making small pizza")
  val smallPizza = Pizza("Small", "")
  chooseToppings(smallPizza)
}

def makeMediumPizza(pizza: Pizza): Unit = {
  println("Making medium pizza")
  val mediumPizza = Pizza("Medium", "")
  chooseToppings(mediumPizza)
}

def makeLargePizza(pizza: Pizza): Unit = {
  println("Making large pizza")
  val largePizza = Pizza("Large", "")
  chooseToppings(largePizza)
}

def chooseToppings(pizza: Pizza): Unit = {
  val userChoice = UserChoice(
    "What toppings would you like?",
    List(
      ("1: Corn", (pizza) => ()),
      ("2: Pineapple", (pizza) => ()),
      ("3: Onion", (pizza) => ()),
      ("4: Ham", (pizza) => ()),
      ("5: Mushrooms", (pizza) => ())
    )
  )
  val choiceAction = offerUserChoice(userChoice)

  choiceAction(pizza)
}
