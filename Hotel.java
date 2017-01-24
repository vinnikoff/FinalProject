/**
 * Created by VINNI on 22.01.17.
 */
public class Hotel {

    private long id;
    private String hotelName;
    private String cityName;

    public Hotel(long id, String cityName, String hotelName) {
        this.id = id;
        this.hotelName = hotelName;
        this.cityName = cityName;
    }

    public long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
