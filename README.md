Proyecto de Ingeniería de Software, semestre 2020-2
Pasos para correr correctamente el proyecto.
- Importar a eclipse el proyecto.
- Actualizar dependencias de Maven.
- Cambiar web.xml la contraseÃ±a de jdbc
- Cambiar en web.xml la ruta donde se quieran guardar las imágenes de los alimentos.
- Cambiar en la carpeta Servers el archivo server.xml y agregar después del contexto de Yum la línea:
<Context docBase="path\a las\imagenes" path="/Yum/imagenes"/>
- Correr en tomcat 8.5
