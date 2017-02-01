package nonamedev.a360virtualmuseum;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private TabHost tabHost;
    private LocalActivityManager localActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        tabHost = (TabHost) findViewById(R.id.foodTabHost);
        localActivityManager = new LocalActivityManager(MainActivity.this, false);
        tabHost.setup(localActivityManager);
        localActivityManager.dispatchCreate(savedInstanceState);

        //Setup TabHost
        setupTabHost();

    }   // Main Method

    private void setupTabHost() {

        TabHost.TabSpec tabSpec;

        Intent firstIntent = new Intent(MainActivity.this, FirstActivity.class);
        tabSpec = tabHost.newTabSpec("First").setIndicator("First Tab").setContent(firstIntent);

        tabHost.addTab(tabSpec);

        Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);
        tabSpec = tabHost.newTabSpec("Second");
        tabSpec.setIndicator("Second Tab").setContent(secondIntent);
        tabHost.addTab(tabSpec);

        Intent thirdIntent = new Intent(MainActivity.this, SecondActivity.class);
        tabSpec = tabHost.newTabSpec("Third");
        tabSpec.setIndicator("Third Tab").setContent(secondIntent);
        tabHost.addTab(tabSpec);

    }   // setup

}   // MainClass
