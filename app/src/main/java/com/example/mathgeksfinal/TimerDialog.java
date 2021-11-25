package com.example.mathgeksfinal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerDialog {

    private Context context;
    private Dialog TimerDialog;

    private TextView textviewTimeUp;

    public TimerDialog(Context mContext){this.context = mContext;}

    public void timerDialog(){

        TimerDialog = new Dialog(context);
        TimerDialog.setContentView(R.layout.timer_dialog);

        final Button btTimer = (Button)TimerDialog.findViewById(R.id.bt_timer);

        btTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerDialog.dismiss();
                Intent intent = new Intent(context,PlayActivity.class);
                context.startActivity(intent);
            }
        });

        TimerDialog.show();
        TimerDialog.setCancelable(false);
        TimerDialog.setCanceledOnTouchOutside(false);

        TimerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
