# Pila con Spring Boot

Práctica de Estructuras de Datos: usar la pila (`Stack`) que ya viene en Java dentro de una API sencilla hecha con Spring Boot.

La API ya está armada y corre, pero todavía no guarda nada. Tu trabajo es conectar los endpoints con una pila.

## Requisitos

- JDK 17 o más nuevo
- IntelliJ IDEA (Community está bien)
- Internet la primera vez que abras el proyecto (para descargar Gradle y las librerías)

No hace falta instalar Gradle ni Spring. El proyecto trae todo lo necesario.

## Cómo abrir el proyecto

1. Clona el repositorio.
2. En IntelliJ: **File > Open** y selecciona la carpeta `stackSpring`.
3. Si pregunta, elige **Trust Project**.
4. Espera a que termine de cargar (abajo a la derecha se ve el progreso). La primera vez tarda unos minutos.
5. Si IntelliJ te pide un SDK, selecciona tu JDK en **File > Project Structure > Project > SDK**.

## Estructura

```
src/main/java/com/example/stackspring/
├── StackSpringApplication.java     <- arranca la aplicación (no se toca)
└── controller/
    └── PilaController.java         <- AQUÍ VA TU CÓDIGO
```

## El ejercicio

Abre `PilaController.java`. Tiene tres endpoints que por ahora no hacen nada:

| Endpoint | Método en Java | Qué debe hacer |
|---|---|---|
| `POST /pila?valor=hola` | `agregar` | Meter el valor a la pila (`push`) |
| `GET /pila/pop` | `sacar` | Sacar el elemento de arriba (`pop`) y regresarlo |
| `GET /pila` | `verPila` | Regresar toda la pila |

Lo que tienes que hacer:

1. Crear una pila de `String` como atributo de la clase usando `java.util.Stack` (el import ya está).
2. En `agregar`, hacer `push` del valor.
3. En `sacar`, hacer `pop` y regresar el valor que salió. Ojo: si la pila está vacía, `pop()` lanza una excepción. Revisa primero con `isEmpty()`.
4. En `verPila`, regresar la pila completa.

Son unas 5 líneas en total.

Métodos de `Stack` que te sirven:

| Método | Qué hace |
|---|---|
| `push(x)` | Mete `x` arriba |
| `pop()` | Saca y regresa el de arriba |
| `peek()` | Regresa el de arriba sin sacarlo |
| `isEmpty()` | `true` si no hay elementos |
| `size()` | Cuántos elementos hay |

## Cómo correr la aplicación

Abre `StackSpringApplication.java` y dale clic al botón verde ▶ junto a `main`.

Cuando en la consola aparezca algo como `Tomcat started on port 8080`, la API ya está lista en `http://localhost:8080`.

Para detenerla, usa el botón rojo ■. Cada vez que cambies código, detén y vuelve a correr.

## Cómo probar

Los dos GET se pueden abrir directo en el navegador. El POST no; para ese usa Postman, `curl` o el archivo `peticiones.http` (en IntelliJ, si tu versión lo soporta).

### Agregar (push)

- Método: **POST**
- URL: `http://localhost:8080/pila?valor=hola`
- Body: ninguno

```bash
curl -X POST "http://localhost:8080/pila?valor=hola"
```

### Sacar (pop)

- Método: **GET**
- URL: http://localhost:8080/pila/pop

### Ver toda la pila

- Método: **GET**
- URL: http://localhost:8080/pila

## Resultado esperado

| Petición | Respuesta |
|---|---|
| `GET /pila` | `[]` |
| `POST /pila?valor=uno` | `Se agrego: uno` |
| `POST /pila?valor=dos` | `Se agrego: dos` |
| `POST /pila?valor=tres` | `Se agrego: tres` |
| `GET /pila` | `["uno","dos","tres"]` |
| `GET /pila/pop` | `Se saco: tres` |
| `GET /pila` | `["uno","dos"]` |

La lista se muestra de abajo hacia arriba: el último elemento es el tope.

Si `GET /pila` sigue regresando `[]` después de hacer POST, todavía no estás guardando nada en la pila.

> Los datos viven en memoria. Si detienes la aplicación, la pila vuelve a empezar vacía.

## Reto extra

Agrega un endpoint `GET /pila/peek` que regrese el elemento de arriba sin sacarlo.

## Errores comunes

- **`EmptyStackException`**: hiciste `pop()` con la pila vacía. Revisa con `isEmpty()` antes.
- **`Port 8080 was already in use`**: ya tienes la aplicación corriendo en otra ventana. Detenla primero.
- **IntelliJ marca todo en rojo**: espera a que termine de descargar las dependencias, o da clic en el ícono del elefante (Gradle) y luego en **Reload**.
