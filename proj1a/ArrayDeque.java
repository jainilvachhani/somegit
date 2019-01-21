public class ArrayDeque<T>{
    private int first = 4;
    private int last = 4;
    private float usageFactor = 0;
    private T[] items;
    private int size;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity){
        System.out.println("in reisze");
        T[] a = (T[]) new Object[capacity];
        int i,j=0;
        for(i=first;i<size-1;i++){
            a[j] = items[i];
            j++;
        }
        for(i=0;i<last;i++){
            a[j] = items[i];
            j++;
        }
        j=0;
        T[] b = (T[]) new Object[capacity];
        for(i=capacity/4;i<capacity/4 + size;i++){
            b[i] = a[j];
            System.out.println(i + " " + b[i] + " " );
            j++;
        }
        System.out.println();
        items = b;
        first = capacity/4;
        last = capacity/4 + size;
    }

    public void addLast(T x){
        if(size==items.length){
            resize(size*2);
        }
        System.out.println("size is  "+items.length + " last "+ last);
        items[last] = x;
        last = (last+1)%items.length;
        size++;
    }

    public void addFirst(T x){

        if(size==items.length){
            resize(size*2);
        }
        first--;
        if(first<0){
            first = items.length;
        }
        items[first] = x;
        size++;
    }

    public T removeFirst(){
        first++;
        if(first==items.length){
            first = 0;
        }
        if(items[first]==null){
            return null;
        }
        usageFactor = items.length/size;
        System.out.println("uagege fcca " + usageFactor);
        if(usageFactor>4){
            System.out.println("in usage ");
            resize(items.length/2);
        }
        T item = items[first];
        items[first] = null;
        size--;
        return  item;
    }

    public T removeLast(){
        if(items[last]==null){
            return null;
        }
        usageFactor = items.length/size;
        System.out.println("uagege fcca " + usageFactor);
        System.out.println("uagege fcca " + usageFactor);
        if(usageFactor>4){
            resize(items.length/2);
        }
        if(last-1==0){
            T item = items[items.length-1];
            items[items.length-1] = null;
            last = items.length;
            size--;
            return item;
        }
        else{
            T item = items[last-1];
            items[last] = null;
            last--;
            size--;
            return item;
        }

    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        System.out.println("first " +first + " last "+ last + " size " +size );
        int i,cnt=0;
        /*for(i=first+1;cnt<size;i++){
            if(i==items.length-1){
                break;
            }
            System.out.print(items[i] + " ");
            cnt++;
        }
        for(i=0;i<=last && cnt<size;i++){
            if(cnt==size-1){
                break;
            }
            System.out.print(items[i] + " ");
            cnt++;
        }*/
        for(i=0;i<items.length;i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public int size(){
        return size;
    }

    public T get(int index){
        int i,cnt=0;
        for(i=first+1;cnt<size;i++){
            if(index==0){
                return items[i];
            }
            if(i==items.length-1){
                break;
            }
            cnt++;
            index--;
        }
        for(i=0;i<=last && cnt<size;i++){
            if(index==0){
                return items[i];
            }
            cnt++;
            index--;
        }
        return null;
    }

    public static void main(String args[]){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
//        arrayDeque.printDeque();
        arrayDeque.addLast(1);
  //      arrayDeque.printDeque();

        arrayDeque.addLast(2);
    //    arrayDeque.printDeque();

        arrayDeque.addLast(3);

      //  arrayDeque.printDeque();
        arrayDeque.addLast(4);
     //   arrayDeque.printDeque();

        arrayDeque.addLast(5);
      //  arrayDeque.printDeque();

        arrayDeque.addLast(6);

        arrayDeque.printDeque();
        arrayDeque.addLast(7);
        arrayDeque.printDeque();
        System.out.println(arrayDeque.removeFirst());
        //System.out.println(arrayDeque.removeLast());
    }
}