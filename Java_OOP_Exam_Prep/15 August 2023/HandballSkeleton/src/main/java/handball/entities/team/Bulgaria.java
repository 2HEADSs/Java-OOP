package handball.entities.team;

public class Bulgaria extends BaseTeam {

    private static int ADVANTAGE = 115;

    public Bulgaria(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        //PAVEL super.getAdvantage()
        //I can only play Outdoor!
        setAdvantage(getAdvantage() + ADVANTAGE);
    }
}
