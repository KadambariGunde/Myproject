package myapplication.bits;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Trainer on 12/22/2015.
 */
public class Utils {

    public static void toastIt(Context context, String msg){
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0,0);
        toast.show();
    }
}
