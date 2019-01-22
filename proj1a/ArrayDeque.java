public class ArrayDeque<T>{
    private int first = 0;
    private int last = 7;
    private T items[];
    private int size ;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        int r = items.length - first;
        int n = items.length;
        T a[] = (T[]) new Object[capacity];
        System.arraycopy(items,first,a,0,r);
        System.arraycopy(items,0,a,r,first);
        items = a;
        first = 0;
        last = n;
    }

    public void addFirst(T x){
        first = (first-1)% (items.length-1);
        items[first] = x;
        size++;
        if(first==last){
            resize(size*2);
        }
    }

    public void addLast(T x){
        last = (last+1)%(items.length-1);
        items[last] = x;
        size++;
        if(first==last){
            resize(size*2);
        }
    }

    public T removeFirst(){
        T result = (T) items[first];
        items[first] = null;
        first = (first+1)%(items.length-1);
        size--;
        return result;
    }

    public T removeLast(){
        last = (last-1)%(items.length-1);
        T result = (T) items[last];
        items[last] = null;
        size--;
        return result;
    }

    public int size(){
        return size;
    }

    public T get(int index){
        return items[(first+index)%(items.length-1)];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int i,j;
        for(i=first,j=0;j<size;i = (i+1)%(items.length-1),j++){
            System.out.print(items[i]);
        }
        System.out.println();
    }

}