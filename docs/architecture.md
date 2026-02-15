# Architecture

## 1. High-level overview
The application is a single Windows desktop utility that contains:
- **GUI (JavaFX)** — the classic desktop window.
- **Embedded Server (Spring Boot)** — serves the Web Remote UI and the HTTP API.
- **Core (shared services)** — a single source of business logic used by both GUI and Server.

Clients:
- Safari on iPhone (Web Remote),
- Siri/Shortcuts (via HTTP API),
- later: on-PC speech input.

## 2. Core rule
All inputs (GUI / Web / Siri) resolve to a `commandId` and call:

`Core.CommandExecutor.execute(commandId)`

Neither GUI nor Web knows how commands are implemented. They only reference IDs.

## 3. Core modules (shared services)
### 3.1 CommandRegistry
Responsibilities:
- Load and validate `commands.json`.
- CRUD operations (create/update/archive/restore/delete).
- Import/Export (merge/replace).
- Atomic save and timestamped backups.
- Format versioning via `schemaVersion`.

### 3.2 CommandExecutor
Responsibilities:
- Execute a command by `commandId`.
- Support command types: `shell`, `program`, `url`, `scene`.
- Execute scenes strictly sequentially (no parallel mode).
- Return a structured result: ok/error + message + metadata.

### 3.3 HealthChecker
Responsibilities:
- Detect broken commands (missing paths, invalid references, etc.).
- Provide status “OK/Broken” with reasons.

### 3.4 AuditLog (Event Log)
Responsibilities:
- Store last N events (executions, errors, edits).
- Exposed to GUI and Web Remote for diagnostics.

## 4. Server module (Spring Boot)
Responsibilities:
- Serve Web Remote UI (static assets and/or server-rendered pages).
- Provide REST API endpoints for:
  - list commands,
  - execute command,
  - admin-only CRUD and import/export,
  - health checks,
  - logs.

The server calls Core services directly (no “loopback HTTP” between modules).

## 5. GUI module (JavaFX)
Responsibilities:
- Show server status (running/stopped, port, reachable URLs).
- Buttons: start/stop server, open web remote, run health check.
- Commands UI: list, create/edit, archive/restore, delete.
- Import/Export UI.
- Activity log UI (last N events).

GUI calls Core services directly and reacts to Core events.

## 6. Data & storage
See `data-and-storage.md`:
- `data/commands.json`,
- backups,
- logs.

## 7. Extensibility
New input types can be added without rewriting Core:
- e.g., an on-PC speech module maps recognized text -> `commandId` -> executor.
