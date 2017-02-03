import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.*;
import java.lang.*;

//В Controller обрабатывается вся логика
public class Controller {
    DAO dao = new DAO();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int key1=1;//метка для понимания - есть ли бронь на комнату в методе bookRoom
    int key2=1;//метка для понимания - есть ли бронь на комнату в методе bookRoom

    public Controller() throws IOException {
    }

    void Main () throws IOException{
//Обработка Логина (на присутствие в базе данных)
        List<User> user = new ArrayList<>();
        user=dao.getUserDao();
        int i = 0;
        while (i < 100) {
// занёс в цикл, чтобы можно было несколько раз тестировать без выхода из программы
            if (i == 0) {
                i++;
                System.err.println("Введите Ваш логин:");

                String inputLogin;
                inputLogin = br.readLine();

                long userFind = user.stream()
                        .filter(s -> s.getLogin().equals(inputLogin)).count();

                UserOperation(userFind);// если введённый логин есть в базе, переход к выбору операций
            } else UserOperation(1);// если введённого логина нет в базе, переход к выбору операций, но с меткой - 1
        }
    }


    void UserOperation (long userFind) throws IOException, Ex {
// даёт выбор возможных операций и, если пользователь не зарегистрирован - регистрирует
    try {
    if (userFind > 0) {//если юзер уже зарегистрирован
        System.out.println("Какую операцию Вы хотите произвести:\n 1. Забронировать номер (нажмите 1),\n 2. Отменить бронирование (нажмите 2),\n 3. Найти гостиницу по названию(нажмите 3),\n 4. Найти гостиницы в городе(нажмите 4),\n 5. Найти комнаты по параметрам (нажмите 5)\n"
        );
        int inputOperation;
        inputOperation = Integer.valueOf(br.readLine());

        if (inputOperation > 0 && inputOperation < 7)
            findOperation(inputOperation);
        else System.out.println("Вы внесли неверное число!");

    } else {//если не зарегистрирован - регистрация
        System.out.println("Вы не зарегистрированы!");
        System.out.println("Для регистрации введите придуманный Вами логин:");

        String inputLoginReg;
        inputLoginReg = br.readLine();

        long k = dao.getUserDao().size() + 1;//формирование очередного id
        User user = new User(k, inputLoginReg);//формирование нового юзера

        dao.registerUser(user);//метод, отвечающий за регистрацию
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

//метод, отвечающий за обработку введенных данных (и соответственно, выборе подходящего метода)
// при выборе пользователем операции в UserOperation
void findOperation(int a) {
//если пользователь нажал "1", то выполняется блок:
    if (a == 1) {
        //запрос данных необходимых для выполнения бронирования в методе bookRoom
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
            //запрос данных необходимых для выполнения отмены бронирования в методе cancelReservation
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
//запрос данных необходимых для выполнения поиска гостиниц в методе findHotelByNameout
        System.out.println("Введите название Гостиницы для поиска:");
        try {
            String inputHotelName;
            inputHotelName = br.readLine();

            Collection<Hotel>findHotelByNameout = new ArrayList<>();

            findHotelByNameout = findHotelByName(inputHotelName);
            int f= findHotelByNameout.size();//для понимания количества подходящих гостиниц
            if (f>0) {System.out.println("Запрашиваемая Гостиница находится в городах:\n");

                findHotelByNameout.forEach(n -> System.out.println(n.getCityName()));
            }
            else System.out.println("По запрашиваемым параметрам гостиницы отсутствуют");}

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }

    if (a == 4) {
//запрос данных необходимых для выполнения поиска гостиниц в методе findHotelByCityout
        System.out.println("Введите название города:");
        try {
            String inputCityName;
            inputCityName = br.readLine();

            Collection<Hotel>findHotelByCityout = new ArrayList<>();
            findHotelByCityout = findHotelByCityout(inputCityName);

            int f= findHotelByCityout.size();//для понимания количества подходящих гостиниц
            if (f>0) {System.out.println("В запрашиваемом городе находятся гостиницы:\n");
                findHotelByCityout.forEach(n -> System.out.println(n.getHotelName()));
            }
            else System.out.println("По запрашиваемым параметрам гостиницы отсутствуют");}

        catch (IOException e) {
            System.out.println("it is not integer value");}
    }

    if (a == 5) {
//запрос данных необходимых для выполнения поиска номеров в методе findRoom
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

            //для формирования Map в соответствии с заданием
            Map<String, String> param = new HashMap<>();
            param.put("inputHotelName", inputHotelName);
            param.put("inputCityName", inputCityName);
            param.put("inputPrice", inputPrice);
            param.put("inputPerson", inputPerson);

            Collection<Room> findRoom = new ArrayList<>();
            findRoom = findRoom(param);
            int VolRoomAll= findRoom.size();
//если по запрашиваемым параметрам найден номер:
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

//метод, отвечающий за резервирование
public void bookRoom(long inputroomId, long inputuserId, long inputhotelId){


    try {

            Set<Map.Entry<Integer, Room>> set = dao.maproomoutDao().entrySet();//вытягиваем из базы Комнаты

            for (Map.Entry<Integer, Room> y: set)
            {

                if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==false){
                    int s= y.getKey();
                    //заносим в базу данных Комнат изменения (меняем значение поля Reserve с false на true)
                    dao.savebookRoom(s, y, inputuserId);//передается ID комнаты, сама комната и ID юзера, который бронирует

                    key1++;//метка для понимания, что операция по резервирования прошла
                    break;
                }
                if (y.getValue().getId()==inputroomId && y.getValue().getIdHotel()==inputhotelId && y.getValue().getReserve()==true){
                    key1--;//метка для понимания, что операция по резервирования не прошла
                    System.out.println("Комната уже забронирована");

                    break;
                }
            }
//если комнат подходящих не нашлось, метка не меняется и выдает сообщение
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
//метка была увеличина, поэтому выдается сообщение о забронированной комнате
        if (key1 > 1){
            System.out.println("Забронировано для userId-" + inputuserId + " комнату-" + inputroomId + " в отеле-" + inputhotelId);
        key1=1;//возвращаем метку в начальное положение

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

    //метод, отвечающий за снятие резервирования (аналогичен bookRoom)
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

//Метод для поиска отелей по названию
    public Collection<Hotel> findHotelByName(String inputHotelName){

        List<Hotel> findhotelbyName = new ArrayList<>();
            findhotelbyName = dao.getHotelDao().stream()
                    .filter(s -> s.getHotelName().equals(inputHotelName))
                    .collect(Collectors.toList());
        return findhotelbyName;
    }

    //Метод для поиска отелей по названию города
    public Collection<Hotel> findHotelByCityout(String inputCityName){

        List<Hotel> findHotelByCityout = new ArrayList<>();
        findHotelByCityout = dao.getHotelDao().stream()
                .filter(s -> s.getCityName().equals(inputCityName))
                .collect(Collectors.toList());
        return findHotelByCityout;
    }

//метод для поиска комнат по ID
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


