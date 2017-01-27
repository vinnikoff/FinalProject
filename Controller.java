import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.*;
import java.lang.*;


public class Controller {
    DAO dao = new DAO();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int key1=1;
    int key2=1;

    void Main () throws IOException{

        List<User> user = new ArrayList<>();
        user=dao.getUserDao();
        int i = 0;
        while (i < 100) {

            if (i == 0) {
                i++;
                System.err.println("Введите Ваш логин:");

                String inputLogin;
                inputLogin = br.readLine();

                long userFind = user.stream()
                        .filter(s -> s.getLogin().equals(inputLogin)).count();

                UserOperation(userFind);
            } else UserOperation(1);

        }
    }


    void UserOperation (long userFind) throws IOException, Ex {

    try {
    if (userFind > 0) {
        System.out.println("Какую операцию Вы хотите произвести:\n 1. Забронировать номер (нажмите 1),\n 2. Отменить бронирование (нажмите 2),\n 3. Найти гостиницу по названию(нажмите 3),\n 4. Найти гостиницы в городе(нажмите 4),\n 5. Найти комнаты по параметрам (нажмите 5)\n"
        );
        int inputOperation;
        inputOperation = Integer.valueOf(br.readLine());

        if (inputOperation > 0 && inputOperation < 7)
            findOperation(inputOperation);
        else System.out.println("Вы внесли неверное число!");

    } else {
        System.out.println("Вы не зарегистрированы!");
        System.out.println("Для регистрации введите придуманный Вами логин:");

        String inputLoginReg;
        inputLoginReg = br.readLine();
        long k = dao.getUserDao().size() + 1;
        User user = new User(k, inputLoginReg);

        dao.registerUser(user);
    }
}

catch (NumberFormatException e) {

    System.out.println("Вы внесли неверную информацию (неверный формат данных)!!!");
    Ex exception = new Ex();
    throw exception;

}

catch (IOException e) {
    System.out.println("Error!!!");
}

finally {
    UserOperation(1);
}

    }


void findOperation(int a) {

    if (a == 1) {
        System.out.println("Введите roomId");
        try {
            long inputroomId;
            inputroomId = Integer.valueOf(br.readLine());

            System.out.println("Введите  userId");
            long inputuserId;
            inputuserId = Integer.valueOf(br.readLine());

            System.out.println("Введите hotelId");
            long inputhotelId;
            inputhotelId = Integer.valueOf(br.readLine());
            bookRoom(inputroomId, inputuserId, inputhotelId);}

             catch (IOException e) {
                System.out.println("it is not integer value");}

    }
    if (a == 2) {
        System.out.println("Введите roomId");
        try {
            long inputroomId;
            inputroomId = Integer.valueOf(br.readLine());

            System.out.println("Введите  userId");
            long inputuserId;
            inputuserId = Integer.valueOf(br.readLine());

            System.out.println("Введите hotelId");
            long inputhotelId;
            inputhotelId = Integer.valueOf(br.readLine());
            cancelReservation(inputroomId, inputuserId, inputhotelId);}

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }
    if (a == 3) {

        System.out.println("Введите название Гостиницы для поиска:");
        try {
            String inputHotelName;
            inputHotelName = br.readLine();

            Collection<Hotel>findHotelByNameout = new ArrayList<>();
            findHotelByNameout = findHotelByName(inputHotelName);
            int f= findHotelByNameout.size();
            if (f>0) {System.out.println("Запрашиваемая Гостиница находится в городах:\n");
                findHotelByNameout.forEach(n -> System.out.println(n.getCityName()));
            }
            else System.out.println("По запрашиваемым параметрам гостиницы отсутствуют");}

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }

    if (a == 4) {

        System.out.println("Введите название города:");
        try {
            String inputCityName;
            inputCityName = br.readLine();

            Collection<Hotel>findHotelByCityout = new ArrayList<>();
            findHotelByCityout = findHotelByCityout(inputCityName);
            int f= findHotelByCityout.size();
            if (f>0) {System.out.println("В запрашиваемом городе находятся гостиницы:\n");
                findHotelByCityout.forEach(n -> System.out.println(n.getHotelName()));
            }
            else System.out.println("По запрашиваемым параметрам гостиницы отсутствуют");}

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }

    if (a == 5) {

        try {
            System.out.println("Введите  название гостиницы:");
            String inputHotelName;
            inputHotelName = br.readLine();

            System.out.println("Введите  город:");
            String inputCityName;
            inputCityName = br.readLine();

            System.out.println("Введите  стоимость проживания:");
            String inputPrice;
            inputPrice = br.readLine();

            System.out.println("Введите  количество персон:");
            String inputPerson;
            inputPerson = br.readLine();

            Map<String, String> param = new HashMap<>();
            param.put("inputHotelName", inputHotelName);
            param.put("inputCityName", inputCityName);
            param.put("inputPrice", inputPrice);
            param.put("inputPerson", inputPerson);

            Collection<Room> findRoom = new ArrayList<>();
            findRoom = findRoom(param);
            int VolRoomAll= findRoom.size();

            if (VolRoomAll>0) {
                System.out.println("По запрашиваемым параметрам найдена информация!");
                System.out.println("Количество свободных комнат: "+dao.VolReservOut(findRoom)+"\n");

            }
            else System.out.println("По запрашиваемым параметрам гостиницы отсутствуют");

            }

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }


}
public void bookRoom(long inputroomId, long inputuserId, long inputhotelId){


    try {

            Set<Map.Entry<Integer, Room>> set = dao.maproomoutDao().entrySet();

            for (Map.Entry<Integer, Room> y: set)
            {

                if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==false){
                    int s= y.getKey();
                    dao.savebookRoom(s, y, inputuserId);
                   // data.maproomout().put(s, new Room(y.getValue().getId(), y.getValue().getIdHotel(), inputuserId, y.getValue().getPrice(), y.getValue().getPersons(), y.getValue().getHotelName(), true));// можно вынести в DAO
                    key1++;
                    break;
                }
                if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==true){
                    key1--;
                    System.out.println("Комната уже забронирована");

                    break;
                }
            }

        if (key1 == 1) {
            System.out.println("По запрашиваемым данным ничего не найдено");
            key1=1;
            System.out.println("Вы хотите выполнить ещё одну операцию? (да - 1, нет 2)");
                int inputOperation;
                inputOperation = Integer.valueOf(br.readLine());
                if (inputOperation==2) {
                    System.out.println("До свидания!");
                    System.exit(0);
            }

        }

        if (key1 > 1){
            System.out.println("Забронировано для userId-" + inputuserId + " комнату-" + inputroomId + " в отеле-" + inputhotelId);
        key1=1;
        System.out.println("Вы хотите выполнить ещё одну операцию? (да - 1, нет 2)");
        int inputOperation;
        inputOperation = Integer.valueOf(br.readLine());
        if (inputOperation==2) {
            System.out.println("До свидания!");
            System.exit(0);}}
    }

    catch (IOException e) {
        System.out.println("it is not integer value");}

    }


    public void cancelReservation(long inputroomId, long inputuserId, long inputhotelId){

        Set<Map.Entry<Integer, Room>> set = dao.maproomoutDao().entrySet();

        for (Map.Entry<Integer, Room> y: set)
        {

            if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==true){
                int s= y.getKey();

                dao.maproomoutDao().put(s, new Room(y.getValue().getId(), y.getValue().getIdHotel(), 0, y.getValue().getPrice(), y.getValue().getPersons(), y.getValue().getHotelName(), false));// можно вынести в DAO
                key2++;
                break;
            }
            if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==false){
                key2--;
                System.out.println("Комната ещё не забронирована");
                break;
            }
        }

        if (key2==1) System.out.println("По запрашиваемым данным ничего не найдено");
        if (key2>1) System.out.println("Снята бронь  для userId-"+inputuserId+" комнату-"+inputroomId+ " в отеле-"+inputhotelId);

    }


    public Collection<Hotel> findHotelByName(String inputHotelName){

        List<Hotel> findhotelbyName = new ArrayList<>();
            findhotelbyName = dao.getHotelDao().stream()
                    .filter(s -> s.getHotelName().equals(inputHotelName))
                    .collect(Collectors.toList());
        return findhotelbyName;
    }

    public Collection<Hotel> findHotelByCityout(String inputCityName){

        List<Hotel> findHotelByCityout = new ArrayList<>();
        findHotelByCityout = dao.getHotelDao().stream()
                .filter(s -> s.getCityName().equals(inputCityName))
                .collect(Collectors.toList());
        return findHotelByCityout;
    }


   public Collection<Room> findRoom(Map<String, String> params){

       List<Room> roomList = new ArrayList<>(dao.maproomoutDao().values());

       String inputHotelName = params.get("inputHotelName");
       String inputCityName = params.get("inputCityName");
       int inputPrice = Integer.parseInt(params.get("inputPrice"));
       int inputPerson = Integer.parseInt(params.get("inputPerson"));

       List<Room> findRoom = roomList.stream()
               .filter(s -> s.getHotelName().equals(inputHotelName) && s.getCityName().equals(inputCityName) && s.getPrice()==inputPrice && s.getPersons()==inputPerson)
               .collect(Collectors.toList());

        return findRoom;
    }


    

}


