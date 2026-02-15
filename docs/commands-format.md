# Command Registry Format (commands.json)

## 1. File structure
`commands.json` is a single JSON file containing:
- `schemaVersion` (integer)
- `commands` (array of command objects)

Scenes are represented as commands with `type: "scene"`.

## 2. Command ID rules
- Unique `id` per command
- Lowercase ASCII: letters, digits, underscore
- Keep IDs stable (Web UI and Siri may reference them)

## 3. Common fields
Required: `id`, `title`, `group`, `type`  
Optional: `enabled`, `archived`, `adminOnly`, `confirm`, `order`

## 4. Types
- `shell`: cmd/PowerShell command
- `program`: full path to exe (+ optional args)
- `url`: protocol/URL (e.g., `steam://...`)
- `scene`: sequential steps (see `scenes.md`)

## 5. Health
Expose runtime health info via API (computed, not stored):
- `health.status`: `ok` | `broken`
- `health.reason`: string
