/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TianZhang
 */
import java.net.URL;
import java.util.*;
/**
 * This class is used to complete the generation of meeting schedule
 * @author jiashengqiu
 */
public class Scheduler {
    LinkedList<Schedule> schedules=new LinkedList<Schedule>();//To store all the shedules get from XML
    int numURL;//To store the number of URL
    /**
     * Get the available Insects from two schedules
     * @param s1 the first schedule
     * @param s2 the second schedule
     * @param minTime minimum time allowed for meeting 
     * @return 
     */
    public static LinkedList<Timeslot> getInsectionBySchedule(LinkedList<Timeslot> s1,LinkedList<Timeslot> s2,int minTime){
        if(s1==null||s2==null) return null; 
        LinkedList<Timeslot> result=new LinkedList<Timeslot>();
        Timeslot temp;
        for(Timeslot i:s1){
            for(Timeslot j:s2){
                temp=Timeslot.getInsection(i, j);
                if(temp!=null&&temp.getEndTime()-temp.getStartTime()>=minTime&&!result.contains(temp)){
                    result.add(temp);
                }        
            }
        }
        return result;
    }
    /**
     * Print the available time slots to meet day by day
     * @param minTime the minimum time that can be set by user
     * @throws Exception 
     */
    public void printMeetingSchedules(int minTime) throws Exception{
        List<String> days=Arrays.asList("Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        LinkedList<Timeslot> result;
        for(int i=0;i<7;i++){
            String day=days.get(i);
            int j=0;
            while(j<numURL&&schedules.get(j).getAvailable(day)==null){
                j++;
            }
            result=schedules.get(j).getAvailable(day);
            j++;
            while(j<numURL){
                if(schedules.get(j).getAvailable(day)!=null){
                     
                    result=getInsectionBySchedule(result,schedules.get(j).getAvailable(day),minTime);
                   
                }
                j++;
            }
            if(result!=null&&result.size()>0){
                for(Timeslot t:result){
                    System.out.println(day+":"+t.toString());
  
                }
            }
          
            else{
                System.out.println("This group cannot meet on "+day+".");
            }
        }
        
    }
    /**
     * main method for testing 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
        URLList u=new URLList("./urlList.xml");
        Scheduler scheduler=new Scheduler();
        scheduler.numURL=u.getNumURL();
        System.out.println("Loading "+scheduler.numURL+" schedules.....");
        for(int i=0;i<scheduler.numURL;i++){
            scheduler.schedules.add(new Schedule(new URL(u.getURL(i))));
        }
        System.out.println("Now calculating...");
        scheduler.printMeetingSchedules(3600);
    }

    
}
