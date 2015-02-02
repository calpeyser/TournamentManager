package MockTrial;

import javax.persistence.*;
import java.util.*;
import Base.Record;

@Entity
public class Team extends Record {
	@Basic public String schoolName;
	@Basic public String designation; // for this tournament.  Ex. "936"
	@OneToMany public List<Player> players;
	@OneToMany public Map<Player, Integer> ranksByPlayer;
	
	@OneToMany public List<Team> wins;
	@OneToMany public List<Team> losses;
	
	public Team(String schoolName, String designation, List<Player> players) {
		this.schoolName = schoolName;
		this.designation = designation;
		this.players = players;
		
		this.ranksByPlayer = new HashMap<Player,Integer>();
		for (Player player : this.players) {
			ranksByPlayer.put(player, 0);
		}
		
		this.wins = new ArrayList<Team>();
		this.losses = new ArrayList<Team>();
	}
	
	@Override
	public String toString() {
		return schoolName;
	}
	
}
