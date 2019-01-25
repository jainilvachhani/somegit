public class ArrayDeque<T> implements Deque<T>{
    private int first;
    private int last;
    private T items[];
    private int size ;


    public ArrayDeque(){
        items = (T[]) new Object[8];
        first = 3;
        last = 4;
        size = 0;
    }

    private void resize(int capacity){
        T a[] = (T[]) new Object[capacity];
        int i,curr = Math.floorMod(first+1,items.length);
        for(i=0;i<size;i++){
            a[i] = items[curr];
            curr=Math.floorMod(curr+1,items.length);
        }
        items = a;
        first = items.length-1;
        last = size;
    }

    @Override
    public void addFirst(T x){
        if(size==items.length){
            resize(size*2);
        }
        items[first] = x;
        first = Math.floorMod(first-1,items.length);
        size++;
    }

    @Override
    public void addLast(T x){
        if(size==items.length){
            resize(size*2);
        }
        items[last] = x;
        last = Math.floorMod(last+1,items.length);
        size++;
    }

    @Override
    public T removeFirst(){
        if(size==0){
            return null;
        }
        int curr = Math.floorMod(first+1,items.length);
        T result = items[curr];
        items[curr] = null;
        first = curr;
        size--;
        if(items.length>16 && size<(int) items.length*0.25){
            resize(items.length/2);
        }
        return result;
    }

    @Override
    public T removeLast(){
        if(size==0){
            return null;
        }
        int curr = Math.floorMod(last-1,items.length);
        T result = items[curr];
        items[curr] = null;
        last = curr;
        size--;
        if(items.length>16 && size<(int) items.length*0.25){
            resize(items.length/2);
        }
        return result;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if(index>size || index<0){
            return null;
        }
        return items[Math.floorMod(first+1+index,items.length)];
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public void printDeque(){
        int i,cnt;
        for(i=Math.floorMod(first+1,items.length),cnt=0;cnt<size;i=Math.floorMod(i+1,items.length),cnt++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
}