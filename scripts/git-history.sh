#!/usr/bin/env bash
# Run ONCE in the project root to create an incremental commit history, then push to GitHub:
#   git remote add origin https://github.com/<you>/<repo>.git && git push -u origin main
set -e
cd "$(dirname "$0")/.."
git init -b main
c() { git add "$@" >/dev/null; }
c .gitignore README.md;                                  git commit -qm "Add README and .gitignore"
c src/main/java/bridge/implementor/Renderer.java;        git commit -qm "Add Renderer implementor interface"
c src/main/java/bridge/abstraction/Shape.java;           git commit -qm "Add Shape abstraction holding a Renderer (the bridge)"
c src/main/java/bridge/implementor/VectorRenderer.java;  git commit -qm "Add VectorRenderer (SVG) concrete implementor"
c src/main/java/bridge/implementor/RasterRenderer.java;  git commit -qm "Add RasterRenderer (ASCII pixels) concrete implementor"
c src/main/java/bridge/abstraction/Circle.java src/main/java/bridge/abstraction/Square.java
git commit -qm "Add Circle and Square refined abstractions"
c src/main/java/bridge/abstraction/Rectangle.java;       git commit -qm "Add Rectangle: new shape without touching renderers"
c src/main/java/bridge/Main.java;                        git commit -qm "Add client demo with runtime renderer switching"
c src/test/java/bridge/BridgeSmokeTest.java scripts;     git commit -qm "Add smoke tests and run script"
git log --oneline
