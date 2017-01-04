import java.util.Scanner;

/**
 * 기간 : 2017.01.04
 * 내용 : Queue 구현
 * 개념 : List 부분에 구현된 것의 대부분
 * 평가 : Enqueue , Dequeue
 */
public class Queue<T1, T2, T3> {
    // 삽입
    static int Count = 0; // 스택의 갯수 ( 임시 객체 때문에 - 1 )
    public int QueueNumber; // 스택 번호 갯수
    public Queue[] Tempqueue; // 스택을 이용한 연산

    public T1 Content1; // 내용 1
    public T2 Content2; // 내용 2
    public T3 Content3; // 내용 3

    public void init() {
        Count = 0;
    }

    public Queue[] Enqueue(Queue[] stack) {
        Count++;
        Tempqueue = new Queue[stack.length + 1];
        for (int i = 0; i < Tempqueue.length; i++) {
            Tempqueue[i] = new Queue();
            if (i != (Tempqueue.length - 1))
                Tempqueue[i] = stack[i];
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("내용을 넣어주세요 : ");
        System.out.print("Content 1 : ");
        Tempqueue[Tempqueue.length - 1].Content1 = (T1) scanner.nextLine();
        System.out.print("Content 2 : ");
        Tempqueue[Tempqueue.length - 1].Content2 = (T2) scanner.nextLine();
        System.out.print("Content 3 : ");
        Tempqueue[Tempqueue.length - 1].Content3 = (T3) scanner.nextLine();
        for (int i = 0; i < Tempqueue.length; i++) {
            Tempqueue[i].QueueNumber = (i + 1);
        }
        return Tempqueue;
    }

    //삭제
    public Queue[] Dequeue(Queue[] queue) {
        Count--;
        Tempqueue = new Queue[queue.length - 1];
        for (int i = 0; i < Tempqueue.length; i++) {
            Tempqueue[i] = queue[i+1];
        }
        for (int i = 0; i < Tempqueue.length; i++) {
            Tempqueue[i].QueueNumber = (i + 1);
        }
        return Tempqueue;
    }

    // 출력
    public void Print(Queue[] queue) {
        System.out.println("찾을 번호를 눌러주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        for (int i = 0; i < queue.length; i++) {
            if (queue[i].QueueNumber == Numbers) {
                System.out.println("Queue 번호 : " + queue[i].QueueNumber);
                System.out.println("Content 1 : " + queue[i].Content1);
                System.out.println("Content 2 : " + queue[i].Content2);
                System.out.println("Content 3 : " + queue[i].Content3);
            }
        }

    }

    //길이파악
    public void length(Queue[] queue) {
        System.out.println("Queue 갯수 : " + Count);
    }
}
