# AGENTS.md — fst-graalvm-demo

Guidance for AI agents (and humans) working in this repository.

## Project

A small demo of [FST](https://github.com/RuedigerMoeller/fast-serialization)
(Fast Serialization) and GraalVM, alongside vlingo. `BootstrapApp`
serializes a `Person` to bytes with FST and reads it back, printing the
round trip. Companion to `SerializationComparisonBenchmark` in the
vlingo ecosystem.
Stack: Java, Maven, FST, JUnit (test), `exec-maven-plugin` for the demo.

## Commands

Verified 2026-11-13 against CI (`.github/workflows/ci.yml`) and `pom.xml`.

| Task | Command |
|---|---|
| Package (CI) | `mvn -B package` |
| Run demo | `mvn -q exec:java` |
| Test only | `mvn -B test` |

Requires: JDK + Maven. `target/` is build output.

## Architecture

```
src/main/java/com/hamzajg/demo/fst/
  Person.java            serializable POJO (name, age)
  BootstrapApp.java      main: FST serialize → bytes → deserialize → print
src/test/java/com/hamzajg/demo/fst/
  SerializationRoundTripTest.java   round-trip assertion
pom.xml                  FST dep, exec plugin, surefire
```

Minimal by design: one entity, one serializer path, one test. The point
is demonstrating FST + GraalVM-native-friendliness, not building a
serialization framework.

## Conventions

- Keep it tiny — this is a demo; complexity belongs in benchmarks, not
  here.
- `Person` must stay FST-serializable (Java serialization contract) —
  adding non-serializable fields breaks the demo.
- Tests assert the round trip — keep them.

## Landmines

- **Default branch is `main`, not `master`.**
- `target/` exists locally — build output, don't commit.
- FST version is pinned in `pom.xml` — don't bump casually.
- GraalVM angle is about native-image compatibility — avoid APIs FST/
  native can't handle (reflection-heavy tricks).

## Agent rules

- Branch from `main`; name branches `feat/*`, `fix/*`, `chore/*`, `docs/*`.
- Docs-only changes still go through a PR; never commit to `main` directly.
- Verify before claiming: `mvn -B package` and `mvn -q exec:java` —
  report real output.
- Keep changes minimal — this is a demo, not a product.
- Update AGENTS.md / docs/ARCHITECTURE.md when structure or commands
  change.
