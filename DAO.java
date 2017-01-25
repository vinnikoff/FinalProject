import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by VINNI on 24.01.17.
 */


public class  DAO {
    DataB data = new DataB();

    public void registerUser (User user){
        data.getUser().add(user);
        System.out.println("Спасибо! Вы зарегистрированы!");}

  public void savebookRoom (int s, Map.Entry<Integer, Room> y, long inputuserId) {
   data.maproomout().put(s, new Room(y.getValue().getId(), y.getValue().getIdHotel(), inputuserId, y.getValue().getPrice(), y.getValue().getPersons(), y.getValue().getHotelName(), true));

   }

}
