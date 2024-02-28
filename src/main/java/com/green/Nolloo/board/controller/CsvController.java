package com.green.Nolloo.board.controller;

import com.opencsv.CSVReader;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/csv")
public class CsvController {

    //csv 파일 읽어오기
    @RequestMapping("/readCsv")
    public String readCsv() {
        //엑셀 파일에서 하나의 행 데이터를 가져와서 저장할 변수
        String[] rowData = null;

        try{
            //utf-8 형태로 csv 파일 파싱
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream("D:\\01-STUDY\\dev\\Nolloo\\src\\main\\resources\\csv\\festival.csv"), "EUC-KR"));

            // 컬럼명은 저장되지 않도록 한 줄 읽기
            csvReader.readNext();


            do{
                //엑셀 파일의 한 줄씩 읽어서 rowData에 저장
                //만약 가져온 데이터가 없으면 rowData에[는 null이 들어감
                rowData = csvReader.readNext();

                //들어온 데이터가 있으면..
                if(rowData != null){
                    System.out.println(Arrays.toString(rowData));
                }
            }while(rowData != null);



        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
