# fst-graalvm-demo

A small demo of [FST](https://github.com/RuedigerMoeller/fast-serialization)
(Fast Serialization) and GraalVM, in conjunction with
[github.com/vlingo](https://github.com/vlingo) and
[vlingo.io](https://vlingo.io).

`BootstrapApp` serializes a `Person` to bytes with FST and reads it back:

```
Original:   Person{name='Ada Lovelace', age=36}
Bytes:      46
Round trip: Person{name='Ada Lovelace', age=36}
Equal:      true
```

## Requirements

- JDK 17 for the JVM demo and tests (FST 2.57 predates the module system)
- GraalVM 21.1 for the native-image profile

## Maven build

```bash
mvn install
```

## Run the JVM demo

FST 2.57 reflects into JDK internals that JDK 16+ closes by default, so the
JVM needs the same `--add-opens` flags the tests use:

```bash
java \
  --add-opens java.base/java.lang=ALL-UNNAMED \
  --add-opens java.base/java.util=ALL-UNNAMED \
  --add-opens java.base/java.util.concurrent=ALL-UNNAMED \
  --add-opens java.base/java.math=ALL-UNNAMED \
  --add-opens java.base/java.io=ALL-UNNAMED \
  --add-opens java.base/java.net=ALL-UNNAMED \
  --add-opens java.base/java.text=ALL-UNNAMED \
  --add-opens java.base/java.time=ALL-UNNAMED \
  -jar target/fst-graalvm-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Run the tests

```bash
mvn test
```

`SerializationRoundTripTest` asserts that the bytes read back into an equal but
distinct `Person`.

## Generate native image resource configs

```bash
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image \
  -jar target/fst-graalvm-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Build the native image

```bash
mvn install -Pnative-image
```

## Run the native image

```bash
./target/fst-graalvm
```

## Known native-image issue

The native image currently fails while FST registers the default classes:

```
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError:
SerializationConstructorAccessor class not found for declaringClass:
[Ljava.lang.Object; (targetConstructorClass: java.lang.Object).
Usually adding [Ljava.lang.Object; to serialization-config.json fixes the problem.
```

Adding `java.lang.Object` (and the array class) to
`src/main/resources/META-INF/native-image/serialization-config.json` is the
documented fix; the JVM demo and tests are unaffected.

## Layout

```
src/main/java/com/hamzajg/demo/fst/
  BootstrapApp.java   serializes and reads back a Person
  Person.java         the serializable value object
src/test/java/com/hamzajg/demo/fst/
  SerializationRoundTripTest.java
src/main/resources/META-INF/native-image/   GraalVM reflection/serialization config
```
