# ADR 0001 — Initial architecture

Date: 2026-11-13 (baseline recorded retroactively)
Status: accepted (describes what is built)

## Context

A companion demo for vlingo's serialization work — show FST round-trip
and its compatibility with GraalVM native images.

## Decisions

1. **Single POJO, single path.** `Person` (name, age) serialized by FST
   in `BootstrapApp`. Rationale: the demo's job is to show the
   mechanism, not explore the space — benchmarks do that.
2. **FST over JDK serialization.** Chosen for speed + native-image
   friendliness (no reflection-heavy tricks). Rationale: aligns with
   vlingo's GraalVM direction.
3. **Maven + exec plugin.** `mvn -q exec:java` runs the demo;
   `mvn -B package` is the CI gate. Rationale: standard, zero-config.
4. **Round-trip test.** `SerializationRoundTripTest` asserts
   serialize→deserialize equality. Rationale: the one behavior that
   matters, tested.

## Consequences / open questions

- Demo-grade only — no versioning, schema evolution, or error handling.
- FST version pinned in `pom.xml`; upgrades are deliberate.
- If the demo grows (multiple types, benchmarks), split into modules —
  but resist until there's a real need.
