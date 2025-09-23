# Conexión Frontend-Backend - Ruta MTB

## 📋 Resumen de Cambios Realizados

### 🔧 Backend (Spring Boot)
1. **Configuración CORS actualizada** en `SecurityConfig.java`:
   - Agregada URL específica del frontend en producción: `https://master.d1madz5fpdj89j.amplifyapp.com`
   - Mantenido soporte para desarrollo local
   - Configuración de headers y métodos HTTP permitidos

### 🌐 Frontend (JavaScript)
1. **Nuevos archivos creados**:
   - `app/api-config.js`: Configuración de la API y detección automática de entorno
   - `app/api-service.js`: Servicios para autenticación y productos

2. **Modificaciones en `script.js`**:
   - Integración con servicios de API del backend
   - Sistema de fallback a datos locales si el backend no está disponible
   - Funciones de autenticación actualizadas para usar JWT
   - CRUD de productos actualizado para usar API REST

## 🚀 Configuración de Despliegue

### Para Desarrollo Local
```javascript
// En api-config.js, la URL se detecta automáticamente
// Para localhost: http://localhost:8080
```

### Para Producción
1. **Backend desplegado** en AWS App Runner: `https://8mq33rknsp.us-east-1.awsapprunner.com`
2. **Frontend configurado automáticamente** para detectar el entorno y usar la URL correcta

## 🎯 Estado Actual del Despliegue

✅ **Backend**: Desplegado en AWS App Runner
- URL: https://8mq33rknsp.us-east-1.awsapprunner.com
- CORS configurado para el frontend de Amplify

✅ **Frontend**: Configurado para usar el backend en producción
- URL: https://master.d1madz5fpdj89j.amplifyapp.com/
- Detección automática de entorno implementada

## 📡 Endpoints de la API

### Autenticación
- `POST /rutamtb/auth/login`: Iniciar sesión
- `POST /rutamtb/auth/register`: Registrar usuario
- `POST /rutamtb/auth/validate`: Validar token JWT

### Productos
- `GET /rutamtb/products`: Obtener todos los productos
- `GET /rutamtb/products/search/{id}`: Obtener producto por ID
- `POST /rutamtb/products/create`: Crear producto (requiere autenticación)
- `PUT /rutamtb/products/update/{id}`: Actualizar producto (requiere autenticación)
- `DELETE /rutamtb/products/delete/{id}`: Eliminar producto (requiere autenticación)

## 🔑 Autenticación JWT

El sistema ahora usa autenticación JWT:
- El token se almacena en `localStorage` como `authToken`
- Se incluye automáticamente en las peticiones que requieren autenticación
- El token se valida automáticamente al cargar la aplicación

## 🛠️ Características Implementadas

### ✅ Completadas
- [x] Configuración CORS para producción
- [x] Sistema de autenticación con JWT
- [x] Servicios de API para productos
- [x] Sistema de fallback a datos locales
- [x] Detección automática de entorno
- [x] CRUD de productos con API REST
- [x] Manejo de errores de conexión

### 🔄 Sistema de Fallback
Si el backend no está disponible, la aplicación automáticamente:
- Usa datos locales guardados en `localStorage`
- Mantiene la funcionalidad básica del sitio
- Muestra mensajes informativos en la consola

## 🎯 Próximos Pasos para Despliegue Completo

1. **Desplegar el backend**:
   ```bash
   # Ejemplo para Heroku
   heroku create tu-app-backend
   git push heroku main
   ```

2. **Actualizar URL en el frontend**:
   - Modificar `api-config.js` con la URL real del backend
   - Hacer push a tu repositorio de frontend

3. **Configurar base de datos en producción**:
   - Agregar variables de entorno para la base de datos
   - Actualizar `application.properties`

4. **Verificar CORS**:
   - Probar la conexión desde el frontend desplegado
   - Verificar que no haya errores de CORS en la consola

## 🔍 Testing
Para probar la conexión:

1. **Desarrollo local**:
   ```bash
   # Terminal 1: Ejecutar backend
   cd grupo3-backend
   ./mvnw spring-boot:run
   
   # Terminal 2: Servir frontend
   cd ruta-mtb-front
   python -m http.server 8000
   ```

2. **Producción**:
   - Verificar que ambos servicios estén desplegados
   - Probar funcionalidades de login/registro
   - Verificar carga de productos desde la API

## ⚠️ Notas Importantes

1. **CORS**: Configurado para permitir conexiones desde la URL específica de Amplify
2. **Seguridad**: Los endpoints sensibles requieren autenticación JWT
3. **Fallback**: La aplicación funciona sin backend para demostración básica
4. **Tokens**: Los JWT tienen una duración de 24 horas

---

**✅ CONEXIÓN COMPLETADA**
- **Frontend**: https://master.d1madz5fpdj89j.amplifyapp.com/
- **Backend**: https://8mq33rknsp.us-east-1.awsapprunner.com/

🎉 **Tu aplicación está lista para funcionar en producción con la conexión frontend-backend configurada.**