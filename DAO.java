import java.util.ArrayList;
import java.util.List;

/**
 * Created by VINNI on 24.01.17.
 */


public class  DAO {

    public void registerUser (User user){
        DataB data = new DataB();

        data.getUser().add(user);
        System.out.println("Спасибо! Вы зарегистрированы!");


    }
}
