1.- Se generaron los .war en los directorios
	\mi-cuenta-web\war\mi-cuenta-web.war
	\api_rest\war\api_rest.war
	
2.- para hacer andar las apis y la web, debera desplegar estos war en jboss, en su preferencia jboss7

3.- la pagina principal de la web tiene dos opciones
	Login(si previamente ya se registro)
	Registrarse(darse de al en la aplicacion)
	
4.- al iniciar sesion con el boton login, les mostrara sus datos y saldo de su cuenta, datos que podra editar si lo desea
	saldo que podra actualiza si asi tb lo desea

5.- Tiene la opcion de ver las transacciones realizadas por usuario


6.- Las pruebas unitarias de los proyectos estan en el directorio test

7.- se instalo cucumber para java, solo creo los features en el directorio de test que se encuentra en el proyecto web mi-cuenta-web.war