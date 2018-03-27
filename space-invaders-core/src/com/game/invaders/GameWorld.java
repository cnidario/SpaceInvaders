package com.game.invaders;

import com.game.invaders.scene.NodeGroup;
import com.game.invaders.scene.SceneGraph;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.components.StateMachineActorC;
import com.game.invaders.scene.actor.invader.InvaderState;
import com.game.invaders.scene.actor.invader.InvaderStateMachine;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;

public class GameWorld {
	private SceneGraph scene;
	private Actor player;
	private EventManager event_manager;
	
	public GameWorld(EventManager event_manager) {
		this.event_manager = event_manager;
		setupSceneGraph();
	}
	private void setupSceneGraph() {
		NodeGroup root = new NodeGroup(NodeGroup.ROOT_PARENT);
		scene = new SceneGraph(root);
	}
	
	public void create() {
		createInvaders();
		player = createPlayer();
		scene.getRoot().children().add(player);
	}
	
	private void createInvaders() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				Actor invader = createInvader(j*96, -i*76);
				scene.getRoot().children().add(invader);
				event_manager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, invader));
			}
		}
	}
	private Actor createInvader(float x, float y) {
		Actor invader = new Actor();
		invader.addComponent(GameResources.INVADER.RENDER_COMPO(invader));
		invader.addComponent(GameResources.INVADER.COLLISION_COMPO(invader));
		InvaderStateMachine stm = new InvaderStateMachine();
		invader.addComponent(new StateMachineActorC<InvaderState>(invader, stm));
		invader.getPos().x = x;
		invader.getPos().y = y;
		return invader;
	}
	private Actor createPlayer() {
		Actor player = new Actor();
		player.addComponent(GameResources.PLAYER.RENDER_COMPO(player));
		player.addComponent(GameResources.PLAYER.CONTROLLER_COMPO(player));
		return player;
	}
	public void update(float dt) {
	}
	public void init() {
	}
}
