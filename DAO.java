import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by VINNI on 24.01.17.
 */


public class  DAO implements abstrDAO{
    DataB data = new DataB();

    List<User> daoUser = data.getUser();//получаем список юзеров (чтобы не тянуть напрямую из Базы данных)
    List<Hotel> daoHotel = data.getHotel();//получаем список Отелей
    Map<Integer, Room> daomaproomout = data.maproomout();//получаем список комнат

    public DAO() throws IOException {
    }

    //регистрация юзера с записью в файл
    public void registerUser (User user) throws IOException {
        data.getUser().add(user);
        System.out.println("Спасибо! Вы зарегистрированы!");

        FileWriter bw = new FileWriter("user.txt", true);
        String iduser=String.valueOf(user.getId());
        String loginuser=user.getLogin();
        String all = "#"+iduser+"#"+loginuser;//формируем структуру файла, чтобы легче потом было вытягивать значения
        bw.write("\n"+all);
        bw.flush();

    }

    //сохраняем информацию о резервировании комнаты (в т.ч. заносим id юзера и меняем значение поля reserve на  true)
  public void savebookRoom (int s, Map.Entry<Integer, Room> y, long inputuserId) {
      data.maproomout().put(s, new Room(y.getValue().getId(), y.getValue().getIdHotel(), inputuserId, y.getValue().getPrice(), y.getValue().getPersons(), y.getValue().getHotelName(), true));
  }

  //определяем количество зарезервированных комнат при использовании метода findRoom в Controller
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
