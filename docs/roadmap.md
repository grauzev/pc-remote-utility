# Roadmap

## v0.1 (MVP) — working and useful
- JavaFX window:
  - server status (port, reachable URLs),
  - last N audit events,
  - command management (CRUD + archive),
  - import/export (single JSON file),
  - broken command check (health).
- Spring Boot server:
  - Web Remote UI + HTTP API (execute, list, logs),
  - admin endpoints (CRUD, import/export, health).
- Core:
  - `CommandRegistry` (JSON, validation, backups, atomic save),
  - `CommandExecutor` (single execution queue),
  - scenes: sequential steps + delay.
- Siri/Shortcuts:
  - a few quick shortcuts,
  - one universal shortcut (list -> choose -> execute).

## v0.2 — quality and convenience
- Import preview (summary of added/updated/conflicts).
- Better health checks and test execution reporting.
- Optional: `jobId` for execute + job status endpoint.
- Optional: separate tokens (execute-only vs admin).
- Export logs.

## v0.3 — advanced features
- On-PC speech-to-text module (voice assistant on PC).
- Phrase mapping: recognized text -> `commandId`.
- Optional: wake word (“movie mode”).

## v1.0 — product polish
- Packaging for Windows (jpackage: installer/exe).
- Autostart and tray mode.
- UI/UX polishing and docs cleanup.
