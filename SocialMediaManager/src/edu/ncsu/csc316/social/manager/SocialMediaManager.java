package edu.ncsu.csc316.social.manager;

import java.io.FileNotFoundException;

import java.util.Comparator;
//import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.list.List;
//import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.map.Map;
//import edu.ncsu.csc316.dsa.map.Map.Entry;
//import edu.ncsu.csc316.dsa.map.SkipListMap;
import edu.ncsu.csc316.social.data.Connection;
import edu.ncsu.csc316.social.data.Person;
import edu.ncsu.csc316.social.dsa.Algorithm;
import edu.ncsu.csc316.social.dsa.DSAFactory;
import edu.ncsu.csc316.social.dsa.DataStructure;
import edu.ncsu.csc316.social.io.InputReader;

/**
 * SocialManager manages Connection information. SocialMediaManager
 * can sort a connection roster.
 * @author Rishi Jeswani
 *
 */
public class SocialMediaManager {
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
	List<Person> pList;
	/**
	 * A roster of connections in the system
	 */
	List<Connection> cList;

	/**
	 * Initializes a SocialMediaManager
	 * @param peopleFile - the path to the input person CSV file
	 * @param connectionFile - the path to the input connection CSV file
	 * @throws FileNotFoundException if the file is not found.
	 */
	public SocialMediaManager(String peopleFile, String connectionFile) throws FileNotFoundException {
		this(peopleFile, connectionFile, DataStructure.SKIPLIST);
	}
	/**
	 * Initializes a SocialMediaManager
	 * @param peopleFile - the path to the input person CSV file
	 * @param connectionFile - the path to the input connection CSV file
	 * @param mapType - if user wants to use a different map.
	 * @throws FileNotFoundException if the file is not found.
	 */

	public SocialMediaManager(String peopleFile, String connectionFile, DataStructure mapType)
			throws FileNotFoundException {
		DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
		DSAFactory.setComparisonSorterType(Algorithm.QUICKSORT);
		DSAFactory.setNonComparisonSorterType(Algorithm.RADIX_SORT);
		DSAFactory.setMapType(mapType);
		pList = InputReader.readPersonData(peopleFile);
		cList = InputReader.readConnectionData(connectionFile);
		outMap = DSAFactory.getMap(null);
		for (Person person : pList) {
			outMap.put(person.getId(), person);
		}
	}

	/**
	 * Getter method to get all the people in the map.
	 * @return outMap A roster of persons in the system
	 */
	public Map<String, Person> getPeople() {
		return outMap;
	}
	/**
	 * Getter method to get all the people with the respective connections in the map.
	 * @return outputMapPerson A roster of persons with the respective connection list in the system
	 */
	public Map<String, List<Connection>> getConnectionsByPerson() {
		outputMapPerson = DSAFactory.getMap(null);
		outMap = getPeople();
		String username = "";
		List<Connection> cListFinal1 = DSAFactory.getIndexedList();
		List<Connection> cListFinal2 = DSAFactory.getIndexedList();
		String[] s = new String[2];
		for (String id : outMap) { 
			username = id;
			cListFinal1 = DSAFactory.getIndexedList();

			for (Connection connection : cList) {
				s = connection.getPeople();
				if (s[0].equals(username) || s[1].equals(username)) {
					cListFinal1.addLast(connection);
				}
			}

			Connection arr[] = new Connection[cListFinal1.size()];

			for (int i = 0; i < cListFinal1.size(); i++) {
				arr[i] = cListFinal1.get(i);
			}

			DSAFactory.getComparisonSorter(new ConnectionComparator()).sort(arr);

			cListFinal2 = DSAFactory.getIndexedList();

			for (int i = 0; i < cListFinal1.size(); i++) {
				cListFinal2.addLast(arr[i]);
			}

			outputMapPerson.put(username, cListFinal2);

		}

		return outputMapPerson;

	}
	/**
	 * Getter method to get all the people with the respective connections and the platforms in the map.
	 * @return outputMapPlatform A roster of platform with the respective connections list in the system
	 */
	public Map<String, List<Connection>> getConnectionsByPlatform() {
		outputMapPlatform = DSAFactory.getMap(null);
		for (Connection connection : cList) {
			String pL = connection.getPlatform();
			if (outputMapPlatform.get(pL) == null) {

				List<Connection> cListFinal1 = DSAFactory.getIndexedList();

				for (Connection connection2 : cList) {
					if (pL.equals(connection2.getPlatform())) {
						cListFinal1.addLast(connection2);

					}
				}
				Connection arr[] = new Connection[cListFinal1.size()];

				for (int i = 0; i < cListFinal1.size(); i++) {
					arr[i] = cListFinal1.get(i);
				}

				DSAFactory.getComparisonSorter(new PlatformComparator()).sort(arr);

				List<Connection> cListFinal2 = DSAFactory.getIndexedList();
				for (int i = 0; i < cListFinal1.size(); i++) {
					cListFinal2.addLast(arr[i]);
				}

				outputMapPlatform.put(pL, cListFinal2);
			}

		}
		return outputMapPlatform;

	}

	/**
	 * Comparator to compare connections based on names.
	 * 
	 * @author Rishi Jeswani
	 *
	 */
	private class ConnectionComparator implements Comparator<Connection> {
		/**
		 * Compares connections based on id name in ascending order.
		 * 
		 * @return 1 if id of the first connection is higher than the second connection,
		 *         -1 if id of the first connection is lower than the second connection
		 *         and 0 if they are equal.
		 */
		@Override
		public int compare(Connection c1, Connection c2) {
			if (c1 == null || c2 == null) {
				return 0;
			}
			String s1[] = c1.getPeople();
			String s2[] = c2.getPeople();
			Person s10 = outMap.get(s1[0]);
			Person s11 = outMap.get(s1[1]);
			Person s20 = outMap.get(s2[0]);
			Person s21 = outMap.get(s2[1]);
			String s10First = s10.getFirst();
			String s11First = s11.getFirst();
			String s20First = s20.getFirst();
			String s21First = s21.getFirst();
			
			String s10Last = s10.getLast();
			String s11Last = s11.getLast();
			String s20Last = s20.getLast();
			String s21Last = s21.getLast();
			
			if (s1[0].equals(s2[0])) {
				if (s11Last.compareTo(s21Last) == 0) {
					if (s11First.compareTo(s21First) == 0) {
						if (s11.getId().compareTo(s21.getId()) == 0) {
							return c1.getDate().compareTo(c2.getDate());
						} else {
							return s11.getId().compareTo(s21.getId());
						}
					} else {
						return s11First.compareTo(s21First);
					}
				} else {
					return s11Last.compareTo(s21Last);
				}
			}

			else if (s11.equals(s21)) {
				if (s10Last.compareTo(s20Last) == 0) {
					if (s10First.compareTo(s20First) == 0) {
						if (s10.getId().compareTo(s20.getId()) == 0) {
							return c1.getDate().compareTo(c2.getDate());
						} else {
							return s10.getId().compareTo(s20.getId());
						}
					} else {
						return s10First.compareTo(s20First);
					}
				} else {
					return s10Last.compareTo(s20Last);
				}
			} else if (s1[0].equals(s2[1])) {
				if (s11Last.compareTo(s20Last) == 0) {
					if (s11First.compareTo(s20First) == 0) {
						if (s11.getId().compareTo(s20.getId()) == 0) {
							return c1.getDate().compareTo(c2.getDate());
						} else {
							return s11.getId().compareTo(s20.getId());
						}
					} else {
						return s11First.compareTo(s20First);
					}
				} else {
					return s11Last.compareTo(s20Last);
				}
			} else {
				if (s10Last.compareTo(s21Last) == 0) {
					if (s10First.compareTo(s21First) == 0) {
						if (s10.getId().compareTo(s21.getId()) == 0) {
							return c1.getDate().compareTo(c2.getDate());
						} else {
							return s10.getId().compareTo(s21.getId());
						}
					} else {
						return s10First.compareTo(s21First);
					}
				} else {
					return s10Last.compareTo(s21Last);
				}
			}
		}

	}
	/**
	 * Compares connections based on date in ascending order.
	 * Compares id of connection if the dates are same.
	 * 
	 * @return 1 if date of the first connection is higher than the second connection,
	 *         -1 if date of the first connection is lower than the second connection
	 *         and 0 if they are equal.
	 */
	private class PlatformComparator implements Comparator<Connection> {

		@Override
		public int compare(Connection c1, Connection c2) {
			if (c1.getDate().compareTo(c2.getDate()) == 0) {
				return c1.getId().compareTo(c2.getId());
			} else {
				return c1.getDate().compareTo(c2.getDate());
			}

		}

	}
}