/**
 * .
 */
package list_stack;
import java.util.Scanner;

// 기능 이름을 편하게 적어놓기 위한 abstract class
abstract class Prototype<T1, T2, T3> {
    public int Number; // List Node 번호
    public T1 Content1; // 내용 1
    public T2 Content2; // 내용 2
    public T3 Content3; // 내용 3
    // default 생성자
    public Prototype() {
        this.Number = -128;
        Content1 = null;
        Content2 = null;
        Content3 = null;
    }
    // 생성자
    public Prototype(int Number) {
        this.Number = Number;
        Content1 = null;
        Content2 = null;
        Content3 = null;
    }
    // 함수들
    public abstract void List_init(List[] list); // List 초기화
    public abstract void List_Modify(List[] list); // Node 내용 수정
    public abstract List[] List_Push(List[] list); // Node 삽입
    public abstract List[] List_Pop(List[] list); // Node 삭제
    public abstract void List_Print(List[] list); // Node 탐색

}

// 실제 List 구현 코드
class List<T1, T2, T3> extends Prototype<T1, T2, T3> {
    // List 초기화
    @Override
    public void List_init(List[] list) {

    }

    // 리스트를 삽입하는 것
    @Override
    public List[] List_Push(List[] list) {
        System.out.println("내용을 삽입할 리스트 번호를 선택해 주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        if ((list.length+1)< Numbers) {
            System.out.println("이격된 곳의 삽입 불가능");
        } else if (Numbers <= 0) {
            System.out.println("삽입 불가능");
        } else {
            List[] Temp = new List[list.length + 1];
            for (int i = 0; i < (list.length + 1); i++) {
                Temp[i] = new List();
            }
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("내용을 넣어주세요 : ");
            System.out.print("Content 1 : ");
            Temp[Numbers - 1].Content1 = (T1) scanner1.nextLine();
            System.out.print("Content 2 : ");
            Temp[Numbers - 1].Content2 = (T2) scanner1.nextLine();
            System.out.print("Content 3 : ");
            Temp[Numbers - 1].Content3 = (T3) scanner1.nextLine();

            // 해당되는 노드 이후는 전부 뒤로 이격후 , 원하는 원소를 삽입
            for (int i = 0; i < list.length; i++) {
                if (i < Numbers - 1) {
                    Temp[i] = list[i];
                } else if (i == Numbers - 1) {
                    continue;
                } else {
                    Temp[i + 1] = list[i];
                }
            }
            for (int i = 0; i < list.length+1; i++) {
                Temp[i].Number = i + 1;
            }
            return Temp;
        }
        return list;
    }

    // 삭제하는 Method
    @Override
    public List[] List_Pop(List[] list) {
        if (list.length == 0) {
            System.out.println("삭제를 할 수 없습니다.");
            return list;
        }

        System.out.println("내용을 삭제할 리스트 번호를 선택해 주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        // 내용을 삭제할 리스트를 제외한 나머지 부분을 임시 리스트에 옮긴 후 , 그 리스트를 반환
        if (Numbers > list.length) {
            System.out.println("삭제 불가능");
        } else {
            List[] Temp = new List[(list.length - 1)];
            for (int i = 0; i < Temp.length; i++) {
                int Count = 1;
                if (Numbers != Count) {
                    Temp[i] = list[i];
                    Temp[i].Number = Count;
                }
            }
            return Temp;
        }
        return list;
    }

    // 수정하는 Method
    @Override
    public void List_Modify(List[] list) {
        int Length = list.length;
        System.out.println("내용을 수정할 리스트 번호를 선택해 주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        if (Length == 0) {
            System.out.println("0개 입니다 .");
            System.out.println(Length);

        } else if (Numbers > Length) {
            System.out.println("존재하지 않습니다.");
        } else {
            System.out.println("내용을 넣어주세요 : ");
            System.out.print("Content 1 : ");
            list[Numbers].Content1 = (T1) scanner.nextLine();
            System.out.print("Content 2 : ");
            list[Numbers].Content2 = (T2) scanner.nextLine();
            System.out.print("Content 3 : ");
            list[Numbers].Content3 = (T3) scanner.nextLine();
        }
    }

    // List를 Print 하는 함수
    @Override
    public void List_Print(List[] list) {
        System.out.println("찾을 번호를 눌러주세요 :");
        Scanner scanner = new Scanner(System.in);
        int Numbers = scanner.nextInt();
        for (int i = 0; i < list.length; i++) {
            if (list[i].Number == Numbers) {
                System.out.println("리스트 번호 : " + list[i].Number);
                System.out.println("Content 1 : " + list[i].Content1);
                System.out.println("Content 2 : " + list[i].Content2);
                System.out.println("Content 3 : " + list[i].Content3);
            }
        }
    }


}

// 람다식을 위한 연습
@FunctionalInterface
interface InterfaceList {
    public int method();
}