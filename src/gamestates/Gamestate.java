package gamestates;

public enum Gamestate {
	PLAYING, 
	MENU, 
	OPTIONS, 
	CHARACTER,
	GAMEOVER,
	WINNER,
	TRADING,
	LOADING,
	INFORM,
	DIALOUE;

	public static Gamestate state = MENU;
}
