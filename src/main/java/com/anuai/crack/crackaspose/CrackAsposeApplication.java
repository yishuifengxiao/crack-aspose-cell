package com.anuai.crack.crackaspose;

import com.aspose.cells.License;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class CrackAsposeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrackAsposeApplication.class, args);

        /*
        // 修改 License.class
        try{
            crackAspose("C:\\Users\\DYG\\Desktop\\1\\aspose-cells-20.7.jar");
            System.out.println("Success!");
        }catch (Exception e){
            e.printStackTrace();
        }
        */



        // 测试破解后的 aspose-cells-20.7-crack.jar
        boolean auth = authrolizeLicense();
        if (!auth) {
            System.out.println("aspose 许可无效！");
            return;
        }


        System.out.println("aspose 已就绪！");


        try{
            Workbook wb = new Workbook();
            Worksheet ws = wb.getWorksheets().get(0);
            for (int i = 0; i < 9; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    ws.getCells().get(i,j).setValue((i + 1) + "*" + (j + 1) + "=" + (i + 1) * (j + 1));
                }
            }

            wb.save("C:\\Users\\DYG\\Desktop\\1\\text.xlsx");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void crackAspose(String JarPath) throws NotFoundException,CannotCompileException, IOException {
        // 这个是得到反编译的池
        ClassPool pool = ClassPool.getDefault();

        // 取得需要反编译的jar文件，设定路径
        pool.insertClassPath(JarPath);

        CtClass cc_License = pool.get("com.aspose.cells.License");

        CtMethod method_isLicenseSet = cc_License.getDeclaredMethod("isLicenseSet");
        method_isLicenseSet.setBody("return true;");

        CtClass cc_License2 = pool.get("com.aspose.cells.License");
        CtMethod method_setLicense = cc_License2.getDeclaredMethod("setLicense");
        method_setLicense.setBody("{    a = new com.aspose.cells.License();\n" +
                "    com.aspose.cells.zbiw.a();}");

        CtMethod method_k = cc_License.getDeclaredMethod("k");
        method_k.setBody("return new java.util.Date(Long.MAX_VALUE);");


        cc_License.writeFile("C:\\Users\\DYG\\Desktop\\1");
    }









    public static boolean authrolizeLicense() {
        boolean result = false;
        try {
            InputStream is = com.aspose.cells.License.class.getResourceAsStream("/com.aspose.cells.lic_2999.xml");
            License asposeLicense = new License();
            asposeLicense.setLicense(is);
            is.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
