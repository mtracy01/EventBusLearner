package com.example.matthew.eventbuslearner;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.greenrobot.event.EventBus;


public class DisplayActivity extends Activity {

    private EventBus bus = EventBus.getDefault();

    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new TextView(this);

        view.setTextSize(20f);
        view.setPadding(20,20,20,20);
        view.setText("Waiting for events...");

        setContentView(view);

        //Register a subscriber
        bus.register(this);
    }

    @Override
    protected void onDestroy() {
        // Unregister
        bus.unregister(this);
        super.onDestroy();
    }

    public void onEvent(ChargingEvent event){
        view.setText(view.getText() + "\n" + event.getData());
    }

}
