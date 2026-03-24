#!/usr/bin/env sh
set -e

# Minimal Maven wrapper fallback for this repository.
# Uses installed mvn if present.
if command -v mvn >/dev/null 2>&1; then
  exec mvn "$@"
fi

echo "Error: 'mvn' not found in PATH. Install Maven 3.9+ or use mvnw.cmd on Windows." >&2
exit 1
