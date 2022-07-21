package gamestates;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import entity.item.*;
import main.GamePanel;
import utilz.Gamestate;


public class UI {
    GamePanel gp;
    Font MineCraft;
    BufferedImage coin;

    Graphics2D g2;

    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();

    private Menu menuState;
    private Playing playingState;
    private Character characterSate;
    private Option optionState;
    private Winner winState;
    private GameOver gameOverState;
    private Loading loadingState;
    private Inform informState;
    private Dialogue dialogueSate;
    private Trading tradingSate;
    private PaintUI painUI;
    //private PaintUI menuState, playingState,characterSate,optionState,winState,gameOverState,loadingState,informState,dialogueSate,tradingSate;
    //public ArrayList<PaintUI> panitUI = new ArrayList<>();
    
    public String currentDialogue = "";
    public int commandNum = 0;

    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int titleScreenState = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int counter = 0;
    public int subState = 0;
    public Entity npc;

    public UI(GamePanel gp) {
        this.gp = gp;
        initClasses();
        try {
            InputStream is = getClass().getResourceAsStream("/Font/determination.ttf");
            MineCraft = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // CREATE HUD OBJECT
        Item Coin = new Coin(gp);
        coin = Coin.getImage();

    }

    private void initClasses() {
        menuState = new Menu(gp);
        playingState = new Playing(gp);
        characterSate = new Character(gp);
        optionState = new Option(gp);

        winState = new Winner(gp);
        gameOverState = new GameOver(gp);
        loadingState = new Loading(gp);
        informState = new Inform(gp);
        dialogueSate = new Dialogue(gp);
        tradingSate = new Trading(gp);

    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(MineCraft);
        g2.setColor(Color.white);

        switch (Gamestate.state) {
        case CHARACTER:
            characterSate.draw(g2);
            break;
        case DIALOUE:
            dialogueSate.draw(g2);
            break;
        case GAMEOVER:
            gameOverState.draw(g2);
            break;
        case INFORM:
            informState.draw(g2);
            break;
        case LOADING:
            loadingState.draw(g2);
            break;
        case MENU:
            menuState.draw(g2);
            break;
        case OPTIONS:
            optionState.draw(g2);
            break;
        case PLAYING:
            playingState.draw(g2);
            break;
        case TRADING:
            tradingSate.draw(g2);
            break;
        case WINNER:
            winState.draw(g2);
            break;
        default:
            break;
    	}
//        switch (Gamestate.state) {
//        case CHARACTER:
//        	painUI = new Character(gp);
//            break;
//        case DIALOUE:
//        	painUI = new Dialogue(gp);
//            break;
//        case GAMEOVER:
//        	painUI = new GameOver(gp);
//            break;
//        case INFORM:
//        	painUI =  new Inform(gp);
//            break;
//        case LOADING:
//        	painUI = new Loading(gp);
//            break;
//        case MENU:
//        	painUI = new Menu(gp);
//            break;
//        case OPTIONS:
//        	painUI = new Option(gp);
//            break;
//        case PLAYING:
//        	painUI = new Playing(gp);
//            break;
//        case TRADING:
//        	painUI = new Trading(gp);
//            break;
//        case WINNER:
//        	painUI = new Winner(gp);
//            break;
//        default:
//            break;
//    }
//      painUI.draw(g2);
}

    public int getItemIndexonSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }
}
