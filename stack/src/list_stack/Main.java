package list_stack;
import java.util.Scanner;

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
