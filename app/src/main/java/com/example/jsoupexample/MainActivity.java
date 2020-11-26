package com.example.jsoupexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TableItem> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect
                            ("https://profkomstud.khai.edu/schedule/lecturer/pjavka-evgenij-valentinovich")
                            .get();

                    Elements tables = doc.getElementsByClass("table");
                    Element table = tables.get(0);

                    Elements table_data = table.getElementsByTag("td");
                    //Log.d("devcpp","table_data size -> "+table_data.size());

                    String currentDay = "";

                    int indexFirstLesson = 1;
                    int indexSecondLesson = 3;
                    int indexThirdLesson = 5;
                    int indexFourthLesson = 7;
                    int currentLesson = -1;
                    boolean flag = false;

                    for(int i = 0; i <table_data.size();i++){
                        //Log.d("devcpp",i+" - "+table_data.get(i).html());
                        if(table_data.get(i).html().equals("<i class=\"fal fa-alarm-exclamation\"></i>")){
                            table_data.remove(i);
                        }
                        if (table_data.get(i).html().equals("Понеділок")){
                            currentDay ="Понеділок";
                            continue;
                        }
                        if (table_data.get(i).html().equals("Вівторок")){
                            currentDay = "Вівторок";
                            continue;
                        }
                        if (table_data.get(i).html().equals("Середа")){
                            currentDay = "Середа";
                            continue;
                        }
                        if (table_data.get(i).html().equals("Четвер")){
                            currentDay = "Четвер";
                            continue;
                        }
                        if (table_data.get(i).html().equals("П'ятниця")){
                            currentDay = "П'ятниця";
                            continue;
                        }

                        if (table_data.get(i).html().equals("08:00 - 09:35")){
                            currentLesson = indexFirstLesson;
                            flag = true;
                            continue;
                        }
                        if (table_data.get(i).html().equals("09:50 - 11:25")){
                            currentLesson = indexSecondLesson;
                            flag = true;
                            continue;
                        }
                        if (table_data.get(i).html().equals("11:55 - 13:30")){
                            currentLesson = indexThirdLesson;
                            flag = true;
                            continue;
                        }
                        if (table_data.get(i).html().equals("13:45 - 15:20")){
                            currentLesson = indexFourthLesson;
                            flag = true;
                            continue;
                        }

                        String[] lessonInfo = getLessonInfo(table_data.get(i).html());
                        if(lessonInfo[0].equals("") && lessonInfo[1].equals("") && lessonInfo[2].equals("")){
                            continue;
                        }

                        if(flag){
                            //Log.d("devcpp",currentDay+" "+currentLesson+" - "+lessonInfo[1]+"; "+ lessonInfo[2]+"; "+ lessonInfo[0]);
                            data.add(new TableItem(-1,currentDay,"",currentLesson,lessonInfo[1],lessonInfo[2],lessonInfo[0]));
                        }else{
                            //Log.d("devcpp",currentDay+" "+(currentLesson+1)+" - "+lessonInfo[1]+"; "+ lessonInfo[2]+"; "+ lessonInfo[0]);
                            data.add(new TableItem(-1,currentDay,"",currentLesson+1,lessonInfo[1],lessonInfo[2],lessonInfo[0]));
                        }
                        flag = false;

                    }


                    for(int i = 0; i< data.size();i++){
                        Log.d("devcpp",data.get(i).toString());
                    }


                } catch (IOException e) {
                    Log.d("devcpp",e.getMessage());
                }
            }
        });
        thread.start();
    }

    String[] getLessonInfo(String str){
        String[] result = {"","",""};
        if(!str.equals("<i class=\"fal fa-calendar-minus\"></i>")){
            String[] list = str.split(",");
            if (list[0].contains("<a")){
                if (list.length >= 3){
                    int indexBegin = list[0].indexOf("data-content=\"")+14;
                    int indexEnd = list[0].indexOf("<br>\">");
                    String groups = list[0].substring(indexBegin, indexEnd);
                    groups = groups.replace("<br>",",");
                    result[0] = groups;
                    if(list.length == 3){
                        result[1] = list[1];
                        result[2] = list[2];
                    }else if(list.length > 3){
                        result[2] = list[list.length-1];
                        for(int i = 1; i<list.length-1;i++){
                            result[1] = result[1] + list[i];
                        }
                    }
                }
            }else{
                result[0] = list[0];
                result[1] = list[1];
                result[2] = list[2];
            }
        }
        return result;
    }
}