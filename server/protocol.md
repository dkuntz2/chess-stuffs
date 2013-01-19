# Chess Protocol

## getting a piece:
	
Request: 	`get:[x coord]:[y coord];`<br>
Return:  	`[piece char (.getChar())]:[location in p.arraylist]:[Player Color (b or w)];`

## Moving a piece:

Request:	`move:[piece's x coord]:[piece's y coord]:[new x coord]:[new y coord];`<br>
Return:		`[valid / invalid depending on if you can or can't move];`

## Attaching self:

Request:	`attach:[ip address];`<br>
Return:		nothing

## Removing self:

Request:	`remove:[ip address];`<br>
Return:		nothing

## Get a player

Request:	`player:[b / w];`<br>
Return:		Player Object

## Update Alert (sent to remoteObserver)

Request:	`update;`<br>
Return:		nothing...
