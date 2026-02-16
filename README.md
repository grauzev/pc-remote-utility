# PC Remote Utility

A Windows desktop utility for controlling a PC from an iPhone:
- browser-based Web Remote (Safari),
- Siri / Shortcuts integration,
- configurable command registry (`commands.json`),
- sequential scenes (macros) for multi-step actions.

> Status: **Work in progress (MVP v0.1)**. The codebase is not implemented yet; documentation is available.

## Why this project
The goal is to build a reliable and secure local-network controller for common PC actions
(e.g., switching displays, launching apps/games), while keeping command definitions editable
without changing the code.

## Documentation
See the documentation index: `docs/index.md`

Key docs:
- Requirements: `docs/requirements.md`
- Architecture: `docs/architecture.md`
- API: `docs/api.md`
- Security: `docs/security.md`
- Roadmap: `docs/roadmap.md`

## Security note
The utility is designed for **local network usage** and will require an API token.
No direct internet exposure (no port-forwarding). Remote access should be done via VPN (future).

## Planned MVP (v0.1)
- JavaFX desktop window + embedded Spring Boot server
- Web Remote UI + HTTP API
- Command management (CRUD + archive) and import/export (single JSON file)
- Sequential execution queue and command health checks
