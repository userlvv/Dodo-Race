import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.1 -- 29-07-2017
 */
public class MyDodo extends Dodo
{
    private int score1 = 0;
    private int score2 = 0;
    public MyDodo() {
        super( EAST );
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
     * Test if Dodo can move forward, 
     * i.e. there are no obstructions or end of world in the cell in front of her.
     * 
     * <p> Initial:   Dodo is somewhere in the world
     * <p> Final:     Same as initial situation
     * 
     * @return  boolean true if Dodo can move (thus, no obstructions ahead)
     *                  false if Dodo can't move
     *                      there is an obstruction or end of world ahead
     */
    public boolean canMove() {
        if ( borderAhead() || fenceAhead() ){
            return false;
        } else {
            return true;
        }
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
        }
    }

    /**
     * Places all the Egg objects in the world in a list.
     * 
     * @return List of Egg objects in the world
     */
    public List<Egg> getListOfEggsInWorld() {
        return getWorld().getObjects(Egg.class);
    }

    public List<Integer> createListOfNumbers() {
        return new ArrayList<> (Arrays.asList( 2, 43, 7, -5, 12, 7 ));
    }

    /**
     * Method for praciticing with lists.
     */
    public void practiceWithLists( ){
        List<Integer> listOfNumbers = createListOfNumbers();
        
        //the following is incorrect and is to be fixed in challenge 6.1c
        System.out.println("First element: " + listOfNumbers.get(1) ); 
    }

    public void practiceWithListsOfSurpriseEggs( ){
        List<SurpriseEgg>  listOfEggs = SurpriseEgg.generateListOfSurpriseEggs( 12, getWorld() );
    }
    
    public List<SurpriseEgg> makeListOfSurpriseEgg() {
        return SurpriseEgg.generateListOfSurpriseEggs(10, getWorld());
    }

    public void printCoordinatesOfEgg(Egg egg) {         
        System.out.println("X: " + egg.getX() + ", Y: " + egg.getY());     
    }
    
    public List<SurpriseEgg> makeListOfSurpriseEggAndPrintCoordinates() {
        List<SurpriseEgg> listOfEggs = makeListOfSurpriseEgg();
        for (SurpriseEgg egg : listOfEggs) {
            printCoordinatesOfEgg(egg);
        }
        return listOfEggs;
    }
    
    public List<SurpriseEgg> makeListOfSurpriseEggAndFindMostValued() {
        List<SurpriseEgg> listOfEggs = makeListOfSurpriseEgg();
        int highestValue = 0;
        
        for (SurpriseEgg egg : listOfEggs) {
            if (egg.getValue() > highestValue) {
                highestValue = egg.getValue();
                printCoordinatesOfEgg(egg);
                System.out.println("Valued: " + highestValue);
            }
        }
        return listOfEggs;
    }
    
    public void printAverageValueOfEggs() {
        List<SurpriseEgg> listOfEggs = makeListOfSurpriseEgg();
        double total = 0;
        for (SurpriseEgg egg : listOfEggs) {
            total += egg.getValue();
        }
        double avg = total / listOfEggs.size();
        System.out.println("Average: " + avg);
    }
    
    public void moveRandomly() {
        int myNrOfStepsTaken = 0;
        
        while (myNrOfStepsTaken < Mauritius.MAXSTEPS) {
            setDirection(randomDirection());
            if (!borderAhead() && !fenceAhead()) {
                step();
                myNrOfStepsTaken++;
                
                getScore(score1 + 1, score2); 
            }
        }
    }
    

    public void getScore(int score1, int score2) {
        this.score1 = score1;
        this.score2 = score2;        
    }
    
        public boolean validCoordinates(int x, int y) {
        int worldBorder = getWorld().getWidth();
        if (x < worldBorder || y < worldBorder) {
            return true;
        } else {
            return false;
        }
    }
    
        public void faceDirection(int direction) {
        while (getDirection() != direction) {
            turnRight();
        }
    }
    
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
    
    public void goToClosestEgg() {
        List<Egg> eggs = getListOfEggsInWorld();
        if (eggs.isEmpty()) {
            return;
        }
        Egg closest = eggs.get(0);
        int shortestDist = 1000;
        
        for (Egg e : eggs) {
            int distX = e.getX() - getX();
            int distY = e.getY() - getY();
            int totalDist = distX * distX + distY * distY;
            
            if (totalDist < shortestDist) {
                shortestDist = totalDist;
                closest = e;
            }
        }
        goToLocation(closest.getX(), closest.getY());
        pickUpEgg();
    }
    
    public void dodoRace() {
        int steps = 0;
        int score = 0;
        
        while (steps < 40) {
            List<Egg> eggs = getListOfEggsInWorld();
            if (eggs.isEmpty()) {
                break;
            }
            
            Egg bestEgg = eggs.get(0);
            int bestScore = -1;
            
            for (Egg e : eggs) {
                int distX = e.getX() - getX();
                int distY = e.getY() - getY();
                int totalDist = Math.abs(distX) + Math.abs(distY);
                
                if (totalDist == 0) {
                  continue;  
                }
                int valueScore = e.getValue() / totalDist;
                
                if (score > bestScore) {
                    bestScore = score;
                    bestEgg = e;
                }
            }
            int oldX = getX();
            int oldY = getY();
        
            goToLocation(bestEgg.getX(), bestEgg.getY());
            pickUpEgg();
            
            score += bestEgg.getValue();
            
            int moved = Math.abs(getX() - oldX) + Math.abs(getY() - oldY);
            System.out.println(moved);
            
            steps += moved;
            if (steps >= 40) {
                System.out.println("40 steps reached.");
                break;
            }
        }
        System.out.println("Score: " + score);
        System.out.println("Steps: " + steps);
    }
}
