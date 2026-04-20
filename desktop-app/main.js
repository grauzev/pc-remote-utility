const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("path");
const { spawn } = require("child_process");
const http = require("http");

// === Top-level state ===
let mainWindow = null;
let backendProcess = null;
let appState = null;

// === App state ===
function createInitialAppState() {
  return {
    startup: {
      phase: "starting",
      message: null,
    },
    server: {
      status: "unknown",
      message: null,
    },
    commands: {
      status: "unknown",
      message: null,
      items: [],
    },
    reload: {
      status: "not_performed",
      message: null,
    },
  };
}

// === Renderer communication ===
function sendAppStateToRenderer() {
  if (!mainWindow) {
    return;
  }

  mainWindow.webContents.send("app:state-changed", appState);
}

// === App state updates ===
function updateAppState(patch) {
  appState = {
    ...appState,
    ...patch,
    startup: {
      ...appState.startup,
      ...patch.startup,
    },
    server: {
      ...appState.server,
      ...patch.server,
    },
    commands: {
      ...appState.commands,
      ...patch.commands,
    },
    reload: {
      ...appState.reload,
      ...patch.reload,
    },
  };

  sendAppStateToRenderer();
}

// === Window ===
function createMainWindow() {
  mainWindow = new BrowserWindow({
    width: 1280,
    height: 800,
    webPreferences: {
      preload: path.join(__dirname, "preload.js"),
    },
  });

  mainWindow.loadFile(path.join(__dirname, "src", "renderer", "index.html"));
}

// === Backend status ===
function isServerRunning() {
  return new Promise((resolve) => {
    const request = http.get("http://localhost:8080/api/status", (response) => {
      response.resume();
      resolve(response.statusCode === 200);
    });

    request.on("error", () => {
      resolve(false);
    });

    request.setTimeout(2000, () => {
      request.destroy();
      resolve(false);
    });
  });
}

// === Backend process ===
function startBackendProcess() {
  const projectRoot = path.join(__dirname, "..");

  backendProcess = spawn("mwnw.cmd", ["spring-boot:run"], {
    cwd: projectRoot,
    shell: true,
  });
}

// === Backend readiness ===
async function waitForServerReady() {
  const maxAttempts = 30;
  const delayMs = 1000;

  for (let attempt = 0; attempt < maxAttempts; attempt += 1) {
    const running = await isServerRunning();

    if (running) {
      return;
    }

    await new Promise((resolve) => {
      setTimeout(resolve, delayMs);
    });
  }

  throw new Error("Local server did not become ready in time.");
}

// === Startup flow ===
async function runStartupFlow() {
  updateAppState({
    startup: {
      phase: "starting",
      message: null,
    },
    server: {
      status: "unknown",
      message: null,
    },
    commands: {
      status: "unknown",
      message: null,
      items: [],
    },
  });

  try {
    const alreadyRunning = await isServerRunning();

    if (!alreadyRunning) {
      startBackendProcess();
      await waitForServerReady();
    }

    updateAppState({
      server: {
        status: "running",
        message: null,
      },
    });

    const runtimeCommands = await new Promise((resolve, reject) => {
      const request = http.get(
        "http://localhost:8080/api/commands",
        (response) => {
          if (response.statusCode !== 200) {
            response.resume();
            reject(
              new Error(
                `Commands endpoint returned status ${response.statusCode}.`,
              ),
            );
            return;
          }

          let rawData = "";

          response.on("data", (chunk) => {
            rawData += chunk;
          });

          response.on("end", () => {
            try {
              const parsedData = JSON.parse(rawData);
              resolve(parsedData);
            } catch (error) {
              reject(new Error("Failed to parse runtime commands response."));
            }
          });
        },
      );

      request.on("error", () => {
        reject(new Error("Failed to fetch runtime commands."));
      });

      request.setTimeout(2000, () => {
        request.destroy();
        reject(new Error("Runtime commands request timed out."));
      });
    });

    updateAppState({
      startup: {
        phase: "ready",
        message: null,
      },
      commands: {
        status: "loaded",
        message: null,
        items: runtimeCommands,
      },
    });
  } catch (error) {
    updateAppState({
      startup: {
        phase: "failed",
        message: error.message,
      },
      server: {
        status: "failed",
        message: error.message,
      },
      commands: {
        status: "failed",
        message: error.message,
        items: [],
      },
    });
  }
}
