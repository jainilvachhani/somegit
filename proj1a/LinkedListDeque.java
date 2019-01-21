public class LinkedListDeque<Items> {
    private class StuffNode {
        public Items item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(Items i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode first;
    private StuffNode last;
    private int size;

    public LinkedListDeque(){
        first = null;
        last = null;
        size = 0;
    }


    public Items removeFirst(){
        Items temp = first.item;
        if(size==1){
            first = null;
            last = null;
            size--;
            return temp;
        }
        else{
            first = first.next;
            first.prev = null;
            size--;
            return temp;
        }

    }

    public Items removeLast(){
        Items temp = last.item;
        if(size==1){
            first = null;
            last = null;
            size--;
            return temp;
        }
        else {
            last = last.prev;
            last.next = null;
            size--;
            return temp;
        }
    }

    /** Adds x to the front of the list. */
    public void addFirst(Items x) {
        size++;
        StuffNode temp = first;
        if(temp == null) {
            first = new StuffNode(x, null, null);
            last = first;
            return;
        }
        temp = new StuffNode(x,this.first,null);
        temp.next.prev = temp;
    }

    public Items get(int i) {
        int j;
        StuffNode temp = first;
        for(j=0;j<i;j++){
            temp = temp.next;
        }
        return temp.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(Items x) {
        size++;
        StuffNode temp = last;
        if(temp == null) {
            this.first = new StuffNode(x, null, null);
            last = first;
            return;
        }
        temp = new StuffNode(x,null,this.last);
        this.last = temp;
        temp.prev.next= temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        StuffNode temp = first;
        while(temp!=null){
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}