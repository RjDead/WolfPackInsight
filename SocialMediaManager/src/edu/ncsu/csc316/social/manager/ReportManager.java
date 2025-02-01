package edu.ncsu.csc316.social.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.social.data.Connection;
import edu.ncsu.csc316.social.data.Person;
import edu.ncsu.csc316.social.dsa.Algorithm;
import edu.ncsu.csc316.social.dsa.DSAFactory;
import edu.ncsu.csc316.social.dsa.DataStructure;
import edu.ncsu.csc316.social.io.InputReader;
/**
 * ReportManager manages Connection information. ReportMediaManager
 * can print the data in a certain format.
 * @author Rishi Jeswani
 *
 */
public class ReportManager {
	/**
	 * A manager of connections with person in the system
	 */
    private SocialMediaManager manager;
	/**
	 * A roster of connections with person in the system
	 */
	Map<String, List<Connection>> outputMapPerson;
	/**
	 * A roster of connection with platform in the system
	 */
	Map<String, List<Connection>> outputMapPlatform;
	/**
	 * A roster of persons in the system
	 */
	Map<String, Person> outMap;
	/**
	 * A roster of persons in the system
	 */
	List<Person> pList ;
	/**
	 * A roster of connections in the system
	 */
	List<Connection> cList ;
	/**
	 * Initializes a ReportManager
	 * @param peopleFile - the path to the input person CSV file
	 * @param connectionFile - the path to the input connection CSV file
	 * @throws FileNotFoundException if the file is not found.
	 */
    public ReportManager(String peopleFile, String connectionFile) throws FileNotFoundException {
        this(peopleFile, connectionFile, DataStructure.SKIPLIST);
    }
	/**
	 * Initializes a ReportManager.
	 * @param peopleFile - the path to the input person CSV file
	 * @param connectionFile - the path to the input connection CSV file
	 * @param mapType - if user wants to use a different map.
	 * @throws FileNotFoundException if the file is not found.
	 */
    public ReportManager(String peopleFile, String connectionFile, DataStructure mapType) throws FileNotFoundException {
        manager = new SocialMediaManager(peopleFile, connectionFile, mapType);
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST); 
        DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
        DSAFactory.setMapType(mapType);
        pList = InputReader.readPersonData(peopleFile);
        cList = InputReader.readConnectionData(connectionFile);

    }
	/**
	 * Getter method to get all the people with the respective connections in the map.
	 * Uses string builder to build the output.
	 * @return outputMapPerson A roster of persons with the respective connection list in the system
	 * @throws FileNotFoundException if the file is not found.
	 */
    public String getConnectionsByPerson() throws FileNotFoundException {
    	
    	StringBuilder sb = new StringBuilder();
    	if (pList.isEmpty()) {
			sb.append("No people information was provided.");
    	}
    	else if (cList.isEmpty()) {
			sb.append("No connections exist in the social media network.");
    	}
    	else{
    		
    		outputMapPerson = manager.getConnectionsByPerson();
    		outMap = manager.getPeople();
    		for (Entry<String, List<Connection>> entry : outputMapPerson.entrySet()) {
    			Person temp = outMap.get(entry.getKey());
    			sb.append("Connections for ");
    			sb.append(temp.getFirst());
    			sb.append(" ");
    			sb.append(temp.getLast());
    			sb.append(" ("); 
    			sb.append(entry.getKey());
    			sb.append(") {\n"); 
    			List<Connection> connect = entry.getValue();
    			if (connect.isEmpty()) {
    				sb.append("   No connections exist\n");
    			}
    			else {
    				for (Connection connection : entry.getValue()) { 
        				sb.append("   ");
        				String s[] = connection.getPeople();
        				Person temp1 = outMap.get(s[1]); 
        				Person temp2 = outMap.get(s[0]);
        				if (s[0].equals(entry.getKey())) {
        					sb.append(temp1.getFirst());
        	    			sb.append(" ");
        					sb.append(temp1.getLast());
        	    			sb.append(" (");
        	    			sb.append(temp1.getId());
        	    			sb.append(") on ");
        				}
        				else {
        					sb.append(temp2.getFirst());
        	    			sb.append(" ");
        					sb.append(temp2.getLast());
        	    			sb.append(" (");
        	    			sb.append(temp2.getId());
        	    			sb.append(") on ");
        				}
        				sb.append(connection.getPlatform());
        				sb.append(" since ");
        				sb.append(connection.getDate());
        				sb.append("\n");
        			}
    			}
    			
    			sb.append("}\n");
    		}
    	}
		String finalS =  sb.toString();
		return finalS;
    }
	/**
	 * Getter method to get all the people with the respective connections and the platforms in the map.
	 * Uses String builder to append theoutput togetehr.
	 * @return outputMapPlatform A roster of platform with the respective connections list in the system
	 */
    public String getConnectionsByPlatform() {
    	StringBuilder sb = new StringBuilder();
    	if (pList.isEmpty()) {
			sb.append("No people information was provided.");
    	}
    	else if (cList.isEmpty()) {
			sb.append("No connections exist in the social media network.");
    	}
    	else{
    		outputMapPlatform = manager.getConnectionsByPlatform();
    		outMap = manager.getPeople();
    		for (Entry<String, List<Connection>> entry : outputMapPlatform.entrySet()) {
    			sb.append("Connections on ");
    			sb.append(entry.getKey());
    			sb.append(" {\n");
    			for (Connection connection : entry.getValue()) {
    				sb.append("   ");
    				sb.append(connection.getDate());
    				sb.append(": ");
    				String s[] = connection.getPeople();
    				if (outMap.get(s[0]).getLast().compareTo(outMap.get(s[1]).getLast()) == 0){
    					if(outMap.get(s[0]).getFirst().compareTo(outMap.get(s[1]).getFirst()) == 0) {
    						if(outMap.get(s[0]).getId().compareTo(outMap.get(s[1]).getId()) < 0) {
    		    				sb.append(outMap.get(s[0]).getFirst());
    		    				sb.append(" ");
    		    				sb.append(outMap.get(s[0]).getLast());
    			    			sb.append(" (");
    		    				sb.append(outMap.get(s[0]).getId());
    			    			sb.append(") <--> ");
    		    				sb.append(outMap.get(s[1]).getFirst());
    		    				sb.append(" ");
    		    				sb.append(outMap.get(s[1]).getLast());
    			    			sb.append(" (");
    		    				sb.append(outMap.get(s[1]).getId());
    			    			sb.append(")\n");
    						}
    						else {
       		    				sb.append(outMap.get(s[1]).getFirst());
    		    				sb.append(" ");
    		    				sb.append(outMap.get(s[1]).getLast());
    			    			sb.append(" (");
    		    				sb.append(outMap.get(s[1]).getId());
    			    			sb.append(") <--> ");
    		    				sb.append(outMap.get(s[0]).getFirst());
    		    				sb.append(" ");
    		    				sb.append(outMap.get(s[0]).getLast());
    			    			sb.append(" (");
    		    				sb.append(outMap.get(s[0]).getId());
    			    			sb.append(")\n");
    							
    						}
    					}
    					else if (outMap.get(s[0]).getFirst().compareTo(outMap.get(s[1]).getFirst()) < 0){
    	    				sb.append(outMap.get(s[0]).getFirst());
    	    				sb.append(" ");
    	    				sb.append(outMap.get(s[0]).getLast());
    		    			sb.append(" (");
    	    				sb.append(outMap.get(s[0]).getId());
    		    			sb.append(") <--> ");
    	    				sb.append(outMap.get(s[1]).getFirst());
    	    				sb.append(" ");
    	    				sb.append(outMap.get(s[1]).getLast());
    		    			sb.append(" (");
    	    				sb.append(outMap.get(s[1]).getId());
    		    			sb.append(")\n");
    					}
    					else {
   		    				sb.append(outMap.get(s[1]).getFirst());
		    				sb.append(" ");
		    				sb.append(outMap.get(s[1]).getLast());
			    			sb.append(" (");
		    				sb.append(outMap.get(s[1]).getId());
			    			sb.append(") <--> ");
		    				sb.append(outMap.get(s[0]).getFirst());
		    				sb.append(" ");
		    				sb.append(outMap.get(s[0]).getLast());
			    			sb.append(" (");
		    				sb.append(outMap.get(s[0]).getId());
			    			sb.append(")\n");
    						
    					}
    				}
    				
    				else if (outMap.get(s[0]).getLast().compareTo(outMap.get(s[1]).getLast()) < 0){
        				sb.append(outMap.get(s[0]).getFirst());
        				sb.append(" ");
        				sb.append(outMap.get(s[0]).getLast());
    	    			sb.append(" (");
        				sb.append(outMap.get(s[0]).getId());
    	    			sb.append(") <--> ");
        				sb.append(outMap.get(s[1]).getFirst());
        				sb.append(" ");
        				sb.append(outMap.get(s[1]).getLast());
    	    			sb.append(" (");
        				sb.append(outMap.get(s[1]).getId());
    	    			sb.append(")\n");
    				}
    				else {
		    			sb.append(outMap.get(s[1]).getFirst());
	    				sb.append(" ");
	    				sb.append(outMap.get(s[1]).getLast());
		    			sb.append(" (");
	    				sb.append(outMap.get(s[1]).getId());
		    			sb.append(") <--> ");
	    				sb.append(outMap.get(s[0]).getFirst());
	    				sb.append(" ");
	    				sb.append(outMap.get(s[0]).getLast());
		    			sb.append(" (");
	    				sb.append(outMap.get(s[0]).getId());
		    			sb.append(")\n");
    				}

    			}
    			sb.append("}\n");
    		}
    	}
		String finalS =  sb.toString();
		return finalS;
    }


}