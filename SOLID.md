# Notas sobre principios SOLID del código

SRP - "Una clase debería tener una sola razón para cambiar" (C Martin)
OCP - Una clase debe permitir extender su funcionalidad sin modificar su código
LSP - Una implementación de una parte del sistema debería poder ser reemplazada por otra distinta sin alterar su corrección?
ISP - Un cliente no debe ser forzado a depender en métodos que no usa
DIP - (C Martin) 
	High-level modules should not depend on low-level modules. Both should depend on abstractions
	Abstractions should not depend on details. Details should depend on abstractions
	
En DIP entran los conceptos de inyección de dependencias, e inversión de control. Esto es que las clases más generales (abstractas) controlan tanto la creación como el flujo del código más especializado.
Código especializado no debe crear objetos. En realidad los objetos que son dependencias suyas. Crear objetos dinámicamente se realizará a través de dependencias "Factory" que serán inyectadas.
Código de menor nivel de abstracción no debe llamar a código de mayor nivel de abstracción. El flujo debe estar en control del framework que dirige y llama al código especializado.
	
Mismo nivel de abstracción. Misma capa, trabaja mismos conceptos.


{@link SpaceInvaders#create()} es el Composition Root, donde se realiza la inyección de dependencias.
(Composition root)[http://www.dotnetcurry.com/patterns-practices/1285/clean-composition-roots-dependency-injection]
Más métodos en esa clase violan SRP

SpaceInvaders viola SRP porque compone contiene el Composition Root y además otras 2 al mesno responsabilidades, es responsable del game loop, también del cálculo del diferencial de tiempo para la actualización del game loop.

GameWorld.createInvaders() viola DI, no diría que SRP. El nombre no está acorde ya que la responsabilidad de GameWorld es obviamente crear los game objects iniciales del juego.

Obviamente EventSystem no está cerrado a cambios O/C principle. Debería desechar la manutención de un enumerado y pasar a interfaz y eventos que implementen esa interfaz. La reflectividad lo permite, no sé si rompe principios.

ControllerSystem.handle, ese switch me suena a violación de OCP. Cómo hacerlo extensible a cualquier tipo de "comando" desconocido?
Mapea eventos de control -> a cambios de estado (de componente). Luego podría ser redefinible un mapeo, añadir nuevos mapeos, etc. Composición over inh.
Comportamiento general: Mapeo que no conozco o no soporto -> delegar ó rechazar/obviar

Patrón Cadena de responsabilidad?
Y el mapeo ha de construirse. A nivel más purista quizá algo como:
control.addControlHandler( new MoveLeftControl(new MovingLeftPlayerState()) )

2 patrones de comunicacion: Eventos - donde el evento sigue notificandose al resto de suscriptores
							Controles ¿? - donde el control muere una vez procesado?

La arquitectura de sistemas tampoco está abierta.
Quizá algo más del estilo. Registrador de sistemas. Al notificarse la añadición de uno se puede inyectar las dependencias.
Con IoC

CollisionSystem no cumple OCP, SRP
EntityBuilder no SRP, OCP, DI
EntityMapper - caso clave

EntityMapper viola DIP en los systems, debería ser inyectado (quizá como factory)
