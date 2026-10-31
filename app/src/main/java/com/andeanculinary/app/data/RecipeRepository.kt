package com.andeanculinary.app.data

import com.andeanculinary.app.model.*

object RecipeRepository {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "ceviche-clasico-corvina",
            title = "Ceviche Clásico de Corvina",
            quechuaOrLocalName = "Siwichi Costeño",
            subtitle = "El emblema del Pacífico: corvina curada en leche de tigre al instante",
            description = "Pescado blanco salvaje cortado en dados de 2 cm, marinado al momento con zumo de limón recién exprimido, ají limo, cilantro fresco y cebolla morada crujiente.",
            region = Region.COSTA,
            category = Category.CEVICHES,
            prepTimeMinutes = 20,
            cookTimeMinutes = 5,
            difficulty = "Fácil",
            spiceLevel = 3,
            defaultServings = 4,
            imageUrl = "/assets/images/ceviche_clasico_1793429743979.jpg",
            tags = listOf("Sin Gluten", "Mariscos", "Pescado Fresco"),
            pairing = Pairing(
                drink = "Pisco Sour Clásico (Quebranta)",
                description = "La fresca acidez del limón piurano armoniza con la textura sedosa del pisco batido."
            ),
            culturalHistory = "Declarado Patrimonio Cultural de la Nación, el ceviche costero ancestral se preparaba con chicha y tumbo antes de la llegada de las limas traídas de España.",
            ingredients = listOf(
                Ingredient("c1", "Filete de corvina salvaje fresca", 700.0, "g", "cortado en dados de 2 cm"),
                Ingredient("c2", "Limones peruanos verdes y jugosos", 12.0, "unidades", "exprimidos suavemente al momento"),
                Ingredient("c3", "Ají limo rojo y amarillo", 2.0, "unidades", "finamente picados sin venas"),
                Ingredient("c4", "Cebolla morada mediana", 1.5, "unidades", "cortada en pluma fina en agua helada"),
                Ingredient("c5", "Dientes de ajo rallados", 2.0, "dientes"),
                Ingredient("c6", "Choclo desgranado cocido", 2.0, "tazas"),
                Ingredient("c7", "Camote glaseado", 2.0, "unidades"),
                Ingredient("c8", "Cancha serrana tostada", 1.0, "taza")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Curado inicial con sal marina y frío",
                    "Coloca los dados de corvina en un bol frío. Espolvorea sal marina y frota suavemente 2 cubos de hielo sobre el pescado durante 45 segundos.",
                    "Frotar el bol con medio ají limo antes de colocar el pescado transfiere los aceites aromáticos.",
                    45,
                    "Curado en frío"
                ),
                CookingStep(
                    2,
                    "Infusión de ajo y ají limo",
                    "Retira el hielo, añade el ajo rallado y la mitad del ají limo. Mezcla con cuchara de metal.",
                    "Usa una cuchara de acero inoxidable o peltre; la madera absorbe los cítricos."
                ),
                CookingStep(
                    3,
                    "Emulsión de Leche de Tigre",
                    "Exprime los limones presionando solo hasta la mitad con los dedos. Mezcla vigorosamente por 90 segundos.",
                    "La diferencia de temperatura cataliza la emulsión láctea natural.",
                    90,
                    "Emulsión de jugo"
                ),
                CookingStep(
                    4,
                    "Cebolla crocante y cilantro",
                    "Escurre la cebolla morada y agrégala al bol junto con el cilantro fresco picado. Da dos vueltas finales suaves."
                ),
                CookingStep(
                    5,
                    "Emplatado ceremonial andino",
                    "Sirve de inmediato bañado con abundante leche de tigre, camote dulce, choclo y cancha crujiente."
                )
            )
        ),
        Recipe(
            id = "lomo-saltado-criollo",
            title = "Lomo Saltado Criollo al Wok",
            quechuaOrLocalName = "Lomo al Jugo Chifa",
            subtitle = "El choque perfecto entre la tradición criolla y la técnica del wok hei",
            description = "Tiras de lomo fino selladas a fuego volcánico en wok ahumado, salteadas con cebolla morada, tomate, ají amarillo crujiente, sillao, vinagre tinto y cilantro.",
            region = Region.COSTA,
            category = Category.STIR_FRIES,
            prepTimeMinutes = 15,
            cookTimeMinutes = 10,
            difficulty = "Intermedio",
            spiceLevel = 2,
            defaultServings = 4,
            imageUrl = "/assets/images/lomo_saltado_1793429757894.jpg",
            tags = listOf("Tradición Chifa", "Fuego Alto", "Carne Vacuna"),
            pairing = Pairing(
                drink = "Vino Tinto Malbec o Cerveza Negra",
                description = "Los taninos equilibran la caramelización del wok y la acidez del vinagre tinto."
            ),
            culturalHistory = "Nacido en las fondas chinas del Barrio Chino de Lima en el siglo XIX, fusionó el sellado a fuego vivo con los ingredientes peruanos.",
            ingredients = listOf(
                Ingredient("ls1", "Lomo fino de res (tenderloin)", 750.0, "g", "en tiras gruesas de 1.5 x 4 cm"),
                Ingredient("ls2", "Cebolla morada en pétalos", 2.0, "unidades"),
                Ingredient("ls3", "Tomates maduros en gajos", 3.0, "unidades"),
                Ingredient("ls4", "Ají amarillo fresco en juliana", 2.0, "unidades"),
                Ingredient("ls5", "Sillao oscuro peruano", 4.0, "cucharadas"),
                Ingredient("ls6", "Vinagre tinto de vino", 3.0, "cucharadas"),
                Ingredient("ls7", "Papas amarillas rústicas fritas", 4.0, "unidades")
            ),
            steps = listOf(
                CookingStep(
                    1,
                    "Mise en place de la carne",
                    "Seca bien la carne con toalla de papel y salpimienta justo antes de ponerla en el fuego.",
                    "Si la carne está húmeda soltará agua en vez de caramelizarse."
                ),
                CookingStep(
                    2,
                    "Sellado volcánico al wok",
                    "Calienta el wok al humo blanco. Vierte la mitad de la carne en una sola capa sin moverla 45 segundos. Saltea rápidamente.",
                    "Hacerlo en dos tandas asegura que el wok no pierda calor.",
                    90,
                    "Sellado en wok"
                ),
                CookingStep(
                    3,
                    "Salteado de cebolla y ají amarillo",
                    "En el mismo wok sofríe el ajo y ají amarillo por 15 segundos. Añade la cebolla y saltea a fuego máximo por 1 minuto.",
                    "La cebolla debe tronar al morderse: nunca quedar blanda.",
                    60,
                    "Salteado crujiente"
                ),
                CookingStep(
                    4,
                    "Desglase con vinagre y sillao",
                    "Devuelve la carne, vierte el vinagre por los bordes calientes, añade el sillao y tomates.",
                    "Echar el vinagre por el borde elimina la acidez punzante dejando el perfume frutal.",
                    45,
                    "Desglase al jugo"
                ),
                CookingStep(
                    5,
                    "Final con papas y cilantro",
                    "Apaga el fuego, incorpora cilantro y sirve con las papas recién fritas y arroz blanco."
                )
            )
        ),
        Recipe(
            id = "aji-de-gallina-cremoso",
            title = "Ají de Gallina Cremoso con Pecanas",
            quechuaOrLocalName = "Uchu Gallina",
            subtitle = "El guiso aterciopelado más venerado de la gastronomía limeña",
            description = "Pechuga tierna de ave en hebras finas, envuelta en crema sedosa de ají amarillo soasado, pan remojado en caldo sustancioso, leche evaporada y nueces pecanas tostadas.",
            region = Region.COSTA,
            category = Category.STEWS,
            prepTimeMinutes = 25,
            cookTimeMinutes = 35,
            difficulty = "Intermedio",
            spiceLevel = 2,
            defaultServings = 4,
            imageUrl = "/assets/images/aji_gallina_1793429773538.jpg",
            tags = listOf("Confort Food", "Cremoso", "Tradición Virreinal"),
            pairing = Pairing(
                drink = "Chilcano de Pisco con maracuyá",
                description = "El frescor cítrico corta la suntuosidad de la crema de pecanas."
            ),
            culturalHistory = "Evolucionó del Manjar Blanco medieval español adaptado por cocineros criollos con el ají amarillo peruano.",
            ingredients = listOf(
                Ingredient("ag1", "Pechuga de pollo deshilachada", 800.0, "g"),
                Ingredient("ag2", "Pasta de ají amarillo", 1.0, "taza"),
                Ingredient("ag3", "Pasta de ají mirasol tostado", 2.0, "cucharadas"),
                Ingredient("ag4", "Pan francés remojado en caldo y leche", 4.0, "rebanadas"),
                Ingredient("ag5", "Leche evaporada", 1.5, "tazas"),
                Ingredient("ag6", "Pecanas tostadas molidas", 60.0, "g"),
                Ingredient("ag7", "Queso parmesano rallado", 50.0, "g"),
                Ingredient("ag8", "Papas amarillas sancochadas", 4.0, "unidades"),
                Ingredient("ag9", "Aceitunas botija y huevos duros", 4.0, "unidades")
            ),
            steps = listOf(
                CookingStep(1, "Cocción del pollo", "Hierve la pechuga en caldo aromatizado por 20 minutos y deshilacha a mano."),
                CookingStep(2, "Licuado de base cremosa", "Procesa el pan remojado con las pecanas tostadas hasta que no queden grumos."),
                CookingStep(3, "Aderezo dorado", "Sofríe la cebolla, ajo y pasta de ají amarillo hasta que se separe el aceite."),
                CookingStep(4, "Fusión aterciopelada", "Vierte la crema de pan y pecanas a fuego mínimo por 7 minutos hasta espesar."),
                CookingStep(5, "Emplatado tradicional", "Sirve sobre cama de papa amarilla caliente con aceituna botija y huevo duro.")
            )
        )
    )

    fun getRecipeById(id: String): Recipe? = recipes.find { it.id == id }
}
