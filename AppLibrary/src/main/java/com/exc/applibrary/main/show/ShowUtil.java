package com.exc.applibrary.main.show;

import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ShowUtil {

    public static String getxmldata(String frame, String duration, String videoName, String IsFile) {

        String y_str = gettimeData(true);
        String h_str = gettimeData(false);

        XmlSerializer serializer = Xml.newSerializer();
        OutputStream out = new ByteArrayOutputStream();
        String xml = "";
        try {
            serializer.setOutput(out, "UTF-8");

            serializer.startTag(null, "Programs");
            serializer.startTag(null, "Program");

            insertXMl(serializer, "ID", "111");
            insertXMl(serializer, "Name", "FastProgram");
            insertXMl(serializer, "IsTimer", "1");

            serializer.startTag(null, "Files");
            serializer.startTag(null, "File");

            insertXMl(serializer, "Name", videoName);
            insertXMl(serializer, "Duration", duration);
            insertXMl(serializer, "Frames", frame);
            insertXMl(serializer, "Audio", "0");
            insertXMl(serializer, "IsFile", IsFile);
            insertXMl(serializer, "XAxis", "0");
            insertXMl(serializer, "YAxis", "0");
            insertXMl(serializer, "Width", "0");
            insertXMl(serializer, "Height", "0");
            insertXMl(serializer, "Transparent", "0");
            insertXMl(serializer, "Volume", "0");
            insertXMl(serializer, "PlayRate", "0");
            insertXMl(serializer, "StartFrame", "0");
            insertXMl(serializer, "StopFrame", "0");
            insertXMl(serializer, "FrameAdvance", "0");
            insertXMl(serializer, "TopCut", "0");
            insertXMl(serializer, "LeftCut", "0");
            insertXMl(serializer, "RightCut", "0");
            insertXMl(serializer, "BottomCut", "0");

            serializer.endTag(null, "File");
            serializer.endTag(null, "Files");


            serializer.startTag(null, "Timers");
            serializer.startTag(null, "Timer");

            insertXMl(serializer, "IsImmediatePlay", "1");
            insertXMl(serializer, "ByYear", "1");
            insertXMl(serializer, "ByMonth", "1");
            insertXMl(serializer, "ByDay", "1");
            insertXMl(serializer, "ByTime", "1");
            insertXMl(serializer, "StartDate", y_str);
            insertXMl(serializer, "StopDate", y_str);
            insertXMl(serializer, "StartTime", h_str);
            insertXMl(serializer, "StopTime", "23:59:59");
            insertXMl(serializer, "ByWeek", "0");
            insertXMl(serializer, "BySunday", "0");
            insertXMl(serializer, "ByMonday", "0");
            insertXMl(serializer, "ByTuesday", "0");
            insertXMl(serializer, "ByWednesday", "0");
            insertXMl(serializer, "ByThursday", "0");
            insertXMl(serializer, "ByFriday", "0");
            insertXMl(serializer, "BySaturday", "0");

            serializer.startTag(null, "Festivals");
            serializer.startTag(null, "Festival");
            serializer.startTag(null, "Type");
            serializer.endTag(null, "Type");

            insertXMl(serializer, "AppointTime", "0");
            insertXMl(serializer, "BeginTime", "00:00:00");
            insertXMl(serializer, "EndTime", "00:00:00");

            serializer.endTag(null, "Festival");
            serializer.endTag(null, "Festivals");

            serializer.endTag(null, "Timer");
            serializer.endTag(null, "Timers");

            serializer.endTag(null, "Program");
            serializer.endTag(null, "Programs");

            serializer.endDocument();

            xml = out.toString();
            Log.d("8888", xml);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return xml;
    }

    //获取时间
    public static String gettimeData(boolean is_year) {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd"); //设置时间格式
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
        Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
        String y_createDate = formatter.format(curDate);   //格式转换

        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss"); //设置时间格式
        formatter1.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
        Date curDate1 = new Date(System.currentTimeMillis()); //获取当前时间
        String h_createDate = formatter1.format(curDate1);   //格式转换

        return is_year ? y_createDate : h_createDate;
    }

    //把String转化为double
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static void insertXMl(XmlSerializer serializer, String name, String text) {
        try {
            serializer.startTag(null, name);
            serializer.text(text);
            serializer.endTag(null, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
