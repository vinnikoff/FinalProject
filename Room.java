/**
 * Created by VINNI on 02.12.16.
 */
import java.util.Objects;
import java.lang.*;

import java.lang.String;

public class Room {

    private long id;
    private long idHotel;
    private long idUser;
    private int price;
    private int persons;
    private String hotelName;
    private String cityName;
    //private String cityName;
    private boolean reserve;


    public Room(long id, long idHotel, long inputuserId, int price, int persons, String hotelName, boolean b){}

    public Room(long id, long idHotel, long idUser, int price, int persons, String hotelName, String cityName, boolean reserve) {
        this.id = id;
        this.idHotel = idHotel;
        this.idUser = idUser;
        this.price = price;
        this.persons = persons;
        this.hotelName = hotelName;
        this.cityName = cityName;
        this.reserve = reserve;
    }


    public long getId() {
        return id;
    }

    public long getIdHotel() {
        return idHotel;
    }

    public int getPrice() {
        return price;
    }

    public int getPersons() {
        return persons;
    }

    public String getHotelName() {
        return hotelName;
    }

    public boolean getReserve() {
        return reserve;
    }

    public String getCityName() {
        return cityName;
    }

    // public String getCityName() {
  //      return cityName;
  //  }

    public boolean isReserve() {
        return reserve;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdHotel(long idHotel) {
        this.idHotel = idHotel;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

//    public void setCityName(String cityName) {
  //      this.cityName = cityName;
  //  }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

   // void bookRoom(long roomId, long userId, long hotelId)
 //   {
    //    System.out.println("бронирую ...:"+roomId +" "+userId+" "+hotelId);
   // }

    /*@Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (getClass() != o.getClass())
            return false;
        Room e = (Room) o;
        return this.id == e.getId() &&
                this.persons == e.getPersons() &&
                this.price == e.getPrice() &&
                this.hotelName.equals(e.getHotelName());
    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + persons;
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        return result;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                idHotel == room.idHotel &&
                price == room.price &&
                persons == room.persons &&
                reserve == room.reserve &&
                Objects.equals(hotelName, room.hotelName) &&
                Objects.equals(cityName, room.cityName);
                //&& Objects.equals(cityName, room.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idHotel, price, persons, hotelName, cityName, reserve);
    }

    /*@Override
    public String toString()
    {
        return id + " " + price+" "+" "+persons+" "+hotelName+" "+reserve;
    }*/

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", idHotel=" + idHotel +
                ", idUser=" + idUser +
                ", price=" + price +
                ", persons=" + persons +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", reserve=" + reserve +
                '}';
    }
}
