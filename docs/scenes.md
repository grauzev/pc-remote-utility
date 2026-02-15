# Scenes (Macros)

## 1. Principle
A scene is a command with `type: "scene"` that contains a list of steps.
Steps execute **strictly sequentially** (no parallel execution in v0.1).

## 2. Step kinds (v0.1)
### 2.1 runCommand
Runs another command by `commandId`.

Fields:
- `kind`: `runCommand`
- `commandId`: string

### 2.2 delay
Waits a fixed time between steps.

Fields:
- `kind`: `delay`
- `delayMs`: number

## 3. Errors and timeouts
- A scene stops on the first error.
- The result message must include: failed step index + reason.
- A global timeout policy can be added later (v0.2) if needed.

## 4. Example (concept)
Example steps:
1) runCommand -> `tv`
2) delay -> 1500
3) runCommand -> `open_player`

Exact commands depend on the user’s setup.
