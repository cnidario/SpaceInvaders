21/03/2018
GameWorld crea los actores de los invaders
GameWorld deja una notificación de creación de actor
CollisionManager recoge esta notificación y crea el registro para observar colisiones
En este punto CollisionManager debe saber qué estrategia de colisión requiere el actor
Quizá incluír tipo de actor, INVADER, PLAYER, y una factory en base a esto proveede la estrategia, también hace falta el boundingbox, quizá un componente de colisión sería mejor...
TODO 