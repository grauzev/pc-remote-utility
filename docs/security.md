# Security

## 1. Threat model (practical)
- The utility must not be exposed directly to the public internet.
- On a local network, other devices may attempt requests, so authentication is required.
- Editing/importing/deleting commands must be protected separately (admin mode).

## 2. v0.1 rules
1) Bind to local network only (configurable if needed).
2) Require an API token for all API calls.
3) Require admin mode for:
   - CRUD operations,
   - import/export,
   - hard delete,
   - running health checks (optional admin-only).

## 3. Command execution constraints
- Execute only commands defined in `commands.json` (no arbitrary user input).
- For `shell` commands: keep a strict whitelist (no free-form text from requests).
- For `script` commands (planned):
  - allow only under an approved directory (e.g., `data/scripts/`),
  - do not auto-elevate privileges.

## 4. UI protection
- Admin login via PIN/password.
- Admin session timeout can be added later.

## 5. Network recommendations
- Windows Firewall: allow inbound only for Private network profile.
- Do not configure router port-forwarding.
- For remote access outside home: use VPN (future).

## 6. Logging
- Log security-relevant actions: execute, edit, import, delete (audit events).
- Do not log secrets (tokens, passwords).
