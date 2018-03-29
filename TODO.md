En este fichero van todas las ideas de tareas realizables, para no estar escasos.
Leyenda:
- `FUN` - funcionalidad con visibilidad fuera, en el producto, para el usuario
- `SER` - funcionalidad interna, servicio
- `REF` - refactoring
- `IDE` - idea posiblemente difusa
- `FAC` - fácil
- `ARQ` - arquitectura, servicios, no es funcionalidad sino algo que es inversión de futuro, flexibilidad
- `DIF` - dificultad considerable, o posiblemente no estimable

- `[SER, REF]` Hay un patrón que se repite en los sistemas, que es gestionar una lista de actores (o estructura de datos arbitraria) ante determinados eventos, añadir en presencia de componentes, eliminar en ausencia, registrar eventos de añadir y eliminar componentes. Se puede generalizar y extraer en funcionalidad más genérica. De momento es repitiendo código.
- `[IDE]` Jerarquía de eventos? quizá pensar mejor, COMPONENT_CHANGED y sub eventos ADDED, REMOVED es mejor que los dos? 
- `[ARQ, FAC]` dispose en sistemas, lo contrario de init
- `[ARQ, SER, IDE, DIF]` Contenedor que implemente inyección de dependencias, quizá usar reflectividad y anotaciones.
- `[REF, FAC]` Desacoplar SceneGraph de Actores
- `[SER]` Lista de acciones
- `[IDE]` ComponentMapper o retriever, expresiones para conseguir las entidades y los componentes?, Objeto memo/cache, gestionado por eventos para desacoplar el mantenimiento de los 'componentes gestionados por un sistema'. Los componentes que gestuiona un sistema se definen únicamente por una combinación de componentes que poseen, una relación de sus componentes (y de los valores de estos posiblemente, aunque esto ya es a otro nivel, de ejecución, dentro del propio sistema que descarta unos u otros) Relaciones básicas.
- tiene componente
- Conexiones lógicas: Y, O, NO
Expression pattern. Factory method que parsee notación polaca inversa (por sencillez), aunque infija con paréntesis es fácil
Expresiones:
- <expresión> ::= Nombre de componente
- <expresión> ::= (<expresión>)
- <expresión> ::= <expresión> and <expresión>
- <expresión> ::= <expresión> or <expresión>
- <expresión> ::= not <expresión>
ComponentMatcher -> Mantiene caché con colaboración del EventManager de entidades que cumplen cierto predicado
ComponentMapper -> Extrae componentes de una entidad, problema de disyunciones