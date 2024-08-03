package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import java.awt.*;
import java.awt.event.KeyEvent;


import main.Game;

public class GameWonOverlay {

    
    private Playing playing;
    
    public GameWonOverlay(Playing playing) {
        this.playing = playing;
    }

    public void draw(Graphics g ){
        g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		g.setColor(Color.YELLOW);
		g.drawString("You Win!", (Game.GAME_WIDTH / 2 - 250), 250);
		g.drawString("Please Exit the Game", (Game.GAME_WIDTH / 2 - 350), 300);
    }

    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Gamestate.state = Gamestate.MENU;
            playing.resetGame();
        }
    }
}

