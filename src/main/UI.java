package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.awt.BasicStroke;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import entity.Entity;
import entity.item.*;
import gamestates.Character;
import gamestates.Dialogue;
import gamestates.GameOver;
import gamestates.Gamestate;
import gamestates.Inform;
import gamestates.Loading;
import gamestates.Menu;
import gamestates.Option;
import gamestates.PaintUI;
import gamestates.Playing;
import gamestates.Trading;
import gamestates.Winner;
import utilz.LoadSave;

public class UI {
    GamePanel gp;
    Font MineCraft;
    BufferedImage coin;

    Graphics2D g2;

    public ArrayList<String> message = new ArrayList<String>();
    public ArrayList<Integer> messageCounter = new ArrayList<Integer>();

    public Menu menuState;
    public Playing playingState;
    public Character characterSate;
    public Option optionState;
    public Winner winState;
    public GameOver gameOverState;
    public Loading loadingState;
    public Inform informState;
    public Dialogue dialogueSate;
    public Trading tradingSate;
    public PaintUI painUI;

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
    	characterSate =  new Character(gp);
    	optionState = new Option(gp);
 



        winState = new Winner(gp);
        gameOverState = new GameOver(gp);
        loadingState =  new Loading(gp);
        informState = new Inform(gp) ;
        dialogueSate = new Dialogue(gp) ;
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
     // TITLE STATE
        if (gp.gameState == gp.titleState) {
        	menuState.draw(g2);
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            playingState.draw(g2);
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            //drawdialogueScreen();
        	dialogueSate.draw(g2);
        }
        // INFORM STATE
        if (gp.gameState == gp.informState) {
           informState.draw(g2);
        }
        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {

            //drawCharacterScreen();
        	characterSate.draw(g2);
            //drawInventory(gp.player, true);
        }
        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            //drawOptionsScreen();
            optionState.draw(g2);
        }
        // GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
        	
            gameOverState.draw(g2);
        }
        // LOADING STATE
        if (gp.gameState == gp.loadingState) {
            loadingState.draw(g2);
        }
        // TRADING STATE
        if (gp.gameState == gp.tradingState) {
        	tradingSate.draw(g2);
        }
       
        
//        // TITLE STATE
//        if (gp.gameState == gp.titleState) {
//        	painUI = new Menu(gp);
//        }
//        // PLAY STATE
//        if (gp.gameState == gp.playState) {
//           painUI = new Playing(gp);
//        }
//        // DIALOGUE STATE
//        if (gp.gameState == gp.dialogueState) {
//            painUI = new Dialogue(gp);
//        }
//        // INFORM STATE
//        if (gp.gameState == gp.informState) {
//             //informState.draw(g2);
//             painUI = new Inform(gp);
//        }
//        // OPTIONS STATE
//        if (gp.gameState == gp.optionsState) {
//            //optionState.draw(g2);
//            painUI = new Option(gp);
//        }
//        // GAME OVER STATE
//        if (gp.gameState == gp.gameOverState) {
//        	painUI = new GameOver(gp);
//            //gameOverState.draw(g2);
//        }
//        // LOADING STATE
//        if (gp.gameState == gp.loadingState) {
//            //loadingState.draw(g2);
//            painUI = new Loading(gp);
//        }
//        // TRADING STATE
//        if (gp.gameState == gp.tradingState) {
//            //drawTradeScreen();
//            painUI = new Trading(gp);
//        }
//        // CHARACTER STATE
//        if (gp.gameState == gp.characterState) {
//            //characterSate.draw(g2);
//            painUI = new Character(gp);  
//        }
//        // Winner
//        if (gp.gameState == gp.winState) {
//            //winState.draw(g2);
//            painUI = new Winner(gp); 
//        }
//        painUI.draw(g2);
    }



    public int getItemIndexonSlot(int slotCol, int slotRow) {
        int itemIndex = slotCol + slotRow * 5;
        return itemIndex;
    }

    public void drawdialogueScreen() {
        // WINDOW
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);
        x += gp.tileSize;
        y += 32;

        g2.drawImage(npc.avatar, x, y, (int) (gp.tileSize * 2.5), (int) (gp.tileSize * 2.5), null);
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        x += gp.tileSize * 3;
        y += 16;
        g2.drawString(npc.getName() + ":", x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        y += 36;
        for (String line : currentDialogue.split("\n")) {

            g2.drawString(line, x, y);
            y += 40;
        }

    }

    private void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(255, 255, 255, 210);
        g2.setColor(c);
        g2.setColor(new Color(255, 255, 255, 200));

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(156, 100, 3);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        c = new Color(255, 148, 24);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 10, y + 10, width - 20, height - 20, 15, 15);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getXforAlignToRightText(String text, int tailX) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }

}
