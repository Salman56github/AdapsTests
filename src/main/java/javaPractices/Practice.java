package javaPractices;


import java.util.*;


public class Practice {

    public static void ReverseOfString(){
        String name = "Java";
        String temp = "";
        for (int i=name.length()-1;i>=0;i--){
            temp = temp+ name.charAt(i);
        }
        System.out.println(temp);
    }

    public static void CheckPalindrome(){
        String word = "124";
        String temp = "";

        for (int i=word.length()-1;i>=0;i--){
            temp = temp + word.charAt(i);
        }

        if (word.equalsIgnoreCase(temp)){
            System.out.println("palindrome");
        } else System.out.println("Not a palindrome");

    }

    public static void FindDuplicateElement(){
        String [] str = {"java","C#","java","python"};
        ArrayList list = new ArrayList();

        for (String s : str){
            list.add(s);
        }
    }

    public static void RemoveDuplicates(){
        String word = "automation";
        String main = "";
        String temp = "";
        for (char c : word.toCharArray()){
            if (temp.indexOf(c)==-1){
                temp = temp+c;
            } else {
                main = main+c;
            }
        }
        System.out.println(main);
    }

    public static void CountWordsInSentence(){
        String word = "kdfk dsfhs dshiod";
        int count=0;
        for(char c : word.toCharArray()){
            if (c!=' '){
                count++;
            }
        }
        System.out.println(count);
    }

    public static void CountCharInSentence(){
        String word = "automation";
        HashMap<Character,Integer> map = new HashMap();

        for(char c: word.toCharArray()){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }
            else{
                map.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> c : map.entrySet()){
            System.out.println(c);
        }
    }

    public static void CountVowelsAndConsonants(){
        String word = "salman";
        int vowels = 0;
        int consonants = 0;

        for(char c: word.toCharArray()){
            if("aeiou".indexOf(c)!=-1){
                vowels++;
            }
            else{
                consonants++;
            }
        }
        System.out.println("vowels "+ vowels);
        System.out.println("consonants "+ consonants);
    }


    public static void main(String[] args) {

    }
}
