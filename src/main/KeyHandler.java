package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utilz.*;

public class KeyHandler implements KeyListener {

    private GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed, cPressed,
            escPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;

    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        int code = e.getKeyCode();
        // TITLE STATE
        switch (Gamestate.state) {
            case CHARACTER:
                characterState(code);
                break;
            case DIALOUE:
                dialogueState(code);
                break;
            case GAMEOVER:
                gameOverState(code);
                break;
            case INFORM:
                dialogueState(code);
                break;
            case LOADING:
                break;
            case MENU:
                titleState(code);
                break;
            case OPTIONS:
                optionsState(code);
                break;
            case PLAYING:
                playState(code);
                break;
            case TRADING:
                tradingState(code);
                break;
            case WINNER:
                winState(code);
                break;
            default:
                break;

        }
    }

    private void winState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.playMusic(0);
            Gamestate.state = Gamestate.MENU;
            gp.restart();
        }
    }

    private void tradingState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSE(9);
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSE(9);
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }

    }

    private void gameOverState(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                Gamestate.state = Gamestate.PLAYING;
                gp.retry();
                //gp.restart();
                gp.playMusic(13);
            } else if (gp.ui.commandNum == 1) {
                gp.playMusic(0);
                Gamestate.state = Gamestate.MENU;
                gp.restart();
            }
        }
    }

    private void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            Gamestate.state = Gamestate.PLAYING;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0:
                maxCommandNum = 5;
                break;
            case 3:
                maxCommandNum = 1;
                break;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.getVolumeScale() > 0) {
                    int volumeScale = gp.music.getVolumeScale() - 1;

                    gp.music.setVolumeScale(volumeScale);
                    ;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.getVolumeScale() > 0) {
                    int volumeScale = gp.se.getVolumeScale() - 1;

                    gp.se.setVolumeScale(volumeScale);

                    gp.playSE(9);
                }
            }

        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if (gp.ui.commandNum == 1 && gp.music.getVolumeScale() < 5) {
                    int volumeScale = gp.music.getVolumeScale() + 1;
                    gp.music.setVolumeScale(volumeScale);
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if (gp.ui.commandNum == 2 && gp.se.getVolumeScale() < 5) {
                    int volumeScale = gp.se.getVolumeScale() + 1;
                    gp.se.setVolumeScale(volumeScale);

                    gp.playSE(9);
                }
            }
        }

    }

    public void titleState(int code) {

        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.playSE(9);
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.playSE(9);
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }

            }
            if (code == KeyEvent.VK_ENTER) {

                if (gp.ui.commandNum == 0) {
                    gp.stopMusic();
                    gp.playMusic(13);
                    Gamestate.state = Gamestate.PLAYING;

                }
                if (gp.ui.commandNum == 1) {
                    gp.ui.titleScreenState = 1;
                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }

            }

        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W) {
                gp.playSE(14);
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                gp.playSE(14);
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                gp.playSE(14);
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                gp.playSE(14);
                rightPressed = true;
            }
            if (code == KeyEvent.VK_F) {
                gp.playSE(14);
                shotKeyPressed = true;

            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(14);
                enterPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.playSE(14);
                escPressed = true;
            }
            if (code == KeyEvent.VK_C) {
                gp.playSE(14);
                cPressed = true;

            }

            if (code == KeyEvent.VK_B) {

                gp.ui.titleScreenState = 0;

            }

        }
    }

    public void playState(int code) {

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_C) {
            Gamestate.state = Gamestate.CHARACTER;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;

        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;

        }
        if (code == KeyEvent.VK_ESCAPE) {
            Gamestate.state = Gamestate.OPTIONS;

        }

    }

    public void dialogueState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    private void playerInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.playerSlotRow != 0) {
                gp.ui.playerSlotRow--;
                gp.playSE(9);
            }

        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.playerSlotCol != 0) {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.playerSlotRow != 3) {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }

        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.playerSlotCol != 4) {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
    }

    private void npcInventory(int code) {
        if (code == KeyEvent.VK_W) {
            if (gp.ui.npcSlotRow != 0) {
                gp.ui.npcSlotRow--;
                gp.playSE(9);
            }

        }
        if (code == KeyEvent.VK_A) {
            if (gp.ui.npcSlotCol != 0) {
                gp.ui.npcSlotCol--;
                gp.playSE(9);
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gp.ui.npcSlotRow != 3) {
                gp.ui.npcSlotRow++;
                gp.playSE(9);
            }

        }
        if (code == KeyEvent.VK_D) {
            if (gp.ui.npcSlotCol != 4) {
                gp.ui.npcSlotCol++;
                gp.playSE(9);
            }
        }
    }

    public void characterState(int code) {
        if (code == KeyEvent.VK_C) {
            Gamestate.state = Gamestate.PLAYING;
        }

        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        playerInventory(code);

    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;

        }
        if (code == KeyEvent.VK_C) {
            cPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    public boolean isShotKeyPressed() {
        return shotKeyPressed;
    }

    public void setShotKeyPressed(boolean shotKeyPressed) {
        this.shotKeyPressed = shotKeyPressed;
    }

}
