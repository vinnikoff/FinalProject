/**
 * Created by VINNI on 03.12.16.
 */
import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        int i = 0;

        DataB data = new DataB();

        Controller controller = new Controller();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<User> user = new ArrayList<>();

        user=data.getUser();

        while (i < 100) {

            if (i == 0) {
                i++;
                System.err.println("Введите Ваш логин:");

                String inputLogin;
                inputLogin = br.readLine();

                long userFind = user.stream()
                        .filter(s -> s.getLogin().equals(inputLogin)).count();

                controller.UserOperation(userFind);
            } else controller.UserOperation(1);

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





