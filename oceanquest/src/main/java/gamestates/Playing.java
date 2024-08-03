package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import objects.ObjectManager;
import sprites.EnemyManager;
import sprites.Player;
import sprites.TileManager;
import ui.GameOverOverlay;
import ui.GameWonOverlay;

import java.awt.geom.Rectangle2D;

public class Playing extends State implements Statemethods{
    private Player player;
    private EnemyManager enemyManager;
    private TileManager tiles;
    private ObjectManager objectManager;
    private GameOverOverlay gameOverOverlay;
    private GameWonOverlay gameWonOverlay;

    private boolean gameOver;
    private boolean gameWon;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    public boolean checkEnemyHit(Rectangle2D.Float attackbox) {
        return enemyManager.checkEnemyHit(attackbox);
    }

    public void againstPlayer(){
        enemyManager.againstPlayer();
    }

    private void initClasses() {
        gameWonOverlay = new GameWonOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        objectManager = new ObjectManager(this);
        enemyManager = new EnemyManager(this);
        player = new Player(0,0, (int) (110 * Game.SCALE), (int) (100 * Game.SCALE), this);
        tiles = new TileManager();
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }



    @Override
    public void update() {
        tiles.update();
        if (!gameOver && !gameWon) {
            player.update();
            enemyManager.update();
            objectManager.update(player);
        }
    }
        



    @Override
    public void draw(Graphics g) {
        tiles.pDraw(g);
        player.render(g);
        enemyManager.draw(g); 
        objectManager.draw(g);

        if (gameOver) {
            gameOverOverlay.draw(g);
        }

        if (gameWon) {
            gameWonOverlay.draw(g);
        }
    }

    public void resetGame() {
        player.resetAll();
		enemyManager.resetAllEnemies();
        gameOver = false;
        gameWon = false;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver && !gameWon) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    player.setUp(true);
                    break;
                case KeyEvent.VK_S:
                    player.setDown(true);
                    break;
                case KeyEvent.VK_SPACE:
                    objectManager.shootCannon(true);
                    break;
            }        
        } else if (gameOver){
            gameOverOverlay.KeyPressed(e);
        } else {
            System.out.println("yay");
            gameWonOverlay.KeyPressed(e);
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_W:
                    player.setUp(false);
                    break;
                case KeyEvent.VK_S:
                    player.setDown(false);
                    break;
                case KeyEvent.VK_SPACE:
                    objectManager.shootCannon(false);
            }     
        }   
    }




}
