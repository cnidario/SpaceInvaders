21/03/2018
GameWorld crea los actores de los invaders
GameWorld deja una notificación de creación de actor
CollisionManager recoge esta notificación y crea el registro para observar colisiones
En este punto CollisionManager debe saber qué estrategia de colisión requiere el actor
Quizá incluír tipo de actor, INVADER, PLAYER, y una factory en base a esto proveede la estrategia, también hace falta el boundingbox, quizá un componente de colisión sería mejor...
TODO 

EventSystem -> eventos inmediatos? trigger

Componentizar actores.
Un actor posee una serie de componentes. Registro Enum. Interfaz ActorComponent
Puede haber un factory que carga de descripción XML o algo
Actor posee
	- getComponent
Componentes:
	- RenderComponentInterface <- son strategy/state, quizá tiene color, alguno tendrá shader, sprite
	- PhysicsComponent <- uno en concreto tiene boundingbox, otros otras formas, material, dimensiones, forma, orientacion/posicion?
	
	
Lista de actores, o árbol (dependencias, scene-graph), agrupar por tipos quizá, subseleccionar para eficiencia
Otros sistemas:
	- SoundManager - sonido
	- Controller - acciones de los eventos de entrada, separado de input, un controller estará asociado a un actor
		o quizá es un componente que tienen, un strategy que se suscribe a eventos de control
	- Lógica de juego - se suscribe a eventos de golpeado por ejemplo y opera en los actores, crea, destruye
		o bien strategy por actor, o algo que asigne un strategy global a un grupo de actores (lo cual sería equivalente a un manager global y la info del actor eliminado)
	- operaciones básicas genéricas por actor. visible/no, activo/no, eliminar
	- stages? estado de juego apilado?
	- AiSystem
	Sistemas leen de componentes
	
Quizá sistemizar y meter en processmanager

Desgranar una a una funcionalidades, asignar responsabilidades, exponer soluciones y decisiones técnicas