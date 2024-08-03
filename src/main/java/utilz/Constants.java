package utilz;

import main.Game;

public class Constants {

    public static class CannonBall {
        public static int BALL_DEFAULT_WIDTH = 15;
        public static int BALL_DEFAULT_HEIGHT = 15;

        public static int BALL_HEIGHT = (int) (BALL_DEFAULT_HEIGHT * Game.SCALE);
        public static int BALL_WIDTH = (int) (BALL_DEFAULT_WIDTH * Game.SCALE);

        public static float SPEED = (2f * Game.SCALE);
    }

    public static class Enemy {
        public static final int ENEMY_SHIP = 0;

        public static final int BACK = 0;
        public static final int IDLE = 2;
        public static final int FRONT = 4;
        public static final int DEAD = 1;

        public static final int ENEMY_WIDTH_DEFAULT = 110;
        public static final int ENEMY_HEIGHT_DEFAULT = 100;

        public static final int ENEMY_WIDTH = (int) (ENEMY_WIDTH_DEFAULT * Game.SCALE);
        public static final int ENEMY_HEIGHT = (int) (ENEMY_HEIGHT_DEFAULT * Game.SCALE);

        public static int getSpriteAmount(int enemy_action) {
            switch(enemy_action) {
                case BACK:
                case IDLE:                
                case FRONT:
                    return 9;
                default:
                    return 1;
            }
        }

        public static int getMaxHealth() {
            return 1;
        }

        public static int getEnemyDmg() {
            return 1;
        }
    }

    public static class UI {
        public static class Buttons {
            public static final int BUTTON_DEFAULT_WIDTH = 30;
            public static final int BUTTON_DEFAULT_HEIGHT = 10;
            public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH * Game.SCALE);
            public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT * Game.SCALE);
        }
    }
    
    //Everything in here will revolve around the player

    public static class PlayerConstants {
        public static final int BACK = 0;
        public static final int IDLE = 2;
        public static final int FRONT = 4;

        public static int getSpriteAmount(int player_action) {

            switch(player_action) {
                case BACK:
                case IDLE:                
                case FRONT:
                    return 9;
                default:
                    return 1;
            }
        }
     }
}
