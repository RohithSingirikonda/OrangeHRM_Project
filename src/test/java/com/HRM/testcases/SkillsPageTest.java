package com.HRM.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.HRM.base.TestBase;
import com.HRM.pages.AdminModulePage;
import com.HRM.pages.LoginPage;
import com.HRM.pages.SkillsPage;
import com.HRM.util.TestUtil;

public class SkillsPageTest extends TestBase {

	
	public String actual_message;
	String AddSkills_sheet = "AddSkills";
	
	LoginPage loginPage;
	AdminModulePage adminModulePage;
	SkillsPage skillsPage;
	
	public SkillsPageTest(){
		super();
	}
	
	
	@BeforeMethod
	public void setUp(){
		init();
		loginPage = new LoginPage();
		adminModulePage  = new AdminModulePage();
		skillsPage = new SkillsPage();
		loginPage.AdminLogin(prop.getProperty("admin_username"), prop.getProperty("admin_password"));
		adminModulePage.ClickonSkillsMenu();
	}
	
	@Test(priority=1, enabled=true)
	public void VerifySkillsPageHeaderTest(){
		actual_message = skillsPage.SkillsPageHeader();
		Assert.assertEquals(actual_message, "Skills");
	}
	
	@Test(priority=2, enabled=true)
	public void VerifyAddSkillsPageHeaderTest(){
		actual_message = skillsPage.AddSkillsPageHeader();
		Assert.assertEquals(actual_message, "Add Skills");
	}
	
	@DataProvider(name = "AddSkillSet")
	public Object[][] getOrangeHRMTestData(){
		Object data[][] = TestUtil.getTestData("AddSkills");
		return data;
	}
	
	
	@Test(dataProvider="AddSkillSet", priority=3, enabled=true)
	public void VerifyAddSkillTest(String skill, String desc){
		actual_message = skillsPage.AddSkill(skill, desc);

		if (actual_message.contains("Required")) {
			assertTrue(actual_message.contains("Required"));
		} 
		else if(actual_message.contains("exists")) {
			assertTrue(actual_message.contains("Already exists"));
		}else {
			assertTrue(actual_message.contains("Successfully Saved"));
		}
		
	}
	
	
	@DataProvider(name = "DeleteSkillSet")
	public Object[][] getOrangeHRMTestData1(){
		Object data[][] = TestUtil.getTestData("DeleteSkills");
		return data;
	}
	
	
	@Test(dataProvider="DeleteSkillSet", priority=4, enabled=true)
	public void VerifyDeleteSkillTest(String skill){
		actual_message = skillsPage.DeleteSkill(skill);
		if (actual_message.contains("Success"))
		assertTrue(actual_message.contains("Successfully Deleted"));
	}
	
	
	@DataProvider(name = "EditSkillSet")
	public Object[][] getOrangeHRMTestData2(){
		Object data[][] = TestUtil.getTestData("EditSkills");
		return data;
	}
	
	
	@Test(dataProvider="EditSkillSet", priority=5, enabled=true)
	public void VerifyEditSkillTest(String existingskill, String newskill){
		actual_message = skillsPage.EditSkill(existingskill, newskill);
		if (actual_message.contains("Required")) {
			assertTrue(actual_message.contains("Required"));
		} else {
			assertTrue(actual_message.contains("Successfully Updated"));
		}
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}
	
}
