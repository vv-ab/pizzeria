package model

enum Size(val price: Int) {
  case Undefined extends Size(0)
  case Small extends Size(5)
  case Medium extends Size(7)
  case Large extends Size(10)
}