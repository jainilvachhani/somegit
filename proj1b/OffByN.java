public class OffByN implements CharacterComparator{

    int off;

    public OffByN(int off){
        this.off = off;
    }

    public boolean equalChars(char x, char y){
        return (x==y-off) || (y==x-off);
    }
}