package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import ui.GameOverOverlay;

public class Game implements Runnable{

    // Initializing Gamewindow and Gamepanel
    private GameWindow gamewindow;
    private GamePanel gamepanel;
    // Creating a thread for gameloop to run on
    private Thread loopThread;
    // Creating variables to store FPS and UPS speeds
    private final int FPS = 120;
    private final int UPS = 200;

    private Playing playing;
    private Menu menu;
    private GameOverOverlay gameover;

    // Variables for tiles
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = TILES_DEFAULT_SIZE * (int) SCALE;
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    

    // Passing gamepanel into gamewindow right here to get all panel objects populated
    // on the frame, also resizing the window in this constructor as well since this 
    // will be what is directly called into the main class at the end, the game loop
    // is also present in this class

    public Game() {
        //Initializing all the classes
        initClasses();

        gamepanel = new GamePanel(this);

        // Passes gamewindow with the gamepanel into the Game
        gamewindow = new GameWindow(gamepanel);

        // Indicates the size of the gamepanel 
        gamepanel.setFocusable(true);

        // Changes where the gamepanel will show on the screen (it will show in the center)
        gamepanel.requestFocusInWindow();

        // Starts the other loop thread made, always has to be the last thing
        startGameLoop();

    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        // Starts the loop thread, the run() class is automatic because of implementing runnable to 
        loopThread = new Thread(this);
        // .start() calls the implement Runnable run() method
        loopThread.start();
    }

    //This method is to get all the updates that are over in game panel and update them with the gameloop
    public void update() {
        //This will control what gamestate we are in
        switch(Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case GAMEOVER:
            default:
                break;
            
        }
    }

    //only reason this is here is to connect game and player for gamepanel.
    public void render(Graphics g) {
        switch(Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            case GAMEOVER:
                gameover.draw(g);
            default:
                break;
            
        }
    }

    //This method comes with the implementation of using other threads, it is to set what is gonna 
    //Be done in that other thread.
    @Override
    public void run() {
        // getting the time per frame in nanoseconds (1 billion nanoseconds == 1 second),
        // that is why we are doing that high numerator because FPS and UPS is per second
        double time_per_frame = 1000000000.0/FPS;
        double time_per_update = 1000000000.0/UPS;

        // A variable to mark the previous time for the update
        long previousTime = System.nanoTime();

        // Ticker variables for the update and fps counters
        long updates = 0;
        long frames = 0;

        // A separate variable for the if block that console prints the FPS and UPS counts
        long lastCheck = System.currentTimeMillis();

        // The variables that will help account for lag
        double deltaU = 0.0;
        double deltaF = 0.0;

        while(true) {

            long currentTime = System.nanoTime();
            
            //These variables represent the % of filling up to that time_per_var variable. Once
            //At 1, it will be at a full time_per_var, and then can execute. If it goes over 1
            //It is lagging behind and thus needs to speed up for the next time now and advance
            //quicker. This helps account for that lag and speed up the system if thats the 
            //case
            deltaU += (currentTime - previousTime) / time_per_update;
            deltaF += (currentTime - previousTime) / time_per_frame;
            previousTime = currentTime;

            // If the var is = or over 1, trigger an update, add to the ticker, subtract 1 only,
            // We DO NOT RESET TO 0 because we want to have a little left over if it went over
            // This will account for game lag and trigger the next update sooner.
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // If the var is = or over 1, trigger an update, add to the ticker, subtract 1 only,
            // We DO NOT RESET TO 0 because we want to have a little left over if it went over
            // This will account for game lag and trigger the next update sooner.
            if (deltaF >= 1) {
                gamepanel.repaint();
                frames++;
                deltaF--;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }  
        }
        
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }


    //These will allow us to make the switch methods for our keyboard, this is why we pass in the classes
    //as parameters to begin with
    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }
}
