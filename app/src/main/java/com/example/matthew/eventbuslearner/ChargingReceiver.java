package com.example.matthew.eventbuslearner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import de.greenrobot.event.EventBus;

/**
 * Created by Matthew on 5/18/2015.
 */
public class ChargingReceiver extends BroadcastReceiver{

    private EventBus eventBus = EventBus.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {
        ChargingEvent chargingEvent = null;

        // Get current time

        Time time = new Time();
        time.setToNow();
        String timeOfEvent = time.format("%H:%M:%S");

        String eventData = "@" + timeOfEvent + "This device started ";

        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            chargingEvent=new ChargingEvent(eventData+"charging.");
        } else if(intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            chargingEvent=new ChargingEvent(eventData+"discharging.");
        }

        eventBus.post(chargingEvent);
    }
}
