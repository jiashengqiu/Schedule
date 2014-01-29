/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This class is used to read the URL XML file and get the URL string
 * @author Tian Zhang
 */
import java.io.*;
import java.util.*;
import javax.xml.stream.*;
public class URLList {
    private ArrayList<String> urlList=new ArrayList<String>();
    /**
     * Read through the xml and store the url in the array list
     * @param urlXML
     * @throws Exception 
     */
    public URLList(String urlXML) throws Exception{
        XMLInputFactory factory=XMLInputFactory.newInstance();
        try{
            Reader read=new FileReader(urlXML);
            XMLStreamReader reader=factory.createXMLStreamReader(read);
            // Go through the entail XML
            while(reader.hasNext()){
                int event=reader.next();
                if (event==XMLStreamConstants.START_ELEMENT 
                        && "URL".equalsIgnoreCase(reader.getLocalName())){
                    urlList.add(reader.getElementText());
                }
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    /**
     * get the number of URL
     * @return the number of URL
     * @throws Exception 
     */
    public int getNumURL() throws Exception{
        return urlList.size();
    }
    /**
     * get the URL by index
     * @param i index of the URL
     * @return the URL string
     */
    public String getURL(int i){
        try{
        if(getNumURL()!=0){
            return urlList.get(i);
        }
         else{
            return null;
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
     
    }
    /**
     * main method to test the URLList
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
        URLList u=new URLList("./URLList.xml");
        for(int i=0;i<4;i++){
            System.out.println(u.getURL(i));
        }
    }
    
    
}
