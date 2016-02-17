package myapplication.bits;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Trainer on 12/24/2015.
 */
public class RatingTask extends AsyncTask<Integer, Float, String> {

    private Activity activity;
    private RatingBar rbResult;
    private static float ratingCount = 0;

    public RatingTask(Activity activity){
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rbResult = (RatingBar) activity.findViewById(R.id.rbResult);
    }

    @Override
    protected String doInBackground(Integer... params) {

        try{
            for(Integer integer: params){
                publishProgress(ratingCount++);
                Thread.sleep(integer);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "Rating Completed";
    }

    @Override
    protected void onProgressUpdate(Float... values) {
        super.onProgressUpdate();
        rbResult.setRating(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(activity.getBaseContext(), s, Toast.LENGTH_SHORT).show();
        rbResult.setRating(5);
    }
}
