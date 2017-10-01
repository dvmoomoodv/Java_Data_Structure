import javafx.geometry.Pos;

import java.util.*;

public class BFS {

    private int Number; // 항의 갯수
    private boolean EmptyNumber; // 비어있는지 확인
    private String[] Context; // 확인 내용
    public int find = 0;
    public int[][] Edge;
    public boolean[] Track;    // 갔다 왔는지 확인
    public int[] Route = {0,1,2,3,4,5,6,7,8,9};
    public char[] Code = {'A','B','C','D','E','F','G','H','I','J'}; // 확인 코드
    public int queue_route = 0; // Queue내부의 요소를 검색하도록 확인


    public BFS(){
        this.Number = 0;
        this.EmptyNumber = false;
        this.Context = new String[0];
        this.Track = new boolean[10];
        for(int i=0;i<10;i++)this.Track[i] = false;
        Edge = new int[10][];
        for(int i=0;i<10;i++){
            Edge[i] = new int[10];
        }
        Edge = new int[][]{{0, 1, 0, 0, 0, 0, 1, 0, 0, 0}, // A
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, // B
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // C
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, // D
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0}, // E
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // F
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, // G
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // H
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0}, // I
                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0}};// J
    }

    // 삽입 함수
    public void Insert(int Position, String Data){
        // 삽입 불가능한 위치에 삽입
        if((Position>(this.Number + 2)) || (Position<1)){
            System.out.println("입력할 수 없습니다.");
        }
        // 맨 처음에 추가할 경우
        // 아무것도 존재하지 않는 경우
        else if(Position == 1 && Number ==0){
            this.EmptyNumber = true;
            this.Number++;
            this.Context = new String[this.Number];
            this.Context[0] = Data;
        }
        // 맨 처음 위치에 있지만, 이미 존재하고 있는 값이 있는 경우
        else if((Position == 1) && (Number>0)){
            String[] Temp = new String[this.Number];
            for (int i = 0; i < this.Number; i++)
                Temp[i] = this.Context[i];
            this.Number++;
            this.Context = new String[this.Number];
            this.Context[0] = Data;
            for(int i=1;i<this.Number;i++) {
                this.Context[i] = "";
                this.Context[i] = Temp[i - 1];
            }

        }
        // 맨뒤에 Data를 넣을 경우
        else if(Position == this.Number+1){
            this.Number++;
            String[] Temp = new String[this.Number];
            for(int i=0;i<(this.Number)-1;i++)
                Temp[i] = this.Context[i];
            Temp[Number-1] = Data;
            this.Context = new String[this.Number];
            for(int i=0;i<this.Number;i++) {
                this.Context[i] = "";
                this.Context[i] = Temp[i];
            }
        }
        else{ // 중간에 넣는 경우
            // 포인터가 없기 떄문에 동적 배열을 사용한다.
            String[] left = new String[Position];
            String[] right = new String[this.Number - (Position-1)];
            for(int i=0;i<Position-1;i++){
                left[i] = this.Context[i];
            }
            left[Position-1] = Data;
            for(int i=Position-1;i<this.Number;i++){
                right[i-(Position-1)] = this.Context[i];
            }
            this.Number++;
            this.Context = new String[this.Number];
            for(int i=0;i<this.Number;i++){
                if(i<Position){
                    this.Context[i] = "";
                    this.Context[i] = left[i];
                }else {
                    this.Context[i] = "";
                    this.Context[i] = right[i-Position];
                }
            }

        }

    }
    // 삭제하는 함수
    public void Delete(int Position){
        // 삭제 불가능한 위치에 삽입
        if(this.Number == 0){
            System.out.println("삭제할 것이 없습니다.");
        }
        else if((Position>(this.Number + 2)) || (Position<1)){
            System.out.println("삭제할 수 없습니다.");
        }
        // 맨 처음 삭제
        else if(Position == 1 && Number ==1){
            this.Number = 0;
            this.EmptyNumber = false;
            this.Context = new String[0];
        }
        // 맨 처음 위치에 있지만, 이미 존재하고 있는 값이 있는 경우
        else if((Position == 1) && (Number>1)){
            Number--;
            String[] Temp = new String[this.Number];
            for (int i = 0; i < this.Number; i++)
                Temp[i] = this.Context[i+1];
            this.Context = new String[Number];
            for(int i=0;i<this.Number;i++) {
                this.Context[i] = "";
                this.Context[i] = Temp[i];
            }

        }
        // 맨 마지막 삭제
        else if(Position == this.Number+1) {
            this.Number--;
            String[] Temp = new String[this.Number];
            for(int i=0;i<Number;i++){
                Temp[i] = this.Context[i];
            }
            this.Context = new String[this.Number];
            for(int i=0;i<Number;i++){
                this.Context[i] = Temp[i];
            }

        } // 중간에 삭제하는 경우
        else{

            // 포인터가 없기 떄문에 동적 배열을 사용한다.
            String[] left = new String[(Position-1)];
            String[] right = new String[this.Number - (Position-1)];
            for(int i=0;i<Position-1;i++){
                left[i] = this.Context[i];
            }
            for(int i=Position-1;i<this.Number;i++){
                right[i-(Position-1)] = this.Context[i];
            }
            this.Number--;
            this.Context = new String[this.Number];
            for(int i=0;i<this.Number;i++){
                if(i<(Position-1)){
                    this.Context[i] = "";
                    this.Context[i] = left[i];
                }else {
                    this.Context[i] = "";
                    this.Context[i] = right[i-(Position-1)];
                }
            }
        }


    }
    // 해당 위치를 탐색하는 함수
    public String Retrieve(int Position){
        if(this.Number == 0){
            return "데이터가 없습니다.";
        }
        if(Position > this.Number)
            return "해당 위치에 Data가 없다.";
        else {
            return this.Context[Position-1];
        }
    }
    // 다시 생성
    public void Create(){
        this.Number = 0;
        this.EmptyNumber = false;
        this.Context = new String[0];
    }
    // 리스트 자체를 삭제
    public void Destroy(){
        this.Number = 0;
        this.EmptyNumber = false;
        this.Context = new String[0];
    }
    // 비어있는지 확인
    public void Isempty(){
        if(this.EmptyNumber == false){
            System.out.println("리스트가 비어있습니다.");
        }else{
            System.out.println("리스트가 비어있지 않습니다.");
        }
    }
    // 리스트의 길이
    public int Length(){
        return this.Number;

    }
    // 모든 항 확인
    public void AllRetrieve(){
        for(int i=0;i<this.Number;i++)
            System.out.println((i+1)+":" + this.Context[i]);
    }


    /* 스택 전용 함수 */
    // 삽입
    public void Push(String Data){
        this.Insert(1,Data);
    }
    //삭제
    public void Pop(){
        this.Delete(1);
    }
    //검색
    public void GetTop(){
        this.Retrieve(1);
    }
    //초기화
    public void Init(){
        this.Create();
    }
    //파괴
    public void Stack_Destroy(){
        this.Destroy();
    }


    /* 큐 전용 함수 */
    //삽입
    public void Add(String Data){
        this.Insert((this.Number+1),Data);
    }
    //삭제
    public void Remove(){this.Delete(1);}
    //초기화
    public void Queue_Init(){this.Create();}
    //파괴
    public void Queue_Destroy(){this.Destroy();}
    //큐 읽기
    public String GetFront(){
        return this.Retrieve(1);
    }


    public void BFS(BFS bfs,int i,int End){
        // 방문했는지 확인하고 준비
        if(bfs.Track[i] == false){
            bfs.Add(String.valueOf(bfs.Route[i])); // Stack에 집어넣음
            bfs.Track[i] = true; // 해당 방문을 했다고 표시
        }

        // 목적지와 일치했을 경우 탐색 종료
        if(queue_route > 0) {
            if (Integer.parseInt(bfs.Retrieve(queue_route)) == End) {
                find = 1;
            }

        }

        // 일치하는것이 끝나지 않았을 경우
        if(find == 0) {
            // 모든 지점을 찾아 입력
            for (int j = 0; j < 10; j++) {
                if (bfs.Edge[i][j] == 1) {
                    if (bfs.Track[j] == false) {
                       bfs.Add(String.valueOf(bfs.Route[j]));
                       bfs.Track[j] = true;
                    }
                }

            }
                    try {
                        queue_route++;
                        int route = Integer.parseInt(bfs.Retrieve(queue_route));
                        bfs.BFS(bfs, route, End);
                    }catch(NumberFormatException e){
                            find = 1;
                            bfs.Destroy();
                            bfs.Create();
                    }
            }
     }


    public static void main(String[] args){
        BFS bfs = new BFS();
        int Position;
        String Data;
        Scanner scanner = new Scanner(System.in);
        int Select;
        int Start=0, End=0;
        String Data_Start, Data_End;

        while(true){
            bfs = new BFS();
            System.out.println("해당 경로를 갈 수 있는지 탐색합니다.");
            System.out.print("시작점 : ");
            Data_Start = scanner.nextLine();
            System.out.print("종료점 : ");
            Data_End = scanner.nextLine();
            for(int i=0;i<10;i++){
                if(Data_Start.charAt(0)==bfs.Code[i]){
                    Start = i;
                    break;
                }
            }
            for(int i=0;i<10;i++){
                if(Data_End.charAt(0)==bfs.Code[i]){
                    End = i;
                    break;
                }
            }

            bfs.BFS(bfs,Start,End); // 깊이 우선 탐색 시작

            int length = bfs.Length();
            if(length == 0){ // 찾는 경로가 없을 경우
                System.out.println("해당 경로가 없습니다.");
            }else { // 찾는 경로가 있는 경우
                for (int i = 0; i < bfs.queue_route; i++) {
                    System.out.println("경로" + (i + 1) + " : " + bfs.Code[Integer.parseInt(bfs.GetFront())]);
                    bfs.Pop();
                }
            }
            bfs.find = 0;
            bfs.queue_route = 0;
            bfs.Queue_Destroy();
            System.out.println("종료입니다.");
        }

    }
}
