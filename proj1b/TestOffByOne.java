import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.
    @Test
    public void testOffByOne(){
        assertTrue(offByOne.equalChars('x','y'));
        assertTrue(offByOne.equalChars('y','x'));
        assertFalse(offByOne.equalChars('x','x'));
        assertFalse(offByOne.equalChars('X','y'));
        assertTrue(offByOne.equalChars('1','2'));
    }
}
