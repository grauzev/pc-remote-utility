# Web Remote (UI/UX)

## 1. Goals
- Phone-first experience (Safari): big buttons, minimal friction.
- Clear grouping: Displays / Games / Apps / Scenes.
- Visible execution status and meaningful error messages.

## 2. Pages (MVP v0.1)
### 2.1 Dashboard
- Big command buttons (enabled and not archived).
- Grouped layout.
- Status widget: last executed command + result.

### 2.2 Commands
- List with search/filter by group.
- Actions: Run / Edit / Archive / Delete.
- Health indicator: OK / Broken (+ reason).

### 2.3 Command Editor (Create/Edit)
- Form depends on command type.
- Buttons: Save / Cancel / Test Run.

### 2.4 Archive
- Archived commands list.
- Actions: Restore / Delete.

### 2.5 Import / Export
- Export: download current registry as one file.
- Import: upload file, choose mode (merge/replace), require admin confirmation.

### 2.6 Logs
- Last N audit events for quick diagnostics.

## 3. UX principles
- Errors must be explicit (missing path, unknown commandId, permission denied).
- Dangerous actions require confirmation (and admin mode when applicable).
- Prefer soft-delete (archive) over hard-delete.
