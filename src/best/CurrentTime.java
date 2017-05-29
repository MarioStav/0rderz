package best;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    public String timeString = "";

    public CurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        this.timeString += sdf.format(new Date());
    }
}