// 해싱 자리수 접기 알고리즘
import java.io.*;
import java.util.*;


// Node 처리
class Node{
    public String[] key;
    public String[] value;
    public Node(){
        key = new String[0];
        value = new String[0];
    }
    public Node(int size){
        key = new String[size];
        value = new String[size];
        for(int i=0;i<size;i++){
            value[i] = "";
            key[i] = Integer.toString(i);
        }
    }
    // Key를 이용한 Value 출력
    public String get(String Key){
        for(int i=0;i<this.key.length;i++){
            if(this.key[i].equals(Key)){
                return this.value[i];
            }
        }
        return "해당 키와 일치하는 문자는 없습니다.";
    }
    // ASCII를 이용하여 값을 변환하여 저장
    public void set(String key,String value){
       int temp = 0;
       char[] keyArray = key.toCharArray();
       for(int i=0;i<keyArray.length;i++){
           temp  += (int)keyArray[i]; // key 변환
       }
       this.key[temp%this.key.length] = key;
       this.value[temp%this.key.length] = value;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int cases , sizes;
        String value, key;
        Node Node = new Node();
        while(true){
            System.out.print(" 1 : 사이즈 조절 , 2: 입력 , 3 : 출력 : ");
            cases = scanner.nextInt();
            switch(cases){
                case 1:
                    System.out.print("사이즈를 정해주세요 : ");
                    sizes = scanner.nextInt();
                    Node = new Node(sizes);
                break;
                case 2:
                    scanner.nextLine();
                    System.out.print("넣을 위치를 정해주세요 : ");
                    key = scanner.nextLine();
                    System.out.print("넣을 값을 정해주세요 : ");
                    value = scanner.nextLine();
                    Node.set(key,value);
                break;
                case 3:
                    scanner.nextLine();
                    System.out.print("찾을 위치를 정해주세요 : ");
                    key = scanner.nextLine();
                    value = Node.get(key);
                    System.out.println("키 : " + key + " 값 : " + value);
                break;

            }

        }
    }
}

