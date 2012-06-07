# Chess Protocol

## getting a piece:
	
Request: 	get:[x coord]:[y coord];
Return:  	[piece char (.getChar())]:[location in p.arraylist]:[Player Color (b or w)];

## Moving a piece:

Request:	move:[piece's x coord]:[piece's y coord]:[new x coord]:[new y coord];
Return:		[valid / invalid depending on if you can or can't move];

## Attaching self:

Request:	attach:[ip address];
Return:		nothing

## Removing self:

Request:	remove:[ip address];
Return:		nothing

## Get a player

Request:	player:[b / w];
Return:		Player Object

## Update Alert (sent to remoteObserver)

Request:	update;
Return:		nothing...