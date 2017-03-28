package selbylei.com.lsn6_recyclerview_animator;

/**
 * Created by selbylei on 17/3/21.
 */

public class QQMessage {
    private int resIcon;
    private String name;
    private String msg;
    private String time;


    public QQMessage(int resIcon, String name, String msg, String time) {
        this.resIcon = resIcon;
        this.name = name;
        this.msg = msg;
        this.time = time;
    }


    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
