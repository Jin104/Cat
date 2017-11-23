package com.jin.cat.Knowledge.Food.Health;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by inhye on 2017-11-23.
 */

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> three = new ArrayList<String>();
        three.add("소독약으로 상처를 닦고 꽉 조이지 않도록 붕대를 감고 병원으로 이동한다." +
                "피가 멈추지 않을 때에는 상처에서 2~3cm 떨어져 심장과 가까운 쪽을 천으로 묶는다.");


        List<String> two = new ArrayList<String>();
        two.add("살짝 잡아당겨 보고 쉽게 나올 것 같으면 그대로 꺼내고 고양이가 저항한다면 그대로 둔다." +
                "억지로 잡아당기면 장이 다치기 때문에 병원으로 이동한다.");


        List<String> one = new ArrayList<String>();
        one.add("떼어낸 후 식용유 등으로 닦아 끈적임을 제거한다." +
                "이 경우 병원에 갈 필요는 없다.");


        expandableListDetail.put("자상, 출혈", three);
        expandableListDetail.put("항문밖으로 실이 나와있다", two);
        expandableListDetail.put("접착테이프가 달라 붙었다", one);
        return expandableListDetail;
    }
}