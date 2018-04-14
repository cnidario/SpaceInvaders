package com.game.invaders.system.impact;

import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Animation;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.event.InvaderImpactedEvent;
import com.game.engine.system.event.EventSystem.EventListener;

public class InvaderImpactSystem extends AbstractProcess {
	private EntityManager manager;
	private EventSystem eventManager;
	
	public InvaderImpactSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
	}
	@Override
	public void init() {
		eventManager.registerHandler(new EventListener<InvaderImpactedEvent>() {
			@Override
			public void handle(InvaderImpactedEvent e) {
				int invader = e.getInvader();
				int shoot = e.getShoot();
				Invader invader_c = (Invader) manager.componentFor(invader, Invader.class);
				invader_c.setStateID(InvaderStateID.DYING);
				invader_c.setDyingTime(GameConfigData.INVADER.EXPLOSION_DELAY);
				TiltExploding explo_c = new TiltExploding(6, GameConfigData.INVADER.EXPLOSION_DELAY/5);
				manager.addComponent(invader, explo_c);
				Animation anim_c = (Animation) manager.componentFor(invader, Animation.class);
				if(anim_c != null)
					manager.markComponentForRemove(invader, anim_c);
				manager.markEntityForRemove(shoot);
			}
		}, InvaderImpactedEvent.class);
	}
	@Override
	public void update(float dt) {
		
	}
}
