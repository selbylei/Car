package selbylei.com.lsn10_materialdesign_toolbar_searchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;




public class TitleBar extends RelativeLayout {

    private Context context;
    private View view;
    private RelativeLayout layout;

    private LinearLayout leftBtn;
    private LinearLayout rightBtn;
    private TextView title, rightTextView;//rightTag;

    private ImageView leftIcon, rightIcon;

    public TitleBar(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.title_bar, this, true);
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        leftBtn = (LinearLayout) view.findViewById(R.id.titlebar_left_btn);
        rightBtn = (LinearLayout) view.findViewById(R.id.titlebar_right_btn);
        title = (TextView) view.findViewById(R.id.titlebar_title_text);
        rightTextView = (TextView) view.findViewById(R.id.titlebar_right_text);
        leftIcon = (ImageView) view.findViewById(R.id.titleBar_left_img);
        rightIcon = (ImageView) view.findViewById(R.id.titlebar_right_img);
        setDefaultListner();
    }

    private void setDefaultListner() {
        leftBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (context instanceof AppCompatActivity) {
                    AppCompatActivity activity = (AppCompatActivity) context;
                    activity.finish();
                }
            }
        });

    }

    public TitleBar setViewBackColor(int resid) {
        layout.setBackgroundColor(resid);
        return this;
    }

    public TitleBar setViewBackRes(int resid) {
        layout.setBackgroundResource(resid);
        return this;
    }

    public TitleBar setViewBackRes(Drawable drawable) {
        layout.setBackgroundDrawable(drawable);
        return this;
    }

    public TitleBar setLayouHeight(float dip) {
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, dip2px(context, dip));
        layout.setLayoutParams(lp);
        return this;

    }

    public TitleBar setTitle(String titleText) {
        if (TextUtils.isEmpty(titleText)) {
            title.setText("");
        } else {
            title.setText(titleText);
        }
        return this;
    }

    public TitleBar setTitleColor(int resid) {
        title.setTextColor(ContextCompat.getColor(context, resid));
        return this;
    }

    public TitleBar setTitleSize(float size) {
        title.setTextSize(size);
        return this;
    }

    public TitleBar showLeft(boolean isVisiable) {
        if (isVisiable) {
            leftBtn.setVisibility(View.VISIBLE);
        } else {
            leftBtn.setVisibility(View.GONE);
        }
        return this;
    }

    public TitleBar showRight(boolean isVisiable) {
        if (isVisiable) {
            rightBtn.setVisibility(View.VISIBLE);
        } else {
            rightBtn.setVisibility(View.GONE);
        }
        return this;
    }

    public TitleBar setLeftContent(int resId) {
        if (resId != 0) {
            leftIcon.setImageResource(resId);
        }
        return this;
    }

    public TitleBar setLeftBackground(int resId) {
        leftBtn.setBackgroundResource(resId);
        return this;
    }

    public TitleBar setRightTextContent(String content) {
        if (content != null) {
            rightIcon.setVisibility(View.GONE);
            rightTextView.setVisibility(View.VISIBLE);
            rightTextView.setText(content);
        }
        return this;
    }

    public TitleBar setRightTextBackground(int id) {
        if (id != 0) {
            rightIcon.setVisibility(View.GONE);
            rightIcon.setVisibility(View.VISIBLE);
            rightTextView.setBackgroundResource(id);
        }
        return this;
    }

    public TitleBar setRightImgContent(int id) {
        if (id != 0) {
            rightTextView.setVisibility(View.GONE);
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setImageResource(id);
        }
        return this;
    }

    /**
     * 设置左边的事件
     */
    public TitleBar setLeftListener(OnClickListener listener) {
        if (leftBtn.getVisibility() == View.VISIBLE) {
            leftBtn.setOnClickListener(listener);
        }
        return this;
    }

    public TitleBar setRightListener(OnClickListener listener) {
        if (rightBtn.getVisibility() == View.VISIBLE) {
            rightBtn.setOnClickListener(listener);
        }
        return this;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
