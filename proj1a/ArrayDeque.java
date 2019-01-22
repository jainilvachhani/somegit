public class ArrayDeque<T>{
    private int first = 3;
    private int last = 4;
    private T items[];
    private int size ;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        int r = items.length - first;
        System.out.println(r+ "  r ");
        int n = items.length;
        T a[] = (T[]) new Object[capacity];
        System.arraycopy(items,first,a,0,r);
        System.arraycopy(items,0,a,r,first);
        items = a;
        first = 0;
        last = n;
    }

    public void addFirst(T x){
        first = (first-1)% (items.length);
        items[first] = x;
        size++;
        if(first==last){
            resize(size*2);
        }
    }

    public void addLast(T x){
        items[last] = x;
        last = (last+1)%(items.length);
        size++;
        if(first==last){
            resize(size*2);
        }
    }

    public T removeFirst(){
        first = (first+1)%(items.length);
        T result = (T) items[first];
        items[first] = null;
        size--;
        return result;
    }

    public T removeLast(){
        last = (last-1)%(items.length);
        T result = (T) items[last];
        items[last] = null;
        size--;
        return result;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        return items[(first+1+index)%(items.length)];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int i,j;
        /*for(i=first,j=0;j<size;i = (i+1)%(items.length-1),j++){
            System.out.print(items[i]);
        }*/
        for(i=0;i<items.length;i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[]){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
        arrayDeque.printDeque();
        arrayDeque.addLast(1);
        arrayDeque.printDeque();
        arrayDeque.addLast(2);
        arrayDeque.printDeque();
        arrayDeque.addLast(3);
        arrayDeque.printDeque();
        arrayDeque.addLast(4);
        arrayDeque.printDeque();
        arrayDeque.addLast(5);
        arrayDeque.printDeque();
        arrayDeque.addLast(6);
        arrayDeque.printDeque();
        arrayDeque.addLast(7);
        arrayDeque.printDeque();
        //System.out.println(arrayDeque.get(0));
        System.out.println(arrayDeque.removeFirst());
        System.out.println(arrayDeque.removeLast());
    }
}