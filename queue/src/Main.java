import java.util.Scanner;
@FunctionalInterface
interface InterfaceList {
    public int method();
}

public class Main {
    static int QueueCount = 0;
    public static void main(String[] args) {
        InterfaceList interfaceList;
        Queue[] queue; // 실제 Queue
        Queue[] Temp; // 내용 넣을 큐
        Queue Tempqueue; // 임시용 Queue
        queue = new Queue[QueueCount];
        Tempqueue = new Queue();

        while (true) {
            int ContentNumber; // 숫자 입력
            // 기능 선택 람다식
            interfaceList = () -> {
                System.out.println("기능을 선택해주세요 .");
                int Temps;
                System.out.println("1 : 초기화 / 2 : 삽입 / 3 : 삭제 / 4 : 탐색 / 5 : 길이 파악 ");
                Scanner scanner = new Scanner(System.in);
                Temps = scanner.nextInt();
                return Temps;
            };

            ContentNumber = interfaceList.method();
            switch (ContentNumber) {
                // 초기화 ( 무 상태로 만드는 것 )
                case 1:
                    QueueCount = 0;
                    queue = new Queue[QueueCount];
                    Tempqueue.init();
                    break;
                // 삽입
                case 2:
                    QueueCount++;
                    Temp = new Queue[QueueCount];
                    for(int i = 0;i<QueueCount;i++)
                    {
                        Temp[i] = new Queue();
                    }
                    Temp = Tempqueue.Enqueue(queue);
                    queue = new Queue[QueueCount];
                    for(int i=0;i<QueueCount;i++)
                    {
                        queue[i] = new Queue();
                        queue[i] = Temp[i];
                    }

                    break;
                // 삭제
                case 3:
                    if(QueueCount > 0) {
                        QueueCount--;
                        Temp = new Queue[QueueCount];
                        for (int i = 0; i < QueueCount; i++) {
                            Temp[i] = new Queue();
                        }
                        Temp = Tempqueue.Dequeue(queue);
                        queue = new Queue[QueueCount];
                        for (int i = 0; i < QueueCount; i++) {
                            queue[i] = new Queue();
                            queue[i] = Temp[i];
                        }
                    }
                    break;
                case 4:
                    Tempqueue.Print(queue);
                    break;
                case 5:
                    Tempqueue.length(queue);
                    break;
            }
        }
    }
}
