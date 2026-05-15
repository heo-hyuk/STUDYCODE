# Project Workflow: STUDYCODE

## Commit & Organization Convention
- **Mandatory Folderization**: For every commit request, move the relevant project files into a new folder at the project root.
- **Naming Convention**: `YYYY-MM-DD_TaskName` (e.g., `2026-05-15_ConsoleBoard`).
- **Scope**: Move the entire project structure (src, build.gradle, etc.) into the new folder to keep each version/step isolated at the root.

## Technical Standards
- Maintain the Spring Boot project structure within the subfolders.
- Ensure all package declarations remain consistent with the folder structure if necessary, though keeping the internal `src/main/java` structure is preferred.
