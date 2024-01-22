package com.example.rtdataassetcoord.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.rtdataassetcoord.common.BusException;
import com.example.rtdataassetcoord.common.R;
import com.example.rtdataassetcoord.config.StorageProperties;
import com.example.rtdataassetcoord.dto.UpLoadDto;
import com.example.rtdataassetcoord.service.StorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.HashMap;
import java.util.List;


@RestController
@Api(tags = "文件")
@RequestMapping("/api/rtdataassetcoord/file")
public class StorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageProperties storageProperties;



    private static HashMap<Object, Object> index = new HashMap<>();
    private static int count=0;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件")
    public R handleFileUpload(HttpServletRequest request, @RequestPart("file") MultipartFile file) throws Exception {
        if(null == file) throw new BusException("上传空文件");
        String storePath = storageService.store(file);
        UpLoadDto upLoadDto = new UpLoadDto();
        upLoadDto.setRelativePath("/download/"+storePath);
        //upLoadDto.setUrlPath(serverConfig.getUrl()+"/download/"+storePath);
        upLoadDto.setUrlPath(storePath);
        return new R().Success(upLoadDto);
    }


    @PostMapping("/uploadHeadImg")
    @ApiOperation(value = "上传头像")
    public R uploadHeadImg(HttpServletRequest request, @RequestPart("file") MultipartFile file) throws Exception {
        if(null == file) throw new BusException("上传空文件");
        String storePath = storageService.store(file);
        UpLoadDto upLoadDto = new UpLoadDto();
        upLoadDto.setRelativePath("/download/"+storePath);
        //upLoadDto.setUrlPath(serverConfig.getUrl()+"/download/"+storePath);
        upLoadDto.setUrlPath(storePath);
        return new R().Success(upLoadDto);
    }


    @PostMapping("/upload-picture")
    @ApiOperation(value = "上传图片")
    public R<String> uploadPreviewFile(HttpServletRequest request, @RequestPart("file") List<MultipartFile> files) {
        if(null == files) throw new BusException("上传空文件");
        String imagePath = new String();
        for (MultipartFile file:files) {
            String storePath = storageService.store(file);
            String url = "http://"+request.getServerName()+":"+request.getServerPort()+"/api/rtdataassetcoord/file/preview/"+storePath+",";
            imagePath += url;
        }
        imagePath = imagePath.substring(0, imagePath.length() - 1);
        return new R().Success(imagePath);
    }

    @GetMapping("/preview/{dirName:.+}/{fileName:.+}")
    @ApiOperation(value = "根据文件名实现预览功能")
    public void previewFile(@PathVariable String dirName,
                            @PathVariable String fileName, HttpServletResponse response) throws IOException {
        showImg("./"+storageProperties.getLocation()+"/"+dirName+"/"+fileName, response);
    }

    public static void showImg(String path, HttpServletResponse response){
        if(path!=null&&!path.equals("")){

            try {
                FileInputStream fis = new FileInputStream(path);
                ServletOutputStream os = response.getOutputStream();

                byte [] b = new byte[1024*8];
                while(fis.read(b)!=-1){
                    os.write(b);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/download/{dirName:.+}/{fileName:.+}")
    @ApiOperation(value = "根据文件名从下载文件")
    public ResponseEntity<byte[]> serveFile(@PathVariable String dirName,
                                            @PathVariable String fileName) throws IOException {
        if(StringUtils.isEmpty(fileName)) throw new BusException("查找文件名字为空");
        FileInputStream fi = new FileInputStream("./"+storageProperties.getLocation()+"/"+dirName+"/"+fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/download"));
        headers.set("Content-Disposition", "attachment;fileName*=UTF-8''" + UriUtils.encode(fileName, "UTF-8"));
        return new ResponseEntity<byte[]>(IOUtils.toByteArray(fi), headers, HttpStatus.OK);
    }
    
}
