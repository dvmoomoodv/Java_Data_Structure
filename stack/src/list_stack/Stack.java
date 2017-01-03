package list_stack;

import java.util.Scanner;

/**
 * 기간 : 2017.01.03
 * 내용 : Stack 구현
 * 개념 : List 부분에 구현된 것의 대부분
 * 평가 : 스택부분은 Push , Pop이라 매우 쉬웠음
 */
public class Stack<T1,T2,T3>{
    // 삽입
    static int Count = 0 ; // 스택의 갯수 ( 임시 객체 때문에 - 1 )
    public int StackNumber; // 스택 번호 갯수
    public Stack[] Tempstack ; // 스택을 이용한 연산

    public T1 Content1; // 내용 1
    public T2 Content2; // 내용 2
    public T3 Content3; // 내용 3
    public void init()
    {
        Count = 0;
    }
    public Stack[] Push(Stack[] stack){
        Count++;
        Tempstack = new Stack[stack.length+1];
        for(int i = 0;i<Tempstack.length ; i++)
        {
            Tempstack[i] = new Stack();
            if(i!=(Tempstack.length-1))
            Tempstack[i] = stack[i];
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("내용을 넣어주세요 : ");
        System.out.print("Content 1 : ");
        Tempstack[Tempstack.length - 1].Content1 = (T1) scanner.nextLine();
        System.out.print("Content 2 : ");
        Tempstack[Tempstack.length - 1].Content2 = (T2) scanner.nextLine();
        System.out.print("Content 3 : ");
        Tempstack[Tempstack.length - 1].Content3 = (T3) scanner.nextLine();
        for(int i=0;i<Tempstack.length;i++)
        {
            Tempstack[i].StackNumber = (i+1);
        }
        return Tempstack;
    }
    //삭제
    public Stack[] Pop(Stack[] stack)
    {   Count --;
        Tempstack = new Stack[stack.length-1];
        for(int i = 0;i<Tempstack.length ; i++) {
            Tempstack[i] = stack[i];
        }
        for(int i=0;i<Tempstack.length;i++)
        {
            Tempstack[i].StackNumber = (i+1);
        }
        return Tempstack;
    }
    // 출력
    public void Print(Stack[] stack)
    {
        System.out.println("찾을 번호를 눌러주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        for (int i = 0; i < stack.length; i++) {
            if (stack[i].StackNumber == Numbers) {
                System.out.println("스택 번호 : " + stack[i].StackNumber);
                System.out.println("Content 1 : " + stack[i].Content1);
                System.out.println("Content 2 : " + stack[i].Content2);
                System.out.println("Content 3 : " + stack[i].Content3);
            }
        }

    }
    //길이파악
    public void length(Stack[] stack)
    {
        System.out.println("Stack의 갯수 : "+ Count);
    }
}
