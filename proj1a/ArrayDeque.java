public class ArrayDeque<T>{
    private int capacity = 8;
    private int first = capacity/2;
    private int last = capacity/2;
    private float usageFactor = 0;
    private boolean changeFirst = false, changeLast = false;
    private T[] items;
    private int size;
    public ArrayDeque(){
        items = (T[]) new Object[capacity];
        size = 0;
    }

    private void resize(int capacity){
        //System.out.println("in reisze");
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
            //System.out.println(i + " " + b[i] + " " );
            j++;
        }
        //System.out.println();
        items = b;
        first = capacity/4;
        last = capacity/4 + size-1;
        changeFirst = false;
        changeLast = false;
      //  System.out.println("false f");
    }

    public void addLast(T x){
        //System.out.println("trye f");
        if(size==items.length){
            resize(size*2);
        }
        //System.out.println("size is  "+items.length + " last "+ last);
        items[last] = x;
        last = (last+1)%items.length;
        size++;
        changeLast = true;

    }

    public void addFirst(T x){
        if(size==items.length){
            resize(size*2);
        }

        if(first<0){
            first = items.length-1;
        }
        items[first] = x;
        first--;
        size++;
        changeFirst = true;

    }

    public T removeFirst(){
        if(changeFirst){
        //    System.out.println("in here");
            first++;
        }
        if(first==items.length){
            first = 0;
        }
        //System.out.println(first);

        if(items[first]==null || size==0){
            return null;
        }
        usageFactor = items.length/size;
        //System.out.println("uagege fcca " + usageFactor);
        if(usageFactor>4 && items.length>16){
          //  System.out.println("in usage ");
            resize(items.length/2);
        }
        T item = items[first];
        items[first] = null;
        size--;
        changeFirst = true;
        return  item;
    }

    public T removeLast(){
        //System.out.println(changeLast);
        if(changeLast){
            //System.out.println("here");
            last--;
        }
        if(last==0){
            last = items.length-1;
        }
        if(items[last]==null || size==0){
            return null;
        }
        usageFactor = items.length/size;
       // System.out.println("uagege fcca " + usageFactor);
       // System.out.println("uagege fcca " + usageFactor);
        if(usageFactor>4 && items.length>16){
            resize(items.length/2);
        }
        T item = items[last];
        items[last] = null;
        size--;
        changeLast = true;
        return item;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
      //  System.out.println("first " +first + " last "+ last + " size " +size );
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
        for(i=first;cnt<size;i++){
            if(index==0){
                return items[i];
            }
            cnt++;
            index--;
            if(i==items.length-1){
                break;
            }

        }
        //System.out.println(index);
        for(i=0;i<=last && cnt<size;i++){
            if(index==0){
                return items[i];
            }
            cnt++;
            index--;
        }
        return null;
    }

   /* public static void main(String args[]){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
        arrayDeque.size();
        arrayDeque.addFirst(2);

        System.out.println(arrayDeque.removeFirst());
    }*/
}