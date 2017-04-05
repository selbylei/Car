package selbylei.com.lsn9_materialdesign_textinputlayout;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_password;
    private Button btn;

    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn = (Button) findViewById(R.id.btn);

        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

//        //开启计数
//        textInputLayout.setCounterEnabled(true);
        //检测长度应该低于6位数
        textInputLayout.getEditText().addTextChangedListener(new LengthTextWatcher(textInputLayout, "长度不能超过6位"));


    }


    private class LengthTextWatcher implements TextWatcher {

        private TextInputLayout textInputLayout;
        private String errorStr;
        private CharSequence beforeText;

        LengthTextWatcher(TextInputLayout textInputLayout, String errorStr) {

            this.textInputLayout = textInputLayout;
            this.errorStr = errorStr;

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            beforeText=s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (textInputLayout.getEditText().getText().toString().length() <= 6) {
                textInputLayout.setErrorEnabled(false);
            } else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorStr);
            }
        }
    }
}
