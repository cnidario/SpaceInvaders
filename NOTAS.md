Actor - es todo objeto de juego que puede estar en la "escena", también conocido como GameObject, todo objeto de juego
Todo actor tiene: Aunque pueda reestructurarse por componentes y subclasificar a renderizable o no
	- posición en el mundo/escena
	- estado que indica si está activo, visible (si es de tipo renderizable), etc.

El jugador tiene (lo cual se puede aplicar a los invaders):
	- Un estado -> moviéndose, parado, disparando, alcanzado

El juego se organiza por subsistemas, para desacoplarlos se utiliza una cola de eventos, también un ProcessManager para la planificar ejecución de tareas que puedan durar varios "ticks" o iteraciones del game loop.
Estos sistemas son:
	- CollisionManager - gestor de colisiones. Controla, notifica cuando sucede una colisión. Detecta colisiones.
	- EventManager - gestor de eventos. Registra eventos y notifica.
	- InputManager - gestor de entrada. Registra la entrada y notifica.
	- ProcessManager - planificador de tareas
	
	Además: Renderizado y Control de la lógica de juego??