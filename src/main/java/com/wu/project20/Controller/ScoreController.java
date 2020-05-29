package com.wu.project20.Controller;


import com.wu.project20.Mapper.ScoreDao;
import com.wu.project20.bean.Score;
import com.wu.project20.unZip.UnZip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class ScoreController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ScoreDao scoreDao;

    @RequestMapping(method =  RequestMethod.POST ,value = "/upload")
    @ResponseBody
    public  Score fileUpLoad(@RequestParam("file")MultipartFile file, Map<String, Object> map ,
                             @RequestParam("id") String id) throws Exception {

        String fileName=file.getOriginalFilename();
        //String fileNameSuffix=fileName.substring(fileName.lastIndexOf(".") + 1);
        //long fileSize=file.getSize();//字节数
        String filePath="C:\\Users\\ASUS\\Desktop\\wer\\";
        File fileSaving = new File(filePath + fileName);

        try {
            file.transferTo(fileSaving);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(id);
        //System.out.println(fileNameSuffix);

        File file1=new File(filePath + fileName);
        UnZip.deCompress(file1,filePath+id);
        map.put("filename",fileName);

        scoreDao.callback(Integer.valueOf(id));
        return scoreDao.SelectScoreById(id);
    }





}
