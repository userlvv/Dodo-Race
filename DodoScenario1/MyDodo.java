import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead()){
            return false;
        } else {
            return true;
        }
    }
    
    public void climbOverFence() {
        if ( fenceAhead() ) {
            turnLeft();
            move();
            turnRight();
            move();
            move();
            turnRight();
            move();
            turnLeft();
        }
    }
    
    public boolean grainAhead() {
        move();
        if (onGrain() == true) {
            turnRight();
            turnRight();
            move();
            turnRight();
            turnRight();
            return true;
        } else {
            turnRight();
            turnRight();
            move();
            turnRight();
            turnRight();
            return false;
        }
    }


    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step
            nrStepsTaken++;                 // increment the counter
            if ( borderAhead() ) {
                System.out.println("Can't take anymore steps");
            }else{
                System.out.println("Moved" + nrStepsTaken);
            }
        }
    }

    
    
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdge(){
        while( ! borderAhead() ){
            move();
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */
    
    public boolean canLayEgg( ){
      if( onEgg() ){
            return false;
        }else{
            return true;
            }
    }  
    
    /*
     * Checks if its possible to lay an egg, if yes it'll lay an egg
     */
    public void layEggIfPossible() {
        if ( canLayEgg() ) {
            layEgg();
        }
    }
    
    /*
     * Turns 180 degrees
     */
    public void turn180() {
        turnRight();
        turnRight();
    }
    
    /*
     * Checks if its on an egg and tells you
     */
    public void goToEgg() {
        while (!onEgg() ) {
            move();
        }
        System.out.println("Found an egg");
    }
    
    /*
     * Walks to the start of the world and faces to the end of the world
     */
    public void goBackToStartOfRowAndFaceBack() {
        turnRight();
        turnRight();
        while( ! borderAhead() ){
            move();
        }
        turnRight();
        turnRight();
    }
    
    /*
     * Walks to end of world but climbs over fences if there are any
     */
    public void walkToWorldEdgeClimbingOverFences() {
        while ( ! borderAhead() ) {
        if (fenceAhead() ) {
            climbOverFence();
        } else {
            move();
        }
        }
    }
    
    /* Picks up grain if there is grain and prints its coordinates
     * 
     */
    public void pickUpGrainsAndPrintCoordinates() {
            while ( ! borderAhead() ) {
            if ( onGrain() ) {
                System.out.println("X = " + getX() + ", Y = " + getY());
            }
            move();
        }
        if ( onGrain() ) {
                System.out.println("X = " + getX() + ", Y = " + getY());
            }
    }
    
    /*
     * Lets Mimi take one step backwards
     */
    public void stepOneCellBackwards() {
        turnRight();
        turnRight();
        move();
        turnLeft();
        turnLeft();
    }
    
    /*
     * Lays egg in nest if there isnt a egg in that nest
     */
    public void worldEmptyNestsTopRow() {
        while ( ! borderAhead() ) {
            if ( onNest() && ! onEgg() ) {
                layEgg();
            }
            move();
        }
        if ( onNest() && ! onEgg() ) {
            layEgg();
        }
    }
    
    public void walkToNestClimbingOverFence() {
        while ( ! onNest() ) {
            if ( fenceAhead() ) {
                climbOverFence();
            } else {
                move();
            }
        }
        if ( onNest() ) {
        layEgg();
        }
    }
    /*
     * Walks around a non-squared fenced area
     */
    public void walkAroundFencedArea() {
        while ( ! onEgg() ) {       
            turnRight();
            while ( fenceAhead() ) {
                turnLeft();
            }
            move();
        }
    }
    /*
     * Walks over eggs to go to nest
     */
    public void eggToNestTrails() {
        move();
        while (! onNest() ) {
            if (! nestAhead() ) {
                if ( eggAhead() ) {
                   move(); 
                } else {
                    turnRight();
                    if ( eggAhead() ) {
                       move();
                    } else {
                        turnLeft();
                        turnLeft();
                    }
                }
            } else {
                move();
            }
        }
    }
    /*
     * Finds its way thru maze
     */
    public void findWayThruMaze() {
        while (! onNest() ) {
            turnRight();
            if (canMove() ) {
                move();
            } else {
                turnLeft();
                if (canMove() ) {
                move();
                } else {
                    turnLeft(); 
                }
            }
        }
    }
    
    /*
     * variabele bla, ff wachten
     */
    
    /*
     * Faces east if not facing east
     */
    public void faceEast() {
        while (getDirection() != EAST) {
            turnRight();
        }
    }
    
    public void faceDirection(int direction) {
        while (getDirection() != direction) {
            turnRight();
        }
    }
    
    /*
     * checks if coords are valid
     */
    public boolean validCoordinates(int x, int y) {
        int worldBorder = getWorld().getWidth();
        if (x < worldBorder || y < worldBorder) {
            return true;
        } else {
            return false;
        }
    }
    
    /*
     * moves to given coords
     */
    public void goToLocation(int x, int y) {
        if (validCoordinates(x,y) == true) {
            int xAxis = x - getX();
            int yAxis = y - getY();
            System.out.println("X: " + xAxis);
            System.out.println("Y: " + yAxis);
            if (xAxis < 0) {
                faceDirection(WEST);
                for (int i = xAxis; i < 0; i++) {
                move();
            }
            } else {
                faceDirection(EAST);
                for (int i = 0; i < xAxis; i++) {
                move();
                }
            }
        
            if (yAxis < 0) {
                faceDirection(NORTH);
                for (int i = yAxis; i < 0; i++) {
                move();
                }
            } else {
                faceDirection(SOUTH);
                for (int i = 0; i < yAxis; i++) {
                move();
                }
            }
        } else if (validCoordinates(x,y) == false) {
            System.out.println("Invalid Coordinates");
        }
    }
    
    /*
     * lays trail of eggs
     */
    public void layTrailOfEggs(int n) {
        for (int i = 0; i < n; i++) {
            layEgg();
            move();
        }
    }
    
    /*
     * Counts eggs in one row
     */
    public int countEggsInRow() {
        int totalEggs = 0;
        while (!borderAhead()) {
            if (onEgg()) {
                totalEggs++;
            }
            move();
        }
        if (onEgg()) {
            totalEggs++;
        }
        goBackToStartOfRowAndFaceBack();
        return totalEggs;
    }
    
    /*
     * counts all eggs in world
     */
    public int countEggsInWorld() {
        int worldBorder = getWorld().getWidth();
        int totalEggs = 0;
        if (getX() != 0 || getY() != 0) {
            goToLocation(0,0);
            faceEast();
        }
        for (int i = 0; i < worldBorder; i++) {
            while (!borderAhead()) {
                if (onEgg()) {
                    totalEggs++;
                }
                move();
            }
            if (onEgg()) {
                totalEggs++;
            }
            goBackToStartOfRowAndFaceBack();
            turnRight();
            move();
            turnLeft();
        }
        return totalEggs;
    }
    /*
     * shows which row has most eggs
     */
    public int mostEggsInRow() {
        int worldBorder = getWorld().getWidth();
        int maxEggsRow = 0;
        int bestRow = 0;
        if (getX() !=0 || getY() != 0) {
            goToLocation(0,0);
            faceEast();
        }
        for (int row = 0; row < worldBorder; row++) {
            int eggsInRow =0;
            while (!borderAhead()) {
                if (onEgg()) {
                    eggsInRow++;
                }
                move();
            }
            if (onEgg()) {
                eggsInRow++;
            }
            if (eggsInRow > maxEggsRow) {
                maxEggsRow = eggsInRow;
                bestRow = row;
            }
            goBackToStartOfRowAndFaceBack();
            turnRight();
            move();
            turnLeft();
        }
        System.out.println("Best Row = number " + bestRow);
        return bestRow;
    }
    
    /*
     * Creates monument (stairs) of eggs
     */
    public void eggMonumentCorner() {
        if (getX() !=0 || getY() != 0) {
            goToLocation(0,0);
            faceEast();
        }
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();
        for (int row = 0; row < worldHeight; row++) {
            int eggsInRow = row + 1;
            for (int col = 0; col < eggsInRow; col++) {
                int x = 0 + col;
                int y = 0 + row;
                if (x < worldWidth && y < worldHeight) {
                    goToLocation(x, y);
                    layEgg();
                    }
                }
            }
        goToLocation(0,0);
        faceEast();
        }
    /*
     * Creates monument (stairs) of eggs
     */
    public void eggMonumentCornerBy2() {
        if (getX() !=0 || getY() != 0) {
            goToLocation(0,0);
            faceEast();
        }
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();
        for (int row = 0; row < worldHeight; row++) {
            int eggsInRow = 1;
            for (int i = 0; i < row; i++) {
                eggsInRow = eggsInRow * 2;
            }   
            for (int col = 0; col < eggsInRow; col++) {
                int x = 0 + col;
                int y = 0 + row;
                if (x < worldWidth && y < worldHeight) {
                    goToLocation(x, y);
                    layEgg();
                    }
                }
            }
        goToLocation(0,0);
        faceEast();
        }
    /*
     * Creates piramide of eggs
     */
    public void eggPiramide() {
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();
        int startX = getX();
        int startY = getY();
        for (int row = 0; row < worldHeight; row++) {
            int eggsInRow = row * 2 + 1;
            int startColumn = startX - row;
            for (int col = 0; col < eggsInRow; col++) {
                int x = startColumn + col;
                int y = startY + row;
                if (x < worldWidth && y < worldHeight) {
                    goToLocation(x, y);
                    layEgg();
                }
            }
        }
        goToLocation(0, 0);
        faceEast();
    }
    /*
     * Gives average per row
     */
    public double averageEggRow() {
        int worldHeight = getWorld().getHeight();
        int totalEggs = countEggsInWorld();
        double average = (double) totalEggs / worldHeight;
        return average;
    }
    
    public void parityBit() {
        int worldWidth = getWorld().getWidth();
        int worldHeight = getWorld().getHeight();
        
        for (int row = 0; row < worldHeight; row++) {
            goToLocation(0, row);
            faceEast();
            int eggCount = 0;
            while (!borderAhead()) {
                if (onEgg()) {
                    eggCount++;
                }
                move();
            }
            if (onEgg()) {
                eggCount++;
            }
            if (eggCount % 2 != 0 ) {
                layEggIfPossible();
            }
        }
        for (int col = 0; col < worldWidth; col++) {
            goToLocation(col, 0);
            faceDirection(SOUTH);
            int eggCount = 0;
            while (!borderAhead()) {
                if (onEgg()) {
                    eggCount++;
                }
                move();
            }
            if (onEgg()) {
                eggCount++;
            }
            if (eggCount % 2 != 0 ) {
                layEggIfPossible();
            }
        }
        goToLocation(0,0);
        faceEast();
    }
}
    
    

