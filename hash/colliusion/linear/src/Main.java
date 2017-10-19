import java.io.*;
import java.util.*;

// 열린 어드레싱 : 선형 검색
// Node 처리
class Node{
    public int[] key;
    public String[] value;
    public boolean[] full_Empty; // 유무 확인
    public Node(){
        key = new int[0];
        value = new String[0];
        full_Empty = new boolean[0];
    }
    public Node(int size){
        key = new int[size];
        value = new String[size];
        full_Empty = new boolean[size];
        for(int i=0;i<size;i++){
            value[i] = "";
            key[i] = -1;
            full_Empty[i] = false;
        }
    }

    public String get(int location){
        String returnValue = this.value[(location-1)%key.length];
        return returnValue;
    }

    public void set(int key,String value){
        // 해당된 해쉬 인덱스에 이미 값이 있을 경우 다른 레코드로 이동.
        boolean test = false;
        if(this.full_Empty[(key-1)%this.key.length] == false){
            this.key[(key-1)%this.key.length] = key;
            this.value[(key-1)%this.key.length] = value;
            this.full_Empty[(key-1)%this.key.length] = true;
        }
        else{
            int choice =  this.key[(key-1)%this.key.length];
            int temp = this.key[(key-1)%this.key.length];
            while(test == false) {
                if(this.full_Empty[temp] == false) {
                    this.key[temp] = key;
                    this.value[temp] = value;
                    this.full_Empty[temp] = true;
                    test = true;
                }else{
                    temp++;
                    if(temp == this.key.length) temp = 0;
                    else if(temp == choice){
                        System.out.println("더 이상 삽입할 수 없습니다.");
                        break;
                    }
                }
            }
        }
    }
    public void test(){
        for(int i=0;i<key.length;i++){
            System.out.println(i + ": " + this.value[i]);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int cases , sizes;
        String value;
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
                    System.out.print("넣을 위치를 정해주세요 : ");
                    sizes = scanner.nextInt();
                    System.out.print("넣을 값을 정해주세요 : ");
                    Scanner scanner1 = new Scanner(System.in);
                    value = scanner1.nextLine();
                    Node.set(sizes,value);
                break;
                case 3:
                    System.out.print("찾을 위치를 정해주세요 : ");
                    sizes = scanner.nextInt();
                    value = Node.get(sizes);
                    System.out.println("사이즈 : " + sizes + " 값 : " + value);
                break;
                case 4:
                    Node.test();
                break;
            }

        }
    }
}

