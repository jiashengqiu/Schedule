/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.stream.*;
/**
 *This class is to get the schedule information from XML
 * @author Tian Zhang
 */
public class Schedule {
    private ArrayList<LinkedList<Timeslot>> schedule=new ArrayList<LinkedList<Timeslot>>();
     //Keep the 7 days into a Arraylist to make it easy to access
    List<String> days=Arrays.asList("Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
      
    public Schedule(URL url) throws FileNotFoundException, XMLStreamException, IOException {
        
        String tempStartTime="";// to save the startTime before the timeslot generation
       
        //Start to load XML
        
        XMLInputFactory factory=XMLInputFactory.newInstance();
        InputStream input = url.openStream();
        XMLStreamReader reader=factory.createXMLStreamReader(input);
        
        //Go through the entail XML 
        while(reader.hasNext()){
            int event=reader.next();
            if (event==XMLStreamConstants.START_ELEMENT){
                String element=reader.getLocalName();
                if (days.contains(element)){ //if the elements is dats create a list
                    schedule.add(new LinkedList<Timeslot>());
                } else if("start".equalsIgnoreCase(element)){
                    tempStartTime=reader.getElementText();
                } else if("end".equalsIgnoreCase(element)){
                    schedule.get(schedule.size()-1).add(new Timeslot(tempStartTime,reader.getElementText()));
                }
            }
        }
       
        reader.close();
        input.close();
    }
    
    public LinkedList getAvailable(String day) throws Exception{
        try{
            int index=days.indexOf(day);
            return schedule.get(index);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    public static void main(String[] args) throws Exception{
        Schedule s=new Schedule(new URL("http://www.andrew.cmu.edu/user/mm6/95-702/McCarthysSchedule/schedule3.xml"));
        System.out.println(s.getAvailable("Tuesday"));
    }
}
