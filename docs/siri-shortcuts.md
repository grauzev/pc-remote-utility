# Siri / Shortcuts

## 1. Goal
Enable voice control from iPhone via Siri/Shortcuts:
- a few “quick shortcuts” for top commands,
- one universal shortcut that loads the command list and lets the user choose.

## 2. Quick shortcuts (MVP)
Examples:
- "TV" -> execute `tv`
- "Monitor" -> execute `monitor`
- "Extend" -> execute `extend`

These are the fastest for daily use.

## 3. Universal shortcut (recommended)
Flow:
1) Call `GET /api/commands`
2) Show the list (Choose from List)
3) Call `POST /api/execute` with the selected `commandId`

Benefit:
- Adding a new command in the utility automatically makes it available in the universal Siri menu.

## 4. Technical notes
- The PC utility must be reachable on local Wi-Fi.
- The API token is stored inside the Shortcut.
- Future improvement (v0.2): separate user token (execute only) and admin token (edit/import).
