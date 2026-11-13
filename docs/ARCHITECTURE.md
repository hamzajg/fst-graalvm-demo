# Architecture — fst-graalvm-demo

Current-state map. Descriptive, not aspirational; see `docs/adr/` for why.

## Shape

```
fst-graalvm-demo/           Maven single-module (Java)
├── src/main/java/com/hamzajg/demo/fst/
│   ├── Person.java             serializable POJO (name, age)
│   └── BootstrapApp.java       main — FST write → read → print round trip
├── src/test/java/com/hamzajg/demo/fst/
│   └── SerializationRoundTripTest.java
└── pom.xml                 FST dependency, exec + surefire plugins
```

## Flow

`BootstrapApp.main` → create `Person` → FST `ObjectOutput`/`ObjectInput`
to bytes → deserialize → print original vs round-trip + equality.
Test asserts the same round trip.

## Purpose

Demonstrate FST (fast Java serialization) and its GraalVM
native-image friendliness — companion to vlingo's serialization
benchmarks. Not a general serialization library.

## State

None — pure in-memory demo.
