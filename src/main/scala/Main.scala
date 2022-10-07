class UserChoice(
  val text: String,
  val choices: List[(String, () => Unit)]
)

def offerUserChoice(userChoice: UserChoice): () => Unit = {
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
  val choiceAction = offerUserChoice(userChoice)

  choiceAction()
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
  val choiceAction = offerUserChoice(userChoice)

  choiceAction()
}
