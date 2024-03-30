package handball.entities.team;

public class Germany extends BaseTeam {
    private static int ADVANTAGE = 145;

    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        //PAVEL super.getAdvantage()
        //I can only play Outdoor!
        setAdvantage(getAdvantage() + ADVANTAGE);
    }
}
