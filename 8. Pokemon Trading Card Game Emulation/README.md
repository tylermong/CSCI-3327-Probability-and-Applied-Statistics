# Pokémon Trading Card Game Emulation
A Java-based emulation of the Pokémon Trading Card Game. This console application allows two players to battle using prebuilt decks, featuring core game mechanics like attacks, energy attachment, and card effects. The game follows the official rules and game procedure from the Pokémon TCG: Sun & Moon—Celestial Storm rulebook.[^1]

## Summary
This project implements a functional emulation of the Pokémon Trading Card Game in Java. It adheres to the official game rules from the Pokémon TCG: Sun & Moon—Celestial Storm rulebook, including deck building, game setup, turn management, and win conditions.

The emulation features:
- Complete game setup with shuffling, initial draws, and mulligan handling.
- Multiple prebuilt decks with different Pokémon types.
- Core gameplay mechanics including:
  - Drawing cards.
  - Playing Pokémon on bench.
  - Attaching Energy cards.
  - Using Trainer cards.
  - Attacking and retreating.
- Proper turn structure and player interaction.
- Win condition checks (prize cards, no active Pokémon, empty deck).

The code is organized into packages for game management, cards (Pokémon, Energy, Trainer), and card piles (Deck, Hand, Discard, etc.), creating a modular and extensible design that accurately represents the physical card game's mechanics.

## TODO
- [x] Improve comments to enhance clarity.

[^1]: [Pokémon Trading Card Game Rules - The Pokémon Company International](https://assets.pokemon.com/assets/cms2/pdf/trading-card-game/rulebook/sm7_rulebook_en.pdf)