# Pokemon Mulligan Probability Simulation
In collectible card games, a mulligan refers to the process of adjusting which cards are in a player's initial hand of cards. Card games have various official rules for how mulligans are performed.

In the Pokémon Trading Card Game, each player needs at least one Basic Pokémon card in their opening hand to start the game.[^1]

## Summary
This project computes the probability of a player's initial hand resulting in a mulligan, for each number of Pokemon in the deck (from 0/60 to 60/60). It runs this simulation N times (specified in the Main class) allowing for a more accurate probability. The results for each deck configuration are printed to the console.

This project computes the probability of a player's initial hand requiring a mulligan in the Pokémon Trading Card Game. It simulates drawing hands from a deck and determines if each hand contains at least one Basic Pokémon card. The simulation runs multiple trials to estimate the probability of needing to mulligan based on deck composition.

This project demonstrates set operations by implementing union, intersection, and complement functions. These functions are applied to integer, floating point, and string sets, with the results printed to the console.

## TODO
- [x] Improve comments to enhance clarity.

[^1]: [Mulligan (games) - Wikipedia](https://en.wikipedia.org/wiki/Mulligan_(games)#Card_games)