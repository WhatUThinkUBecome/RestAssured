package APITest;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import files.Payload;
import files.ReusableMethods;


public class TeamRCB {
	JsonPath js;
	int N;
	@BeforeClass
	public void Pre_Req() {
		//Convert Payload to JsonPath to access the values
		js = ReusableMethods.rawToJson(Payload.rcbTeamDetailsJson());
		N = js.getList("player.name").size();
	}
	@Test
	public void TC1_VerifyIflimitOfForeignersIsNotMoreThan4() {
		int foreignersCount =0;
		for(int i=0;i<N;i++) {
			if(!(js.getString("player.country["+i+"]").equalsIgnoreCase("India")))
				foreignersCount++;
		}
		Assert.assertTrue(foreignersCount<=4);
	}
	@Test
	public void TC2_VerifyIfTeamHasAtleastOneWicketKeeper() {
		boolean wickerKeeper=false;
		for(int i=0;i<N;i++) {
			if((js.getString("player.role["+i+"]").equalsIgnoreCase("Wicket-keeper")))
			{
				wickerKeeper=true;
				break;
			}
		}
		Assert.assertTrue(wickerKeeper);
	}

}
