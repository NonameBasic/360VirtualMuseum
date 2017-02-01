package nonamedev.a360virtualmuseum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Authen extends AppCompatActivity {

    private EditText userEditText, passEditText;
    private String userString, passString;
    private String[] userStrings = new String[4];
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);


    }

    public void clickLogin(View view) {
        userString = userEditText.getText().toString().trim();
        passString = passEditText.getText().toString().trim();


        if (userString.equals("") || passString.equals("")) {
            MyAlert myAlert = new MyAlert(Authen.this);
            myAlert.myDialog(R.drawable.doremon48, "Has Space", "Please fill all blank");
        } else {
            checkUserPass();
        }

    }

    private void checkUserPass() {
        try {
            MySynchronize mySynchronize = new MySynchronize(Authen.this);
            MyConstant myConstant = new MyConstant();
            mySynchronize.execute(myConstant.getUrlUser());
            String strJASON = mySynchronize.get();
            Log.d("1FebV1", "JSON =>" + strJASON);
            JSONArray jsonArray = new JSONArray(strJASON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) //User = table column name
                {
                    userStrings[0] = jsonObject.getString("id"); // id = table column name
                    userStrings[1] = jsonObject.getString("User");
                    userStrings[2] = jsonObject.getString("Password");
                    userStrings[3] = jsonObject.getString("Name");
                    aBoolean = false;
                }
            }
            if (aBoolean)
            {
                MyAlert myAlert = new MyAlert(Authen.this);
                myAlert.myDialog(R.drawable.nobita48,"User False","No User Exist");

            }
            else if(!passString.equals(userStrings[2]))
            {
                MyAlert myAlert = new MyAlert(Authen.this);
                myAlert.myDialog(R.drawable.nobita48,"Password False","Wrong Password");
            }
            else
            {
                //user password true
                Toast.makeText(Authen.this,"Welcome"+userStrings[3],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Authen.this,ServiceActivity.class);
                intent.putExtra("Login",userString);
                startActivity(intent);
                finish();
            }


        } catch (Exception e) {
            Log.d("1FebV1", "e CheckUserPass =>" + e.toString());
        }

    }//checkUserPass
}
