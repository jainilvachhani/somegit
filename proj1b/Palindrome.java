public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        int i;
        for(i=0;i<word.length();i++){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindrome(Deque<Character> deque){
        if(deque.size()<=1){
            return true;
        }
        else {
            return deque.removeFirst().equals(deque.removeLast()) && isPalindrome(deque);
        }
    }

    public boolean isPalindrome(String word){
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc){
        if(deque.size()<=1){
            return true;
        }
        else {
            return cc.equalChars(deque.removeFirst(),deque.removeLast()) && isPalindrome(deque,cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        return isPalindrome(deque,cc);
    }
}
