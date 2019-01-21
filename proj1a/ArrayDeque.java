public class ArrayDeque<T>{
    public int first = 0;
    public int last = 0;
    float usageFactor = 0;
    private T[] items;
    private int size;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        int i,j=0;
        for(i=first;i<size;i++){
            items[i] = a[j];
            j++;
        }
        for(i=0;i<first;i++){
            items[i] = a[j];
            j++;
        }
        j=0;
        T[] b = (T[]) new Object[capacity];
        for(i=(capacity/2);i<(capacity/2) + size;i++){
            b[i] = a[j];
            j++;
        }
        items = b;
        first = (capacity/2)-1;
        last = (capacity/2) + size;
    }

    public void addLast(T x){
        if(first==(last+1)%items.length){
            resize(size*2);
        }
        items[(last+1)%items.length] = x;
        last = (last+1)%items.length;
        size++;
    }

    public void addFirst(T x){
        first--;
        if(first<0){
            first = items.length;
        }
        if(last == first){
            resize(size*2);
        }
        items[first] = x;
        size++;
    }

    public T removeFirst(){

        if(items[first]==null){
            return null;
        }
        usageFactor = size/items.length;
        if(usageFactor <0.25){
            resize(items.length/2);
        }
        T item = items[first];
        first++;
        size--;
        return  item;
    }

    public T removeLast(){
        if(items[last]==null){
            return null;
        }
        usageFactor = size/items.length;
        if(usageFactor <0.25){
            resize(items.length/2);
        }
        T item = items[last];
        last--;
        size--;
        return item;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int i;
        for(i=first;i!=last;i++){
            System.out.println(items[i] + " ");
            if(i==size-1){
                break;
            }
        }
        for(i=0;i<last;i++){
            System.out.println(items[i] + " ");
            if(i==size-1){
                break;
            }
        }
    }

    public int size(){
        return size;
    }

    public T get(int index){
        int i;
        for(i=first;i!=last;i++){
            index--;
            if(index==0){
                return items[i];
            }
            if(i==size-1){
                break;
            }
        }
        for(i=0;i<last;i++){
            index--;
            if(index==0){
                return items[i];
            }
            if(i==size-1){
                break;
            }
        }
        return null;
    }
}