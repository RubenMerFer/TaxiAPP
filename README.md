# TaxiAPP
Aplicación Android desarrollada en Kotlin que permite simular la reserva de un viaje en taxi.

## Funcionalidades
- Selección de ciudad origen y destino
- Elección de fecha y hora
- Opción de viaje:
  - Solo ida
  - Ida y vuelta
- Validación de datos:
  - Campos obligatorios
  - DNI con formato correcto
  - Fechas coherentes
- Visualización de resumen del viaje

## Tecnologías utilizadas
- Kotlin
- Android Studio
- Activities e Intents
- Spinner, RadioGroup, EditText
- DatePicker y TimePicker

## Flujo de la aplicación
1. Introducción de datos en `RecogidaActivity`
2. Validación de los datos
3. Envío mediante `Intent`
4. Visualización en `ResumenActivity`

## Autor
RubenMerFer
