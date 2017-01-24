/**
 * Created by VINNI on 03.12.16.
 */
import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException{


        /*List<User> user = new ArrayList<>();
        user.add(new User(1, "login1"));
        user.add(new User(2, "login2"));

        List<Hotel> hotel = new ArrayList<>();
        hotel.add(new Hotel(1, "cityName1", "hotelName1"));
        hotel.add(new Hotel(2, "cityName2", "hotelName2"));
        hotel.add(new Hotel(3, "cityName3", "hotelName1"));

        Map<Integer, Room> room = new HashMap<>();
        room.put(1, new Room(1, 1, 0, 100, 2, "hotelName1", false));
        room.put(2, new Room(2, 2, 1, 200, 3, "hotelName2", true));*/
        int i=0;

            DataB data = new DataB();
            Controller controller = new Controller();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (i < 100) {
                i++;
                System.err.println("Введите Ваш логин:");

                String inputLogin;
                inputLogin = br.readLine();

                long userFind = data.getUser().stream()
                        .filter((p) -> p.getLogin().equals(inputLogin)).count();


                if (userFind > 0) {
                    System.out.println("Какую операцию Вы хотите произвести:\n 1. Забронировать номер (нажмите 1),\n 2. Отменить бронирование (нажмите 2),\n 3. Найти гостиницу по названию(нажмите 3),\n 4. Найти гостиницы в городе(нажмите 4),\n 5. Найти комнаты по параметрам (нажмите 5)\n"
                    );
                    int inputOperation;
                    inputOperation = Integer.valueOf(br.readLine());

                    if (inputOperation > 0 && inputOperation < 7)
                        controller.findOperation(inputOperation);
                    else System.out.println("Вы внесли неверное число!");

                } else System.out.println("Вы не зарегистрированы");

            }
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    System.out.println("closed is failed");
                }

            }




}





