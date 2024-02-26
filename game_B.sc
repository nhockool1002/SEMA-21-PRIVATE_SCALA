import scala.io.StdIn.readLine
import scala.util.Random

object TextAdventureGame {

  case class Player(name: String, var health: Int, var location: String, var inventory: List[String] = Nil)

  val random = new Random()

  def main(args: Array[String]): Unit = {
    println("Welcome to the Text Adventure Game!")
    println("What's your name?")
    val playerName = readLine()
    var player = Player(playerName, 100, "Village")

    println(s"Welcome, $playerName! Let's start the adventure in ${player.location}.")

    while (player.health > 0) {
      println("\nChoose your action:")
      println("1. Explore")
      println("2. Rest")
      println("3. Inventory")
      println("4. Quit")
      val choice = readLine()

      choice match {
        case "1" => explore(player)
        case "2" => rest(player)
        case "3" => showInventory(player)
        case "4" =>
          println("Thanks for playing!")
          return
        case _ => println("Invalid choice! Try again.")
      }
    }
    println("Game over! You are out of health.")
  }

  def explore(player: Player): Unit = {
    println("You are exploring...")
    val event = random.nextInt(4)
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
      case 3 =>
        println("You discovered a new location!")
        player.location = randomLocation()
        println(s"You are now in ${player.location}")
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
      val loot = generateLoot()
      player.inventory :+= loot
      println(s"You obtained $loot")
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

  def showInventory(player: Player): Unit = {
    println("Inventory:")
    if (player.inventory.isEmpty) {
      println("Empty")
    } else {
      player.inventory.foreach(item => println(item))
    }
  }

  def randomLocation(): String = {
    val locations = List("Forest", "Cave", "Mountain", "Castle")
    locations(random.nextInt(locations.length))
  }

  def generateLoot(): String = {
    val lootItems = List("Gold", "Potion", "Sword", "Shield")
    lootItems(random.nextInt(lootItems.length))
  }
}
