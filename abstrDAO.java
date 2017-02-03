
import java.util.*;
/**
 * Created by VINNI on 26.01.17.
 */

//дженерики пока особо не используются, для дальнейшей оптимизации кода когда-то в будущем ))
public interface abstrDAO <T, S, E, R> {

  List<T> getUserDao();
    List<S> getHotelDao();
   Map<E, R> maproomoutDao();

}
