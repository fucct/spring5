import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Cabinet {
    public static void main(String[] args) {
        Map<Integer, String> cabinets = new HashMap<>();
        for(int i=1;i<=52;i++){
            cabinets.put(i, null);
        }
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("본인의 이름을 입력하세요 : ");
            String name1 = sc.nextLine();
            System.out.println("본인의 사물함 자리는? : ");
            int cabinet1 = Integer.parseInt(sc.nextLine());

            cabinets.put(cabinet1, name1);

            System.out.println("페어의 이름을 입력하세요 : ");
            String name2 = sc.nextLine();
            System.out.println("페어의 사물함 자리는? : ");
            int cabinet2 = Integer.parseInt(sc.nextLine());

            cabinets.put(cabinet2, name2);

            System.out.println("사용성이 떨어지는 자리 번호를 입력하세요");
            int number = Integer.parseInt(sc.nextLine());

            List<String> names = new ArrayList<>();



            BufferedReader reader = new BufferedReader(new FileReader("/Users/mac/Desktop/Github/TIL/spring5/java-cabinet/src/main/java/input.txt"));
            String line="";
            while((line = reader.readLine())!=null){
                names.add(line);
            }
            names.remove(name1);
            names.remove(name2);
            Collections.shuffle(names);

            int index = 1;
        } catch (IOException e) {
            System.out.println("파일이없습니다.");
        }
    }
}
