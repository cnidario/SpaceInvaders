package com.game.invaders.system.sound;

import java.util.Random;
import com.badlogic.gdx.audio.Sound;
import com.game.engine.component.Component;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class SoundResponseSystem extends AbstractProcess {
	private NodeSet nodeSet;
	private Random rnd;
	private Sound[] clips;
	
	@SuppressWarnings("unchecked")
	public SoundResponseSystem(EntityNodeSetFactory entityNodeSetFactory, Class<? extends Component> comp, Random rnd, Sound[] clips) {
		super();
		nodeSet = entityNodeSetFactory.create(comp);
		this.rnd = rnd;
		this.clips = clips;
	}
	@Override
	public void update(float dt) {
		if (!nodeSet.isEmpty()) {
			clips[rnd.nextInt(clips.length)].play(1f);
		}
	}
}
