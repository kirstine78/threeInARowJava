/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threerow;

import javax.swing.JOptionPane;

/**
 *
 * @author Kirsti
 */
public class ThreeRow 
{
    // constant, the amount needed in a row
    final static int IN_A_ROW_SUCCESS = 3;
    
    final static String GRID_CHARACTER_DEFAULT  = "?";
    final static String GRID_CHARACTER_1        = "X";
    final static String GRID_CHARACTER_2        = "O";
    
    // minimum should be grid of 3 x 3
    final static int ROWS       = 4;
    final static int COLUMNS    = 5;
    final static int FULL_GRID  = ROWS * COLUMNS;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        // declare variables
        int counter = 0;        
        boolean keepPlaying = true;  // flag
        boolean hasLost = false;  // flag
        
        // get an 2D array
        String[][] grid = getTwoDimensionalArray(ROWS, COLUMNS);      
        
        // play untill board is full or losing
        while (keepPlaying)
        {
//            JOptionPane.showMessageDialog(null, "BOARD\n\n" + displayTwoDimensionalArray(grid));
            
            boolean positionOk = false;
            String character = "";
            int positionX = 0;
            int positionY = 0;
            
            // keep asking for position if not free
            while (!positionOk)
            {
                // prompt user to pick a position
                positionX = Integer.parseInt(JOptionPane.showInputDialog(null, "BOARD\n\n" 
                                            + displayTwoDimensionalArray(grid) 
                                            + "\n\nPick X coordinate (0 - " 
                                            + (COLUMNS - 1) + ")" + " for: "
                                            + getCharacter(counter)));
                
                positionY = Integer.parseInt(JOptionPane.showInputDialog(null, "BOARD\n\n" 
                                            + displayTwoDimensionalArray(grid) 
                                            + "\n\nPick Y coordinate (0 - " 
                                            + (ROWS - 1) + ")" + " for: "
                                            + getCharacter(counter)));

                // check if free
                if (isPositionAvailable(grid, positionX, positionY))
                {
                    // update flag
                    positionOk = true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Sorry position is not free, try again");                    
                }                
            }
            
            // update grid
            updateGrid(grid, getCharacter(counter), positionX, positionY);
            
            // increment counter
            counter++;

            // check if 3 in a row
            if (isThreeInARowVersion2D(grid, GRID_CHARACTER_1) || isThreeInARowVersion2D(grid, GRID_CHARACTER_2))
            {
                // update flags
                keepPlaying = false;
                hasLost = true;             
            }
            
            // check if grid is full
            if (counter == FULL_GRID)
            {
                // update flag keepPlaying
                keepPlaying = false;                
            }            
        }
        
        if (hasLost)
        {
            JOptionPane.showMessageDialog(null, "You LOST\n\n" + displayTwoDimensionalArray(grid));               
        } 
        else
        {
            JOptionPane.showMessageDialog(null, "You WON\n\n" + displayTwoDimensionalArray(grid));     
        }
    }
    
    
    /***************** 1D array *******************/
    
    /**
     * Build an array of strings
     * @param length
     * @return Array of strings
     */
    public static String[] getArray(int length)
    {        
        String[] anArray = new String[length];
        
        // populate array
        for (int i = 0; i < length; i++)
        {
            anArray[i] = GRID_CHARACTER_DEFAULT;
        }
        
        return anArray;
    }
    
    
    /**
     * 
     * @param someArray
     * @return String build from elements values in array
     */
//    public static String displayArray(String[] someArray)
//    {        
//        // string to return
//        String str = "";
//        
//        // loop through array
//        for(String value : someArray)
//        {
//            // add to str
//            str += value + " ";            
//        }
//        return str;
//    }  
    
    
    /**
     * Checks if there is 3 in a row of a specific character that is passed as argument
     * @param someArray the array being searched through
     * @param someCharacter the character we search if there is 3 in a row of
     * @return true if there is 3 in a row of the character passed in, else false
     */
    public static boolean isThreeInARowHorizontally(String[] someArray, String someCharacter)
    {        
        int arrayLength = someArray.length;
        
        // boolean to return
        boolean isInARowPresent = false;
                    
        // loop through array; make sure you only check until and including third last element
        for(int i = 0; i <= arrayLength - IN_A_ROW_SUCCESS; i++)
        {            
//            System.out.println("Start at column: " + i);
//            
//            System.out.println(someArray[i] + " " + someArray[i+1] + " " + someArray[i+2]);
//            System.out.println();
            
            if (someArray[i].equals(someCharacter) && someArray[i + 1].equals(someCharacter) && someArray[i + 2].equals(someCharacter))
            {
                isInARowPresent = true;
                return isInARowPresent;
            }
        }
        return isInARowPresent;        
    }
    
    
    /**************** 2D array ********************/
    
    /**
     * 
     * @param rows
     * @param columns
     * @return grid, array of arrays
     */
    public static String[][] getTwoDimensionalArray(int rows, int columns)
    {    
        String[][] aTwoDimensionalArray = new String[rows][columns];
        
//        String[] anArray = new String[columns];
        
        // populate 2D array
        for (int i = 0; i < rows; i++)
        {
            // get a 1D array
            String[] anArray = getArray(columns);
            
            // add 1D array to the 2D array
            aTwoDimensionalArray[i] = anArray;         
        }
        
        return aTwoDimensionalArray;
    }
    
    
    /**
     * 
     * @param someTwoDimensionalArray which shall be a non-ragged array and minimum 3 x 3
     * @return grid values represented as a string
     */
    public static String displayTwoDimensionalArray(String[][] someTwoDimensionalArray)
    {        
        // assumining that the grid is always 
        
        // string to return
        String str = "";
        
        int rowCount = someTwoDimensionalArray.length;
        
        int columnsCount = someTwoDimensionalArray[0].length;
        
        // build grid from 2D array
        for (int i = 0; i < rowCount; i++)
        {
            for (int j = 0; j < columnsCount; j++)
            {
                // add to the string
                str += someTwoDimensionalArray[i][j] + " ";
            }       
            str += "\n";
        }
        
        return str;
    } 
    
    
    /**
     * 
     * @param some2DArray
     * @param someCharacter to find three in a row of
     * @return true if there is three in a row vertically or horizontally
     */
    public static boolean isThreeInARowVersion2D(String[][] some2DArray, String someCharacter)
    {         
//        System.out.println("CHECKING HORIZONTALLY");
//        System.out.println();
        
        // check if there is 3 in a row horizontally for all rows in the grid
        for(int i = 0; i < some2DArray.length; i++)
        {
//            System.out.println("row: " + i);
//            System.out.println();
            
            if (isThreeInARowHorizontally(some2DArray[i], someCharacter))
            {
                return true;                
            }
        }
        
        // check if there is 3 in a row vertically in the grid
        return isThreeInARowVertically(some2DArray, someCharacter);           
    }
    
    
    /**
     * 
     * @param some2DArray a grid
     * @param someCharacter a character you want to see if there is three in a row of
     * @return true if three in a row was found in a row in a grid
     */
    public static boolean isThreeInARowVertically(String[][] some2DArray, String someCharacter)
    {        
//        System.out.println("CHECKING VERTICALLY");
//        System.out.println();
        
        // get rows amount in grid
        int arrayLength = some2DArray.length;
        
        // boolean to return
        boolean isInARowPresent = false;
        
        // loop through outer array; ; make sure you only check until and including third last row amount
        for(int i = 0; i <= arrayLength - IN_A_ROW_SUCCESS; i++)
        {
//            System.out.println("row number: " + i);
//            System.out.println();
            
            // loop through inner array
            for(int j = 0; j < some2DArray[i].length; j++)
            {
//                System.out.println("column number: " + j);
//
//                System.out.println(some2DArray[i][j]);
//                System.out.println(some2DArray[i+1][j]);
//                System.out.println(some2DArray[i+2][j]);
                
                if (some2DArray[i][j].equals(someCharacter) && some2DArray[i+1][j].equals(someCharacter) && some2DArray[i+2][j].equals(someCharacter))
                {
                    isInARowPresent = true;
                    return isInARowPresent;
                }
            }
        }
        
        return isInARowPresent;        
    }
    
    /**
     * Update grid on certain x, y position to a certain character
     * @param some2DArray
     * @param character
     * @param xPos
     * @param yPos 
     */
    public static void updateGrid(String[][] some2DArray, String character, int xPos, int yPos)
    {
        some2DArray[yPos][xPos] = character;
    }
    
    
    /**
     * checks if position x, y is free
     * @param some2DArray
     * @param xPos
     * @param yPos
     * @return true if position is free, else false
     */
    public static boolean isPositionAvailable(String[][] some2DArray, int xPos, int yPos)
    {
        boolean isFree = false;
        
        // check if position x, y has GRID_CHARACTER_DEFAULT
        if (some2DArray[yPos][xPos].equals(GRID_CHARACTER_DEFAULT))
        {
//            System.out.println("Position is FREE");
//            System.out.println();      
            isFree = true;
        }
        else
        {
//            System.out.println("Position is OCCUPIED");
//            System.out.println();                    
        }
        return isFree;
    }
    
    
    public static String getCharacter(int number)
    {
        // decide which character
        if (number % 2 == 0)  // even
        {
            return GRID_CHARACTER_1;
        }
        else  // odd
        {
            return GRID_CHARACTER_2;            
        }        
    }
    
}
