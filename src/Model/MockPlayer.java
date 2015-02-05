package Model;

import java.lang.reflect.*;

import javax.persistence.*;

import Base.*;
import Data.InitializeWith;

@Entity
public class MockPlayer extends Record {
	@Basic @MustBeSet public String name;
	@Basic public Integer score;
		
	public MockPlayer() {
		this.score = 0;
	}
	
	@Override
	public String toString() {
		return name;
	}
		
}
