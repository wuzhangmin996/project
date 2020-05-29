package com.wu.project20.unZip;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;

import java.io.File;

public class UnZip {
    private static void unzip(String sourceZip, String destDir) throws Exception {
        try {
            Project p = new Project();
            Expand e = new Expand();
            e.setProject(p);
            e.setSrc(new File(sourceZip));
            e.setOverwrite(false);
            e.setDest(new File(destDir));
           /*
           ant下的zip工具默认压缩编码为UTF-8编码，
           而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
           所以解压缩时要制定编码格式
           */
            e.setEncoding("gbk");
            e.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void unfile(String sourceFile, String destDir) throws Exception {

        UnZip.unzip(sourceFile, destDir);
    }

    /**
     * 解压到指定目录
     */
    public static void deCompress(String sourceFile, String destDir) throws Exception {
        if (sourceFile == null || destDir == null) {
            throw new RuntimeException("deCompress: 目录不能为空");
        }
        // 保证文件夹路径最后是"/"或者"\"
        char lastChar = destDir.charAt(destDir.length() - 1);
        if (lastChar != '/' && lastChar != '\\') {
            destDir += File.separator;
        }
        unfile(sourceFile, destDir);
    }

    /**
     * 解压到指定目录
     */
    public static void deCompress(File sourceFile, String destDir) throws Exception {
        if (!sourceFile.exists() || sourceFile.isDirectory()) {
            throw new RuntimeException("deCompress: 文件不存在");
        }
        deCompress(sourceFile.getPath(), destDir);
    }


}

