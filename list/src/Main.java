import java.util.Scanner;

/**
 * 기간 : 2017.01.02
 * 내용 : List 구현
 * 개념 : abstract class , Generic ( 틀은 갖췄으나 미사용 ) , Lambda ( 일부 부분 )
 * 평가 : 처음에는 링크드 같이 클래스를 링크시켜 연결을 시키려고 했으나 , 한계점이 명확히 보여 동적 배열을 이용한 List로 구현
 * Java에서 Pointer가 존재하지 않고 , 배열을 원활하게 만들기 편하여 재할당을 계속 함 .
 */


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

public class Main {
    static int listCount = 0;

    public static void main(String[] args) {
        List[] list = new List[listCount]; // 사용자는 0개
        List Temp = new List(); // 임시 사용 변수
        InterfaceList interfaceList;

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
                    listCount = 0;
                    list = new List[listCount];
                    break;
                // 삽입
                case 2:
                    try {
                        listCount++;
                        List[] Templist = new List[listCount];
                        for (int i = 0; i < listCount; i++) Templist[i] = new List();
                        Templist = Temp.List_Push(list);
                        list = new List[listCount];
                        for (int i = 0; i < listCount; i++) {
                            list[i] = new List();
                            list[i] = Templist[i];
                        }
                    }catch(ArrayIndexOutOfBoundsException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                // 삭제
                case 3:
                    try {
                        if(listCount > 0)listCount--;
                        List[] Templist = new List[listCount];
                        for (int i = 0; i < listCount; i++) Templist[i] = new List();
                        Templist = Temp.List_Pop(list);
                        list = new List[listCount];
                        for (int i = 0; i < listCount; i++) {
                            list[i] = new List();
                            list[i] = Templist[i];
                        }
                    }catch(NegativeArraySizeException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    Temp.List_Print(list);
                    break;
                case 5:
                    System.out.println("현재 리스트 갯수 : " + listCount);
                    break;
            }
        }
    }
}
