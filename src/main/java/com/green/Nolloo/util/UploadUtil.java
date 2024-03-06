package com.green.Nolloo.util;

import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.member.vo.MemberImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadUtil {

    public static String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
    public static ImgVO uploadFile(MultipartFile uploadFile){
        ImgVO imgVO = null;
        if (!uploadFile.isEmpty()){
            imgVO =new ImgVO();
            //확장자 추출
            String extension = getExtension(uploadFile.getOriginalFilename());
            //중복되지 않는 파일명 생성
            String fileName =getUUID()+extension;
            try {
                //파일경로+랜덤명.확장자
                File file1 = new File(PathVariable.ITEM_UPLOAD_PATH+fileName);
                uploadFile.transferTo(file1);
                //메인 네임
                imgVO.setAttachedFileName(fileName);
                //첨부파일
                imgVO.setOriginFileName(uploadFile.getOriginalFilename());
                //메인여부
                imgVO.setIsMain("Y");

            } catch (Exception e) {
                System.out.println("파일첨부중 예외발생");
                e.printStackTrace();
            }
        }
        return imgVO;
    }
    //다중첨부 메소드
    public static List<ImgVO> multiUploadFile(MultipartFile[] uploadFiles){
        List<ImgVO> imgList = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles){
            ImgVO vo =uploadFile(uploadFile);
            if (vo != null) {
                vo.setIsMain("N");
                imgList.add(vo);
            }
        }
        return imgList;
    }
    public static MemberImageVO memberUploadFile(MultipartFile memberUploadFile){
        MemberImageVO memberImageVO = null;
        if (!memberUploadFile.isEmpty()){
            memberImageVO =new MemberImageVO();
            //확장자 추출
            String memberExtension = getExtension(memberUploadFile.getOriginalFilename());
            //중복되지 않는 파일명 생성
            String memberFileName =getUUID()+memberExtension;
            try {
                //파일경로+랜덤명.확장자
                File file2 = new File(PathVariable.PROFILE_UPLOAD_PATH+memberFileName);
                memberUploadFile.transferTo(file2);
                //메인 네임
                memberImageVO.setAttachedFileName(memberFileName);
                //첨부파일
                memberImageVO.setOriginFileName(memberImageVO.getOriginFileName());


            } catch (Exception e) {
                System.out.println("파일첨부중 예외발생");
                e.printStackTrace();
            }
        }
        return memberImageVO;
    }

}
