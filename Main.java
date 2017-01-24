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

                } else {
                    System.out.println("Вы не зарегистрированы!");
                    System.out.println("Для регистрации введите придуманный Вами логин:");
                    String inputLoginReg;
                    inputLoginReg = br.readLine();
                    long k=data.getUser().size()+1;
                    User user = new User(k, inputLoginReg);
                    DAO dao = new DAO();
                    dao.registerUser(user);
                }

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





