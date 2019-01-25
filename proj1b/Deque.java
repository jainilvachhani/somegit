public interface Deque<T>{

    void addFirst(T t);
    void addLast(T t);
    boolean isEmpty();
    int size();
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);

}
