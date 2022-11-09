from game_message import Tick, Action, Spawn, Sail, Dock, Anchor, directions

class Bot:
    def __init__(self):
        print("Initializing your super mega duper bot")
        
    def get_next_move(self, tick: Tick) -> Action:
        """
        Here is where the magic happens, for now the move is random. I bet you can do better ;)
        """
        if tick.currentLocation is None:
            return Spawn(tick.map.ports[0])

        
        
        return Sail(directions[tick.currentTick % len(directions)])


# Dans le fond on a 4 actions possible

#     bouger
#     Rester sur place
#     Dock 
#     (Spawn) sur le premier tick

# Le but du jeu c'est de visiter tout les ports en retournant au premier en le moins de tick possible

# On recoit une matrice 2D avec la topologie des tuilles et on reçoit les niveaux de tide possible et l'ordre des tides

# map: {
#     topology: [ // Matrix of tiles, representing terrain height
#       [8,8,7,6,5,5,5],
#       [8,8,6,5,4,4,3]
#       // ...
#     ], 
#     tideLevels: {min: 3, max: 6} // Min and Max possible tide levels
#     // ...
#   },
#   tideSchedule: [5,4,3,3,4,5,6]

# Faque en plus on la position des ports

# currentLocation: { row: 0, column: 0 }, // Position you are in at this time
#   spawnLocation: { row: 10, column: 20 }, // Position you spawned at
#   map: {
#     // ...
#     ports: [
#       { row: 15, column: 7 },
#       { row: 8, column: 8 },
#     ], // List of Positions of ports on the map
#   },
#   visitedPortIndices: [0], // List of numbers referencing Map.ports indices
  