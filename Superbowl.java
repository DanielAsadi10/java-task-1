package task1;

public class Superbowl {

    private int year;
    private String date;
    private String superbowlNumber;
    private String winningTeam;
    private int winningPoints;
    private String losingTeam;
    private int losingPoints;
    private String mvp;
    private String stadium;
    private String city;
    private String state;

    public Superbowl(int year, String date, String superbowlNumber,
                     String winningTeam, int winningPoints,
                     String losingTeam, int losingPoints,
                     String mvp, String stadium, String city, String state) {
        this.year = year;
        this.date = date;
        this.superbowlNumber = superbowlNumber;
        this.winningTeam = winningTeam;
        this.winningPoints = winningPoints;
        this.losingTeam = losingTeam;
        this.losingPoints = losingPoints;
        this.mvp = mvp;
        this.stadium = stadium;
        this.city = city;
        this.state = state;
    }

    public int getYear() {
        return year;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public String getLosingTeam() {
        return losingTeam;
    }

    public String getSuperbowlNumber() {
        return superbowlNumber;
    }
    
    public String getStadium() {
        return stadium;
    }
    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("--------------------------------------------------------------\n");
        sb.append(" Superbowl ").append(superbowlNumber).append(" (").append(year).append(")\n");
        sb.append(" Date: ").append(date).append("\n");
        sb.append(" Venue: ").append(stadium)
          .append(" in ").append(city).append(", ").append(state).append("\n");
        sb.append(" The ").append(winningTeam).append(" beat the ")
          .append(losingTeam).append(" by ")
          .append(winningPoints).append(" points to ")
          .append(losingPoints).append(" points\n");
        sb.append(" MVP: ").append(mvp).append("\n");
        sb.append("--------------------------------------------------------------\n");

        return sb.toString();
    }
}
