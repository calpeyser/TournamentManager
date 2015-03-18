package MootCourt;

import javax.persistence.Basic;

import Base.FormFormat;
import Base.Record;

public class Ballot extends Record {

	@Basic public String JudgeName;
	
	@Basic @FormFormat(tab = "Petitioner") public int P1Argument;
	@Basic @FormFormat(tab = "Petitioner") public int P1Presentation;
	@Basic @FormFormat(tab = "Petitioner") public int P2Argument;
	@Basic @FormFormat(tab = "Petitioner") public int P2Presentation;
	@Basic @FormFormat(tab = "Petitioner") public int PProfessionalism;
	
	@Basic @FormFormat(tab = "Respondent") public int R1Argument;
	@Basic @FormFormat(tab = "Respondent") public int R1Presentation;
	@Basic @FormFormat(tab = "Respondent") public int R2Argument;
	@Basic @FormFormat(tab = "Respondent") public int R2Presentation;
	@Basic @FormFormat(tab = "Respondent") public int RProfessionalism;

	
}
