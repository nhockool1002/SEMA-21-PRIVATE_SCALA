import random

class Player:
    def __init__(self, name):
        self.name = name
        self.health = 100
        self.location = "Village"
        self.inventory = []

def explore(player):
    print("You are exploring...")
    event = random.randint(0, 3)
    if event == 0:
        print("You found a treasure!")
        player.health += 20
        print(f"Your health is now {player.health}")
    elif event == 1:
        print("You encountered a monster!")
        monster_health = random.randint(10, 30)
        fight(player, monster_health)
    elif event == 2:
        print("You found nothing...")
    elif event == 3:
        print("You discovered a new location!")
        player.location = random_location()
        print(f"You are now in {player.location}")

def fight(player, monster_health):
    current_monster_health = monster_health
    while player.health > 0 and current_monster_health > 0:
        print(f"Your health: {player.health}")
        print(f"Monster's health: {current_monster_health}")
        choice = input("Choose your action - 1. Attack, 2. Run: ")
        if choice == "1":
            player_damage = random.randint(10, 20)
            monster_damage = random.randint(5, 15)
            current_monster_health -= player_damage
            player.health -= monster_damage
            print(f"You dealt {player_damage} damage to the monster!")
            print(f"The monster dealt {monster_damage} damage to you!")
        elif choice == "2":
            print("You ran away!")
            return
        else:
            print("Invalid choice! Try again.")
    if player.health > 0:
        print("You defeated the monster!")
        loot = generate_loot()
        player.inventory.append(loot)
        print(f"You obtained {loot}")
    else:
        print("Game over! You were defeated by the monster.")

def rest(player):
    print("You are resting...")
    healing = random.randint(10, 30)
    player.health = min(100, player.health + healing)
    print(f"You restored {healing} health. Your health is now {player.health}")

def show_inventory(player):
    print("Inventory:")
    if not player.inventory:
        print("Empty")
    else:
        for item in player.inventory:
            print(item)

def random_location():
    locations = ["Forest", "Cave", "Mountain", "Castle"]
    return random.choice(locations)

def generate_loot():
    loot_items = ["Gold", "Potion", "Sword", "Shield"]
    return random.choice(loot_items)

def main():
    print("Welcome to the Text Adventure Game!")
    player_name = input("What's your name? ")
    player = Player(player_name)

    print(f"Welcome, {player.name}! Let's start the adventure in {player.location}.")

    while player.health > 0:
        print("\nChoose your action:")
        print("1. Explore")
        print("2. Rest")
        print("3. Inventory")
        print("4. Quit")
        choice = input()

        if choice == "1":
            explore(player)
        elif choice == "2":
            rest(player)
        elif choice == "3":
            show_inventory(player)
        elif choice == "4":
            print("Thanks for playing!")
            return
        else:
            print("Invalid choice! Try again.")

    print("Game over! You are out of health.")

if __name__ == "__main__":
    main()
