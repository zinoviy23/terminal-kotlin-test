#!/bin/sh

./gradlew :installDist > /dev/null

if [[ $1 == "-e" ]]; then
  unset IDEA_INITIAL_DIRECTORY
  unset __INTELLIJ_COMMAND_HISTFILE__
  unset TERMINAL_EMULATOR
fi

LC_CTYPE=UTF-8 JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 ./build/install/terminal-playground/bin/terminal-playground