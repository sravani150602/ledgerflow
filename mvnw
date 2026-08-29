#!/bin/sh
set -eu
MVNW_VERSION=3.9.11
MVNW_BASE="${MAVEN_USER_HOME:-$HOME/.m2}/wrapper/dists/apache-maven-$MVNW_VERSION"
MVNW_BIN="$MVNW_BASE/apache-maven-$MVNW_VERSION/bin/mvn"
if [ ! -x "$MVNW_BIN" ]; then
  mkdir -p "$MVNW_BASE"
  MVNW_ARCHIVE="$MVNW_BASE/maven.tar.gz"
  MVNW_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MVNW_VERSION/apache-maven-$MVNW_VERSION-bin.tar.gz"
  if command -v curl >/dev/null 2>&1; then curl -fsSL "$MVNW_URL" -o "$MVNW_ARCHIVE"; else wget -q "$MVNW_URL" -O "$MVNW_ARCHIVE"; fi
  tar -xzf "$MVNW_ARCHIVE" -C "$MVNW_BASE"
  rm -f "$MVNW_ARCHIVE"
fi
exec "$MVNW_BIN" "$@"
