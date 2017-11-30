package com.jin.cat.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-11-29.
 */

/*public class Disease {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> one = new ArrayList<String>();
        one.add("[원인 및 증상]");


        List<String> two = new ArrayList<String>();
        two.add("살짝 잡아당겨 보고 쉽게 나올 것 같으면 그대로 꺼내고 고양이가 저항한다면 그대로 둔다." +
                "억지로 잡아당기면 장이 다치기 때문에 병원으로 이동한다.");


        List<String> three = new ArrayList<String>();
        three.add("떼어낸 후 식용유 등으로 닦아 끈적임을 제거한다." +
                "이 경우 병원에 갈 필요는 없다.");


        expandableListDetail.put("백혈구 감소증(Panleukopenia)/고양이 전염성 장염", one);
        expandableListDetail.put("항문밖으로 실이 나와있다", two);
        expandableListDetail.put("접착테이프가 달라 붙었다", three);
        return expandableListDetail;
    }
}*/

public class Disease {

    private String title, desc1, desc2, desc3, desc4;
    private boolean isExpandable;

    public Disease() {
        this.isExpandable=true;
    }

    //이렇게 string 2개짜리를 만들어야 되겠지요?
    public Disease(String title, String desc1, String desc2, String desc3, String desc4) {
        this.title = title;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.desc3 = desc3;
        this.desc4 = desc4;
    }

    //생성자 string, string, boolean 3개짜리
    public Disease(String title, String desc1, String desc2, String desc3, String desc4, boolean isExpandable) {
        this.title = title;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.desc3 = desc3;
        this.desc4 = desc4;
        this.isExpandable = isExpandable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc1() {
        return desc1;
    }
    public String getDesc2() {
        return desc2;
    }
    public String getDesc3() {
        return desc3;
    }
    public String getDesc4() {
        return desc4;
    }

   /* public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }*/

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }
}
