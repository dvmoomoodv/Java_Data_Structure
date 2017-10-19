import java.io.*;
import java.util.*;

// Key를 OverWrite해주어야 하나 단순한 논리 설명을 위해 Collision이라 가정하고 Bucket 방식만 사용
// Node 처리
class Node{
    public int[] key;
    public String[][] value;
    public Node(){
        key = new int[0];
        value = new String[0][0];
    }
    public Node(int size){
        key = new int[size];
        value = new String[size][];
        for(int i=0;i<size;i++)
            value[i] = new String[0];
    }

    public void get(int location){
        for(int i=0;i<this.value[location-1].length;i++) {
            System.out.print(this.value[location-1][i] + " ");
        }
        System.out.println();
    }

    public void set(int key,String value){

        int size = this.value[(key-1)%this.key.length].length + 1; // 해당 길이 파악
        if(size==1) this.key[(key-1)%this.key.length] = key;
        String [] Temp = new String[size];
        for(int i=0;i<size-1;i++){
            Temp[i] = this.value[(key-1)%this.key.length][i];
        }
        Temp[size-1] = value;
        this.value[(key-1)%this.key.length] = new String[size];
        for(int i=0;i<size;i++){
            this.value[(key-1)%this.key.length][i] = Temp[i] ;
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
                    Node.get(sizes);
                break;
            }

        }
    }
}

