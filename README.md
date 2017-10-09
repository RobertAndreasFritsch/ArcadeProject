Arcade Framework
================

### Team

- Robert Andreas Fritsch (fritsch-robert@web.de)

### About

This framework is not released yet!

The Arcade Project started as a school project, to get started
with multilayer games based running on a self made "arcade table".
Thats why you can find many implementations specifically for our
environment (e.g. the control tangible module).

But the Arcade Framework is much more. Over the time we created a library 
to program games an every jvm and the "arcade table" specific stuff is marked as
tangible.
So if you dont want to program on our "arcade table" feel free to use this library for your own purposes.

### Features

#### v1.0

- a control module that provides:
  + event based key types translation into frame based key states
- a window module that provides:
  + default full screen
  + frame based rendering using buffers
- a game module that provides:
  + timed game loop
  + game object update mechanism (graphics and time parameter)
  + window and control setup

#### v1.0 tangible

- a tangible control module extending the control module and provides:
  + a constant way of accessing key states in a more comfortable way
- a tangible game module extending the game module and provides:
  + window and tangible control setup
  + specific window configuration

### Getting started

The most common module is the game module. So the best Point to get Started is the README.md
inside the arc-awt-game folder.

have fun.

### Installation

We are using a [Gradle](https://gradle.org/) wrapper as build tool.
So everything you have to do is to open your commandline in the root
directory of this project and type:

gradlew.exe makeZip

now you can find the binaries and the full documentation in the component-folders under build/Zip.
