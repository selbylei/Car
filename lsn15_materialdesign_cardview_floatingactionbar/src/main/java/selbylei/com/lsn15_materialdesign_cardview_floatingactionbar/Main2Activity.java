package selbylei.com.lsn15_materialdesign_cardview_floatingactionbar;

import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private boolean reverse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float toDegree = reverse ? -180 : 180;
                ObjectAnimator animator = ObjectAnimator.ofFloat(v, "rotation", 0.0f, toDegree).setDuration(400);
                animator.start();
                reverse = !reverse;
            }
        });
    }
}
