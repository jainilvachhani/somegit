import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        int i;
        String msg = "";
        msg += "removeFist()\n";
        assertEquals(msg,sad.removeFirst(),ads.removeFirst());
        for(i=0;i<11;i++){
            Integer add = StdRandom.uniform(100);
            sad.addLast(add);
            ads.addLast(add);
            msg += "addLast(" + add + ")\n";
            sad.addFirst(add);
            ads.addFirst(add);
            msg += "addFirst(" + add + ")\n";
            msg += "removeFirst()\n";
            assertEquals(msg,sad.removeFirst(),ads.removeFirst());
        }
        int size = ads.size();
        for(i=0;i<size;i++){
            Integer position = StdRandom.uniform(100);
            position = position % size;
            msg += "get(" + position + ")\n";
            assertEquals(msg,ads.get(position),sad.get(position));
        }
    }
}
