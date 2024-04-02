package handball;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeamTests {
    private Team team;
    private HandballPlayer handballPlayer;

    @Before
    public void setUp() {
        this.team = new Team("test1", 1);
        this.handballPlayer = new HandballPlayer("Ivan");
        team.add(handballPlayer);
    }

    @Test
    public void test_Get_Name() {
        Assert.assertEquals("test1", team.getName());
    }

    @Test
    public void test_Set_Name() {
        Team team1 = new Team("test2", 2);
        Assert.assertEquals("test2", team1.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Null_Should_Throw() {
        Team team1 = new Team(null, 2);
        Assert.assertEquals("test2", team1.getName());
    }

    @Test(expected = NullPointerException.class)
    public void test_Set_Name_Empty_Should_Throw() {
        Team team1 = new Team("", 2);
        Assert.assertEquals("test2", team1.getName());
    }

    @Test
    public void test_Get_Position() {
        Assert.assertEquals(1, team.getPosition());
    }


    @Test
    public void test_Set_Position() {
        Team team1 = new Team("test1", 2);
        Assert.assertEquals(2, team1.getPosition());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Set_Position_Should_Throw() {
        Team team1 = new Team("test1", -1);
    }


    @Test
    public void test_Get_Count() {
        Assert.assertEquals(1, team.getCount());
    }

    @Test
    public void test_Add() {
        Team team2 = new Team("test2", 1);
        team2.add(handballPlayer);
        Assert.assertEquals(1, team2.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_Should_Throw() {
        HandballPlayer handballPlayer1 = new HandballPlayer("Peter");
        team.add(handballPlayer1);
    }


    @Test
    public void test_Remove() {
        team.remove("Ivan");
        Assert.assertEquals(0, team.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_Should_Throw() {
        team.remove("Stefan");
    }

    @Test
    public void test_Player_For_AnotherTeam(){
        team.playerForAnotherTeam("Ivan");
        Assert.assertFalse(handballPlayer.isActive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Player_For_AnotherTeam_Should_Throw(){
        team.playerForAnotherTeam("Peter");
    }

    @Test
    public void test_Get_Statistics(){
Assert.assertEquals("The player Ivan is in the team test1.", this.team.getStatistics());
    }
}
