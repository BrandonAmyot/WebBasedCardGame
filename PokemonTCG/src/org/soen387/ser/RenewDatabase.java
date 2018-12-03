package org.soen387.ser;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.PokeFC;
import org.soen387.dom.model.card.ts.CardTDG;
import org.soen387.dom.model.challenge.ts.ChallengeTDG;
import org.soen387.dom.model.deck.ts.DeckTDG;

public class RenewDatabase {

	public static void main(String[] args) {
		PokeFC.prepareDbRegistry("");
		try {
		UserTDG.dropTable();
		CardTDG.dropTable();
		DeckTDG.dropTable();
		ChallengeTDG.dropTable();
		} catch(Exception e){}
		
		try {
			UserTDG.dropUserRoleTable();
		} catch(Exception e){}
		
		
		try {
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
			CardTDG.createTable();
			DeckTDG.createTable();
			ChallengeTDG.createTable();
		} catch(Exception e){}

	}

}
