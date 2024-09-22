package box;

/**
 * This interface represents a set of non-overlapping axis-aligned boxes.
 */
public interface BoxSet {

    void add(int x,int y,int width,int height) throws IllegalArgumentException;


    void subtract(int x, int y, int width, int height) throws IllegalArgumentException;


    int [][] getBoxes();

    int size();
}
