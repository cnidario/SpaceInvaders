EntityManager - guarda relacion entidades existentes y referencia a sus componentes
EntityNode - apunta a una entidad
		   - recuperar sus componentes
		   - borrar
		   - relaciones con otras entidades (de tabla a ORM)
		   - relacion especial jerárquica (componente especial, SceneNode)
EntityNodeSet - filtra una serie de nodos
			  - En principio por componentes que contiene
			  - También por Spaces (componente especial)
			  - quizá filtrado custom?
EntityNodeManager - mediador entre las operaciones de creación etc con EntityManager entre EntityNode
				  - atento a sus propios cambios para reactualizar nodeset's
				  - atento a creación, eliminación de entidades
				  - a adición, eliminación de componentes
				  - actualización de componentes <- esto necesita rediseñarlos, implementarlos por objetos, 'boxing' de datos
				  - cambios en jerarquía recursivos