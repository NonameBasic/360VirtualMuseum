package nonamedev.a360virtualmuseum;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by Administrator on 1/31/2017.
 */

public class MySynchronize extends AsyncTask<String ,Void, String>
{
    //explicit
    private Context context;

    public MySynchronize(Context context)
    {
        this.context = context;
    }


            @Override
            protected String doInBackground(String... strings)
            {
                try {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.url(strings[0]).build();
                    Response response = okHttpClient.newCall(request).execute();
                    return response.body().string();
                }
                catch (Exception e) {
                    Log.d("1FebV1", "e doin =>"+e.toString());
                    return null;
                }

            }
}
