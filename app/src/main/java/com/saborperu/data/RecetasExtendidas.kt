package com.saborperu.data

import com.saborperu.R

private fun ing(
    name: String,
    quantity: Double?,
    unit: String,
    aisle: String,
    note: String? = null
) = Ingredient(name, quantity, unit, aisle, note)

private fun si(name: String, detail: String) = StepIngredient(name, detail)

private fun step(
    title: String,
    description: String,
    minutes: Int,
    technique: String,
    fireLabel: String,
    chefTip: String,
    timerSeconds: Int,
    ingredients: List<StepIngredient>
) = CookingStep(title, description, minutes, technique, fireLabel, chefTip, timerSeconds, ingredients)

object RecetasExtendidas {

    val all: List<Recipe> = listOf(
        // ---------------- COSTA: PLATOS DE FONDO ----------------
        Recipe(
            id = "causa-limena",
            name = "Causa Limeña con Pollo",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Lima Criolla",
            description = "Papa amarilla sazonada con ají y limón, rellena de pollo deshilachado y mayonesa, servida fría con huevo y aceituna.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 40,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor suave",
            rating = 4.8,
            reviewCount = 410,
            calories = 380,
            proteinGrams = 24,
            carbsGrams = 42,
            badge = "Favorito Limeño",
            regionTag = "Lima",
            tags = listOf("Frío", "Papa amarilla"),
            ingredients = listOf(
                ing("Papa amarilla", 1.0, "kg", "Verdulería", "Para prensar"),
                ing("Pollo deshilachado", 400.0, "g", "Carnicería", "Cocido"),
                ing("Ají amarillo", 2.0, "unid.", "Verdulería", "Sin venas"),
                ing("Limón", 3.0, "unid.", "Verdulería"),
                ing("Mayonesa", 4.0, "cdas.", "Abarrotes"),
                ing("Huevo sancochado", 2.0, "unid.", "Verdulería"),
                ing("Aceitunas", 8.0, "unid.", "Abarrotes"),
                ing("Sal y pimienta", 1.0, "pizca", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Sancochar y prensar la papa",
                    "Sancocha las papas amarillas hasta que estén tiernas, pélaslas aún calientes y pásalas por un prensapapas. Sazona con ají amarillo licuado, limón, sal y aceite.",
                    20, "Papa amarilla", "Fuego medio",
                    "La papa se prensa caliente: así absorbe el ají y queda sedosa, sin grumos.",
                    0,
                    listOf(si("1 kg papa amarilla", "sancochada"), si("2 ajíes amarillos", "licuados"))
                ),
                step(
                    "Preparar el relleno",
                    "Mezcla el pollo deshilachado con mayonesa, sal y pimienta. Prueba el punto de sazón y reserva en frío.",
                    8, "Relleno", "Sin fuego",
                    "Un toque de limón en el pollo equilibra la grasa de la mayonesa.",
                    0,
                    listOf(si("400 g pollo", "deshilachado"), si("4 cdas mayonesa", "ligera"))
                ),
                step(
                    "Armar y enfriar",
                    "Extiende una capa de papa en un molde, añade el relleno de pollo y cubre con otra capa. Refrigera 20 minutos y decora con huevo y aceitunas.",
                    12, "Montaje", "Sin fuego",
                    "Refrigera antes de cortar para que las porciones queden firmes y prolijas.",
                    1200,
                    listOf(si("Huevo", "en rodajas"), si("Aceitunas", "para decorar"))
                )
            ),
            chefQuote = "La causa se sirve fría, pero se prepara con la papa caliente: ese es el secreto de su textura."
        ),
        Recipe(
            id = "arroz-con-pollo",
            name = "Arroz con Pollo Criollo",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Sabor Criollo",
            description = "Arroz graneado con pollo, arvejas, zanahoria y pimiento, perfumado con ají amarillo y culantro fresco.",
            imageRes = R.drawable.lomo_saltado,
            prepMinutes = 45,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.7,
            reviewCount = 520,
            calories = 520,
            proteinGrams = 32,
            carbsGrams = 58,
            badge = "Clásico Casero",
            regionTag = "Costa",
            tags = listOf("One pot", "Familiar"),
            ingredients = listOf(
                ing("Pollo en presas", 1.0, "kg", "Carnicería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Arvejas", 1.0, "taza", "Verdulería"),
                ing("Zanahoria", 2.0, "unid.", "Verdulería"),
                ing("Pimiento rojo", 1.0, "unid.", "Verdulería"),
                ing("Ají amarillo", 2.0, "unid.", "Verdulería"),
                ing("Culantro", 1.0, "rama", "Hierbas y hojas"),
                ing("Ajo", 3.0, "dientes", "Verdulería"),
                ing("Sillao", 2.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "El aderezo criollo",
                    "Licua ají amarillo, culantro, ajo y cebolla. Sofríe la pasta en aceite caliente hasta que pierda el sabor crudo y perfume toda la cocina.",
                    10, "Aderezo", "Fuego medio",
                    "Deja que el aderezo cambie de color: ese brillo dorado es señal de que está listo.",
                    0,
                    listOf(si("2 ajíes amarillos", "licuados"), si("1 rama culantro", "licuada"))
                ),
                step(
                    "Sellar el pollo y añadir arroz",
                    "Dora las presas de pollo en el aderezo, añade sillao y agua. Incorpora el arroz lavado, arvejas, zanahoria y pimiento; rectifica la sal.",
                    15, "Arroz graneado", "Fuego alto",
                    "No revuelvas en exceso: el arroz graneado se logra dejándolo reposar.",
                    600,
                    listOf(si("1 kg pollo", "en presas"), si("2 tazas arroz", "lavado"))
                ),
                step(
                    "Cocinar a vapor",
                    "Baja el fuego, tapa la olla y cocina hasta que el arroz esté tierno y el líquido se haya absorbido. Deja reposar cinco minutos antes de servir.",
                    20, "Vapor", "Fuego bajo",
                    "Un paño limpio bajo la tapa absorbe el vapor y evita que el arroz se humedezca.",
                    1200,
                    listOf(si("Verduras", "en cubos"), si("2 tazas agua", "caliente"))
                )
            ),
            chefQuote = "El arroz con pollo sabe a domingo: aderezo generoso y fuego paciente."
        ),
        Recipe(
            id = "aji-de-gallina",
            name = "Ají de Gallina Cremoso",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Criollo Cremoso",
            description = "Pollo deshilachado en crema de ají amarillo y pan remojado, servido con papas sancochadas, arroz y huevo.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 50,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.8,
            reviewCount = 610,
            calories = 460,
            proteinGrams = 35,
            carbsGrams = 30,
            badge = "Crema de Oro",
            regionTag = "Lima",
            tags = listOf("Cremoso", "Ají amarillo"),
            ingredients = listOf(
                ing("Pechuga de pollo", 800.0, "g", "Carnicería"),
                ing("Ají amarillo", 4.0, "unid.", "Verdulería", "Sin venas"),
                ing("Ají mirasol", 1.0, "unid.", "Verdulería"),
                ing("Pan de molde", 4.0, "rebanadas", "Panadería"),
                ing("Leche evaporada", 1.0, "taza", "Lácteos"),
                ing("Queso parmesano", 50.0, "g", "Lácteos"),
                ing("Papa amarilla", 4.0, "unid.", "Verdulería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Cebolla", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Sancochar y deshilachar",
                    "Sancocha la pechuga en agua con sal y deshiláchala finamente. Reserva el caldo para ajustar la crema más adelante.",
                    15, "Pollo", "Fuego medio",
                    "Deshilacha el pollo tibio: se separa mejor y queda más suave.",
                    0,
                    listOf(si("800 g pechuga", "deshilachada"), si("Caldo", "reservado"))
                ),
                step(
                    "La crema de ají",
                    "Sofríe cebolla y ají amarillo licuado. Remoja el pan en leche, licúalo y añádelo a la olla con el ají mirasol y el caldo. Cocina hasta espesar.",
                    20, "Crema", "Fuego medio",
                    "El pan remojado es el espesante natural: no necesitas harina.",
                    900,
                    listOf(si("4 ajíes amarillos", "licuados"), si("4 panes", "remojados en leche"))
                ),
                step(
                    "Ligar y servir",
                    "Incorpora el pollo, la leche evaporada y el queso. Sirve sobre papas sancochadas con arroz blanco y huevo duro.",
                    10, "Emplatado", "Fuego bajo",
                    "Agrega el queso al final, fuera del fuego, para que no se corte la crema.",
                    0,
                    listOf(si("Papa amarilla", "sancochada"), si("Arroz", "graneado"))
                )
            ),
            chefQuote = "El ají de gallina se reconoce por su color dorado y su textura de terciopelo."
        ),
        Recipe(
            id = "carapulcra-chincha",
            name = "Carapulcra de Chincha",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Chincha Afroperuana",
            description = "Guiso de papa seca remojada con cerdo, maní tostado y ají panca; profundo, especiado y reconfortante.",
            imageRes = R.drawable.pachamanca,
            prepMinutes = 60,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 280,
            calories = 520,
            proteinGrams = 30,
            carbsGrams = 45,
            badge = "Sabor Afroperuano",
            regionTag = "Chincha",
            tags = listOf("Papa seca", "Guiso"),
            ingredients = listOf(
                ing("Papa seca", 500.0, "g", "Abarrotes", "Remojada"),
                ing("Cerdo en trozos", 600.0, "g", "Carnicería"),
                ing("Maní tostado", 100.0, "g", "Abarrotes"),
                ing("Ají panca", 3.0, "cdas.", "Abarrotes"),
                ing("Cebolla", 2.0, "unid.", "Verdulería"),
                ing("Ajo", 4.0, "dientes", "Verdulería"),
                ing("Clavo de olor", 2.0, "unid.", "Abarrotes"),
                ing("Canela", 1.0, "rama", "Abarrotes"),
                ing("Sillao", 2.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Remojar la papa seca",
                    "Remoja la papa seca en agua tibia hasta que se ablande. Escúrrela y reserva el agua del remojo para el guiso.",
                    15, "Papa seca", "Sin fuego",
                    "Cambia el agua dos veces para quitar el exceso de almidón.",
                    900,
                    listOf(si("500 g papa seca", "remojada"), si("Agua", "reservada"))
                ),
                step(
                    "Dorar el cerdo",
                    "Sazona el cerdo con sal, pimienta y comino. Dóralo en aceite caliente, añade cebolla, ajo y ají panca; cocina hasta que el aderezo esté oscuro y aromático.",
                    15, "Sellado", "Fuego alto",
                    "El cerdo debe dorarse antes de añadir líquido: ahí nace el sabor profundo del guiso.",
                    0,
                    listOf(si("600 g cerdo", "en trozos"), si("3 cdas ají panca", "disuelto"))
                ),
                step(
                    "Guisar con maní",
                    "Incorpora la papa seca, el maní molido, clavo, canela y el agua de remojo. Cocina a fuego bajo hasta que la papa esté suave y el guiso espeso.",
                    30, "Guiso", "Fuego bajo",
                    "Mueve con cuidado: la papa seca se pega al fondo cuando el guiso espesa.",
                    1800,
                    listOf(si("100 g maní", "molido"), si("Especias", "clavo y canela"))
                )
            ),
            chefQuote = "La carapulcra es memoria afroperuana: papa seca, maní y tiempo."
        ),
        Recipe(
            id = "anticuchos",
            name = "Anticuchos de Corazón",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Noche Limeña",
            description = "Brochetas de corazón de res marinadas en ají panca y vinagre, asadas a la parrilla y servidas con papa dorada.",
            imageRes = R.drawable.lomo_wok,
            prepMinutes = 35,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.9,
            reviewCount = 730,
            calories = 410,
            proteinGrams = 34,
            carbsGrams = 22,
            badge = "Street Food",
            regionTag = "Lima",
            tags = listOf("Parrilla", "Ají panca"),
            ingredients = listOf(
                ing("Corazón de res", 800.0, "g", "Carnicería", "Limpio"),
                ing("Ají panca", 4.0, "cdas.", "Abarrotes"),
                ing("Ajo", 5.0, "dientes", "Verdulería"),
                ing("Vinagre tinto", 3.0, "cdas.", "Abarrotes"),
                ing("Comino", 1.0, "cdta.", "Abarrotes"),
                ing("Papa dorada", 4.0, "unid.", "Verdulería"),
                ing("Choclo", 2.0, "unid.", "Verdulería"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Marinar el corazón",
                    "Corta el corazón en trozos parejos y marina con ají panca, ajo molido, vinagre, comino y sal. Deja reposar al menos quince minutos.",
                    15, "Marinado", "Sin fuego",
                    "No marine por horas: el vinagre endurece la carne si se pasa de tiempo.",
                    900,
                    listOf(si("800 g corazón", "en trozos"), si("4 cdas ají panca", "para marinar"))
                ),
                step(
                    "Ensartar y preparar guarnición",
                    "Ensarta tres o cuatro trozos por palito sin apretarlos. Sancocha y dora las papas, y sancocha el choclo.",
                    5, "Brochetas", "Fuego medio",
                    "Deja espacio entre los trozos para que el calor llegue por todos lados.",
                    0,
                    listOf(si("Palitos", "de brocheta"), si("Papas", "doradas"))
                ),
                step(
                    "Asar a fuego vivo",
                    "Asa los anticuchos girándolos constantemente y pincelando con la marinada. Retira cuando estén dorados por fuera y jugosos por dentro.",
                    12, "Parrilla", "Fuego alto",
                    "El anticucho se sirve inmediatamente: pierde jugosidad si espera.",
                    600,
                    listOf(si("Marinada", "para pincelar"), si("Choclo", "sancochado"))
                )
            ),
            chefQuote = "Un anticucho bien asado se canta en la calle antes de que lo pruebes."
        ),
        Recipe(
            id = "tiradito",
            name = "Tiradito Nikkei",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Fusión Nikkei",
            description = "Láminas finas de pescado bañadas en crema de ají amarillo y limón, con toque de jengibre y culantro.",
            imageRes = R.drawable.ceviche,
            prepMinutes = 20,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor suave",
            rating = 4.8,
            reviewCount = 390,
            calories = 290,
            proteinGrams = 32,
            carbsGrams = 10,
            badge = "Nikkei",
            regionTag = "Lima",
            tags = listOf("Sin cocción", "Fusión"),
            ingredients = listOf(
                ing("Filete de lenguado", 600.0, "g", "Pescadería", "Bien frío"),
                ing("Ají amarillo", 3.0, "unid.", "Verdulería"),
                ing("Limón", 6.0, "unid.", "Verdulería"),
                ing("Jengibre", 1.0, "trozo", "Verdulería"),
                ing("Culantro", 1.0, "rama", "Hierbas y hojas"),
                ing("Sal", 1.0, "cdta.", "Abarrotes"),
                ing("Camote", 1.0, "unid.", "Verdulería"),
                ing("Choclo", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Cortar el pescado",
                    "Corta el lenguado en láminas muy finas con cuchillo bien afilado y colócalas en un plato plano ligeramente frío.",
                    8, "Corte", "Sin fuego",
                    "Un corte fino y parejo permite que la crema cubra cada lámina por igual.",
                    0,
                    listOf(si("600 g lenguado", "en láminas"), si("Plato frío", "para servir"))
                ),
                step(
                    "La crema nikkei",
                    "Licua ají amarillo, jugo de limón, jengibre, culantro y sal. Prueba y ajusta el ácido sin que opaque el ají.",
                    7, "Crema", "Sin fuego",
                    "Añade el jengibre poco a poco: debe sentirse, no dominar.",
                    0,
                    listOf(si("3 ajíes amarillos", "licuados"), si("6 limones", "jugo colado"))
                ),
                step(
                    "Bañar y servir",
                    "Baña las láminas con la crema y sirve de inmediato con camote sancochado y choclo.",
                    2, "Emplatado", "Sin fuego",
                    "El tiradito se sirve al instante: el pescado debe estar frío y la crema recién hecha.",
                    0,
                    listOf(si("Camote", "en rodajas"), si("Choclo", "desgranado"))
                )
            ),
            chefQuote = "El tiradito es un ceviche sin cebolla: todo el protagonismo es del ají y el limón."
        ),
        Recipe(
            id = "escabeche-pescado",
            name = "Escabeche de Pescado",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Tradición Marina",
            description = "Pescado frito macerado en escabeche de cebolla, ají amarillo y vinagre, servido con camote y arroz.",
            imageRes = R.drawable.patarashca,
            prepMinutes = 35,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor suave",
            rating = 4.6,
            reviewCount = 240,
            calories = 400,
            proteinGrams = 30,
            carbsGrams = 34,
            badge = "Mar y Vinagre",
            regionTag = "Costa",
            tags = listOf("Pescado", "Vinagre"),
            ingredients = listOf(
                ing("Filete de pescado", 800.0, "g", "Pescadería"),
                ing("Cebolla roja", 3.0, "unid.", "Verdulería"),
                ing("Ají amarillo", 3.0, "unid.", "Verdulería"),
                ing("Vinagre tinto", 4.0, "cdas.", "Abarrotes"),
                ing("Harina", 1.0, "taza", "Abarrotes"),
                ing("Huevo", 2.0, "unid.", "Verdulería"),
                ing("Camote", 2.0, "unid.", "Verdulería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Aceite", 1.0, "taza", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Freír el pescado",
                    "Sazona los filetes, pásalos por harina y huevo batido, y fríelos hasta dorar. Escurre sobre papel absorbente.",
                    10, "Fritura", "Fuego alto",
                    "Seca bien el pescado antes de enharinar para que la capa quede crocante.",
                    0,
                    listOf(si("800 g pescado", "en filetes"), si("Harina y huevo", "para rebosar"))
                ),
                step(
                    "El escabeche",
                    "Sofríe cebolla en pluma y ají amarillo. Añade vinagre, sal, orégano y un poco de agua; cocina hasta que la cebolla esté tierna pero firme.",
                    12, "Escabeche", "Fuego medio",
                    "El vinagre se añade al final para conservar su aroma.",
                    600,
                    listOf(si("3 cebollas", "en pluma"), si("4 cdas vinagre", "tinto"))
                ),
                step(
                    "Reposar y servir",
                    "Baña el pescado con el escabeche caliente y deja reposar diez minutos. Sirve con camote sancochado y arroz blanco.",
                    10, "Reposo", "Sin fuego",
                    "El reposo permite que el pescado absorba el escabeche sin perder su crocante.",
                    600,
                    listOf(si("Camote", "sancochado"), si("Arroz", "graneado"))
                )
            ),
            chefQuote = "El escabeche es el abrazo entre el mar y el vinagre."
        ),
        Recipe(
            id = "seco-norteno",
            name = "Seco a la Norteña",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Norte Chico",
            description = "Guiso norteño de res con culantro fresco, cerveza negra y ají amarillo, acompañado de arroz y frijoles.",
            imageRes = R.drawable.lomo_saltado,
            prepMinutes = 55,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 330,
            calories = 560,
            proteinGrams = 38,
            carbsGrams = 48,
            badge = "Sabor Norteño",
            regionTag = "Piura",
            tags = listOf("Culantro", "Guiso"),
            ingredients = listOf(
                ing("Carne de res", 800.0, "g", "Carnicería"),
                ing("Culantro", 1.0, "atado", "Hierbas y hojas"),
                ing("Cerveza negra", 1.0, "botella", "Abarrotes"),
                ing("Ají amarillo", 3.0, "unid.", "Verdulería"),
                ing("Cebolla", 2.0, "unid.", "Verdulería"),
                ing("Ajo", 4.0, "dientes", "Verdulería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Frijoles", 1.0, "taza", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Licuar el culantro",
                    "Licua el culantro con un poco de cerveza y ají amarillo hasta obtener una salsa verde intensa.",
                    10, "Salsa verde", "Sin fuego",
                    "Escalda el culantro un segundo si quieres un verde más brillante.",
                    0,
                    listOf(si("1 atado culantro", "licuado"), si("Cerveza", "para ligar"))
                ),
                step(
                    "Dorar la carne",
                    "Sazona la carne y dórala en aceite. Añade cebolla y ajo, y cocina hasta que el aderezo esté transparente.",
                    15, "Sellado", "Fuego alto",
                    "Dorar bien la carne antes del líquido es lo que da profundidad al seco.",
                    0,
                    listOf(si("800 g res", "en trozos"), si("2 cebollas", "picadas"))
                ),
                step(
                    "Guisar con cerveza",
                    "Incorpora la salsa de culantro y la cerveza negra. Cocina a fuego bajo hasta que la carne esté tierna y la salsa espesa. Sirve con arroz y frijoles.",
                    30, "Guiso", "Fuego bajo",
                    "La cerveza negra aporta amargor y cuerpo: no la reemplaces por agua.",
                    1800,
                    listOf(si("Cerveza negra", "1 botella"), si("Frijoles", "sancochados"))
                )
            ),
            chefQuote = "El seco norteño se cocina con calma y se sirve con orgullo."
        ),

        // ---------------- COSTA: POSTRES ----------------
        Recipe(
            id = "arroz-con-leche",
            name = "Arroz con Leche Cremoso",
            region = Region.COSTA,
            category = RecipeCategory.POSTRE,
            originLine = "Costa • Dulcería Limeña",
            description = "Arroz cocido lentamente en leche, canela y clavo, coronado con pasas y un toque de vainilla.",
            imageRes = R.drawable.suspiro,
            prepMinutes = 35,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.7,
            reviewCount = 450,
            calories = 280,
            proteinGrams = 6,
            carbsGrams = 48,
            badge = "Dulce Tradicional",
            regionTag = "Lima",
            tags = listOf("Postre", "Canela"),
            ingredients = listOf(
                ing("Arroz", 1.0, "taza", "Abarrotes"),
                ing("Leche entera", 1.0, "l", "Lácteos"),
                ing("Leche condensada", 1.0, "tarro", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Pasas", 50.0, "g", "Abarrotes"),
                ing("Vainilla", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocer el arroz",
                    "Sancocha el arroz en agua con una rama de canela hasta que esté muy tierno y casi seco.",
                    15, "Arroz", "Fuego medio",
                    "Cocina el arroz hasta pasarlo un poco: así suelta el almidón que espesa la leche.",
                    0,
                    listOf(si("1 taza arroz", "bien cocido"), si("1 rama canela", "en el agua"))
                ),
                step(
                    "Añadir la leche",
                    "Incorpora la leche entera, la leche condensada, clavo y vainilla. Cocina a fuego bajo moviendo constantemente.",
                    15, "Cremoso", "Fuego bajo",
                    "Mueve con cuchara de madera y raspa el fondo para evitar que se pegue.",
                    900,
                    listOf(si("1 l leche", "entera"), si("1 tarro", "leche condensada"))
                ),
                step(
                    "Espesar y enfriar",
                    "Cocina hasta que la mezcla cubra la cuchara. Retira las especias, añade pasas y sirve tibio o frío con canela molida.",
                    5, "Punto", "Fuego bajo",
                    "El arroz con leche espesa al enfriar: retíralo del fuego un poco líquido.",
                    300,
                    listOf(si("Pasas", "remojadas"), si("Canela", "molida para servir"))
                )
            ),
            chefQuote = "Un buen arroz con leche se mueve despacio y se endulza con paciencia."
        ),
        Recipe(
            id = "mazamorra-morada",
            name = "Mazamorra Morada",
            region = Region.COSTA,
            category = RecipeCategory.POSTRE,
            originLine = "Costa • Dulce Limeño",
            description = "Crema morada de maíz morado, frutas secas y canela; el postre limeño por excelencia, ideal bien frío.",
            imageRes = R.drawable.chicha_morada,
            prepMinutes = 40,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 380,
            calories = 240,
            proteinGrams = 3,
            carbsGrams = 50,
            badge = "Tradición Limeña",
            regionTag = "Lima",
            tags = listOf("Maíz morado", "Frío"),
            ingredients = listOf(
                ing("Maíz morado", 1.0, "kg", "Verdulería"),
                ing("Cáscara de piña", 1.0, "unid.", "Verdulería"),
                ing("Membrillo", 1.0, "unid.", "Verdulería"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Maicena", 4.0, "cdas.", "Abarrotes"),
                ing("Pasas", 50.0, "g", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Hervir el maíz morado",
                    "Hierve el maíz morado con la cáscara de piña, canela y clavo hasta que el agua tome un color intenso.",
                    20, "Infusión", "Fuego medio",
                    "El color se intensifica al reposar: deja enfriar la infusión antes de colar.",
                    1200,
                    listOf(si("1 kg maíz morado", "con coronta"), si("Cáscara de piña", "para el color"))
                ),
                step(
                    "Colar y endulzar",
                    "Cuela el líquido morado, añade azúcar y membrillo picado. Cocina hasta que la fruta esté suave.",
                    10, "Frutas", "Fuego medio",
                    "Prueba el dulzor antes de la maicena: después ya no se corrige bien.",
                    0,
                    listOf(si("Membrillo", "en cubos"), si("1 taza azúcar", "al gusto"))
                ),
                step(
                    "Espesar con maicena",
                    "Disuelve la maicena en agua fría y añádela en hilo, moviendo constantemente hasta espesar. Añade pasas y sirve frío.",
                    8, "Espesante", "Fuego bajo",
                    "La maicena debe cocinar un par de minutos para perder el sabor crudo.",
                    480,
                    listOf(si("4 cdas maicena", "disueltas"), si("Pasas", "al gusto"))
                )
            ),
            chefQuote = "La mazamorra morada se sirve fría, con canela y una sonrisa limeña."
        ),
        Recipe(
            id = "turron-dona-pepa",
            name = "Turrón de Doña Pepa",
            region = Region.COSTA,
            category = RecipeCategory.POSTRE,
            originLine = "Costa • Dulcería Barranquina",
            description = "Capas crocantes de harina bañadas en miel de chancaca con anís, frutas confitadas y grageas de colores.",
            imageRes = R.drawable.picarones,
            prepMinutes = 50,
            portions = 8,
            difficulty = "Avanzado",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 310,
            calories = 340,
            proteinGrams = 4,
            carbsGrams = 52,
            badge = "Mes Morado",
            regionTag = "Lima",
            tags = listOf("Chancaca", "Fiesta"),
            ingredients = listOf(
                ing("Harina", 500.0, "g", "Abarrotes"),
                ing("Mantequilla", 200.0, "g", "Lácteos"),
                ing("Huevos", 3.0, "unid.", "Verdulería"),
                ing("Anís", 1.0, "cdta.", "Abarrotes"),
                ing("Chancaca", 500.0, "g", "Abarrotes"),
                ing("Naranja", 1.0, "unid.", "Verdulería"),
                ing("Frutas confitadas", 100.0, "g", "Abarrotes"),
                ing("Grageas", 50.0, "g", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Preparar la masa",
                    "Mezcla harina, mantequilla, huevos, anís y jugo de naranja hasta obtener una masa suave. Forma tiras largas y colócalas en una bandeja.",
                    20, "Masa", "Sin fuego",
                    "La masa debe quedar firme pero maleable: si se pega, añade harina cucharada a cucharada.",
                    0,
                    listOf(si("500 g harina", "cernida"), si("200 g mantequilla", "fría"))
                ),
                step(
                    "Hornear las capas",
                    "Hornea las tiras a 180 °C hasta que estén doradas. Apílalas en capas sobre un plato profundo.",
                    20, "Horno", "Horno 180 °C",
                    "Deja enfriar las tiras antes de apilarlas para que no se humedezcan.",
                    1200,
                    listOf(si("Tiras de masa", "horneadas"), si("Plato profundo", "para armar"))
                ),
                step(
                    "Bañar con miel",
                    "Prepara la miel con chancaca, anís y naranja. Baña el turrón capa por capa y decora con frutas confitadas y grageas.",
                    10, "Miel", "Fuego medio",
                    "La miel debe estar tibia al bañar: caliente quema las capas, fría no penetra.",
                    0,
                    listOf(si("500 g chancaca", "en miel"), si("Grageas", "para decorar"))
                )
            ),
            chefQuote = "El turrón de Doña Pepa se comparte: cada capa guarda una promesa."
        ),

        // ---------------- COSTA: BEBIDAS ----------------
        Recipe(
            id = "maracuya-sour",
            name = "Maracuyá Sour",
            region = Region.COSTA,
            category = RecipeCategory.BEBIDA,
            originLine = "Costa • Coctelería",
            description = "Variante tropical del pisco sour con jugo de maracuyá, clara de huevo y unas gotas de amargo de angostura.",
            imageRes = R.drawable.pisco_sour,
            prepMinutes = 10,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 260,
            calories = 210,
            proteinGrams = 2,
            carbsGrams = 18,
            badge = "Coctel",
            regionTag = "Ica",
            tags = listOf("Pisco", "Maracuyá"),
            ingredients = listOf(
                ing("Pisco quebranta", 8.0, "oz", "Abarrotes"),
                ing("Maracuyá", 4.0, "unid.", "Verdulería"),
                ing("Jarabe de goma", 4.0, "oz", "Abarrotes"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Clara de huevo", 1.0, "unid.", "Verdulería"),
                ing("Hielo", 4.0, "tazas", "Abarrotes"),
                ing("Amargo de angostura", 4.0, "gotas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Sacar la pulpa",
                    "Corta los maracuyás y extrae la pulpa. Cuela para retirar las semillas y reserva el jugo.",
                    4, "Pulpa", "Sin fuego",
                    "No licues las semillas: aportan amargor y opacan el color del coctel.",
                    0,
                    listOf(si("4 maracuyás", "colados"), si("Jugo", "reservado"))
                ),
                step(
                    "Batir el sour",
                    "En una coctelera, mezcla pisco, jugo de maracuyá, limón, jarabe y clara de huevo. Agita enérgicamente con hielo.",
                    4, "Coctelería", "Sin fuego",
                    "La clara se bate primero sin hielo para lograr una espuma densa.",
                    0,
                    listOf(si("8 oz pisco", "quebranta"), si("1 clara", "de huevo"))
                ),
                step(
                    "Servir",
                    "Cuela el coctel en copas frías y añade una gota de angostura sobre la espuma.",
                    2, "Emplatado", "Sin fuego",
                    "Sirve de inmediato: la espuma empieza a bajar en pocos minutos.",
                    0,
                    listOf(si("Copas", "bien frías"), si("Angostura", "para decorar"))
                )
            ),
            chefQuote = "El maracuyá sour es el verano peruano servido en copa."
        ),
        Recipe(
            id = "limonada-frozen",
            name = "Limonada Frozen",
            region = Region.COSTA,
            category = RecipeCategory.BEBIDA,
            originLine = "Costa • Refresco Veraniego",
            description = "Bebida helada de limón, hierbabuena y hielo licuado, perfecta para los días de sol costeño.",
            imageRes = R.drawable.camu_camu,
            prepMinutes = 8,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.6,
            reviewCount = 190,
            calories = 120,
            proteinGrams = 1,
            carbsGrams = 28,
            badge = "Refrescante",
            regionTag = "Costa",
            tags = listOf("Helado", "Limón"),
            ingredients = listOf(
                ing("Limón", 6.0, "unid.", "Verdulería"),
                ing("Azúcar", 4.0, "cdas.", "Abarrotes"),
                ing("Hierbabuena", 6.0, "hojas", "Hierbas y hojas"),
                ing("Hielo", 4.0, "tazas", "Abarrotes"),
                ing("Agua", 2.0, "tazas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Exprimir y endulzar",
                    "Exprime los limones, cuela el jugo y disuelve el azúcar en el agua. Añade las hojas de hierbabuena.",
                    3, "Jugo", "Sin fuego",
                    "Frota la hierbabuena entre las manos antes de añadirla para liberar su aroma.",
                    0,
                    listOf(si("6 limones", "jugo colado"), si("4 cdas azúcar", "disueltas"))
                ),
                step(
                    "Licuar con hielo",
                    "Licua el jugo con el hielo hasta obtener una textura espesa y helada.",
                    3, "Licuado", "Sin fuego",
                    "Añade el hielo en dos tandas para que la licuadora no se esfuerce.",
                    0,
                    listOf(si("4 tazas hielo", "en cubos"), si("Hierbabuena", "al gusto"))
                ),
                step(
                    "Servir",
                    "Sirve inmediatamente en vasos fríos y decora con una hoja de hierbabuena.",
                    2, "Emplatado", "Sin fuego",
                    "Si esperas, la limonada se separa: remueve antes de servir.",
                    0,
                    listOf(si("Vasos", "helados"), si("Hierbabuena", "para decorar"))
                )
            ),
            chefQuote = "La limonada frozen es el sonido del verano: hielo, limón y risas."
        ),

        // ---------------- SIERRA: PLATOS DE FONDO ----------------
        Recipe(
            id = "cuy-chactado",
            name = "Cuy Chactado",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Cuy marinado y frito a alta temperatura hasta quedar dorado y crocante, servido con papas nativas y sarza.",
            imageRes = R.drawable.pachamanca,
            prepMinutes = 70,
            portions = 4,
            difficulty = "Avanzado",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.8,
            reviewCount = 350,
            calories = 480,
            proteinGrams = 42,
            carbsGrams = 18,
            badge = "Tradición Andina",
            regionTag = "Arequipa",
            tags = listOf("Frito", "Andino"),
            ingredients = listOf(
                ing("Cuy", 2.0, "unid.", "Carnicería", "Limpio"),
                ing("Ajo", 5.0, "dientes", "Verdulería"),
                ing("Comino", 1.0, "cdta.", "Abarrotes"),
                ing("Ají panca", 2.0, "cdas.", "Abarrotes"),
                ing("Vinagre", 2.0, "cdas.", "Abarrotes"),
                ing("Papa nativa", 1.0, "kg", "Verdulería"),
                ing("Aceite", 2.0, "tazas", "Abarrotes"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Marinar el cuy",
                    "Sazona el cuy con ajo, comino, ají panca, vinagre y sal. Deja reposar para que tome sabor.",
                    20, "Marinado", "Sin fuego",
                    "Pincha la carne con un tenedor para que la marinada penetre mejor.",
                    1200,
                    listOf(si("2 cuyes", "limpios"), si("Aderezo", "ají y especias"))
                ),
                step(
                    "Chactar en aceite",
                    "Calienta abundante aceite y fríe el cuy prensado con una piedra o sartén encima hasta que esté dorado y crocante.",
                    25, "Fritura", "Fuego alto",
                    "El cuy se prensa mientras se fríe: así la carne queda jugosa y la piel crocante.",
                    1500,
                    listOf(si("Aceite", "abundante"), si("Peso", "para prensar"))
                ),
                step(
                    "Servir con papas",
                    "Sancocha las papas nativas y prepara una sarza de cebolla. Sirve el cuy caliente sobre las papas.",
                    5, "Emplatado", "Fuego medio",
                    "Sirve el cuy inmediatamente: el crocante de la piel es su mejor carta.",
                    0,
                    listOf(si("Papa nativa", "sancochada"), si("Sarza", "de cebolla"))
                )
            ),
            chefQuote = "El cuy chactado se sirve con orgullo: es el plato de las grandes ocasiones andinas."
        ),
        Recipe(
            id = "olluquito-charqui",
            name = "Olluquito con Charqui",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Cocina Andina",
            description = "Ollucos en juliana guisados con charqui de res, ají amarillo y cebolla; sabor profundo de los Andes.",
            imageRes = R.drawable.pachamanca,
            prepMinutes = 45,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 290,
            calories = 380,
            proteinGrams = 28,
            carbsGrams = 32,
            badge = "Sabor Serrano",
            regionTag = "Sierra",
            tags = listOf("Olluco", "Charqui"),
            ingredients = listOf(
                ing("Olluco", 1.0, "kg", "Verdulería"),
                ing("Charqui de res", 200.0, "g", "Carnicería"),
                ing("Cebolla", 2.0, "unid.", "Verdulería"),
                ing("Ají amarillo", 3.0, "unid.", "Verdulería"),
                ing("Ajo", 3.0, "dientes", "Verdulería"),
                ing("Papa", 4.0, "unid.", "Verdulería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Aceite", 3.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Remojar el charqui",
                    "Remoja el charqui en agua tibia para desalarlo, escúrrelo y córtalo en trozos pequeños.",
                    15, "Charqui", "Sin fuego",
                    "Cambia el agua una vez para controlar el punto de sal.",
                    900,
                    listOf(si("200 g charqui", "remojado"), si("Agua", "tibia"))
                ),
                step(
                    "Sofreír la base",
                    "Sofríe cebolla, ajo y ají amarillo. Añade el charqui y cocina hasta que el aderezo tome un color profundo.",
                    10, "Aderezo", "Fuego medio",
                    "El charqui necesita el aderezo caliente para soltar su sabor ahumado.",
                    0,
                    listOf(si("3 ajíes amarillos", "licuados"), si("Charqui", "en trozos"))
                ),
                step(
                    "Guisar el olluquito",
                    "Incorpora el olluco en juliana y un poco de agua. Cocina hasta que esté tierno pero firme. Sirve con papas y arroz.",
                    15, "Guiso", "Fuego medio",
                    "El olluco suelta agua: no añadas demasiado líquido de inicio.",
                    900,
                    listOf(si("1 kg olluco", "en juliana"), si("Papa", "sancochada"))
                )
            ),
            chefQuote = "El olluquito con charqui es la montaña servida en un plato."
        ),
        Recipe(
            id = "chupe-camarones",
            name = "Chupe de Camarones",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Sopa espesa de camarones de río con papa, choclo, huevo, leche y ají panca; plato bandera arequipeño.",
            imageRes = R.drawable.inchicapi,
            prepMinutes = 55,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.9,
            reviewCount = 400,
            calories = 420,
            proteinGrams = 30,
            carbsGrams = 38,
            badge = "Plato Bandera",
            regionTag = "Arequipa",
            tags = listOf("Sopa", "Camarón"),
            ingredients = listOf(
                ing("Camarones", 500.0, "g", "Pescadería"),
                ing("Papa", 4.0, "unid.", "Verdulería"),
                ing("Choclo", 2.0, "unid.", "Verdulería"),
                ing("Huevo", 2.0, "unid.", "Verdulería"),
                ing("Leche evaporada", 1.0, "taza", "Lácteos"),
                ing("Ají panca", 2.0, "cdas.", "Abarrotes"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Ajo", 3.0, "dientes", "Verdulería"),
                ing("Orégano", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Preparar el caldo",
                    "Sofríe cebolla, ajo y ají panca. Añade agua, las cáscaras de camarón y hierbas; hierve para extraer todo el sabor.",
                    15, "Caldo", "Fuego medio",
                    "Las cáscaras son oro: hierven y aportan el sabor marino al chupe.",
                    900,
                    listOf(si("Cáscaras de camarón", "para el caldo"), si("2 cdas ají panca", "disuelto"))
                ),
                step(
                    "Cocinar las papas",
                    "Cuela el caldo y añade papa en trozos y choclo. Cocina hasta que la papa esté casi tierna.",
                    15, "Papas", "Fuego medio",
                    "La papa debe soltar almidón para espesar naturalmente la sopa.",
                    900,
                    listOf(si("4 papas", "en trozos"), si("2 choclos", "en rodajas"))
                ),
                step(
                    "Añadir camarones y ligar",
                    "Incorpora los camarones, la leche y el huevo batido. Cocina suavemente hasta que los camarones estén rosados; sirve con orégano.",
                    15, "Ligado", "Fuego bajo",
                    "No dejes hervir después de añadir la leche para que no se corte.",
                    600,
                    listOf(si("500 g camarones", "limpios"), si("1 taza leche", "evaporada"))
                )
            ),
            chefQuote = "El chupe arequipeño se come con cuchara y con devoción."
        ),
        Recipe(
            id = "adobo-arequipeno",
            name = "Adobo Arequipeño",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Cerdo marinado en ají panca, chicha de jora y especias, cocido lentamente hasta deshacerse; se sirve con pan.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 60,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 260,
            calories = 500,
            proteinGrams = 34,
            carbsGrams = 28,
            badge = "Desayuno Serrano",
            regionTag = "Arequipa",
            tags = listOf("Cerdo", "Chicha"),
            ingredients = listOf(
                ing("Carne de cerdo", 1.0, "kg", "Carnicería"),
                ing("Ají panca", 4.0, "cdas.", "Abarrotes"),
                ing("Chicha de jora", 2.0, "tazas", "Abarrotes"),
                ing("Ajo", 4.0, "dientes", "Verdulería"),
                ing("Comino", 1.0, "cdta.", "Abarrotes"),
                ing("Orégano", 1.0, "cdta.", "Abarrotes"),
                ing("Pan", 4.0, "unid.", "Panadería"),
                ing("Cebolla", 2.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Marinar el cerdo",
                    "Mezcla ají panca, chicha de jora, ajo, comino y orégano. Marina el cerdo al menos veinte minutos.",
                    20, "Marinado", "Sin fuego",
                    "La chicha de jora ablanda la carne y aporta un dulzor andino.",
                    1200,
                    listOf(si("1 kg cerdo", "en trozos"), si("2 tazas chicha", "de jora"))
                ),
                step(
                    "Sellar la carne",
                    "Escurre el cerdo y dóralo en una olla caliente. Añade cebolla y cocina hasta que empiece a transparentar.",
                    10, "Sellado", "Fuego alto",
                    "Reserva la marinada: se añade después y concentra el sabor.",
                    0,
                    listOf(si("Cerdo", "dorado"), si("Cebolla", "en pluma"))
                ),
                step(
                    "Cocinar lentamente",
                    "Añade la marinada y un poco de agua. Cocina a fuego bajo hasta que la carne se deshaga. Sirve con pan para mojar.",
                    30, "Cocción lenta", "Fuego bajo",
                    "El adobo está listo cuando la carne se separa con la cuchara.",
                    1800,
                    listOf(si("Marinada", "reservada"), si("Pan", "para acompañar"))
                )
            ),
            chefQuote = "El adobo arequipeño desayuna temprano y se cocina sin prisa."
        ),
        Recipe(
            id = "soltero-queso",
            name = "Soltero de Queso",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Ensalada andina de habas, queso fresco, choclo, papas y rocoto, aderezada con limón y aceite.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 30,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.6,
            reviewCount = 210,
            calories = 320,
            proteinGrams = 18,
            carbsGrams = 34,
            badge = "Fresco Andino",
            regionTag = "Arequipa",
            tags = listOf("Ensalada", "Queso"),
            ingredients = listOf(
                ing("Habas", 300.0, "g", "Verdulería"),
                ing("Queso fresco", 300.0, "g", "Lácteos"),
                ing("Papa", 3.0, "unid.", "Verdulería"),
                ing("Choclo", 1.0, "unid.", "Verdulería"),
                ing("Rocoto", 1.0, "unid.", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Limón", 3.0, "unid.", "Verdulería"),
                ing("Aceite", 3.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Sancochar los vegetales",
                    "Sancocha habas, papas y choclo. Deja enfriar y corta en cubos parejos.",
                    15, "Sancochado", "Fuego medio",
                    "Enfría los vegetales antes de mezclar para que no se deshagan.",
                    900,
                    listOf(si("300 g habas", "peladas"), si("3 papas", "sancochadas"))
                ),
                step(
                    "Picar y mezclar",
                    "Corta el queso fresco, la cebolla y el rocoto finamente. Mezcla con los vegetales.",
                    7, "Corte", "Sin fuego",
                    "Retira las venas del rocoto si quieres un picor más amable.",
                    0,
                    listOf(si("300 g queso", "fresco"), si("1 rocoto", "en tiras"))
                ),
                step(
                    "Aderezar",
                    "Adereza con limón, aceite, sal y pimienta. Sirve fresco como entrada o plato principal.",
                    5, "Aderezo", "Sin fuego",
                    "El soltero gana si reposa cinco minutos antes de servir.",
                    300,
                    listOf(si("3 limones", "jugo"), si("Aceite", "de oliva"))
                )
            ),
            chefQuote = "El soltero de queso es fresco, picante y profundamente arequipeño."
        ),
        Recipe(
            id = "ocopa-arequipena",
            name = "Ocopa Arequipeña",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Papas sancochadas bañadas en crema de ají mirasol, maní, huacatay y queso; una salsa andina inolvidable.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 35,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor suave",
            rating = 4.7,
            reviewCount = 230,
            calories = 350,
            proteinGrams = 12,
            carbsGrams = 36,
            badge = "Salsa Andina",
            regionTag = "Arequipa",
            tags = listOf("Huacatay", "Maní"),
            ingredients = listOf(
                ing("Papa amarilla", 1.0, "kg", "Verdulería"),
                ing("Ají mirasol", 4.0, "unid.", "Verdulería"),
                ing("Maní tostado", 100.0, "g", "Abarrotes"),
                ing("Queso fresco", 100.0, "g", "Lácteos"),
                ing("Huacatay", 1.0, "rama", "Hierbas y hojas"),
                ing("Leche", 1.0, "taza", "Lácteos"),
                ing("Cebolla", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Sancochar las papas",
                    "Sancocha las papas amarillas, péllalas y córtalas en rodajas gruesas. Reserva en un plato.",
                    20, "Papas", "Fuego medio",
                    "La papa amarilla le da una textura más cremosa a la ocopa.",
                    1200,
                    listOf(si("1 kg papa amarilla", "sancochada"), si("Sal", "al gusto"))
                ),
                step(
                    "Licuar la salsa",
                    "Licua ají mirasol remojado, maní, queso, huacatay, cebolla y leche hasta obtener una crema espesa.",
                    10, "Crema", "Sin fuego",
                    "Remoja el ají mirasol en agua caliente para que la salsa sea más suave.",
                    0,
                    listOf(si("4 ajíes mirasol", "remojados"), si("100 g maní", "tostado"))
                ),
                step(
                    "Bañar y servir",
                    "Baña las papas con la ocopa y decora con huevo duro y aceitunas. Sirve a temperatura ambiente.",
                    3, "Emplatado", "Sin fuego",
                    "La ocopa se sirve tibia o a temperatura ambiente, nunca fría de refrigeradora.",
                    0,
                    listOf(si("Huevo duro", "en rodajas"), si("Aceitunas", "negras"))
                )
            ),
            chefQuote = "La ocopa huele a huacatay y sabe a domingo arequipeño."
        ),
        Recipe(
            id = "chairo-arequipeno",
            name = "Chairo Arequipeño",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Sopa andina de chuño, trigo, carne y hierbas, reconfortante y con profundo sabor de altura.",
            imageRes = R.drawable.inchicapi,
            prepMinutes = 50,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.6,
            reviewCount = 180,
            calories = 340,
            proteinGrams = 22,
            carbsGrams = 40,
            badge = "Sopa Andina",
            regionTag = "Sierra",
            tags = listOf("Sopa", "Chuño"),
            ingredients = listOf(
                ing("Chuño", 200.0, "g", "Abarrotes"),
                ing("Trigo", 100.0, "g", "Abarrotes"),
                ing("Carne de res", 400.0, "g", "Carnicería"),
                ing("Papa", 3.0, "unid.", "Verdulería"),
                ing("Zanahoria", 2.0, "unid.", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Huacatay", 1.0, "rama", "Hierbas y hojas"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Remojar chuño y trigo",
                    "Remoja el chuño y el trigo por separado. Escurre y reserva.",
                    15, "Remojo", "Sin fuego",
                    "El chuño necesita hidratarse para cocinar parejo.",
                    900,
                    listOf(si("200 g chuño", "remojado"), si("100 g trigo", "remojado"))
                ),
                step(
                    "Cocer la carne",
                    "Hierve la carne con cebolla y sal hasta que esté tierna. Añade zanahoria y papa en trozos.",
                    20, "Caldo", "Fuego medio",
                    "Retira la espuma del caldo para que quede limpio.",
                    1200,
                    listOf(si("400 g res", "en trozos"), si("Verduras", "en cubos"))
                ),
                step(
                    "Añadir chuño y trigo",
                    "Incorpora chuño y trigo y cocina hasta que estén suaves. Añade huacatay al final y sirve caliente.",
                    15, "Sopa", "Fuego medio",
                    "El huacatay se añade al final para conservar su aroma.",
                    900,
                    listOf(si("Chuño y trigo", "escurridos"), si("Huacatay", "picado"))
                )
            ),
            chefQuote = "El chairo es una sopa de altura: abriga el cuerpo y el alma."
        ),

        // ---------------- SIERRA: POSTRES ----------------
        Recipe(
            id = "quesillo-miel",
            name = "Quesillo con Miel",
            region = Region.SIERRA,
            category = RecipeCategory.POSTRE,
            originLine = "Sierra • Cajamarca",
            description = "Queso fresco batido con leche y canela, cuajado al baño maría y bañado en miel de chancaca.",
            imageRes = R.drawable.queso_helado_arequipa,
            prepMinutes = 45,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.7,
            reviewCount = 220,
            calories = 260,
            proteinGrams = 12,
            carbsGrams = 30,
            badge = "Dulce Serrano",
            regionTag = "Cajamarca",
            tags = listOf("Queso", "Chancaca"),
            ingredients = listOf(
                ing("Queso fresco", 500.0, "g", "Lácteos"),
                ing("Leche", 1.0, "l", "Lácteos"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 2.0, "unid.", "Abarrotes"),
                ing("Chancaca", 200.0, "g", "Abarrotes"),
                ing("Huevos", 3.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Batir el queso",
                    "Licua el queso fresco con la leche, el azúcar, los huevos y la canela hasta obtener una mezcla homogénea.",
                    10, "Batido", "Sin fuego",
                    "Un queso fresco y suave da un quesillo más delicado.",
                    0,
                    listOf(si("500 g queso", "fresco"), si("3 huevos", "batidos"))
                ),
                step(
                    "Cuajar al baño maría",
                    "Vierte la mezcla en moldes y cocina al baño maría hasta que cuaje. Deja enfriar y refrigerar.",
                    20, "Baño maría", "Fuego medio",
                    "No dejes hervir el agua: el quesillo debe cuajar lentamente.",
                    1200,
                    listOf(si("Moldes", "para quesillo"), si("Agua", "para baño maría"))
                ),
                step(
                    "Preparar la miel",
                    "Cocina la chancaca con un poco de agua, canela y clavo hasta obtener una miel espesa. Baña el quesillo frío.",
                    10, "Miel", "Fuego medio",
                    "La miel espesa al enfriar: retírala un poco líquida.",
                    600,
                    listOf(si("200 g chancaca", "en trozos"), si("Canela", "y clavo"))
                )
            ),
            chefQuote = "Quesillo con miel: dulce de feria, simple y eterno."
        ),
        Recipe(
            id = "dulce-calabaza",
            name = "Dulce de Calabaza Andina",
            region = Region.SIERRA,
            category = RecipeCategory.POSTRE,
            originLine = "Sierra • Cocina de Valles",
            description = "Calabaza cocida en miel de chancaca con canela y clavo, postre de valles andinos lleno de aroma.",
            imageRes = R.drawable.suspiro,
            prepMinutes = 50,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.6,
            reviewCount = 170,
            calories = 220,
            proteinGrams = 2,
            carbsGrams = 48,
            badge = "Dulce de Valle",
            regionTag = "Sierra",
            tags = listOf("Calabaza", "Chancaca"),
            ingredients = listOf(
                ing("Calabaza", 1.0, "kg", "Verdulería"),
                ing("Chancaca", 400.0, "g", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Naranja", 1.0, "unid.", "Verdulería"),
                ing("Agua", 1.0, "l", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Pelar y cortar",
                    "Pela la calabaza, retira las semillas y córtala en trozos grandes.",
                    10, "Corte", "Sin fuego",
                    "Trozos del mismo tamaño se cocinan de manera pareja.",
                    0,
                    listOf(si("1 kg calabaza", "en trozos"), si("Semillas", "retiradas"))
                ),
                step(
                    "Cocer en miel",
                    "Prepara una miel con chancaca, agua, canela, clavo y cáscara de naranja. Añade la calabaza y cocina a fuego bajo.",
                    25, "Miel", "Fuego bajo",
                    "Cocina despacio para que la calabaza absorba la miel sin deshacerse.",
                    1500,
                    listOf(si("400 g chancaca", "en miel"), si("Cáscara naranja", "para aromatizar"))
                ),
                step(
                    "Reducir y servir",
                    "Sube ligeramente el fuego para reducir la miel hasta que cubra la cuchara. Sirve tibio o frío.",
                    10, "Reducción", "Fuego medio",
                    "La miel sigue espesando al enfriar: no la reduzcas demasiado.",
                    600,
                    listOf(si("Calabaza", "en miel"), si("Canela", "para servir"))
                )
            ),
            chefQuote = "La calabaza en miel es el otoño andino en un plato."
        ),

        // ---------------- SIERRA: BEBIDAS ----------------
        Recipe(
            id = "ponche-macsa",
            name = "Ponche de Macsa",
            region = Region.SIERRA,
            category = RecipeCategory.BEBIDA,
            originLine = "Sierra • Bebida Ritual",
            description = "Bebida caliente de macsa fermentada con canela, clavo y azúcar; reconfortante y tradicional en fiestas andinas.",
            imageRes = R.drawable.emoliente,
            prepMinutes = 30,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 240,
            calories = 160,
            proteinGrams = 2,
            carbsGrams = 30,
            badge = "Bebida Andina",
            regionTag = "Sierra",
            tags = listOf("Caliente", "Macsa"),
            ingredients = listOf(
                ing("Macsa", 500.0, "g", "Abarrotes"),
                ing("Agua", 2.0, "l", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Cáscara de naranja", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Hervir la macsa",
                    "Hierve la macsa en agua con canela, clavo y cáscara de naranja hasta que la bebida tome color y aroma.",
                    15, "Infusión", "Fuego medio",
                    "La macsa es fermentada: al hervir se suaviza su acidez.",
                    900,
                    listOf(si("500 g macsa", "triturada"), si("Especias", "canela y clavo"))
                ),
                step(
                    "Colar",
                    "Cuela la bebida y descarta los sólidos. Regresa el líquido a la olla.",
                    5, "Colado", "Sin fuego",
                    "Cuela con malla fina para una textura limpia.",
                    0,
                    listOf(si("Malla fina", "para colar"), si("Líquido", "reservado"))
                ),
                step(
                    "Endulzar y servir",
                    "Añade azúcar al gusto y sirve muy caliente en tazas, con una rama de canela.",
                    5, "Servicio", "Fuego bajo",
                    "Prueba el dulzor después de colar: la macsa ya aporta algo de azúcar.",
                    0,
                    listOf(si("1 taza azúcar", "al gusto"), si("Canela", "para servir"))
                )
            ),
            chefQuote = "El ponche de macsa huele a fiesta de pueblo y a manos que comparten."
        ),
        Recipe(
            id = "chocolate-andino",
            name = "Chocolate Caliente Andino",
            region = Region.SIERRA,
            category = RecipeCategory.BEBIDA,
            originLine = "Sierra • Bebida Reconfortante",
            description = "Chocolate espeso con leche, canela, clavo y un toque de ají; abrazo líquido para las noches de altura.",
            imageRes = R.drawable.mate_muna,
            prepMinutes = 15,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 1,
            spiceLabel = "Toque picante",
            rating = 4.7,
            reviewCount = 200,
            calories = 280,
            proteinGrams = 8,
            carbsGrams = 38,
            badge = "Bebida Caliente",
            regionTag = "Sierra",
            tags = listOf("Cacao", "Canela"),
            ingredients = listOf(
                ing("Chocolate de taza", 200.0, "g", "Abarrotes"),
                ing("Leche", 1.0, "l", "Lácteos"),
                ing("Canela", 1.0, "rama", "Abarrotes"),
                ing("Clavo de olor", 2.0, "unid.", "Abarrotes"),
                ing("Azúcar", 3.0, "cdas.", "Abarrotes"),
                ing("Ají panca", 1.0, "pizca", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Calentar la leche",
                    "Calienta la leche con canela y clavo sin dejar que hierva.",
                    5, "Infusión", "Fuego bajo",
                    "La leche caliente pero no hirviendo conserva mejor el sabor del cacao.",
                    0,
                    listOf(si("1 l leche", "entera"), si("Canela y clavo", "para aromatizar"))
                ),
                step(
                    "Derretir el chocolate",
                    "Añade el chocolate picado y el azúcar, moviendo hasta que se derrita por completo.",
                    6, "Chocolate", "Fuego bajo",
                    "Chocolate de taza peruano ya trae especias: prueba antes de endulzar.",
                    0,
                    listOf(si("200 g chocolate", "picado"), si("3 cdas azúcar", "al gusto"))
                ),
                step(
                    "Batir y servir",
                    "Bate con molinillo o batidor hasta formar espuma. Añade una pizca de ají panca y sirve caliente.",
                    3, "Espuma", "Sin fuego",
                    "Una pizca de ají realza el cacao sin que se sienta picante.",
                    0,
                    listOf(si("Molinillo", "para batir"), si("Ají panca", "una pizca"))
                )
            ),
            chefQuote = "El chocolate andino se toma despacio: cada sorbo calienta la altura."
        ),

        // ---------------- SELVA: PLATOS DE FONDO ----------------
        Recipe(
            id = "doncella-llicta",
            name = "Doncella a la Llicta",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Cocina Amazónica",
            description = "Pescado de río envuelto en hoja de bijao con ají charapita, cebolla y sachaculantro, cocido al vapor.",
            imageRes = R.drawable.patarashca,
            prepMinutes = 40,
            portions = 3,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.8,
            reviewCount = 260,
            calories = 300,
            proteinGrams = 34,
            carbsGrams = 14,
            badge = "Río y Hoja",
            regionTag = "Amazonía",
            tags = listOf("Bijao", "Pescado de río"),
            ingredients = listOf(
                ing("Doncella", 800.0, "g", "Pescadería"),
                ing("Hojas de bijao", 4.0, "unid.", "Hierbas y hojas"),
                ing("Ají charapita", 8.0, "unid.", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Sachaculantro", 1.0, "rama", "Hierbas y hojas"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Sazonar el pescado",
                    "Sazona la doncella con sal, limón, ají charapita picado y cebolla en pluma.",
                    10, "Sazonado", "Sin fuego",
                    "El ají charapita es pequeño pero potente: ajusta la cantidad a tu gusto.",
                    0,
                    listOf(si("800 g doncella", "limpia"), si("8 ajíes charapita", "picados"))
                ),
                step(
                    "Envolver en bijao",
                    "Coloca el pescado sobre hojas de bijao, añade sachaculantro y cierra el paquete con cuidado.",
                    8, "Envoltura", "Sin fuego",
                    "Pasa las hojas brevemente por calor para que sean flexibles y no se rompan.",
                    0,
                    listOf(si("Hojas de bijao", "amortiguadas"), si("Sachaculantro", "fresco"))
                ),
                step(
                    "Cocinar al vapor",
                    "Cocina los paquetes al vapor o sobre brasas hasta que el pescado esté tierno. Sirve dentro de la hoja.",
                    18, "Vapor", "Fuego medio",
                    "El bijao transmite un aroma herbal que es parte del plato.",
                    900,
                    listOf(si("Paquetes", "de bijao"), si("Agua", "para vapor"))
                )
            ),
            chefQuote = "La doncella a la llicta se abre en la mesa y perfuma toda la casa."
        ),
        Recipe(
            id = "timbuche-pescado",
            name = "Timbuche de Pescado",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Sopa Amazónica",
            description = "Caldo fresco y picante de pescado de río con culantro, ají charapita, cebolla y yuca.",
            imageRes = R.drawable.inchicapi,
            prepMinutes = 35,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 210,
            calories = 260,
            proteinGrams = 28,
            carbsGrams = 18,
            badge = "Sopa de Río",
            regionTag = "Amazonía",
            tags = listOf("Sopa", "Pescado"),
            ingredients = listOf(
                ing("Pescado de río", 700.0, "g", "Pescadería"),
                ing("Yuca", 300.0, "g", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Ají charapita", 10.0, "unid.", "Verdulería"),
                ing("Culantro", 1.0, "atado", "Hierbas y hojas"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Preparar el caldo",
                    "Hierve agua con cebolla y yuca hasta que la yuca empiece a ablandarse.",
                    12, "Caldo", "Fuego medio",
                    "La yuca espesa el caldo de forma natural.",
                    600,
                    listOf(si("300 g yuca", "en trozos"), si("1 cebolla", "en pluma"))
                ),
                step(
                    "Cocinar el pescado",
                    "Añade el pescado en trozos y el ají charapita. Cocina suavemente hasta que el pescado esté tierno.",
                    10, "Pescado", "Fuego medio",
                    "No revuelvas fuerte: el pescado de río se deshace con facilidad.",
                    0,
                    listOf(si("700 g pescado", "en trozos"), si("10 ajíes", "charapita"))
                ),
                step(
                    "Aromatizar y servir",
                    "Añade culantro picado y limón al final. Sirve caliente con yuca y un toque de ají.",
                    5, "Servicio", "Fuego bajo",
                    "El culantro se añade fuera del fuego para que quede verde y aromático.",
                    0,
                    listOf(si("1 atado culantro", "picado"), si("Limón", "al gusto"))
                )
            ),
            chefQuote = "El timbuche es el río servido caliente: fresco, picante y sincero."
        ),
        Recipe(
            id = "cecina-brasa",
            name = "Cecina a la Brasa",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Tarapoto",
            description = "Cecina de cerdo ahumada y asada a las brasas, servida con plátano maduro y ají charapita.",
            imageRes = R.drawable.tacacho,
            prepMinutes = 45,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.8,
            reviewCount = 310,
            calories = 460,
            proteinGrams = 32,
            carbsGrams = 26,
            badge = "Ahumado",
            regionTag = "Tarapoto",
            tags = listOf("Brasa", "Cecina"),
            ingredients = listOf(
                ing("Cecina de cerdo", 800.0, "g", "Carnicería"),
                ing("Plátano maduro", 4.0, "unid.", "Verdulería"),
                ing("Ají charapita", 10.0, "unid.", "Verdulería"),
                ing("Limón", 3.0, "unid.", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Desalar la cecina",
                    "Remoja la cecina en agua tibia para quitar el exceso de sal y sécala bien.",
                    10, "Cecina", "Sin fuego",
                    "Prueba un trozo antes de asar: la salinidad varía según el productor.",
                    600,
                    listOf(si("800 g cecina", "remojada"), si("Agua", "tibia"))
                ),
                step(
                    "Asar a las brasas",
                    "Asa la cecina a fuego medio hasta que esté dorada y ligeramente crocante, girándola para que se cocine parejo.",
                    20, "Brasa", "Fuego medio",
                    "El humo de la brasa es el verdadero sabor de la cecina.",
                    1200,
                    listOf(si("Cecina", "en tiras"), si("Brasas", "de madera"))
                ),
                step(
                    "Servir con plátano",
                    "Asa o fríe el plátano maduro y sirve con cecina, cebolla y ají charapita al limón.",
                    5, "Emplatado", "Fuego medio",
                    "El plátano maduro equilibra la sal de la cecina.",
                    0,
                    listOf(si("Plátano maduro", "asado"), si("Ají charapita", "al limón"))
                )
            ),
            chefQuote = "La cecina a la brasa se come con la mano y se recuerda por años."
        ),
        Recipe(
            id = "arroz-pato-amazonico",
            name = "Arroz con Pato Amazónico",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Cocina de Río",
            description = "Arroz meloso con pato de chacra, culantro, ají charapita y cerveza negra; festín de la amazonía.",
            imageRes = R.drawable.juane,
            prepMinutes = 60,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor medio",
            rating = 4.7,
            reviewCount = 240,
            calories = 590,
            proteinGrams = 36,
            carbsGrams = 55,
            badge = "Festivo",
            regionTag = "Amazonía",
            tags = listOf("Pato", "Culantro"),
            ingredients = listOf(
                ing("Pato en presas", 1.0, "kg", "Carnicería"),
                ing("Arroz", 2.0, "tazas", "Abarrotes"),
                ing("Culantro", 1.0, "atado", "Hierbas y hojas"),
                ing("Cerveza negra", 1.0, "botella", "Abarrotes"),
                ing("Ají charapita", 8.0, "unid.", "Verdulería"),
                ing("Cebolla", 2.0, "unid.", "Verdulería"),
                ing("Ajo", 4.0, "dientes", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Preparar el aderezo",
                    "Licua culantro, ají charapita, cebolla y ajo con un poco de cerveza negra.",
                    15, "Aderezo", "Sin fuego",
                    "El culantro debe quedar bien molido para que el arroz tome color parejo.",
                    0,
                    listOf(si("1 atado culantro", "licuado"), si("8 ajíes", "charapita"))
                ),
                step(
                    "Sellar el pato",
                    "Dora las presas de pato en una olla, añade el aderezo y la cerveza, y cocina hasta que la carne esté tierna.",
                    15, "Sellado", "Fuego alto",
                    "El pato suelta bastante grasa: retira el exceso antes de añadir el arroz.",
                    900,
                    listOf(si("1 kg pato", "en presas"), si("Cerveza negra", "1 botella"))
                ),
                step(
                    "Cocinar el arroz",
                    "Incorpora el arroz y caldo hasta cubrirlo. Cocina a fuego bajo y tapa hasta que esté graneado y jugoso.",
                    25, "Arroz", "Fuego bajo",
                    "Deja reposar cinco minutos antes de servir para que el arroz asiente.",
                    1500,
                    listOf(si("2 tazas arroz", "lavado"), si("Caldo", "del pato"))
                )
            ),
            chefQuote = "El arroz con pato amazónico es fiesta: verde, aromático y generoso."
        ),
        Recipe(
            id = "sacha-sopa-yuca",
            name = "Sopa de Yuca y Sachaculantro",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Sopa de Chacra",
            description = "Sopa cremosa de yuca con gallina, sachaculantro y maíz; sencilla, aromática y llena de selva.",
            imageRes = R.drawable.inchicapi,
            prepMinutes = 40,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.6,
            reviewCount = 190,
            calories = 320,
            proteinGrams = 24,
            carbsGrams = 38,
            badge = "Sopa de Chacra",
            regionTag = "Selva",
            tags = listOf("Yuca", "Gallina"),
            ingredients = listOf(
                ing("Yuca", 500.0, "g", "Verdulería"),
                ing("Gallina", 600.0, "g", "Carnicería"),
                ing("Maíz", 1.0, "unid.", "Verdulería"),
                ing("Cebolla", 1.0, "unid.", "Verdulería"),
                ing("Sachaculantro", 1.0, "rama", "Hierbas y hojas"),
                ing("Ajo", 3.0, "dientes", "Verdulería"),
                ing("Sal", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocer la gallina",
                    "Hierve la gallina con cebolla, ajo y sal hasta que esté tierna. Retira la espuma.",
                    15, "Caldo", "Fuego medio",
                    "La gallina de chacra necesita más tiempo que el pollo de granja.",
                    900,
                    listOf(si("600 g gallina", "en presas"), si("1 cebolla", "picada"))
                ),
                step(
                    "Añadir yuca y maíz",
                    "Incorpora yuca en trozos y maíz desgranado. Cocina hasta que la yuca esté suave.",
                    15, "Sopa", "Fuego medio",
                    "Remueve con cuidado para que la yuca no se deshaga por completo.",
                    900,
                    listOf(si("500 g yuca", "en trozos"), si("1 maíz", "desgranado"))
                ),
                step(
                    "Aromatizar",
                    "Añade sachaculantro picado al final y ajusta la sal. Sirve caliente con un trozo de gallina.",
                    5, "Servicio", "Fuego bajo",
                    "El sachaculantro es intenso: añade poco y prueba.",
                    0,
                    listOf(si("Sachaculantro", "picado"), si("Sal", "al gusto"))
                )
            ),
            chefQuote = "La sopa de yuca sabe a casa de chacra: humilde y abundante."
        ),

        // ---------------- SELVA: POSTRES ----------------
        Recipe(
            id = "dulce-aguaje",
            name = "Dulce de Aguaje",
            region = Region.SELVA,
            category = RecipeCategory.POSTRE,
            originLine = "Selva • Fruto Amazónico",
            description = "Compota dulce de aguaje con canela y clavo, servida tibia; el postre más amazónico de la mesa.",
            imageRes = R.drawable.mousse_copoazu,
            prepMinutes = 25,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.7,
            reviewCount = 160,
            calories = 210,
            proteinGrams = 2,
            carbsGrams = 40,
            badge = "Fruto de Selva",
            regionTag = "Amazonía",
            tags = listOf("Aguaje", "Dulce"),
            ingredients = listOf(
                ing("Aguaje", 6.0, "unid.", "Frutas y verduras"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Canela", 1.0, "rama", "Abarrotes"),
                ing("Clavo de olor", 2.0, "unid.", "Abarrotes"),
                ing("Agua", 2.0, "tazas", "Abarrotes"),
                ing("Limón", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Pelar el aguaje",
                    "Pela los aguajes, retira las semillas y conserva la pulpa con su jugo.",
                    8, "Fruta", "Sin fuego",
                    "El aguaje maduro se reconoce por su color rojo intenso.",
                    0,
                    listOf(si("6 aguajes", "maduros"), si("Pulpa", "reservada"))
                ),
                step(
                    "Cocer con especias",
                    "Cocina la pulpa con agua, azúcar, canela y clavo hasta que se ablande y tome color.",
                    12, "Compota", "Fuego medio",
                    "Mueve de vez en cuando para que no se pegue al fondo.",
                    720,
                    listOf(si("1 taza azúcar", "al gusto"), si("Canela y clavo", "para aromatizar"))
                ),
                step(
                    "Reducir y servir",
                    "Añade unas gotas de limón y cocina hasta que la compota espese. Sirve tibia o bien fría.",
                    5, "Reducción", "Fuego bajo",
                    "El limón corta el dulzor y resalta el sabor del aguaje.",
                    300,
                    listOf(si("Limón", "unas gotas"), si("Canela", "molida"))
                )
            ),
            chefQuote = "El aguaje es el sol de la selva hecho fruta."
        ),
        Recipe(
            id = "platano-horno-aguaje",
            name = "Plátano al Horno con Aguaje",
            region = Region.SELVA,
            category = RecipeCategory.POSTRE,
            originLine = "Selva • Postre de Chacra",
            description = "Plátano maduro horneado con miel de aguaje y canela, un postre sencillo con aroma tropical.",
            imageRes = R.drawable.camu_camu,
            prepMinutes = 30,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.6,
            reviewCount = 140,
            calories = 240,
            proteinGrams = 3,
            carbsGrams = 44,
            badge = "Dulce de Selva",
            regionTag = "Selva",
            tags = listOf("Plátano", "Horno"),
            ingredients = listOf(
                ing("Plátano maduro", 4.0, "unid.", "Verdulería"),
                ing("Pulpa de aguaje", 1.0, "taza", "Frutas y verduras"),
                ing("Azúcar", 3.0, "cdas.", "Abarrotes"),
                ing("Canela", 1.0, "cdta.", "Abarrotes"),
                ing("Mantequilla", 2.0, "cdas.", "Lácteos")
            ),
            steps = listOf(
                step(
                    "Preparar los plátanos",
                    "Abre los plátanos por la mitad sin retirar la cáscara y colócalos en una bandeja con mantequilla.",
                    8, "Preparación", "Sin fuego",
                    "La cáscara funciona como recipiente y evita que el plátano se seque.",
                    0,
                    listOf(si("4 plátanos", "maduros"), si("2 cdas mantequilla", "derretida"))
                ),
                step(
                    "Hornear",
                    "Hornea a 180 °C hasta que el plátano esté tierno y dorado.",
                    15, "Horno", "Horno 180 °C",
                    "Cada horno es distinto: pincha el plátano para comprobar el punto.",
                    900,
                    listOf(si("Plátanos", "en bandeja"), si("Horno", "precalentado"))
                ),
                step(
                    "Bañar con aguaje",
                    "Mezcla pulpa de aguaje con azúcar y canela, calienta y baña los plátanos. Sirve tibio.",
                    5, "Miel", "Fuego bajo",
                    "La pulpa de aguaje puede ser ácida: ajusta el azúcar al gusto.",
                    300,
                    listOf(si("1 taza aguaje", "pulpa"), si("Canela", "molida"))
                )
            ),
            chefQuote = "Plátano y aguaje: dos frutos de la selva que se entienden sin hablar."
        ),

        // ---------------- SELVA: BEBIDAS ----------------
        Recipe(
            id = "aguajina",
            name = "Aguajina",
            region = Region.SELVA,
            category = RecipeCategory.BEBIDA,
            originLine = "Selva • Bebida Amazónica",
            description = "Refresco cremoso de pulpa de aguaje con leche, azúcar y hielo; dulce y exótico.",
            imageRes = R.drawable.camu_camu,
            prepMinutes = 15,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 220,
            calories = 180,
            proteinGrams = 3,
            carbsGrams = 30,
            badge = "Refresco de Selva",
            regionTag = "Amazonía",
            tags = listOf("Aguaje", "Frío"),
            ingredients = listOf(
                ing("Pulpa de aguaje", 1.0, "taza", "Frutas y verduras"),
                ing("Leche", 1.0, "l", "Lácteos"),
                ing("Azúcar", 4.0, "cdas.", "Abarrotes"),
                ing("Hielo", 3.0, "tazas", "Abarrotes"),
                ing("Canela", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Licuar la pulpa",
                    "Licua la pulpa de aguaje con un poco de leche hasta obtener una crema suave.",
                    6, "Licuado", "Sin fuego",
                    "Cuela si prefieres una textura más fina.",
                    0,
                    listOf(si("1 taza aguaje", "pulpa"), si("Leche", "para licuar"))
                ),
                step(
                    "Endulzar",
                    "Añade el resto de la leche, el azúcar y la canela. Mezcla bien y prueba el dulzor.",
                    3, "Mezcla", "Sin fuego",
                    "La canela realza el aroma del aguaje sin taparlo.",
                    0,
                    listOf(si("4 cdas azúcar", "al gusto"), si("Canela", "una pizca"))
                ),
                step(
                    "Servir helado",
                    "Añade hielo, licua brevemente y sirve en vasos altos.",
                    3, "Servicio", "Sin fuego",
                    "Sirve de inmediato para que no pierda la textura helada.",
                    0,
                    listOf(si("3 tazas hielo", "picado"), si("Vasos", "altos"))
                )
            ),
            chefQuote = "La aguajina es la selva en vaso: dulce, cremosa y sin prisa."
        ),
        Recipe(
            id = "refresco-cocona",
            name = "Refresco de Cocona",
            region = Region.SELVA,
            category = RecipeCategory.BEBIDA,
            originLine = "Selva • Bebida de la Chacra",
            description = "Bebida fresca y ligeramente ácida de cocona con azúcar, hierbabuena y hielo.",
            imageRes = R.drawable.camu_camu,
            prepMinutes = 10,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.6,
            reviewCount = 150,
            calories = 110,
            proteinGrams = 1,
            carbsGrams = 24,
            badge = "Fresco Amazónico",
            regionTag = "Selva",
            tags = listOf("Cocona", "Frío"),
            ingredients = listOf(
                ing("Cocona", 6.0, "unid.", "Frutas y verduras"),
                ing("Azúcar", 4.0, "cdas.", "Abarrotes"),
                ing("Agua", 1.0, "l", "Abarrotes"),
                ing("Hierbabuena", 6.0, "hojas", "Hierbas y hojas"),
                ing("Hielo", 3.0, "tazas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Extraer la pulpa",
                    "Corta las coconas, retira la pulpa y licua con un poco de agua.",
                    4, "Pulpa", "Sin fuego",
                    "La cocona es ácida: no necesita limón.",
                    0,
                    listOf(si("6 coconas", "maduras"), si("Agua", "para licuar"))
                ),
                step(
                    "Endulzar y aromatizar",
                    "Cuela la mezcla, añade el resto del agua, azúcar y hierbabuena. Mezcla bien.",
                    4, "Mezcla", "Sin fuego",
                    "Ajusta el azúcar según la acidez de la fruta.",
                    0,
                    listOf(si("4 cdas azúcar", "al gusto"), si("Hierbabuena", "fresca"))
                ),
                step(
                    "Servir",
                    "Sirve bien frío con hielo y una hoja de hierbabuena.",
                    2, "Servicio", "Sin fuego",
                    "El refresco de cocona se disfruta recién hecho.",
                    0,
                    listOf(si("Hielo", "abundante"), si("Hierbabuena", "para decorar"))
                )
            ),
            chefQuote = "La cocona refresca como la sombra de un árbol de la selva."
        )
    )
}
