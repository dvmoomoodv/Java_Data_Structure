package list_stack;
import java.util.Scanner;

// 람다식을 위한 연습
@FunctionalInterface
interface InterfaceList {
    public int method();
}

public class Main {
    static int StackCount = 0;
    public static void main(String[] args) {
        InterfaceList interfaceList;
        Stack[] stack; // 실제 Stack
        Stack[] Temp; // 내용 넣을 스택
        Stack Tempstack; // 임시용 Stack
        stack = new Stack[StackCount];
        Tempstack = new Stack();

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
                    StackCount = 0;
                    stack = new Stack[StackCount];
                    Tempstack.init();
                    break;
                // 삽입
                case 2:
                    StackCount++;
                    Temp = new Stack[StackCount];
                    for(int i = 0;i<StackCount;i++)
                    {
                        Temp[i] = new Stack();
                    }
                    Temp = Tempstack.Push(stack);
                    stack = new Stack[StackCount];
                    for(int i=0;i<StackCount;i++)
                    {
                        stack[i] = new Stack();
                        stack[i] = Temp[i];
                    }

                    break;
                // 삭제
                case 3:
                    if(StackCount > 0) {
                        StackCount--;
                        Temp = new Stack[StackCount];
                        for (int i = 0; i < StackCount; i++) {
                            Temp[i] = new Stack();
                        }
                        Temp = Tempstack.Pop(stack);
                        stack = new Stack[StackCount];
                        for (int i = 0; i < StackCount; i++) {
                            stack[i] = new Stack();
                            stack[i] = Temp[i];
                        }
                    }
                    break;
                case 4:
                    Tempstack.Print(stack);
                    break;
                case 5:
                    Tempstack.length(stack);
                    break;
            }
        }
    }
}
