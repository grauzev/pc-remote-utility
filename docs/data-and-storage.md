# Data & Storage

## 1. Source of truth
The command registry is stored in a single file:

- `data/commands.json`

This file is the single source of truth for:
- web remote buttons,
- Siri/Shortcuts command list,
- command execution definitions,
- scenes (as `type: "scene"` commands).

## 2. Backups
Before applying any change to the registry (create/edit/archive/delete/import):
- create a backup file in `data/backups/`,
- naming example: `commands-YYYYMMDD-HHMMSS.json`.

## 3. Atomic save
To prevent file corruption:
1) write to `commands.json.tmp`,
2) validate the written content,
3) atomically replace `commands.json`.

If validation fails, keep the last known good version.

## 4. Import / Export
### Export
- Exports a single file representing the current registry.
- Optional filters (future): export without archived entries.

### Import
Supported modes:
- **merge** (default): merge by `id` (update existing, add new),
- **replace**: replace the entire registry.

Import flow:
1) upload file,
2) validate JSON + schema,
3) create backup,
4) apply merge/replace,
5) save atomically,
6) show a summary (added/updated/conflicts).

## 5. Logs
Two kinds of logs are useful:
- **App log** (technical): server start/stop, errors, stack traces.
- **Audit log** (user actions): executed commands, edits, imports, deletes.

For UI convenience, keep an in-memory rolling buffer of the last N audit events
and expose it to both GUI and Web UI.

## 6. Schema versioning
`commands.json` contains `schemaVersion`.
When the format evolves, the app must either:
- migrate older versions,
- or support backward compatibility for reading.
