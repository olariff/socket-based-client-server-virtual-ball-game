# Socket-based client-server virtual ball game
A socket-based client-server system to play a virtual ball is designed using Java programming language. Each client application is a player. Once a player receives a ball, it prompts the user whom to pass the ball to. The clients can connect and disconnect from the server at any time.

# Rules of the game
At any point in time, exactly one player has the virtual ball. Thisplayer needs to decide who to pass the ball to. (They are allowed to
pass the ball to themselves.) Once the decision is made, they passthe ball to the corresponding player.
New players can join the game at any time. The number of players in the game is unlimited. Every player joining the game has to be assigned a unique ID that will not change until they leave the game and will not be reused after they leave the game. 
All the players including the one with the ball will immediately learn about new players and,
hence, the current ball owner can decide to pass the ball to the player who has just joint the game.
Any player can leave the game at any time (the client application can be closed/killed). If the player with the ball leaves the game, the server passes the ball to one of the remaining players. If there are no players in the game, the server waits until someone joins the game in which case the first player to connect receives the ball.
