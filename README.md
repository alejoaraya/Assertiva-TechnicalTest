# Assertiva-TechnicalTest

### Sección práctica

_Objetivo:_ Desarrollar un webservice REST (en JAVA preferentemente, o sino en otro lenguaje) que resuelva el siguiente problema.

Problema
Un reconocido procesador de textos incorpora varias funciones convenientes para el procesamiento automático de las palabras que lo componen. Por ejemplo: corrección ortográfica, manejo automático de mayúsculas y minúsculas, sugerencias de sinónimos, etc.

Una de sus funciones le permite corregir automáticamente textos en los cuales existe un error gramatical por utilizar sustantivos en singular donde deberían haber estado en plural. Por ejemplo, si el usuario ingresa una oración como “Los auto de la ciudad eran todos rojos”, el programa le sugiere automáticamente utilizar la palabra “autos” en lugar del singular “auto”.

Con la intención de crear una versión competidora de este exitoso programa, debes implementar una función que dada una lista de N palabras - que serán todas sustantivos en singular- las transforme a plural automáticamente.

Las reglas completas de formación de plurales en castellano son realmente complejas ya que, según la Real Academia Española, por ejemplo, existen ¡17 reglas! que dependen incluso de la acentuación y combinaciones de letras que tenga la palabra y, a veces, dan más de un plural válido posible. Para esta versión del programa sin embargo nos conformaremos con utilizar las siguientes 4 reglas que funcionan correctamente en la gran mayoría de los casos:

- Regla 1 - Si el sustantivo termina en vocal (a, e, i, o, u) se agrega s.

  - Por ejemplo, los siguientes cambios: pato ->patos, lancha -> lanchas.

- Regla 2 - Si el sustantivo termina en s o x, queda igual en plural.
  - Por ejemplo: “el paréntesis” o “los paréntesis”, “la crisis” y “las crisis”, “el fénix” y “los fénix”, “el martes” y “los martes”.
- Regla 3 - Si el sustantivo termina en z, se cambia la z por ces.
  - Por ejemplo, los siguientes cambios: pez -> peces, y voz -> voces.
- Regla 4 - Si el sustantivo termina en otra consonante se agrega es.
  - Por ejemplo, los siguientes cambios: pared -> paredes, limón -> limones, rey ->reyes.

> ### Tener en cuenta
>
> Para crear la función, debes asumir que no existen palabras con plurales irregulares (como bloc -> bloques, espray -> espráis, quórum -> quórums, chef -> chef) sino que, por el contrario, todas se forman utilizando únicamente las reglas anteriores.
>
> Las palabras iniciales pueden estar en Mayúsculas, minúscula o camel case y contendrán las 26 letras del alfabeto inglés, sin “tilde” ni “ñ” y las generadas por la función deben estar en minúsculas.

También se deberá calcular cuántas veces se aplica cada regla al procesar el listado de palabras.

Se debe implementar la función:

pluralizador(palabras : ARREGLO[N] de PALABRAS)

que devuelva un arreglo con las palabras pluralizadas y un arreglo denominado cantidadesPorRegla de tamaño fijo 4 que indique cuantas veces fue utilizada cada una de las 4 reglas

Ejemplo

Si se recibe

palabras[0] = CASA

palabras[1] = Perro

palabras[2] = limon

Se debería devolver:

palabras[0] = casas

palabras[1] = perros

palabras[2] = limones

Y además se debería escribir

2 en cantidadesPorRegla[0]

0 en cantidadesPorRegla[1]

0 en cantidadesPorRegla[2]

1 en cantidadesPorRegla[3]

ya que se aplicó la regla uno en 2 casos (casa y perro) y la regla cuatro en un caso (limon).
