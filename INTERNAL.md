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

----
RenderStrategy dentro de component? no me convence del todo. Quizá acabar migrando a ECS completo
StateMachines más sencillas? devolver id de estado. Una clase por movimiento de la nave???
quizá moving, normal, shooting
Estados más sencillos? Máquinas más sencillas? de sólo el estado en que está, o estado y parámetros, sin funcionalidad??
quizá no va ahí ninguna funcionalidad sólo transiciones

Sistemas, refactorizaer: extraer interfaz común, agrupar entidades, registrar por sistema de observers las entidades, escuchar por el cambio de los componentes. Cada sistema escucha, gestiona sus listas, quizá una clase especial que agrupe por ID's, y registre/borre. Sirve el mismo eventmanager
COMPONENT_ADDED([actor,]compo), COMPONENT_REMOVED([actor,]compo), COMPONENT_CHANGED(compo) [entre corchetes pq actor se puede obtener del compo (?)]
----

Apuntar alto: pensar en anotaciones y framework IoC
Componentes, entidades -> árbol de escena, componente es un nodo cuyo padre es la entidad. El padre de una entidad(actor) puede considerarse un contenedor, o grupo. De este modo es un scene-graph
----
Problema: en constructor de componentes el valor de id es estático, debería instanciarse a la hora de declarar la clase
