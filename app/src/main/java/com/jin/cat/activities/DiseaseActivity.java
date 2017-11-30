package com.jin.cat.activities;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.jin.cat.Knowledge.Food.Health.CustomExpandableListAdapter;
import com.jin.cat.Knowledge.Food.Health.ExpandableListDataPump;
import com.jin.cat.R;
import com.jin.cat.adapter.DiseaseAdapter;
import com.jin.cat.models.Disease;
import com.jin.cat.models.ExpandableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sunmoon on 2017-11-30.
 */


/*public class DiseaseActivity extends AppCompatActivity {

    ExpandableListView disease;
    DiseaseAdapter expandableListAdapter;
    List<String> diseaseTitle;
    HashMap<String, List<String>> tv_cause1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        disease = (ExpandableListView) findViewById(R.id.disease);
        tv_cause1 = ExpandableListDataPump.getData();
        diseaseTitle = new ArrayList<String>(tv_cause1.keySet());
        expandableListAdapter = new DiseaseAdapter(this, diseaseTitle, tv_cause1);
        disease.setAdapter(expandableListAdapter);

        disease.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
        {
            @Override
            public void onGroupExpand(int groupPosition) {}
        });



        disease.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {
            @Override
            public void onGroupCollapse(int groupPosition) {}
        });



        disease.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }
}*/


public class DiseaseActivity extends AppCompatActivity {

    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;
    List<Disease>items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        list = (RecyclerView)findViewById(R.id.disease);
        list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        DiseaseAdapter adapter = new DiseaseAdapter(items);
        list.setAdapter(adapter);
        setData();
    }

    private void setData() {

/*for(int i=0; i<10; i++) {
            ExpandableList item = new ExpandableList ("item", "child");
            items.add(item);
        }*/

        //생성자가 string 2개인 disease를 만들고 싶은데 Disease보면 생성자가 2개인게 없어요
        Disease dis1 = new Disease("백혈구 감소증(Panleukopenia) /\n"+ "고양이 전염성 장염",
                "[원인 및 증상]",
                "고양이 홍역이라고 불리는 전염성이 높은 바이러스가 발병 원인이며 주로 어린 고양이를 죽게 하는 치명적인 질병\n" +
                        "임상 증상으로 식욕부진과 갈증 증세를 나타내며 움직이기 싫어하고 웅크리고 있으며 고열과 구토를 하며 탈수증세와 설사 및 혈변을 나타낸다 백혈구 수가 현저하게 줄어들어 병원체에 대한 저항력이 떨어지며 병이 심해지면 탈수로 인해 체온이 낮아지고 심한 경우 죽게 된다. 사람의 옷이나 신발 및 손을 통해서도 옮겨질 수 있으므로 감염된 고양이가 접한 후에는 철저한 소독이 필요하다.\n",
                "[예방 및 치료]",
                "수분과 영양을 충분히 보급해 주면 치료에 많은 도움이됨 전염성이 매우 강해 다른 고양이들과 격리하고 수의사의 진료를 받아야 한다.\n");
        items.add(dis1);

        Disease dis2 = new Disease("과민성 알레르기 반응(Anaphylaxis)",
                "[원인 및 증상]",
                "약물을 복용하거나 주사할 때 드물게 발생하는 급성 및 과민성 알레르기 반응으로 쇼크 호흡곤란 심장이상을 나타내며 심하면 사망까지 한다. 가끔 예방 접종의 부작용으로서 몇 분에서 하루 사이에 발생할 수도 있다.\n"+ "백신에 의한 알레르기 반응은 광견병 고양이 백혈병 등에서 관찰된다.\n" +
                        "증상은 갑작스러운 구토, 설사, 안면부종, 발작을 일으키며, 체온이 떨어지고 심장박동이 빨라지고, 심할 경우 혼수상태에 빠지거나 죽게된다.\n",
                "[예방 및 치료]",
                "응급상황 이므로 동물 병원으로 옮겨 가능한 빨리 수의사의 진료를 받아야 한다. 예방 접종 전에 항히스타민 제제를 주사하여야 한다. 아토피성 피부염은 알레르기 계절에 예방 접종을 할 경우의 발생 가능성은 높으므로 계절과 알레르기 관계를 고려하여 예방 접종을 해 주어야 한다.\n");
        items.add(dis2);

        Disease dis3 = new Disease("바이러스성 비기관염(Feline Viral Rhinotracheiris, FVR)",
                "[원인 및 증상]",
                "고양이의 herpesvirus에 의해서 발병하며, 전형적인 증상으로 초기에는 심한 콧물 및 눈물이 나오며, 이것이 곧 바로 점액성 및 화농성 분비물로 변한다. 궤양성 결막염 또는 설염이 발생할 수 도 있다.\n",
                "[예방 및 치료]",
                "발병 즉시 수의사의 진료를 받아야 하며 예방 접종이 필수적이다.");
        items.add(dis3);

        Disease dis4 = new Disease("칼리시 바이러스 병(Feline Calici Virus, FCV)",
                "[원인 및 증상]",
                "칼리씨 바이러스는 주로 구강점막과 호흡기에 감염된다. 접총에 의한 감염으로 약 3일 동안의 잠복기를 거쳐 구강 비강 그리고 혀에 궤양이 생기는 것이 특징이다.\n" +
                        "임상증상은 발열, 원기 부족, 식욕부진, 침울 및 타액과 콧물 분비 등의 증상을 나타내고, 콧물과 눈물이 나고, 결막염, 혀, 입술, 입안 및 콧등에 궤양이 발생하며 폐렴이 합병증으로 나타날 수 있다. 대부분 2주일 안에 회복되나 폐렴을 일으키는 경우 사망에 이를 수 있다. 또한 지속적으로 극소량의 병원균 바이러스를 체외로 배출시켜 다른 고양이를 감염시킬 수 있다.\n",
                "[예방 및 치료]",
                "발병하면 수의사에게 진료를 받아야 하며, 발병에 대한 예방을 위해 예방 접종을 실시하여야 한다.\n");
        items.add(dis4);

        Disease dis5 = new Disease("클라미디오시스병(Chlamydiosis)",
                "[원인 및 증상]",
                "세균의 일종인 클라미디아가 고양이에게 결막염과 호흡기 질환을 일으킨다. 바이러스성 결막염을 동반하고 경과가 긴 것이 특징이며, 바이러스성 결막염과 쉽게 구별할 수도 없다.\n",
                "[예방 및 치료]",
                "항생 물질 등으로 치료되나 예방 접종이 가장 바람직하다. 백신은 지속시간이 길지 않고 예방 효과가 불완전하며 백신자체에 대한 부작용의 가능성도 높다.\n");
        items.add(dis5);


        Disease dis6 = new Disease("고양이 백혈병(Feline Leukemia Virus)",
                "[원인 및 증상]",
                "빈혈이나 면역력의 저하, 유산, 신장병 등 여러 질환이 원인이 된다. 감염되어 발병한 고양이는 3-4년이내에 죽게 된다. 타액,콧물, 오줌, 변, 및 벼룩에 의해서 감염되며 어린 고양이는 주로 모유를 통해 감염, 2차 감염에 따라 다양한 발병 양상을 보이나, 개나 사람에게는 감염되지 않는다. 특히 어린 고양이나 나이든 고양이에게 주로 발생한다. 임상 증상으로는 발열, 빈혈, 식용저하, 의기소침이 관찰되며 확실한 치료법이 없고 대부분 극심한 통증 후에 죽는다.\n",
                "[예방 및 치료]",
                "병에 걸린 고양이는 다른 고양이들과 격리시켜 접촉을 피해야 한다. 효과적인 예방법은 예방 접종을 해주는 것이다. 질병이 발생하면 곧바로 수의사의 진료를 받아야 한다. 혈액검사로 감염여부를 확인할 수 있으며 예방 접종은 9주령에서 3-4주 간격으로 2회 접종 한다. 1년뒤 추가 접종하며 완전히 실내에 격리되어 생활하는 고양이의 경우 추가접종은 필요하지 않다.\n");
        items.add(dis6);

        Disease dis7 = new Disease("전염성 기관지염(Rhinotracheitis)",
                "[원인 및 증상]",
                "바이러스나 세균에 의해 감염되는 기관지염의 일종으로 전염성이 강하므로 격리 시켜야 한다. 일반적으로 3-4일 만에 고열로 인하여 눈빛이 흐려지고 눈물이 나거나 눈곱이 끼며 기침 혹은 재채기를 심하게 한다. 호흡기관 및 기관지의 이상과 이로 인해 소화기 질환의 합병증을 유발하는 경우가 많다 또한 설사를 동반하는 경우도 있다.\n",
                "[예방 및 치료]",
                "질병이 발생하면 속히 수의사의 진료를 받아야 하고 반드시 예방 접종을 해 주어야 한다.");

        items.add(dis7);

        Disease dis8 = new Disease("고양이 전염성 복막염(Feline infectious peritonitis)",
                "[원인 및 증상]",
                "바이러스가 원인이며 복막염을 일으켜 복수가 차는 병이다. 복막이나 가슴에 물이 생기는 경우와 간장이나 신장에 딱딱한 물질이 생기는 두 가지 경우가 있는데 이병에 걸리면 원기 부족 식욕 부진에다 열이 오르며 설사로 인해 몸도 매우 야위어 진다.\n" +
                        "감염된 고양이의 체액을 통해 배출된 바이러스를 섭취, 흡입함으로써 감염된다. 어린 고양이로부터 3연령의 고양이에서 발생 빈도가 높다. 여러 마리의 고양이를 함께 키우면 치사율이 5%이나 한마리만 집에서 키우는 고양이의 치사율은 낮다.\n" +
                        "임상증상으로는 복수 발열 식욕저하 체중감소 등이 관찰된다. 병의 발전 양상에 따라 복부와 흉부에 염증이 생기고 장액이 축적되어 흉수와 복수가 관찰되는 습성형과, 장액이 축적이 관찰되지 않는 건성형이 있다.\n",
                "[예방 및 치료]",
                "질병이 발생하면 속히 수의사의 진료를 받아야 하고 반드시 예방 접종을 해 주어야 한다.");
        items.add(dis8);

        Disease dis9 = new Disease("요로 결석증 (Kidney stones)",
                "[원인 및 증상]",
                "신장염 방광염 등의 염증이나 결석이 축적되어 요로 폐쇄 등의 원인이 될 수 있다. 그 밖에 비타민 A부족과, 호르몬 분비의 이상 등이 원인으로 수고양이에서 많이 발생한다.\n"+ "신우, 수뇨관, 방광 및 요도 등 비뇨기계가 결석으로 막혀 오줌을 배설할 수 없는 병이기 때문에 생식기를 빈번히 핥거나 자주 소변 자세를 취한다. 증세가 심하면 기운을 잃고 탈수 증세를 보이고 구토를 반복하는 요독증을 일으키기도 한다. 요도 폐쇄로 방광염 등이 발생하여 혈뇨와 심한 통증을 나타낸다.\n",
                "[예방 및 치료]",
                "약물을 복용하여 결석을 용해 시키는 방법과 결석제거 수술이 있다. 수술 후 결성의 성분을 분석하여 처방식을 먹이거나 결석 유발 성분이 함유된 음식을 먹이지 말아야 한다.\n");
        items.add(dis9);

        Disease dis10 = new Disease("회충(Roundworm), 촌충(Tapeworm)",
                "[원인 및 증상]",
                "기생충은 종류에 따라 피부, 폐, 심장, 간, 소화기관내에 거의 모든 장기에 분포한다. 회충과 촌충은 동물의 소화 기관에 기생하는 대표적인 기생충으로 설사, 피모의 이상 발육 부전 등의 증상을 나타낸다. 회충은 어미 고양이가 수유 중에 유즙을 통해서 전파가 되며 촌충은 벼룩에 의해서 전파되고 주로 어린 고양이보다 어른 고양이에게 감염된다. 변이나 항문주위에 하양 쌀알 모양을 띄고 있는 성충의 분절이 움직이는게 관찰된다. 이러한 분절은 나중에 수백개의 충란을 배출한다.\n" +
                        "임상증상으로는 설사. 피모 이상, 성장 부진 등이 나타날 수 있으며 심한 경우 간, 폐, 뇌까지 손상을 입힐 수 있다.\n",
                "[예방 및 치료]",
                "구충은 3주령부터 구충제를 2-4주 간격으로 3차 구충까지 실시하고 추가 구충을 실시한다. 따라서 충란을 배출하기 전에 규칙적으로 구충제를 먹여야 하고 주변환경을 청결히 해야 한다. 촌충은 이 벼룩을 통해서도 감염되므로 이를 철저히 박멸시키는 것과 주위 환경을 청결하게 하는 것이 중요하다.\n");
        items.add(dis10);

        Disease dis11 = new Disease("람블린 편모증(Giardia)",
                "[원인 및 증상]",
                "생활 주변에 상재 하는 기생충으로서 물을 마시거나 발바닥을 핥을 때 쉽게 감염되며 체내에서 다시 분변으로 배출되어 계속 감염이 이뤄진다. 주로 여러 마리를 키우는 어린 고양이에서 발병한다. 동물의 장관에 기생하나 조직속으로는 침입하지 않으므로 설사증상이 대표적이다\n" +
                        "임상증상이 관찰되지 않는다. 동물에 있어서 통증에 관해서는 밝혀진 바가 없으나 사람에게 감염되면 극심한 복통을 보인다.\n",
                "[예방 및 치료]",
                "제조업체 측에서는 매 1년마다 추가 접종을 주장하지만 이미 미국에서는 3년 예방접종 프로그램이 사용되고 있다 우리나라는 광견병 상재 지역 이므로 매년 추가접종을 권하고 있다.\n");
        items.add(dis11);

        Disease dis12 = new Disease("콕시디움증(Coccidia)",
                "[원인 및 증상]",
                "콕시디움은 설사를 일으키는 원충의 일종으로 감염된 고양이의 배설물에서 배출된다. 감염된 배설물은 곧바로 청결하게 치워야 한다. 주로 새끼 고양이에게 감염이 잘 되며 물 같은 설사나 혈변을 배설하거나 심하면 탈수 및 쇠약증세까지 나타난다.\n",
                "[예방 및 치료]",
                "설파제 등으로 치료가 가능하나, 설사가 심하면 탈수가 되어 위험할 수 있으므로 동물병원의 수의사로부터 진료를 받는 것이 안전하다.\n");
        items.add(dis12);

        Disease dis13 = new Disease("톡소플라즈마증(Toxoplasmosis)",
                "[원인 및 증상]",
                "원충인 톡소플라즈마에 의해 발생한다.\n" +
                        "임상증상으로는 열이 나고 원기 부족 식욕 감퇴 구토 설사 호흡곤란 황달 및 눈의 이상 등 여러가지 증상이 나타난다.\n",
                "[예방 및 치료]",
                "감염되지 않도록 평소에 건강 및 위생관리를 철저히 하여야 한다.");
        items.add(dis13);

        Disease dis14 = new Disease("백선증(Ringworm)",
                "[원인 및 증상]",
                "곰팡이에 의해 발생하는 것으로 주로 털 관리가 잘 안되거나 목욕 후 털의 건조가 제대로 되지 않는 경우 발생하는 피부병의 일종이다. 주로 털이 많이 빠지고 가려움증을 동반하며 몸 전체로 퍼진다. 탈모 부분은 회색을 띠거나 피부에 하얀 비듬이 많이 나와서 동전 모양을 나타낸다.",
                "[예방 및 치료]",
                "고양이의 털은 섬세하고 촘촘하여 목욕 후 습기가 남아 있지 않도록 잘 건조시켜야 한다. 먹는 약, 약용 삼푸와 연고를 함께 사용하면 빠른 치료 효과를 볼수 있다. 평소에는 햇볕을 많이 받도록 해야 하고 통풍이 잘 되는 곳에 머물게 해야 한다. 이미 감염된 고양이에서는 증상이 잠시 완화될 뿐 곧 재발한다.\n");
        items.add(dis14);

        Disease dis15 = new Disease("벼룩",
                "[원인 및 증상]",
                "벼룩의 종류는 1000 종류인데 고양이에게 기생하는 것은 고양이 벼룩과 개벼룩이다 고양이 벼룩은 고양이의 피를 흡혈하기 때문에 퇴치가 가장 중요하다. 고양이를 동물 호텔에 맡기거나 다른 고양이와 서로 접촉시켰을 때 벼룩이 전파 될 가능성은 충분하다. \n" +
                        "또한 벼룩은 고온 다습한 장마철에 번지기 쉽다. 충란은 3주만에 성충이되며 강한 생명력을 지니고 있다. \n" +
                        "또한 기온이 낮은 시기에는 번데기의 상태로 겨울을 넘긴다. 벼룩이 피를 빨 때 수액을 내보내 가려움과 알레르기 피부염을 일으키는 원인이 된다. 물리면 주위에 습진이 나타나고 가려워 이곳을 물거나 긁으면 화농과 탈모를 일으키게 된다.\n",
                "[예방 및 치료]",
                "세면기에 물을 받고 세제를 약간 푼 다음 빗으로 빗어 벼룩이 나오면 헹구어 떨어뜨린다. 털이 엉키기 쉬운 장모종의 경우는 빗으로 벼룩의 움직임을 막아 핀셋으로 잡는 것도 좋은 방법이다.\n" +
                        " 몸 전체에 물을 적신 후 벼룩용 샴푸로 거품을 내어 두었다가 잘 헹구어 준다. \n" +
                        "벼룩은 피를 빨지 않아도 먼지에 섞여있는 유기물을 먹고 살며 알이나 유충 번데기 스프레이를 뿌린 후 소각한다. \n" +
                        "최근에는 안전성이 높은 허브 성분의 벼룩 방지 목걸이를 이용해 보는 것도 좋다.\n");
        items.add(dis15);

        Disease dis16 = new Disease("털 뭉치증 (Hair ball)",
                "[원인 및 증상]",
                "장모종 고양이에게서 많이 나타난다. 고양이가 몸을 핥을 때 잘못해서 털을 삼키게 되면 위 속에 쌓이게 되고 그 안에서 털들이 공 모양의 헤어볼을 만들기 때문에 생기는 병이다.\n" +
                        "식욕은 있으나 먹으면 토해 버리고 기운이 없어지고 점점 야위어 간다. 위 속에 털이 많이 쌓이게 되면 풀이나 화초등을 먹으려고 하는데 이것은 풀을 먹어서 위를 자극해 털을 토하고자 하는 행동이다.\n",
                "[예방 및 치료]",
                "가벼운 증세의 경우 샐러드유를 2-3스푼 정도 먹이면 변과 함께 털 뭉치를 배설하거나 토해낼 수도 있다. 매일 털 관리를 게을리 하지 말아야 하며 특히 털갈이를 할 때에는 빠진 털이나 불필요한 털을 브러시로 완전히 제거해 주어야 한다.\n");
        items.add(dis16);

        Disease dis17 = new Disease("고양이 광견병(Rabies)",
                "[원인 및 증상]",
                "감염된 동물의 타액속의 바이러스가 상처를 통해서 감염된다.\n" +
                        "임상증상으로는 바이러스가 뇌와 척수로 이동하여 물질에 대한 과민반응으로 목의 통증으로 인해 물을 못 마시고 마비 이식증을 일으킨다.\n",
                "[예방 및 치료]",
                "광견병은 고양이 등 모든 온혈동물 치명적인 전염으로 예방 접종이 필요하다.");
        items.add(dis17);
    }
}

