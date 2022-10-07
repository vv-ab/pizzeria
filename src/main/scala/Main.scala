class UserChoice(
  val text: String,
  val choices: List[(String, () => Unit)]
)

def offerUserChoice(userChoice: UserChoice): Int = {
  printUserChoice(userChoice)

  val input = Console.in.readLine().toInt

  input
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

  val input: Int = offerUserChoice(userChoice)

  val choices = userChoice.choices
  val choice = choices(input - 1)
  val choiceAction = choice._2

  choiceAction()
}

def exit(): Unit = {
  println("bye")
}

def chooseSize(): Unit = {
  val userChoice = UserChoice(
    "Which size?",
    List(
      ("1: Small", chooseToppings),
      ("2: Medium", chooseToppings),
      ("3: Large", chooseToppings)
    )
  )
  val input = offerUserChoice(userChoice)

  if (1 == input) {
    println("Chosen: Small")
    chooseToppings()
  }
  else if (2 == input) {
    println("Chosen: Medium")
    chooseToppings()
  }
  else {
    println("Chosen: Large")
    chooseToppings()
  }
}

def chooseToppings(): Unit = {
  val userChoice = UserChoice(
    "What toppings would you like?",
    List(
      ("1: Corn", () => ()),
      ("2: Pineapple", () => ()),
      ("3: Onion", () => ()),
      ("4: Ham", () => ()),
      ("5: Mushrooms", () => ())
    )
  )
  val input = offerUserChoice(userChoice)

  if (1 == input) {
    println("Chosen topping: Corn")
  }
  else if (2 == input) {
    println("Chosen topping: Pineapple")
  }
  else if (3 == input) {
    println("Chosen topping: Onion")
  }
  else if (4 == input) {
    println("Chosen topping: Ham")
  }
  else {
    println("Chosen topping: Mushrooms")
  }
}
