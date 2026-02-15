# Requirements

## 1. Goal
Build a Windows utility that lets the user control a desktop PC:
- from an iPhone via a browser-based web remote (Safari),
- via Siri/Shortcuts (voice on the phone),
- later: via an on-PC voice assistant.

The utility should look and feel like a classic Windows desktop app (a main window),
while also running an embedded local web server for the web remote and HTTP API.

## 2. Core principles
1) One PC application, one source of truth: `commands.json`.
2) All inputs (GUI / Web / Siri) resolve to `commandId` and are executed by a single core executor.
3) Reliability over “magic”: scenes (macros) execute strictly sequentially.
4) Security by default: local network only, API token required, admin mode for edits.

## 3. MVP scope (v0.1)
### 3.1 PC window (JavaFX)
- Show server status: running/stopped, port, reachable URLs (IP/hostname).
- Button: “Open Web Remote” (opens the web UI in the default browser on PC).
- Activity log: last actions, results, timestamps.
- Command management (CRUD):
  - create / edit / archive (soft-delete) / restore / delete (hard-delete),
  - “Check commands” (detect broken commands).
- Import/Export of the command registry as a single JSON file:
  - export (download/save),
  - import (merge/replace) with backup and rollback.
- Security settings:
  - API token,
  - admin PIN/password (required for edits/import/delete).

### 3.2 Web remote (Safari)
- Dashboard: big buttons, grouped (Displays / Games / Apps / Scenes).
- Status widget: last executed command + result.
- Commands area: CRUD + archive + test-run.
- Import/Export page (single file).
- Logs page (last N events).

### 3.3 HTTP API (for Web UI and Siri/Shortcuts)
- List commands (for web UI and the universal Siri shortcut).
- Execute a command by `commandId`.
- Admin-only endpoints: CRUD, import/export, health check.

## 4. Command types (v0.1)
- `shell` — run a whitelisted command (cmd/PowerShell).
- `program` — launch an executable by full path.
- `url` — open a URL / protocol handler (e.g., `steam://...`).
- `scene` — a macro: sequential steps (see `scenes.md`).
- `script` — run a script from an allowed directory (planned; optional in v0.2).

## 5. Definition of a “broken command”
A command is considered broken if:
- a referenced file path (exe/script) no longer exists,
- a scene step references a missing `commandId`,
- a test execution fails (where verifiable),
- a protocol/URL action cannot be executed (where verifiable).

UI should:
- display status “OK / Broken”,
- show a clear reason,
- offer quick actions: Edit / Archive / Delete.

## 6. Non-functional requirements
- Predictable behavior (sequential execution).
- Safe by default (LAN only, token, admin mode).
- Easy extensibility: adding commands should not require code changes (use UI or `commands.json`).
- Diagnostics: logs and clear error reporting.

## 7. Out of scope for v0.1
- On-PC voice assistant (speech-to-text like whisper.cpp).
- Wake-word (“movie mode”).
- Parallel scene execution.
- Remote access from the internet (use VPN later, no port-forwarding).
