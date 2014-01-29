import java.util.*;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Time slot class is used to store the start time and end time of a time slot
 * @author TianZhang
 */
public class Timeslot {
    private int startTime;
    private int endTime;
        /**
     * Constructor
     * @param start
     * @param end 
     */
   public Timeslot(int start,int end){
        if(start<=end){
        startTime=start;
        endTime=end;
        }
    }
    
    /**
     * Constructor of the Time Slot using String
     * @param start:Start time String from XML
     * @param end: End time String from XML
     */
   public Timeslot(String start,String end) {
        
        this(convertStringTime(start),convertStringTime(end));
     
    }
   

    /**get start time of the slot by seconds
     * 
     * @return start time
     */
    public int getStartTime(){
        return startTime;
    }
    
    /**
     * return the end time by seconds
     * @return end time of the slot
     */
    public int getEndTime(){
        return endTime;
    }
     
     
     /**
      * Use to convert string time format to integer time format
      * @param time the string time
      * @return the integer time indicating how many seconds has past in a day
      * @throws Exception 
      */
    public static int convertStringTime(String time){
        String[] timesplice;
        
        timesplice=time.split(":");
        int seconds=Integer.valueOf(timesplice[0])*3600+Integer.valueOf(timesplice[1])*60+Integer.valueOf(timesplice[2]);
        return seconds;
    }
    /**
     * Convert the integer time format to string time format 
     * @param time
     * @return the string expression of the time
     */
    public static String convertIntegerTime(int time){
        DecimalFormat df=new DecimalFormat("00");

        String result=df.format(time/3600)+":"+df.format(time%3600/60)+":"+df.format(time%3600%60);
        return result;
    }
    public String toString(){
        return convertIntegerTime(startTime)+","+convertIntegerTime(endTime);
    }
    /**
     * get the intersection of the time slot t1, t2
     * @param t1
     * @param t2
     * @return 
     */
    public static Timeslot getInsection(Timeslot t1,Timeslot t2){
        Timeslot result=null;
        int st1=t1.getStartTime();
        int et1=t1.getEndTime();
        int st2=t2.getStartTime();
        int et2=t2.getEndTime();
        if(et1<=st2||et2<=st2){
            return null;
        }
        else if(st2>=st1&&et2>=et1){
            result =new Timeslot(st2,et1);
        }
        else if(st1>=st2&&et2>=et1){
            result =new Timeslot(st1,et1);
        }
        else if(st1>=st2&&et1<=et2){
            result=new Timeslot(st1,et1);
        }
        else if(st2>=st1&&et2<=et1){
            result=new Timeslot(st2,et2);
        }
        return result;
    }
    public boolean equals(Object o){
      
        Timeslot t=(Timeslot)o;
        if(t.startTime==this.startTime&&t.endTime==this.endTime){
            return true;
        }
        else{
            return false;
        }
    }
      
}
