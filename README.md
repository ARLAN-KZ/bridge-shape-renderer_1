# Bridge Pattern — Shape × Renderer (Java 17)

Assignment #3, ShP-2216 Software Design Patterns, Astana IT University.

The **Bridge** pattern splits *what* is drawn (shapes) from *how* it is drawn (renderers),
so each side can grow independently. The two hierarchies are linked by composition:
`Shape` holds a `Renderer` reference.

```
abstraction side                         implementor side
Shape (abstract) ──── renderer ────────► Renderer (interface)
 ├─ Circle                                ├─ VectorRenderer  (SVG)
 ├─ Square                                └─ RasterRenderer  (ASCII pixels)
 └─ Rectangle
```

## Structure

| Path | Role |
|---|---|
| `src/main/java/bridge/abstraction/Shape.java` | Abstraction (holds `Renderer`) |
| `.../abstraction/{Circle,Square,Rectangle}.java` | Refined Abstractions |
| `src/main/java/bridge/implementor/Renderer.java` | Implementor interface |
| `.../implementor/{VectorRenderer,RasterRenderer}.java` | Concrete Implementors |
| `src/main/java/bridge/Main.java` | Client — composes and switches at runtime |
| `src/test/java/bridge/BridgeSmokeTest.java` | Dependency-free self-checks |

## Build & run

```bash
./scripts/run.sh
```
or manually:
```bash
mkdir out && javac -d out $(find src -name '*.java')
java -cp out bridge.Main
java -ea -cp out bridge.BridgeSmokeTest
```
Open the project folder in IntelliJ IDEA (JDK 17) and run `bridge.Main`.

## Extending

* New shape → add a subclass of `Shape`; no renderer changes.
* New renderer → add a class implementing `Renderer`; no shape changes.
