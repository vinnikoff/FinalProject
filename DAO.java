import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by VINNI on 24.01.17.
 */


public class  DAO implements abstrDAO{
    DataB data = new DataB();
    List<User> daoUser = data.getUser();
    List<Hotel> daoHotel = data.getHotel();
    Map<Integer, Room> daomaproomout = data.maproomout();

    public void registerUser (User user){
        data.getUser().add(user);
        System.out.println("Спасибо! Вы зарегистрированы!");}

  public void savebookRoom (int s, Map.Entry<Integer, Room> y, long inputuserId) {
      data.maproomout().put(s, new Room(y.getValue().getId(), y.getValue().getIdHotel(), inputuserId, y.getValue().getPrice(), y.getValue().getPersons(), y.getValue().getHotelName(), true));
  }

    public int VolReservOut (Collection<Room> findRoom) {
        int VolRoomAll= findRoom.size();
        Room[] myArray = {};
        myArray = findRoom.toArray(new Room[findRoom.size()]);
        int volReserv=0;
        for (Room reserv: myArray){
            if (reserv.getReserve()) volReserv++;
        }

        int VolReservOut=VolRoomAll-volReserv;
        return VolReservOut;
    }

@Override
      public List<User> getUserDao () {
          return daoUser;
      }

    @Override
    public List<Hotel> getHotelDao () {
        return daoHotel;
    }

    @Override
    public Map<Integer, Room> maproomoutDao () {
        return daomaproomout;
    }
  }
