package com.bsoft.face;




import com.bsoft.face.faceForm.*;

import com.bsoft.face.faceUtil.FaceUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;


public class FaceMain {




public static void  main(String[] args){
    System.out.println("=============begin=============");


    try{
        Map<String,Object> cacheMap=new HashMap<String,Object>();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat capImg=new Mat();
        VideoCapture capture=new VideoCapture(0);
        int height = (int)capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
        int width = (int)capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
        if(height==0||width==0){
            System.out.println("camera not found!");
            return;
        }

        JFrame jf = new JFrame("人脸采集程序");
        cacheMap.put("jf",jf);
       // jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //   jf.setSize(500,500);
        //窗口关闭按钮添加事件

        //顶部区域 身份证号输入框等内容
        JPanel jPanelNorth = FacePanelNorth.getJPanelNorth(cacheMap);

        //中间区域 显示摄像头的panel
        FacePanelCenter facePanelCenter = FacePanelCenter.getJPanelCenter(cacheMap);
        cacheMap.put("facePanelCenter",facePanelCenter);
        //底部区域 显示人脸采集，人脸识别等按钮
        JPanel jPanelSouth= FacePanelSouth.getJPanelSouth(cacheMap);


        BorderLayout borderLayout=new BorderLayout();
        jf.setLayout(borderLayout);

        jf.add( BorderLayout.NORTH,jPanelNorth);
        jf.add( BorderLayout.CENTER,facePanelCenter);
        jf.add( BorderLayout.SOUTH,jPanelSouth);

        jf.setVisible(true);
        jf.setSize(width+jf.getInsets().left+jf.getInsets().right,
                height+jf.getInsets().top+jf.getInsets().bottom);

    int n=0;
    int count=0;
    Mat temp=new Mat();
    FaceUtil faceUtil=new FaceUtil();
    while(jf.isShowing()&&n<500){
        //控制每0.5秒获取一次摄像头内容判断是否存在人脸
    System.out.println("jf.isShowing()&&n<500 conut:"+count);
        Thread.sleep(500);

        count++;
    capture.read(capImg);
    Imgproc.cvtColor(capImg, temp, Imgproc.COLOR_RGB2GRAY);
    facePanelCenter.mImg=faceUtil.mat2BI(faceUtil.detectFace(cacheMap,capImg,count));

    facePanelCenter.repaint();

    }


    //jf.pack();
 // jf.setLocationRelativeTo(null);
    capture.release();
    jf.dispose();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }catch(Exception e){
        e.printStackTrace();;
    }
}


}
