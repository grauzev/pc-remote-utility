# HTTP API

## 1. Principles
- Used by Web UI and Siri/Shortcuts.
- All execution is done by `commandId`.
- Security: API token required; admin mode required for edits/import/delete.

## 2. Endpoints (proposal for v0.1)

### 2.1 Public (token required)
- `GET /api/status`
  - basic server info, version, last execution summary

- `GET /api/commands`
  - list commands (default: exclude archived)
  - optional query: `includeArchived=true`

- `POST /api/execute`
  - body: `{ "commandId": "tv" }`
  - response: `{ "ok": true/false, "message": "...", "timestamp": "..." }`

- `GET /api/logs?limit=N`
  - last N audit events

### 2.2 Admin (token + admin required)
- `POST /api/commands`
  - create command

- `PUT /api/commands/{id}`
  - update command

- `POST /api/commands/{id}/archive`
- `POST /api/commands/{id}/restore`
- `DELETE /api/commands/{id}`
  - hard delete

- `POST /api/health/check`
  - run health check for all commands

- `GET /api/export`
  - download current `commands.json`

- `POST /api/import?mode=merge|replace`
  - upload registry file and apply import

## 3. Errors
- `400` invalid input / schema validation error
- `401` missing/invalid token
- `403` not allowed (admin required)
- `404` command not found
- `500` internal error

## 4. Execution model
- `execute` enqueues the job into a single execution queue.
- Optional future (v0.2): return a `jobId` and provide a job status endpoint.
