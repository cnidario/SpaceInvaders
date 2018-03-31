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