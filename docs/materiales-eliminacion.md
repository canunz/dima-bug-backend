# Eliminación de Material de Apoyo

## API y autorización

- `DELETE /api/conocimientos/{conocimientoId}/materiales/{materialId}`
- `DELETE /api/procedimientos/{procedimientoId}/pasos/{pasoId}/materiales/{materialId}`

Éxito: `204 No Content`. Se conservan las reglas de Spring Security: JWT stateless,
ADMINISTRADOR y TECNICO. Sin autenticación: 401; rol ajeno: 403.
El request solo aporta IDs; no recibe rutas ni referencias de archivos.

## Orden y consistencia

1. Se inicia una transacción nueva de BD (`REQUIRES_NEW`) para garantizar que
   el borrado se confirme antes de tocar filesystem, incluso si existe una
   transacción del llamador.
2. Se valida la existencia y pertenencia del destino y del material persistido.
   Conocimiento exige `pasoId == null`; Paso exige `conocimientoId == null` y
   pertenencia del paso al procedimiento.
3. Las referencias `file:` deben seguir exactamente `file:materiales/{UUID-v4}`,
   según las claves generadas por el adapter existente. Se rechazan referencias
   inválidas antes del borrado. No se utiliza una ruta enviada por el cliente.
4. Se elimina el registro y se hace flush. Para Conocimiento se reindexa dentro
   de la misma transacción; los materiales de pasos no tienen reindexación nueva.
5. Se confirma la transacción. Cualquier fallo anterior impide borrar el archivo.
6. Para un archivo local se invoca `AlmacenamientoArchivoPort.eliminar`.
   Para un enlace externo no se invoca almacenamiento.

El adapter valida nuevamente la clave y su confinamiento al directorio configurado.
Una ausencia previa del archivo se considera eliminación física completada;
`deleteIfExists` permite limpiar el registro sin fallar por un archivo ya ausente.

Si falla filesystem después del commit, se devuelve **500**, con un mensaje que
informa que el registro se eliminó y requiere limpieza operativa. Se registra en
logs el material y la referencia interna, sin devolver rutas físicas en la API.
No se recrea el registro ni se revierte un commit ya confirmado.

## Límites y deuda técnica

BD y filesystem no comparten atomicidad. Se prioriza evitar registros que apunten
a archivos borrados por este flujo. Una caída después del commit o un fallo físico
puede dejar un archivo huérfano. Debe reconciliarse operativamente con la referencia
registrada; no se implementó outbox, cola, tarea de limpieza ni esquema SQL adicional.
Un nuevo DELETE por el mismo ID devuelve 404 si el registro ya fue eliminado;
no constituye un mecanismo de reintento de limpieza física.

Los mocks de transacción verifican el orden, rollback y fallos de commit; las pruebas
de filesystem usan un directorio temporal real. No sustituyen integración con MySQL
real, pruebas de caída de proceso ni concurrencia. El modelo existente carece de
versionado/bloqueo específico para coordinar PUT y DELETE concurrentes.

Persisten el acoplamiento entre Procedimiento y contratos de Conocimiento y la
limpieza pendiente de archivos reemplazados mediante PUT. No se rediseñaron módulos.
