import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by VINNI on 23.01.17.
 */
public class DataB{

    Controller controller = new Controller();

    List<User> user = Arrays.asList(new User(1, "login1"), new User(2, "login2"));

    List<Hotel> hotel = Arrays.asList(new Hotel(1, "cityName1", "hotelName1"), new Hotel(2, "cityName2", "hotelName2"), new Hotel(3, "cityName3", "hotelName1"));

    public List<Hotel> getHotel() {
        return hotel;
    }

    public List<User> getUser() {
        return user;
    }

    Room room1 = new Room(1, 1, 0, 100, 2, "hotelName1", "cityName1", false);
    Room room2 = new Room(2, 2, 1, 200, 3, "hotelName2", "cityName1", true);

    public Map<Integer, Room> maproomout() {
        Map<Integer, Room> room = new HashMap<>();
        room.put(1, room1);
        room.put(2, room2);
        return room;
    }


}