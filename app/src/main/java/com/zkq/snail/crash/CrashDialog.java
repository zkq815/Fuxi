package com.zkq.snail.crash;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author zkq
 * @since 2018/10/24
 */
public class CrashDialog extends Dialog {

    private TextView stackTraceView;
    private Button feedBackView;
    private Button copyView;
    private Button checkUpdateView;
    private Button okView;

    public CrashDialog(@NonNull Context context) {
        super(context);
    }

    public CrashDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CrashDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

//        setContentView(R.layout.dialog_crash);
//
//        stackTraceView = (TextView) findViewById(R.id.stack_trace);
//
//        feedBackView = (Button) findViewById(R.id.feed_back);
//        feedBackView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toDo();
//            }
//        });
//
//        copyView = (Button) findViewById(R.id.copy);
//        copyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShareUtils.copyToClipboard(getContext(), "crash", stackTraceView.getText().toString());
//            }
//        });
//
//        checkUpdateView = (Button) findViewById(R.id.check_update);
//        checkUpdateView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toDo();
//            }
//        });
//
//        okView = (Button) findViewById(R.id.ok);
//        okView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });
    }

    public void setStackTrace(@NonNull final Throwable t) {
        if (stackTraceView != null) {
            final StringWriter writer = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(writer);
            t.printStackTrace(printWriter);
            final String str = writer.toString();
            stackTraceView.setText(str);
        }
    }

    public static void showStackTrace(@NonNull final Activity activity, @NonNull final Throwable t) {
        final CrashDialog dialog = new CrashDialog(activity);
        dialog.show();
        dialog.setStackTrace(t);
    }

    private void toDo() {
//        MeiZuToast.show("TODO");
    }
}
