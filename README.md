# Super Heroes Rework

A Fabric port of the MIT-licensed **SuperHeroesRework-00** project, updated for **Minecraft 26.2**.

Original source: https://github.com/LafaieteOficial2025/SuperHeroesRework-00  
Original author: LafaieteOficial2025  
License: MIT (the original LICENSE notice is retained).

## Environment

- Minecraft Java Edition 26.2
- Fabric Loader 0.19.5+
- Fabric API 0.161.0+26.2
- Java 25
- Fabric Loom 1.17
- Gradle 9.5.1

## Included foundations

- Hero, power, suit, tier and ability-definition JSON data loaders.
- Hero, ability and animation registries.
- Player energy and cooldown state utilities.
- Ability lifecycle hooks (activation, deactivation and tick).
- Animation state, event and bone-transform data structures.
- Fabric server tick integration.

## Important project status

The upstream repository is an early framework prototype, not a finished playable superhero mod. The imported repository did **not** contain hero models, textures, sound assets, actual .fsk animation files, a completed animation parser/renderer, multiplayer state synchronization, or concrete gameplay implementations for all JSON ability definitions. Those features are not represented as finished here. The current port makes the existing Java foundation load on Fabric 26.2 and loads the supplied JSON definitions; it does not invent missing assets or claim unfinished abilities already work.

The old Forge 1.20.1 example-template classes and Forge metadata were removed. The mod id remains `fiskheroesrebirth`.

## Build

Install a Java 25 JDK and Gradle 9.5.1, then run:

```bash
gradle --no-daemon clean build
```

The distributable mod JAR is written to `build/libs/`.

A GitHub Actions workflow also builds the project on pushes and pull requests and publishes the JAR as a downloadable workflow artifact.

## Data layout

Definitions are retained under `src/main/resources/data/fiskheroesrebirth/`:

- `heroes/` and `hero/`
- `powers/` and `power/`
- `suits/`
- `abilities/`
- `tier/`

The original MIT license is included in `LICENSE`.
