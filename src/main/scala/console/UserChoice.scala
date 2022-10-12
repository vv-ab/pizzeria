package console

import model.Pizza

class UserChoice(
  val text: String,
  val choices: List[(String, (Pizza) => Pizza)]
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
