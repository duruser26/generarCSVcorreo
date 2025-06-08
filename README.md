## Función principal

Programa que genera un CSV a partir de un CSV específico de la empresa Alquiler Vacacional. Es información de huéspedes.
El programa permite crear un formato CSV especifico para contactos y su reconocimiento interno en la empresa.
Este programa trata de ser portable sin que se tenga que instalar nada en otro ordenador.

## Estructura

DAO con interfaz que genera una lista del objeto huésped. Dentro del propio DAO reconvierte la fecha de entrada al formato específico interno yy/mm/dd.
El resto de información relevante para el CSV de Google Contacts.

> Formato ULTRA ESPECÍFICO.

## Dependency Management

Dependencias:
> JavaFX
> JRE para ejecutar el .exe que se genera de la compilación (con otro programa) en otro ordenador sin necesidad de instalar nada.
