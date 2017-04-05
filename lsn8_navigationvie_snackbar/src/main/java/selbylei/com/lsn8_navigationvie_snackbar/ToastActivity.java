package selbylei.com.lsn8_navigationvie_snackbar;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {

    private TextView tv_model;
    private Button btn;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        tv_model = (TextView) findViewById(R.id.tv_model);

        btn = (Button) findViewById(R.id.btn_toast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("selbylei");
            }
        });

        btn1 = (Button) findViewById(R.id.btn_snakerbar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnakeBar(v);
            }
        });
    }

    private void showSnakeBar(View view) {
        /**
         *  LENGTH_INDEFINITE表示无穷，需要点击才会消失
         *  不能设置多个action，再次设置会覆盖之前的action
         */

        Snackbar snackbar = Snackbar.make(view, "是否开启加上模式", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_model.setText("加速模式");
            }
        });


        //snackbar显示和隐藏的回调
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
            }

            @Override
            public void onShown(Snackbar transientBottomBar) {
                super.onShown(transientBottomBar);
            }
        });
        snackbar.show();
    }


    public void showCustomToast(String str) {

        Toast toast = new Toast(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(str);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


}
