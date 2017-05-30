/**@author Mario \n @since (30.05.2017)
 * @description A class that has the current time as an attribute*/
package orderPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {
    public String timeString = "";

    public CurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        this.timeString += sdf.format(new Date());
    }
}