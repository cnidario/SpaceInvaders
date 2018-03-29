Actores
-------
Actor es todo objeto de juego que puede estar en la "escena", también conocido como GameObject o Entity
Todo actor tiene:
	- posición en el mundo/escena
	- estado que indica si está activo, visible (si es de tipo renderizable), etc.
	- una lista de componentes, con los datos del resto de posibles aspectos

Los actores se organizan en un árbol de escena, que es una estructura para definir una jerarquía única.

Árbol de escena
---------------
Como todo árbol tiene raíz, tiene nodos de grupo y nodos normales que pueden ser actores o componentes.
Su objetivo es determinar de forma rápida la propiedad de un objeto en sistemas en los cuales el cambio de un objeto produce cambios en otros de forma jerárquica (generalmente es muy útil en representaciones de espacios 3d, donde un objeto mesa puede tener otros objetos como uno que sea vaso, y el objeto taza puede tener a su vez el objeto asa, mover la mesa implica actualizar las matrices de transformación de los 3, esta búsqueda es facilitada por esta estructura)

Componentes
-----------
Sólo contienen datos, no funcionalidad. Son aspectos de las entidades, aka actores, por ejemplo un actor puede tener el componente Collision, el objeto se le añade con ciertos datos necesarios para determinar las colisiones (en el caso más simple una BoundingBox y unos grupos de colisión al que pertenece). El sistema de colisiones lo registrará entonces como de su interés y cuando sea requerido por chequeo de colisiones comprobará ese objeto con ese dato y otros (su posición, etc) junto con otros objetos que también tengan ese componente.

Sistemas
--------
La lógica del juego se organiza en sistemas.
Para desacoplar unos de otros se comunican a través de un sistema de eventos. Patrón observer.
Hay un sistema que maneja la ejecución de sistemas en cada iteración del game loop. 

Estos sistemas son:
- `CollisionManager` - Detecta colisiones. Asociado a componente de colisiones.
- `EventManager` - Recibe y distribuye eventos.
- `InputManager` - Recibe entrada bruta (teclas), la transforma en acciones.
- `ProcessManager` - Planifica tareas y sistemas.
- `PhysicsManager` - Actualiza posiciones en base a velocidades. Procesa componente Physics.
- `ControllerManager` - Transforma acciones de usuario en acciones de juego. Sistema de componente.
- `RenderManager` - Renderizado en pantalla.
Tanto el Render como el Event se procesan fuera del ProcessManager

Principios directores
---------------------
*SOLID*

Principios importantes sistemas ECS (Entidad - Componente - Sistema)
--------------------------------------------------------------------
- Entidad es agregación de componentes sin datos (bueno quizá pueda tener alguno propio, nombre?)
- Componentes son datos puros, no funcionalidad (aspectos de las entidades)
- Sistema incluye la funcionalidad
- La relación no es 1:1 entre Componentes y Sistemas, sino n:m, puede haber un sistema para una combinación determinada de componentes

### Responsabilidades. CRC

#### EntityManager
CRUD sobre entidades, gestiona todo el acceso a las entidades

- Crea/elimina entidades
- Crea componentes
- Asigna/elimina componentes a entidades
- Busca entidades/componentes
----
EventManager para notificar operaciones a otros sistemas

#### EventManager
- Notifica de eventos ocurridos en diversas partes a otras interesadas
- Permite suscribirse/desuscribirse a ciertos tipos de notificaciones
- Define los diversos tipos de eventos posibles
- Recibe eventos
----
No colabora, desacoplado. Aunque define/conoce Eventos y Listeners 

#### SceneGraph
- Mantiene relación jerárquica entre entidades
- Uso aún no especificado
----
No colabora, desacoplado. Aunque tiene un *Iterator* que es el ScenNodePointer, a través del cual se accede a él

#### ProcessManager
- Organiza la ejecución de diversas tareas que duran más de un ciclo del bucle de juego
- Permite añadir/eliminar/controlar tareas a ser ejecutadas
----
No colabora, en principio desacoplado. Aunque podría usar EventManager para notificar eventos? Exporta interfaz Process

#### GameResources
- Encargada de obtener los recursos (audio, imagen, animación, etc) del juego
----
No colabora. Datos estáticos, globales, constantes. Salvo con entidades básicas del framework por debajo (Texture, etc.)

#### GameConfigData
- Datos constantes del juego que no son recursos, configuración
----
No colabora

