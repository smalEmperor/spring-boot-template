package com.template.service.mp;


import com.template.entity.mp.StockTbl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * (StockTbl)表服务实现类
 *
 * @author dufa
 * @since 2021-12-22 16:59:12
 */
public interface StockTblService{

    StockTbl getById();

    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\14328\\Desktop/20220630191916055yvcn-角色授权保存问题 (1).mp4");
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            System.out.println("文件md5值：" + bigInt.toString(16));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
