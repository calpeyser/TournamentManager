package Base;

import javax.persistence.*;

@Entity
public abstract class Record {
	@Id @GeneratedValue long id;
}
