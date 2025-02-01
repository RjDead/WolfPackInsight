package edu.ncsu.csc316.social.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for Report Manager 
 * @author Rishi Jeswani.
 * */
public class ReportManagerTest {
	private ReportManager manager;
	private ReportManager manager1;
	private ReportManager manager2;
	private ReportManager manager3;
	private ReportManager manager4;
	private ReportManager manager5;
	/**
	 * Set Up Method.
	 * @throws FileNotFoundException if the file is not found.
	 * */
	@BeforeEach
    public void setUp() throws FileNotFoundException {
        manager = new ReportManager("input/users.txt", "input/connections.txt");
        manager1 = new ReportManager("input/nousers.txt", "input/connections.txt");
        manager2 = new ReportManager("input/users.txt", "input/noconnections.txt");
        manager3 = new ReportManager("input/userR.txt", "input/connectionsR.txt");
        manager4 = new ReportManager("input/userR.txt", "input/connectionsRJ.txt");
        manager5 = new ReportManager("input/userR.txt", "input/connectionRJ10.txt");
    }
	/**
	 * Method for testing GetConnectionsByPerson method.
	 * @throws FileNotFoundException if the file is not found.
	 */
	@Test
	void testGetConnectionsByPerson() throws FileNotFoundException {
		assertEquals("Connections for Layla Crona (cronal634) {\n"
				+ "   Elroy Daniel (daniele34) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Rowena Price (pricer774) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rowena Price (pricer774) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "}\n"
				+ "Connections for Elroy Daniel (daniele34) {\n"
				+ "   Layla Crona (cronal634) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Santos McLaughlin (mclaughlins441) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Santos McLaughlin (mclaughlins441) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Rowena Price (pricer774) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "}\n"
				+ "Connections for Santos McLaughlin (mclaughlins441) {\n"
				+ "   Elroy Daniel (daniele34) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Elroy Daniel (daniele34) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Rowena Price (pricer774) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Rowena Price (pricer774) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "}\n"
				+ "Connections for Rowena Price (pricer774) {\n"
				+ "   Layla Crona (cronal634) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Layla Crona (cronal634) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Elroy Daniel (daniele34) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "   Santos McLaughlin (mclaughlins441) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Santos McLaughlin (mclaughlins441) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "}\n"
				+ "Connections for Tonita Schumm (schummt809) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "", manager.getConnectionsByPerson());
		assertEquals("No people information was provided.", manager1.getConnectionsByPerson());
		assertEquals("No connections exist in the social media network.", manager2.getConnectionsByPerson());
		assertEquals("Connections for Kashyap Mudavat (kmudvat2) {\n"
				+ "   Sam O'Draharay (odamah123) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Sam O'Draharay (odamah123) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Rishi Peswani (rpeswan2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "}\n"
				+ "Connections for Sam O'Draharay (odamah123) {\n"
				+ "   Stella Chang (schang24) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Kashyap Mudavat (kmudvat2) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Rishi Peswani (rpeswan2) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rjeswan2) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rpeswan2) {\n"
				+ "   Stella Chang (schang24) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Stella Chang (schang24) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "   Sam O'Draharay (odamah123) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "}\n"
				+ "Connections for Stella Chang (schang24) {\n"
				+ "   Sam O'Draharay (odamah123) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rishi Peswani (rpeswan2) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "}\n"
				+ "Connections for Charvi Chang (schang26) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "Connections for Siddhant Soshi (ssoshi29) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "", manager3.getConnectionsByPerson());
		assertEquals("Connections for Kashyap Mudavat (kmudvat2) {\n"
				+ "   Stella Chang (schang24) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Sam O'Draharay (odamah123) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Rishi Peswani (rpeswan2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "   Rishi Peswani (rpeswan2) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "}\n"
				+ "Connections for Sam O'Draharay (odamah123) {\n"
				+ "   Stella Chang (schang24) on Instagram since Tue Dec 24 13:08:22 EST 2019\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Rishi Peswani (rpeswan2) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rjeswan2) {\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Sat Mar 12 11:49:53 EST 2016\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rpeswan2) {\n"
				+ "   Stella Chang (schang24) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Sam O'Draharay (odamah123) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "   Rishi Peswani (rjeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rishi Peswani (rjeswan2) on LinkedIn since Sat Mar 12 11:49:53 EST 2016\n"
				+ "}\n"
				+ "Connections for Stella Chang (schang24) {\n"
				+ "   Charvi Chang (schang26) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Charvi Chang (schang26) on Discord since Mon Dec 24 13:08:22 EST 2018\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Sam O'Draharay (odamah123) on Instagram since Tue Dec 24 13:08:22 EST 2019\n"
				+ "   Rishi Peswani (rpeswan2) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "}\n"
				+ "Connections for Charvi Chang (schang26) {\n"
				+ "   Stella Chang (schang24) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Stella Chang (schang24) on Discord since Mon Dec 24 13:08:22 EST 2018\n"
				+ "}\n"
				+ "Connections for Siddhant Soshi (ssoshi29) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "", manager4.getConnectionsByPerson());
		assertEquals("Connections for Kashyap Mudavat (kmudvat2) {\n"
				+ "   Charvi Chang (schang26) on Discord since Mon Jul 06 10:26:47 EDT 2020\n"
				+ "   Stella Chang (schang24) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Stella Chang (schang24) on Discord since Tue Oct 25 01:14:28 EDT 2022\n"
				+ "   Sam O'Draharay (odamah123) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Rishi Peswani (rpeswan2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "   Rishi Peswani (rpeswan2) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "}\n"
				+ "Connections for Sam O'Draharay (odamah123) {\n"
				+ "   Stella Chang (schang24) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Tue Oct 25 01:14:24 EDT 2022\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rjeswan2) {\n"
				+ "   Charvi Chang (schang26) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "   Charvi Chang (schang26) on Discord since Sat Oct 05 23:39:25 EDT 2013\n"
				+ "   Charvi Chang (schang26) on WeChat since Mon Nov 04 22:39:25 EST 2013\n"
				+ "   Charvi Chang (schang26) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Stella Chang (schang24) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rishi Peswani (rpeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "}\n"
				+ "Connections for Rishi Peswani (rpeswan2) {\n"
				+ "   Charvi Chang (schang26) on Discord since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Charvi Chang (schang26) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Charvi Chang (schang26) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Stella Chang (schang24) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Snapchat since Sat Jul 18 23:30:45 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Discord since Mon Jan 02 15:33:47 EST 2023\n"
				+ "   Rishi Peswani (rjeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "   Rishi Peswani (rjeswan2) on LinkedIn since Sat Mar 12 11:49:52 EST 2016\n"
				+ "}\n"
				+ "Connections for Stella Chang (schang24) {\n"
				+ "   Kashyap Mudavat (kmudvat2) on LinkedIn since Mon Jul 06 10:26:48 EDT 2020\n"
				+ "   Kashyap Mudavat (kmudvat2) on Discord since Tue Oct 25 01:14:28 EDT 2022\n"
				+ "   Sam O'Draharay (odamah123) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Rishi Peswani (rjeswan2) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Rishi Peswani (rpeswan2) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "}\n"
				+ "Connections for Charvi Chang (schang26) {\n"
				+ "   Kashyap Mudavat (kmudvat2) on Discord since Mon Jul 06 10:26:47 EDT 2020\n"
				+ "   Rishi Peswani (rjeswan2) on WeChat since Fri Oct 04 23:39:25 EDT 2013\n"
				+ "   Rishi Peswani (rjeswan2) on Discord since Sat Oct 05 23:39:25 EDT 2013\n"
				+ "   Rishi Peswani (rjeswan2) on WeChat since Mon Nov 04 22:39:25 EST 2013\n"
				+ "   Rishi Peswani (rjeswan2) on Instagram since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Rishi Peswani (rpeswan2) on Discord since Sun Dec 24 13:08:22 EST 2017\n"
				+ "   Rishi Peswani (rpeswan2) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "   Rishi Peswani (rpeswan2) on YouTube since Sun Feb 06 12:06:46 EST 2022\n"
				+ "}\n"
				+ "Connections for Siddhant Soshi (ssoshi29) {\n"
				+ "   No connections exist\n"
				+ "}\n"
				+ "", manager5.getConnectionsByPerson());
		
	}
	/**
	 * Method for testing GetConnectionsByPlatform method.
	 * @throws FileNotFoundException if the file is not found.
	 */
	@Test
	void testGetConnectionsByPlatform() throws FileNotFoundException {
		assertEquals("Connections on Discord {\n"
				+ "   Mon Jan 02 15:33:47 EST 2023: Elroy Daniel (daniele34) <--> Santos McLaughlin (mclaughlins441)\n"
				+ "}\n"
				+ "Connections on Instagram {\n"
				+ "   Sun Dec 24 13:08:22 EST 2017: Layla Crona (cronal634) <--> Elroy Daniel (daniele34)\n"
				+ "}\n"
				+ "Connections on LinkedIn {\n"
				+ "   Sat Mar 12 11:49:52 EST 2016: Layla Crona (cronal634) <--> Rowena Price (pricer774)\n"
				+ "   Mon Jul 06 10:26:48 EDT 2020: Santos McLaughlin (mclaughlins441) <--> Rowena Price (pricer774)\n"
				+ "   Tue Oct 25 01:14:24 EDT 2022: Elroy Daniel (daniele34) <--> Santos McLaughlin (mclaughlins441)\n"
				+ "}\n"
				+ "Connections on Snapchat {\n"
				+ "   Sat Jul 18 23:30:45 EDT 2020: Santos McLaughlin (mclaughlins441) <--> Rowena Price (pricer774)\n"
				+ "}\n"
				+ "Connections on WeChat {\n"
				+ "   Fri Oct 04 23:39:25 EDT 2013: Elroy Daniel (daniele34) <--> Rowena Price (pricer774)\n"
				+ "}\n"
				+ "Connections on YouTube {\n"
				+ "   Sun Feb 06 12:06:46 EST 2022: Layla Crona (cronal634) <--> Rowena Price (pricer774)\n"
				+ "}\n"
				+ "", manager.getConnectionsByPlatform());
		assertEquals("Connections on Discord {\n"
				+ "   Mon Jan 02 15:33:47 EST 2023: Kashyap Mudavat (kmudvat2) <--> Sam O'Draharay (odamah123)\n"
				+ "}\n"
				+ "Connections on Instagram {\n"
				+ "   Sun Dec 24 13:08:22 EST 2017: Stella Chang (schang24) <--> Sam O'Draharay (odamah123)\n"
				+ "}\n"
				+ "Connections on LinkedIn {\n"
				+ "   Sat Mar 12 11:49:52 EST 2016: Stella Chang (schang24) <--> Rishi Peswani (rpeswan2)\n"
				+ "   Mon Jul 06 10:26:48 EDT 2020: Kashyap Mudavat (kmudvat2) <--> Rishi Peswani (rpeswan2)\n"
				+ "   Tue Oct 25 01:14:24 EDT 2022: Kashyap Mudavat (kmudvat2) <--> Sam O'Draharay (odamah123)\n"
				+ "}\n"
				+ "Connections on Snapchat {\n"
				+ "   Sat Jul 18 23:30:45 EDT 2020: Kashyap Mudavat (kmudvat2) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "Connections on WeChat {\n"
				+ "   Fri Oct 04 23:39:25 EDT 2013: Sam O'Draharay (odamah123) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "Connections on YouTube {\n"
				+ "   Sun Feb 06 12:06:46 EST 2022: Stella Chang (schang24) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "", manager3.getConnectionsByPlatform());
		assertEquals("Connections on Discord {\n"
				+ "   Mon Dec 24 13:08:22 EST 2018: Charvi Chang (schang26) <--> Stella Chang (schang24)\n"
				+ "   Mon Jan 02 15:33:47 EST 2023: Kashyap Mudavat (kmudvat2) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "Connections on Instagram {\n"
				+ "   Sun Dec 24 13:08:22 EST 2017: Charvi Chang (schang26) <--> Stella Chang (schang24)\n"
				+ "   Tue Dec 24 13:08:22 EST 2019: Stella Chang (schang24) <--> Sam O'Draharay (odamah123)\n"
				+ "}\n"
				+ "Connections on LinkedIn {\n"
				+ "   Sat Mar 12 11:49:52 EST 2016: Rishi Peswani (rjeswan2) <--> Rishi Peswani (rpeswan2)\n"
				+ "   Sat Mar 12 11:49:53 EST 2016: Rishi Peswani (rjeswan2) <--> Rishi Peswani (rpeswan2)\n"
				+ "   Mon Jul 06 10:26:48 EDT 2020: Kashyap Mudavat (kmudvat2) <--> Rishi Peswani (rpeswan2)\n"
				+ "   Mon Jul 06 10:26:48 EDT 2020: Stella Chang (schang24) <--> Kashyap Mudavat (kmudvat2)\n"
				+ "   Tue Oct 25 01:14:24 EDT 2022: Kashyap Mudavat (kmudvat2) <--> Sam O'Draharay (odamah123)\n"
				+ "}\n"
				+ "Connections on Snapchat {\n"
				+ "   Sat Jul 18 23:30:45 EDT 2020: Kashyap Mudavat (kmudvat2) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "Connections on WeChat {\n"
				+ "   Fri Oct 04 23:39:25 EDT 2013: Sam O'Draharay (odamah123) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "Connections on YouTube {\n"
				+ "   Sun Feb 06 12:06:46 EST 2022: Stella Chang (schang24) <--> Rishi Peswani (rpeswan2)\n"
				+ "}\n"
				+ "", manager4.getConnectionsByPlatform());
	}
}
