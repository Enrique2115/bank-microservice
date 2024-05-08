# Proyecto - Backend

El sistema por desarrollar está planteado en el contexto del negocio bancario.

## Bases por desarrollar

- Desarrollo del proyecto con Java 11
- El proyecto debe utilizar Maven como manejadores de dependencias.
- Los nombres de las clases, métodos y las URLs deberán estar en inglés.
- La base de datos sera simulada.
- Uso de Loombok para reducir código.

## Funcionalidades del sistema

- El sistema debe manejar la información de los clientes de un banco.
- Los clientes del banco son de dos tipos:
    - Personal
    - Empresarial
- El sistema debe manejar la información de los siguientes productos que ofrece el banco:

  #### Pasivos (Cuentas bancarias)

    - Ahorro: libre de comisión por mantenimiento y con un límite máximo de movimientos mensuales.
    - Cuenta corriente: posee comisión de mantenimiento y sin límite de movimientos mensuales.
    - Plazo fijo: libre de comisión por mantenimiento, solo permite un movimiento de retiro o depósito en un día específico del mes.

  #### Activos (Créditos)

    - Personal: solo se permite un solo crédito por persona.
    - Empresarial: se permite más de un crédito por empresa.
    - Tarjeta de Crédito personal o empresarial

- Un cliente personal solo puede tener un máximo de una cuenta de ahorro, una cuenta corriente o cuentas a plazo fijo.
- Un cliente empresarial no puede tener una cuenta de ahorro o de plazo fijo, pero sí múltiples cuentas corrientes.
- Las cuentas bancarias empresariales pueden tener uno o más titulares y cero o más firmantes autorizados.
- Un cliente puede tener un producto de crédito sin la obligación de tener una cuenta bancaria en la institución.
- Un cliente puede hacer depósitos y retiros de sus cuentas bancarias.
- Un cliente puede hacer pagos de sus productos de crédito.
- Un cliente puede cargar consumos a sus tarjetas de crédito en base a su limite de crédito.
- El sistema debe permitir consultar los saldos disponibles en sus productos como:
    - Cuentas bancarias
    - Tarjetas de crédito
- El sistema debe permitir consultar todos los movimientos de un producto bancario que tiene un cliente.

## Recomendaciones y Consideraciones.

- Realicen primero las funcionalidades obligatorias.
- Realicen luego las funcionalidadees opcionales más sencillas.
- No deben tener configuraciones en el código (Principio SOLID).
- Las clases y los métodos deben estar comentados.
- El uso de lambda y streams de Java 11 es obligatorio.
- Deben subir su código a un repositorio remoto, como github.
- <span style="color:red">Cada grupo deberá presentar su propia solución.</span>

  #### Artefactos y entregables

- La entrega del código de este proyecto tiene como fecha fin de entrega el <span style="color:red">viernes 24 de mayo de 2024 a las 06:00 p.m.</span> con el código que esté en ese momento en el repositorio remoto. El mismo que luego será sustentado.
