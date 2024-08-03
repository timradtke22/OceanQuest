package utilz;

import main.Game;
import java.awt.geom.Rectangle2D;

public class HelpMethods {
    
    public static boolean CanMoveHere(float x, float y, float width, float height) {

        //This checks top left
        if (!IsSolid(x, y)) {
            //Checks bottom right
            if (!IsSolid(x+width, y+height)) {
                //Top Right
                if (!IsSolid(x+width, y)) {
                    //Bottom Left
                    if (!IsSolid(x, y+height)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean IsSolid(float x, float y) {

        if (x < 0 || x >= Game.GAME_WIDTH) { 
            return true;
        }

        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }



        return false;
    }


    /* 
    public static boolean CanMoveHereX(float x, int width) {
        if (!IsXSolid(x)){
            if (!IsXSolid(x+width)) {
                return true;
            }
        }
        return false;
    }

    public static boolean CanMoveHereY(float y, int height) {
        if (!IsYSolid(y)) {
            if (!IsYSolid(y+height)) {
                return true;
            }
        }
        return false;
    }
    */

    /* 
    private static boolean IsYSolid(float y) {

        if (y < 0 || y >= Game.GAME_HEIGHT) {
            return true;
        }

        return false;
    }

    private static boolean IsXSolid(float x) {

        if (x < 0 || x >= Game.GAME_WIDTH) { 
            return true;
        }

        return false;
    }
    */
}
