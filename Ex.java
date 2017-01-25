/**
 * Created by VINNI on 25.01.17.
 */
import java.io.IOException;

public class Ex extends IOException{
    public Ex() {
        super("Вы внесли неверную информацию (неверный формат данных)!!!");
        System.exit(0);
    }
}
