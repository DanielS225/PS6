package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person_Test {
		
	private static PersonDomainModel person1;
	private static UUID person1UUID = UUID.randomUUID();			
	
	@BeforeClass
	public static void personInstance() throws Exception{
		
		Date person1Birth = null;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		person1 = new PersonDomainModel();
		 
		try {
			person1Birth = dateFormat.parse("1997-02-25");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		person1.setPersonID(person1UUID);
		person1.setFirstName("Daniel");
		person1.setMiddleName("A");
		person1.setLastName("Schmidt");
		person1.setBirthday(person1Birth);
		person1.setCity("Hockessin");
		person1.setStreet("24 Kent Dr.");
		person1.setPostalCode(19707);
		
		PersonDAL.addPerson(person1);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ArrayList<PersonDomainModel> people = PersonDAL.getPersons();
		for (PersonDomainModel person : people) {
			PersonDAL.deletePerson(person.getPersonID());
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(PersonDAL.getPersons().size(),1);
		assertEquals(PersonDAL.getPerson(person1UUID).getFirstName(),"Daniel");
		
		person1.setFirstName("Dan");
		PersonDAL.updatePerson(person1);
		
		assertEquals(PersonDAL.getPerson(person1UUID).getFirstName(),"Dan");
		
		PersonDAL.deletePerson(person1UUID);
		
		assertEquals(PersonDAL.getPersons().size(),0);
	}
	
}
