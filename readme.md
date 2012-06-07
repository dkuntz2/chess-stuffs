# Chess with Proxy and Observer

## Information

### Drivers

The two main drivers for the chess game are server/ServerDriver for the main
game itself and the concrete subject for the observers, and 
chesswatcher/WatcherDriver for the remote observer.

### Packages

Packages were used to make it easier on my brain and keep things slightly
organized. As such, they may not make much sense.

- chess
  The pacakge that contains all of the chess game stuff, each piece, the board,
  and the two errors (StillInCheckException and InvalidMoveException) used
  for the game.

- chesswatcher
  This package holds all of the remote observer information. The driver class
  for the remote observer is chesswatcher/WatcherDriver

- server
  The actual chess game and the server for the remote observer. The driver
  class is server/ServerDriver

- watcher
  The basis for the observer pattern used - the reason for watcher as opposed
  to observer is that java.util.Observer is also a class, and it gets
  tedious to write observer.Observer each time you want to reference the 
  custom observer.
