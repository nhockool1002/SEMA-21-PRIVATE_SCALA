import scala.io.StdIn.readLine
import scala.util.Random

object TextAdventureGame {

  case class Player(name: String, var health: Int)

  val random = new Random()

  def main(args: Array[String]): Unit = {
    println("Welcome to the Text Adventure Game!")
    println("What's your name?")
    val playerName = readLine()
    val player = Player(playerName, 100)

    println(s"Welcome, $playerName! Let's start the adventure.")

    while (player.health > 0) {
      println("\nChoose your action:")
      println("1. Explore")
      println("2. Rest")
      println("3. Quit")
      val choice = readLine()

      choice match {
        case "1" => explore(player)
        case "2" => rest(player)
        case "3" =>
          println("Thanks for playing!")
          return
        case _ => println("Invalid choice! Try again.")
      }
    }
    println("Game over! You are out of health.")
  }

  def explore(player: Player): Unit = {
    println("You are exploring...")
    val event = random.nextInt(3)
    event match {
      case 0 =>
        println("You found a treasure!")
        player.health += 20
        println(s"Your health is now ${player.health}")
      case 1 =>
        println("You encountered a monster!")
        val monsterHealth = random.nextInt(30) + 10
        fight(player, monsterHealth)
      case 2 =>
        println("You found nothing...")
    }
  }

  def fight(player: Player, monsterHealth: Int): Unit = {
    var currentMonsterHealth = monsterHealth
    while (player.health > 0 && currentMonsterHealth > 0) {
      println(s"Your health: ${player.health}")
      println(s"Monster's health: $currentMonsterHealth")
      println("Choose your action:")
      println("1. Attack")
      println("2. Run")
      val choice = readLine()
      choice match {
        case "1" =>
          val playerDamage = random.nextInt(20) + 10
          val monsterDamage = random.nextInt(15) + 5
          currentMonsterHealth -= playerDamage
          player.health -= monsterDamage
          println(s"You dealt $playerDamage damage to the monster!")
          println(s"The monster dealt $monsterDamage damage to you!")
        case "2" =>
          println("You ran away!")
          return
        case _ =>
          println("Invalid choice! Try again.")
      }
    }
    if (player.health > 0) {
      println("You defeated the monster!")
    } else {
      println("Game over! You were defeated by the monster.")
    }
  }

  def rest(player: Player): Unit = {
    println("You are resting...")
    val healing = random.nextInt(30) + 10
    player.health = Math.min(100, player.health + healing)
    println(s"You restored $healing health. Your health is now ${player.health}")
  }
}
