# Chess Stuffs

This is my [CSC
3050](http://www.carthage.edu/computer-science/courses#CSC3050)
(Object-Oriented Programming)  final project.

The requirements were:

- At least seven distinct classes. Basically no gigantic "God" class.
- At least four inheritance relationships.
- At least four polymorphic method calls.
- GUI-based application.
- At least two discussed design patterns.
- Doesn't reuse prevoius assignments from this or previous classes.

## What Have We Here?

Basically, there are four packages, organized not for other people
specifically, but so that I could more easily figure out what wen where and
how things interacted together. As such, they may not make sense to
everybody else.

### chess

The `chess` package contains everything you'll need to play a basic game of
chess. What that means is some of the more obscure rules are not present in
this package.

All of the pieces (Bishop, King, Knight, Pawn, Queen, and Rook) have their
movement rules built in, and know if they can or can't move to another
position (if they can't, and you attempt to move them, they'll throw an
`InvalidMoveException`, or if you're in check and a move doesn't get you
out of check, a `StillInCheckExcpection`).

I may eventually get around to documenting what exactly it is that they do,
and how they do it. Possibly in Javadoc (more likely not though, I don't
really like javadoc).

The `Board` class has most of what you need if you want to play a game.
Really, the only thing it doesn't do is display a grid for people to see,
but the `server` package contains a `GUIBoard` class that gives you a nice
GUI, and the ability to click on pieces.

### chesswatcher

The `chesswatcher` package exists for anybody who wants to *view* a chess
match run through the `server` package. They can't do anything other than
watch.

The `chesswatcher` implements an observer and proxy pattern, coupled with
the `server` package.

Most people probably won't even need to look at, or use, this packge...

### server

This is where all of the action takes place. The game is implemented on a
`GUIBoard` through the `ServerDriver`.

That said, if you don't want any of the attached observer and proxy stuffs,
you can just run a `GUIBoard` through another driver (the `GUIBoard` will
still have the watcher hooks, but you don't need to worry about those).

### watcher

The basis of the observer pattern used. Yeah, it's just the interfaces for
an observer pattern.

## What Use Does This Have

Well, if you're ever considering using chess pieces for something, you've
got the rules of movement already written. Alternatively, you might not
want to use this at all...

----

Fair warning, I haven't run this since I had to demonstrate that it
functions as discussed with my professor, but it should work, hopefully...

I mean, I got a 98% on it...
