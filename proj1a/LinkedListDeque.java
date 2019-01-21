public class LinkedListDeque<T> {
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode first;
    private StuffNode last;
    private static int size;

    public  LinkedListDeque(){
        first = null;
        last = null;
        size = 0;
    }


    public T removeFirst(){
        if(first==null){
            return null;
        }
        T temp = first.item;
        if(size==1){
            first = null;
            last = null;
            size--;
            return temp;
        }
        else{
            StuffNode x = first;
            first = first.next;
            x.prev = null;
            size--;
            return temp;
        }

    }

    public T removeLast(){
        if(last==null){
            return null;
        }
        T temp = last.item;
        if(size==1){
            first = null;
            last = null;
            size--;
            return temp;
        }
        else {
            StuffNode x = last;
            last = last.prev;
            x.next = null;
            size--;
            return temp;
        }
    }

    public T getRecursive(int index, StuffNode temp){
        if(temp==null){
            return null;
        }
        if(index==0){
            return temp.item;
        }
        return getRecursive(index-1,temp.next);
    }

    public T getRecursive(int index){
        StuffNode temp = first;
        return getRecursive(index,temp);
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        size++;
        StuffNode temp = first;
        if(temp == null) {
            first = new StuffNode(x, null, null);
            last = first;
            return;
        }
        temp = new StuffNode(x,this.first,null);
        this.first = temp;
        temp.next.prev = temp;
    }

    public T get(int i) {
        if(i>=size){
            return null;
        }
        int j;
        StuffNode temp = first;
        for(j=0;j<i;j++){
            temp = temp.next;
        }
        return temp.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(T x) {
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