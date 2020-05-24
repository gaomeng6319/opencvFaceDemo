package com.bsoft.face.faceUtil;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class FaceUtil {

    public BufferedImage mat2BI(Mat mat){
        int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();
        byte[] data=new byte[dataSize];
        mat.get(0, 0,data);
        int type=mat.channels()==1?
                BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;

        if(type==BufferedImage.TYPE_3BYTE_BGR){
            for(int i=0;i<dataSize;i+=3){
                byte blue=data[i+0];
                data[i+0]=data[i+2];
                data[i+2]=blue;
            }
        }
        BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

        return image;
    }
    /**
     * opencv实现人脸识别
     * @param img
     */
    public static Mat detectFace(Map cacheMap,Mat img, int count) throws Exception
    {

        // System.out.println("Running DetectFace ... ");
        // 从配置文件lbpcascade_frontalface.xml中创建一个人脸识别器，该文件位于opencv安装目录中
        //不能使用中文路径
        CascadeClassifier faceDetector = new CascadeClassifier("G:\\workSoft\\opencv\\window\\installPack\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");


        // 在图片中检测人脸
        MatOfRect faceDetections = new MatOfRect();

        faceDetector.detectMultiScale(img, faceDetections);

        //System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        Rect[] rects = faceDetections.toArray();

        //输入了身份证号在采集人脸
        JTextField jTextField_sfzh=(JTextField)cacheMap.get("jTextField_sfzh");
        JTextField jTextField_tips=(JTextField)cacheMap.get("jTextField_tips");
        String tips="";
        boolean collectFace=cacheMap.get("collectFace")==null?false:(boolean)cacheMap.get("collectFace");
        String sfzh=jTextField_sfzh.getText();
        if(!collectFace){
            return img;
        }
        if(rects != null && rects.length >= 1){
            System.out.println("sfzh:"+sfzh+"  count:"+count+"  if(rects != null && rects.length >= 1) --> true  监测到人脸 ");
          //  Imgcodecs.imwrite("G:\\workSoft\\opencv\\faceImage\\face"+count+".png", img);
            Imgcodecs.imwrite("G:\\workSoft\\opencv\\faceImage\\"+sfzh+"_face.png", img);
            for (Rect rect : rects) {
                Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255), 2);


            }
            //采集完成
            cacheMap.put("collectFace",false);
            //窗口显示人脸采集成功
            JFrame jf=(JFrame) cacheMap.get("jf");
            tips="身份证号:"+sfzh+"人脸采集成功！";
           // JOptionPane.showMessageDialog(jf, tips,"提示",JOptionPane.INFORMATION_MESSAGE);
            jTextField_tips.setText(tips);
        }
        return img;
    }
}
