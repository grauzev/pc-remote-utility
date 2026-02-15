# Concurrency & Execution Model

## 1. Why this matters
The app has two independent sources of actions:
- **GUI (JavaFX UI thread)**,
- **Web/API (Spring Boot request threads)**.

They can trigger executions and edits concurrently, so we must avoid race conditions.

## 2. Rule #1: commands execute strictly sequentially
Even if multiple requests arrive at the same time (phone + GUI), command execution must be deterministic.

Design:
- A **single execution queue** (single-thread executor).
- `execute(commandId)` enqueues a task.
- The task produces a result event (ok/error).
- No parallel scene execution in v0.1.

## 3. Rule #2: CommandRegistry is thread-safe
Read access happens frequently; writes are less frequent but critical.

Design:
- Use a **read/write lock**:
  - multiple concurrent reads are allowed,
  - writes block reads briefly.

## 4. Rule #3: atomic writes of commands.json
To prevent corrupted files:
- write to a temporary file,
- replace the original file atomically (move/rename),
- create a timestamped backup before applying changes.

If validation fails, keep the last known good registry in memory and on disk.

## 5. UI updates from background threads
Execution results may arrive from non-UI threads.
The GUI must update its controls via the JavaFX UI thread mechanism.

## 6. Core events
Core publishes events:
- `CommandExecuted` (result + metadata),
- `RegistryChanged` (commands modified),
- `HealthCheckCompleted` (broken commands report).

GUI subscribes to update views.
Web UI typically fetches the latest state on demand (polling is sufficient for v0.1).
