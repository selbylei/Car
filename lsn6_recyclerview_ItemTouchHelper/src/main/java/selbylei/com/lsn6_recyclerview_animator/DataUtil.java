package selbylei.com.lsn6_recyclerview_animator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selbylei on 17/3/21.
 */

public class DataUtil {

    public static List<QQMessage> initData(){
        ArrayList<QQMessage> list =new ArrayList<>();
        list.add(new QQMessage(R.drawable.logo_1,"name1","第1条消息","时间1"));
        list.add(new QQMessage(R.drawable.logo_2,"name2","第2条消息","时间2"));
        list.add(new QQMessage(R.drawable.logo_3,"name3","第3条消息","时间3"));
        list.add(new QQMessage(R.drawable.logo_4,"name4","第4条消息","时间4"));
        list.add(new QQMessage(R.drawable.logo_5,"name5","第5条消息","时间5"));
        list.add(new QQMessage(R.drawable.logo_6,"name6","第6条消息","时间6"));
        list.add(new QQMessage(R.drawable.logo_6,"name7","第7条消息","时间7"));
        list.add(new QQMessage(R.drawable.logo_3,"name8","第8条消息第8条消息第8条消息第8条消息第8条消息第8条消息","时间8"));
        list.add(new QQMessage(R.drawable.logo_2,"name9","第9条消息","时间9"));
        list.add(new QQMessage(R.drawable.logo_1,"name10","第10条消息","时间10"));
        list.add(new QQMessage(R.drawable.logo_3,"name11","第11条消息","时间11"));
        list.add(new QQMessage(R.drawable.logo_5,"name12","第12条消息","时间12"));

        return list;
    }
}
