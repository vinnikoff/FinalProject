import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by VINNI on 23.01.17.
 */

//база данных
public class DataB{

    public DataB() throws IOException {}

    List<User> user = new ArrayList<>();

    //метод для загрузки юзеров из файла
    public List<User> load() throws IOException {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("user.txt"))
        ) {

            String s;
            int vol_str=0;
            while ((s = br.readLine()) != null) {
                sb.append(s + "\n");
                vol_str++;//для понимания количества юзеров в файле
            }
            String new_str = sb.toString();
            String[] new_str2 = new_str.split("#");//юзеров заносим в массив String, удалив спец символ #, разделяя строку вокруг регулярного выражения (в файле юзеры хранятся в виде #1#login1# , где 1-id пользователя, login1 - логин пользователя)

            User[] arrUser = new User[vol_str];//для формирования массива Юзеров

        int j=0;
            //выбираем значения из массива юзеров
    for (int i = 0; i < vol_str; i++) {
        String usertemp=new_str2[j + 2].replaceAll ("\n", "");//здесь хранятся логины (они четные j), также надо убрать перенос на новую строку

        arrUser[i] = new User(Integer.parseInt(new_str2[j + 1]), usertemp);//формируем массива Юзеров
        j=j+2;//для выборки следующей пары - id, login
    }

            for (int k = 0; k < vol_str; k++) {
                user.add(arrUser[k]);//формируем List<User>

            }
           return user;

        }
    }

//конструкция new Hotel: id отеля, название города, название отеля
    List<Hotel> hotel = Arrays.asList(new Hotel(1, "cityName1", "hotelName1"), new Hotel(2, "cityName2", "hotelName2"), new Hotel(3, "cityName3", "hotelName1"));

    public List<Hotel> getHotel() {
        return hotel;
    }

    public List<User> getUser() throws IOException {
        return load();//возвращаем List<User> из метода load(), который загружает юзеров из файла
    }

// конструкция new Room: id комнаты, id отеля, id юзера (который зарезервировал, если нет резервирования - 0), цена за номер, количество персон, Название отеля, город, зарезервировано или нет
    Room room1 = new Room(1, 1, 0, 100, 2, "hotelName1", "cityName1", false);//hotelName1 и cityName1 можно было подтягивать с List<Hotel>
    Room room2 = new Room(2, 2, 0, 200, 3, "hotelName2", "cityName2", false);
    Room room3 = new Room(3, 2, 1, 200, 3, "hotelName2", "cityName1", true);

    //заносим комнаты в базу данных и при необходимости их выдаём (для подстраховки в уникальности комнат используется Map
    public Map<Integer, Room> maproomout() {
        Map<Integer, Room> room = new HashMap<>();
        room.put(1, room1);
        room.put(2, room2);
        room.put(3, room3);
        return room;
    }

    @Override
    public String toString() {
        return "DataB{" +
                "user=" + user +
                ", hotel=" + hotel +
                ", room1=" + room1 +
                ", room2=" + room2 +
                '}';
    }
}