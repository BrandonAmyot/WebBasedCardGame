package org.soen387.ser;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.PokeFC;
import org.soen387.dom.model.challenge.TDG.ChallengeTDG;

public class RenewDatabase {

	public static void main(String[] args) {
		PokeFC.prepareDbRegistry("");
		try {
		UserTDG.dropTable();
		ChallengeTDG.dropTable();
		} catch(Exception e){}
		
		try {
			UserTDG.dropUserRoleTable();
		} catch(Exception e){}
		
		
		try {
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
			ChallengeTDG.createTable();
		} catch(Exception e){}

	}

}
