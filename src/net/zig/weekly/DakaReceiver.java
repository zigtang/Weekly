package net.zig.weekly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DakaReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
			context.startActivity(new Intent(context, NotificationActivity.class));
		}
		
		if(intent.getAction().equals(Intent.ACTION_DATE_CHANGED)){
			context.startActivity(new Intent(context, NotificationActivity.class));
		}
	}

}
