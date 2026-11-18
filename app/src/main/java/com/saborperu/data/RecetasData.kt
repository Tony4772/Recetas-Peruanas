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

object RecetasData {

    val recipes: List<Recipe> = listOf(
        Recipe(
            id = "ceviche-limeno",
            name = "Ceviche Clásico Limeño",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Tradición Marina",
            description = "La joya de la gastronomía peruana: pescado fresco curado al instante con jugo de limón sutil, ají limo y cilantro fresco.",
            imageRes = R.drawable.ceviche,
            prepMinutes = 25,
            portions = 4,
            difficulty = "Intermedio",
            spiceLevel = 2,
            spiceLabel = "Picor suave",
            rating = 4.9,
            reviewCount = 1200,
            calories = 320,
            proteinGrams = 38,
            carbsGrams = 12,
            badge = "Destacado",
            regionTag = "Costa",
            tags = listOf("Sin cocción", "Del mar"),
            ingredients = listOf(
                ing("Corvina fresca en cubos", 600.0, "g", "Pescadería", "Bien fría"),
                ing("Cebolla roja en pluma", 2.0, "unid.", "Verdulería", "Corte fino"),
                ing("Limón sutil", 8.0, "unid.", "Verdulería", "Para jugo"),
                ing("Ají limo", 2.0, "unid.", "Verdulería", "Sin venas"),
                ing("Culantro fresco", 1.0, "rama", "Hierbas y hojas"),
                ing("Sal marina", 1.0, "cdta.", "Abarrotes"),
                ing("Camote sancochado", 1.0, "unid.", "Verdulería"),
                ing("Choclo desgranado", 2.0, "unid.", "Verdulería"),
                ing("Pasta de ají amarillo", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "El pescado bien frío",
                    "Corta la corvina en cubos parejos de dos centímetros y mantenla sobre hielo. Un pescado frío conserva la textura firme y no se deshace al contacto con el limón.",
                    8, "Corte y frío", "Sin fuego",
                    "Pide en la pescadería que limpien la corvina el mismo día y no la laves con agua de caño.",
                    240,
                    listOf(si("600 g corvina", "en cubos de 2 cm"), si("Hielo", "para mantener el frío"))
                ),
                step(
                    "La leche de tigre",
                    "Exprime el limón sutil sin presionar la cáscara y mezcla el jugo con ají limo picado, culantro y sal. Prueba y ajusta el punto ácido antes de incorporarlo al pescado.",
                    7, "Leche de tigre", "Sin fuego",
                    "El limón se exprime al momento: si lo dejas reposar, pierde aroma y amarga.",
                    180,
                    listOf(si("8 limones", "jugo colado"), si("2 ajíes limo", "picados finos"), si("1 rama culantro", "picado"))
                ),
                step(
                    "Mezclar y servir al instante",
                    "Incorpora el pescado a la leche de tigre, añade la cebolla y la pasta de ají amarillo. Sirve de inmediato con camote y choclo para conservar la textura del marisco.",
                    2, "Emplatado", "Sin fuego",
                    "El ceviche limeño no reposa: se mezcla y se sirve para que el pescado quede firme por fuera y jugoso por dentro.",
                    120,
                    listOf(si("2 cebollas rojas", "en pluma"), si("Camote y choclo", "como guarnición"))
                )
            ),
            chefQuote = "El ceviche no espera. Pescado frío, limón recién exprimido y a la mesa en menos de un minuto."
        ),
        Recipe(
            id = "lomo-saltado",
            name = "Lomo Saltado Criollo",
            region = Region.COSTA,
            category = RecipeCategory.FONDO,
            originLine = "Costa • Fusión Chifa",
            description = "Lomo fino salteado al wok con cebolla roja, tomate y ají amarillo, sazonado con sillao y servido con papas fritas y arroz.",
            imageRes = R.drawable.lomo_saltado,
            prepMinutes = 30,
            portions = 4,
            difficulty = "Medio",
            spiceLevel = 2,
            spiceLabel = "Sabor balanceado con toque de ají amarillo",
            rating = 4.9,
            reviewCount = 840,
            calories = 580,
            proteinGrams = 42,
            carbsGrams = 25,
            badge = "Tradición Criolla",
            regionTag = "Costa",
            tags = listOf("Wok", "Criollo"),
            ingredients = listOf(
                ing("Lomo fino de res cortado en tiras gruesas", 600.0, "g", "Carnicería"),
                ing("Cebollas rojas en gajos gruesos", 2.0, "unid.", "Verdulería"),
                ing("Tomates maduros en gajos", 3.0, "unid.", "Verdulería"),
                ing("Ají amarillo en juliana", 1.0, "unid.", "Verdulería", "Sin venas"),
                ing("Sillao oscuro", 3.0, "cdas.", "Abarrotes"),
                ing("Vinagre tinto", 2.0, "cdas.", "Abarrotes"),
                ing("Dientes de ajo molidos", 2.0, "dientes", "Verdulería"),
                ing("Culantro fresco y sal", null, "al gusto", "Hierbas y hojas"),
                ing("Papas amarillas fritas y arroz con choclo", null, "para acompañar", "Abarrotes", "Crujientes")
            ),
            steps = listOf(
                step(
                    "El Sellado a Fuego Vivo",
                    "Calienta el wok hasta que eche humo. Vierte un chorrito de aceite y sella la carne en tandas rápidas de 30 a 45 segundos. La carne debe quedar dorada por fuera y jugosa al centro. Reserva de inmediato en un tazón caliente.",
                    4, "Wok a temperatura máxima", "Fuego máximo",
                    "No amontones la carne: si el wok pierde temperatura, la carne se hierve en sus jugos en lugar de sellarse.",
                    240,
                    listOf(si("600 g lomo fino", "en tiras gruesas"), si("2 cdas. aceite", "para sellar"))
                ),
                step(
                    "El Salteado Criollo y Verduras",
                    "En el mismo wok humeante añade una gota más de aceite, el ajo molido, los gajos de cebolla y el ají amarillo en juliana. Saltea durante un minuto sin que pierdan su textura crocante.",
                    3, "Técnica Wok", "Fuego máximo • Salteado rápido",
                    "Mueve constantemente sin tapar la sartén para lograr el ahumado criollo perfecto.",
                    60,
                    listOf(si("2 cebollas rojas", "en gajos gruesos"), si("1 ají amarillo", "en tiras finas"), si("1 cda. aceite", "para saltear"))
                ),
                step(
                    "El Tomate al Último",
                    "Agrega el tomate maduro en gajos y saltea apenas 30 segundos. Debe calentarse sin deshacerse, aportando acidez y color al conjunto.",
                    2, "Punto de tomate", "Fuego alto",
                    "El tomate entra al final: si lo cocinas demasiado, suelta agua y aguada el salteado.",
                    45,
                    listOf(si("3 tomates maduros", "en gajos gruesos"))
                ),
                step(
                    "La Sazón y Flambeado",
                    "Reincorpora la carne con todos sus jugos. Rocía el vinagre tinto por los bordes para desglasar, añade el sillao y saltea con movimiento enérgico para encender los vapores. Apaga el fuego y espolvorea culantro fresco.",
                    2, "Desglasado y flambeado", "Fuego máximo",
                    "El vinagre va por el borde caliente del wok, no sobre la carne: así desglasa el fondo y levanta el aroma.",
                    90,
                    listOf(si("3 cdas. sillao", "salsa de soya oscura"), si("2 cdas. vinagre tinto", "para desglasar"), si("Culantro", "fresco picado"))
                ),
                step(
                    "El Plato Criollo",
                    "Sirve el salteado inmediatamente sobre las papas amarillas recién fritas y acompaña con arroz con choclo. El plato se arma en el último segundo para que las papas conserven su crocante.",
                    1, "Emplatado", "Sin fuego",
                    "Sirve en plato hondo para que los jugos del wok lleguen a la mesa.",
                    30,
                    listOf(si("Papas amarillas fritas", "recién hechas"), si("Arroz con choclo", "caliente"))
                )
            ),
            chefQuote = "Usa el fuego al máximo absoluto y no amontones la carne en el wok. Saltéala por tandas rápidas para que dore y caramelice, en lugar de hervir en sus jugos."
        ),
        Recipe(
            id = "pisco-sour",
            name = "Pisco Sour Catedral",
            region = Region.COSTA,
            category = RecipeCategory.BEBIDA,
            originLine = "Costa • Bar Limeño",
            description = "El cóctel bandera del Perú: pisco quebranta, limón sutil, jarabe de goma y clara de huevo batida hasta lograr una espuma densa.",
            imageRes = R.drawable.pisco_sour,
            prepMinutes = 10,
            portions = 4,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 610,
            calories = 240,
            proteinGrams = 1,
            carbsGrams = 18,
            badge = "Cóctel Bandera",
            regionTag = "Costa",
            tags = listOf("Cóctel", "Clásico"),
            ingredients = listOf(
                ing("Pisco quebranta", 8.0, "oz", "Abarrotes"),
                ing("Jugo de limón sutil", 4.0, "oz", "Verdulería"),
                ing("Jarabe de goma", 4.0, "oz", "Abarrotes"),
                ing("Clara de huevo", 1.0, "unid.", "Lácteos"),
                ing("Amargo de angostura", 3.0, "gotas", "Abarrotes"),
                ing("Hielo", 2.0, "tazas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Preparar la coctelera",
                    "Enfría la coctelera y añade el pisco, el jugo de limón, el jarabe de goma y la clara de huevo. Agrega hielo hasta la mitad.",
                    3, "Coctelería", "Sin fuego",
                    "Usa limón sutil recién exprimido; el limón tahití cambia por completo el perfil del cóctel.",
                    0,
                    listOf(si("8 oz pisco", "quebranta"), si("4 oz limón", "jugo colado"))
                ),
                step(
                    "Batir con energía",
                    "Agita en seco unos segundos y luego con hielo durante 12 segundos, con movimientos verticales y firmes, para formar una espuma fina y estable.",
                    4, "Shake", "Sin fuego",
                    "La clara debe estar a temperatura ambiente para montar mejor.",
                    0,
                    listOf(si("1 clara", "de huevo"), si("Hielo", "abundante"))
                ),
                step(
                    "Servir con angostura",
                    "Cuela el cóctel en copas frías y deja reposar la espuma. Añade tres gotas de amargo de angostura sobre la superficie y sirve de inmediato.",
                    3, "Emplatado", "Sin fuego",
                    "No remuevas después de poner la angostura: las gotas son parte del ritual.",
                    0,
                    listOf(si("Amargo de angostura", "3 gotas"), si("Copas frías", "para servir"))
                )
            ),
            chefQuote = "Un pisco sour se juzga por la espuma: densa, brillante y con las tres gotas de angostura intactas."
        ),
        Recipe(
            id = "chicha-morada",
            name = "Chicha Morada Casera",
            region = Region.COSTA,
            category = RecipeCategory.BEBIDA,
            originLine = "Costa • Casa Criolla",
            description = "Bebida morada de maíz, piña, canela y clavo, endulzada con chancaca y servida bien fría con limón.",
            imageRes = R.drawable.chicha_morada,
            prepMinutes = 40,
            portions = 6,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 940,
            calories = 180,
            proteinGrams = 1,
            carbsGrams = 45,
            badge = "Bebida Emblemática",
            regionTag = "Costa",
            tags = listOf("Sin alcohol", "Casera"),
            ingredients = listOf(
                ing("Maíz morado", 500.0, "g", "Abarrotes"),
                ing("Piña", 1.0, "unid.", "Verdulería", "Cáscara y pulpa"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 4.0, "unid.", "Abarrotes"),
                ing("Azúcar o chancaca", 1.0, "taza", "Abarrotes"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Agua", 2.0, "L", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Hervir el maíz",
                    "Hierve el maíz morado con las cáscaras de piña, canela y clavo en dos litros de agua durante 30 minutos, hasta que el líquido tome un color profundo.",
                    30, "Infusión", "Fuego medio",
                    "Rompe algunas corontas para liberar más color y sabor.",
                    1800,
                    listOf(si("500 g maíz morado", "con coronta"), si("Cáscara de piña", "bien lavada"))
                ),
                step(
                    "Endulzar y colar",
                    "Cuela la infusión, añade el azúcar o chancaca y remueve hasta disolver. Deja enfriar por completo.",
                    6, "Endulzado", "Fuego bajo",
                    "Endulza en caliente: la chancaca se integra mejor y luego ajustas el dulzor al enfriar.",
                    300,
                    listOf(si("1 taza azúcar", "o chancaca"), si("Canela y clavo", "colados"))
                ),
                step(
                    "Servir bien fría",
                    "Agrega el jugo de limón, mezcla y sirve con hielo y trozos de piña. La chicha morada se disfruta muy fría.",
                    4, "Servicio", "Sin fuego",
                    "Añade el limón solo al final para que no pierda frescura.",
                    0,
                    listOf(si("2 limones", "jugo fresco"), si("Hielo", "abundante"))
                )
            ),
            chefQuote = "El secreto está en la cáscara de piña y en dejar reposar la chicha hasta que el color sea casi negro."
        ),
        Recipe(
            id = "emoliente",
            name = "Emoliente Andino",
            region = Region.COSTA,
            category = RecipeCategory.BEBIDA,
            originLine = "Costa • Carreta Limeña",
            description = "Infusión caliente de cebada, linaza y hierbas medicinales, con limón y azúcar: el clásico refugio de las mañanas limeñas.",
            imageRes = R.drawable.emoliente,
            prepMinutes = 15,
            portions = 2,
            difficulty = "Fácil",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.7,
            reviewCount = 380,
            calories = 90,
            proteinGrams = 2,
            carbsGrams = 20,
            badge = "Tradición Callejera",
            regionTag = "Costa",
            tags = listOf("Caliente", "Herbal"),
            ingredients = listOf(
                ing("Cebada tostada", 3.0, "cdas.", "Abarrotes"),
                ing("Linaza", 2.0, "cdas.", "Abarrotes"),
                ing("Cola de caballo", 1.0, "puñado", "Hierbas y hojas"),
                ing("Alfalfa", 1.0, "puñado", "Hierbas y hojas"),
                ing("Jugo de limón", 2.0, "unid.", "Verdulería"),
                ing("Azúcar", 2.0, "cdas.", "Abarrotes"),
                ing("Agua", 1.0, "L", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Hervir las semillas",
                    "Hierve la cebada y la linaza en un litro de agua durante 10 minutos, moviendo de vez en cuando para que la linaza suelte su textura.",
                    10, "Infusión", "Fuego medio",
                    "La linaza debe hervir lo suficiente para espesar ligeramente la bebida.",
                    600,
                    listOf(si("3 cdas. cebada", "tostada"), si("2 cdas. linaza", "entera"))
                ),
                step(
                    "Añadir las hierbas",
                    "Agrega la cola de caballo y la alfalfa, apaga el fuego y deja reposar 3 minutos tapado para conservar los aromas.",
                    3, "Reposo", "Fuego apagado",
                    "No hiervas las hierbas frescas: el reposo conserva su fragancia.",
                    180,
                    listOf(si("Cola de caballo", "un puñado"), si("Alfalfa", "fresca"))
                ),
                step(
                    "Servir caliente",
                    "Cuela, endulza y añade el jugo de limón al momento. Sirve en vaso de vidrio bien caliente.",
                    2, "Servicio", "Sin fuego",
                    "El limón se añade al final para que no se cocine y pierda su acidez.",
                    0,
                    listOf(si("2 limones", "jugo fresco"), si("2 cdas. azúcar", "al gusto"))
                )
            ),
            chefQuote = "El emoliente se sirve humeante y se bebe a sorbos, mirando la ciudad despertar."
        ),
        Recipe(
            id = "suspiro-limena",
            name = "Suspiro a la Limeña",
            region = Region.COSTA,
            category = RecipeCategory.POSTRE,
            originLine = "Costa • Dulcería Limeña",
            description = "Manjar blanco aterciopelado coronado con merengue al oporto y un toque de canela: el postre más elegante de Lima.",
            imageRes = R.drawable.suspiro,
            prepMinutes = 45,
            portions = 6,
            difficulty = "Medio",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 523,
            calories = 380,
            proteinGrams = 6,
            carbsGrams = 58,
            badge = "Postre Tradicional",
            regionTag = "Costa",
            tags = listOf("Dulce", "Merengue"),
            ingredients = listOf(
                ing("Manjar blanco", 1.0, "taza", "Abarrotes"),
                ing("Leche evaporada", 1.0, "taza", "Lácteos"),
                ing("Yemas de huevo", 3.0, "unid.", "Lácteos"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Claras de huevo", 4.0, "unid.", "Lácteos"),
                ing("Oporto", 2.0, "cdas.", "Abarrotes"),
                ing("Canela molida", 1.0, "cdta.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocinar el manjar",
                    "Mezcla el manjar blanco, la leche evaporada y las yemas en una olla a fuego bajo. Cocina sin dejar de remover hasta que espese y se despegue del fondo.",
                    15, "Dulce base", "Fuego bajo",
                    "Mueve en forma de ocho para evitar grumos y no dejes que hierva.",
                    600,
                    listOf(si("1 taza manjar", "blanco"), si("3 yemas", "batidas"))
                ),
                step(
                    "Montar el merengue",
                    "Bate las claras con el azúcar a punto de nieve firme. Añade el oporto al final, con movimientos suaves para no bajar el volumen.",
                    12, "Merengue", "Sin fuego",
                    "El merengue está listo cuando forma picos firmes que no se doblan.",
                    0,
                    listOf(si("4 claras", "a temperatura ambiente"), si("2 cdas. oporto", "delicado"))
                ),
                step(
                    "Armar y enfriar",
                    "Sirve el manjar en copas, cubre con el merengue y espolvorea canela. Refrigera al menos 20 minutos antes de servir.",
                    18, "Emplatado", "Sin fuego",
                    "Un suspiro se sirve frío, con el merengue recién puesto.",
                    0,
                    listOf(si("Canela molida", "para espolvorear"), si("Copas", "bien frías"))
                )
            ),
            chefQuote = "El suspiro se llama así porque debe ser tan suave que se deshaga con un suspiro."
        ),
        Recipe(
            id = "picarones",
            name = "Picarones con Miel de Chancaca",
            region = Region.COSTA,
            category = RecipeCategory.POSTRE,
            originLine = "Costa • Feria Limeña",
            description = "Aros crujientes de zapallo y camote bañados en miel de chancaca con canela e higo, el dulce más querido de las ferias limeñas.",
            imageRes = R.drawable.picarones,
            prepMinutes = 60,
            portions = 6,
            difficulty = "Medio",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 487,
            calories = 420,
            proteinGrams = 5,
            carbsGrams = 62,
            badge = "Dulce Criollo",
            regionTag = "Costa",
            tags = listOf("Frito", "Chancaca"),
            ingredients = listOf(
                ing("Zapallo", 300.0, "g", "Verdulería"),
                ing("Camote", 200.0, "g", "Verdulería"),
                ing("Harina sin preparar", 300.0, "g", "Abarrotes"),
                ing("Levadura", 1.0, "cda.", "Abarrotes"),
                ing("Chancaca", 250.0, "g", "Abarrotes"),
                ing("Higos secos", 3.0, "unid.", "Abarrotes"),
                ing("Canela", 1.0, "rama", "Abarrotes"),
                ing("Aceite para freír", 1.0, "L", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocinar la masa",
                    "Sancocha el zapallo y el camote, haz un puré y mézclalo con la harina y la levadura. Deja fermentar hasta que duplique su volumen.",
                    25, "Masa", "Fuego medio",
                    "La masa debe quedar suave pero firme: si está muy aguada, añade harina cucharada a cucharada.",
                    900,
                    listOf(si("300 g zapallo", "sancochado"), si("300 g harina", "sin preparar"))
                ),
                step(
                    "Preparar la miel",
                    "Hierve la chancaca con agua, canela e higos hasta obtener una miel espesa y brillante. Cuela y reserva caliente.",
                    15, "Miel", "Fuego bajo",
                    "La miel debe cubrir la cuchara sin escurrirse de inmediato.",
                    600,
                    listOf(si("250 g chancaca", "en trozos"), si("3 higos secos", "para la miel"))
                ),
                step(
                    "Freír en forma de aro",
                    "Calienta el aceite a fuego medio. Con la mano enharinada forma aros y fríelos hasta que doren. Escurre y baña con la miel caliente.",
                    20, "Fritura", "Fuego medio",
                    "El aceite no debe estar demasiado caliente: los picarones deben cocinarse por dentro sin quemarse.",
                    1200,
                    listOf(si("Aceite", "para freír"), si("Miel de chancaca", "caliente"))
                )
            ),
            chefQuote = "Un picarón perfecto es crujiente por fuera, esponjoso por dentro y nada empalagoso."
        ),
        Recipe(
            id = "rocoto-relleno",
            name = "Rocoto Relleno Arequipeño",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Arequipa",
            description = "Emblemático ají rocoto blanqueado, relleno de picadillo de carne sazonada, maní tostado y cubierto con queso paria fundido.",
            imageRes = R.drawable.rocoto_relleno,
            prepMinutes = 50,
            portions = 4,
            difficulty = "Medio",
            spiceLevel = 2,
            spiceLabel = "Picante medio",
            rating = 4.9,
            reviewCount = 412,
            calories = 460,
            proteinGrams = 28,
            carbsGrams = 30,
            badge = "Horno Tradicional",
            regionTag = "Sierra Sur",
            tags = listOf("Arequipa Tradicional", "Picante medio"),
            ingredients = listOf(
                ing("Rocotos grandes", 4.0, "unid.", "Verdulería", "Sin venas"),
                ing("Carne de res molida", 400.0, "g", "Carnicería"),
                ing("Cebolla roja picada", 1.0, "unid.", "Verdulería"),
                ing("Maní tostado", 0.5, "taza", "Abarrotes"),
                ing("Queso paria", 200.0, "g", "Lácteos"),
                ing("Leche evaporada", 0.5, "taza", "Lácteos"),
                ing("Huevos", 2.0, "unid.", "Lácteos"),
                ing("Ají panca molido", 2.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Blanquear los rocotos",
                    "Corta la tapa de los rocotos, retira venas y semillas y hiérvelos tres veces cambiando el agua para moderar el picor. Escurre y reserva.",
                    15, "Blanqueado", "Fuego medio",
                    "No los hiervas demasiado: deben quedar firmes para sostener el relleno.",
                    900,
                    listOf(si("4 rocotos", "sin venas"), si("Agua", "para tres cambios"))
                ),
                step(
                    "Preparar el relleno",
                    "Sofríe la cebolla, añade la carne y el ají panca. Cocina hasta que la carne esté jugosa y suma el maní tostado picado.",
                    15, "Picadillo", "Fuego medio",
                    "El maní aporta textura y profundidad; pícalo grueso para sentirlo.",
                    600,
                    listOf(si("400 g carne", "molida"), si("1/2 taza maní", "tostado y picado"))
                ),
                step(
                    "Rellenar y gratinar",
                    "Rellena los rocotos, coloca una lámina de queso y baña con la mezcla de huevo y leche. Hornea a 180 °C hasta que el queso dore.",
                    20, "Horneado", "Horno a 180 °C",
                    "Deja reposar cinco minutos fuera del horno antes de servir: el relleno se asienta.",
                    1200,
                    listOf(si("200 g queso paria", "en láminas"), si("2 huevos + leche", "para ligar"))
                )
            ),
            chefQuote = "El rocoto se respeta: tres aguas para domar el picor y un queso paria que lo abrace."
        ),
        Recipe(
            id = "pachamanca-olla",
            name = "Pachamanca a la Olla",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Valle Sagrado",
            description = "Tributo a la madre tierra adaptado a cocina casera, con aromas profundos de chincho, marinado andino, papas nativas y choclo tierno.",
            imageRes = R.drawable.pachamanca,
            prepMinutes = 80,
            portions = 6,
            difficulty = "Dificultad media",
            spiceLevel = 1,
            spiceLabel = "Aromático",
            rating = 4.8,
            reviewCount = 365,
            calories = 520,
            proteinGrams = 35,
            carbsGrams = 48,
            badge = "Sabores Ancestrales",
            regionTag = "Tradicional",
            tags = listOf("Hierbas sagradas", "Fogón de barro"),
            ingredients = listOf(
                ing("Carne de res en presas", 1.0, "kg", "Carnicería"),
                ing("Pollo en presas", 1.0, "kg", "Carnicería"),
                ing("Papas nativas", 1.0, "kg", "Verdulería"),
                ing("Choclo tierno", 4.0, "unid.", "Verdulería"),
                ing("Habas verdes", 300.0, "g", "Verdulería"),
                ing("Chincho", 1.0, "puñado", "Hierbas y hojas"),
                ing("Huacatay", 1.0, "puñado", "Hierbas y hojas"),
                ing("Sal de Maras", 2.0, "cdas.", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Marinar las carnes",
                    "Muele chincho, huacatay, sal y ají panca. Marina las carnes al menos 30 minutos para que absorban el aroma andino.",
                    30, "Marinado", "Sin fuego",
                    "El chincho es el alma de la pachamanca; si no lo encuentras, refuerza con huacatay.",
                    0,
                    listOf(si("1 kg res", "en presas"), si("Chincho y huacatay", "molidos"))
                ),
                step(
                    "Armar la olla",
                    "En una olla gruesa coloca piedras limpias al fondo, luego las carnes, las papas, el choclo y las habas. Cubre con hojas de plátano o bijao.",
                    20, "Armado", "Fuego medio",
                    "Las piedras acumulan calor y reproducen el efecto del horno de tierra.",
                    1200,
                    listOf(si("Papas nativas", "lavadas"), si("Hojas de bijao", "para cubrir"))
                ),
                step(
                    "Cocinar a fuego lento",
                    "Tapa la olla y cocina a fuego medio durante 40 minutos. Revisa que no se seque y sirve todo junto, con las hierbas frescas.",
                    40, "Cocción lenta", "Fuego medio",
                    "No destapes a cada rato: el vapor es parte de la cocción.",
                    2400,
                    listOf(si("Habas y choclo", "tiernos"), si("Hierbas frescas", "para servir"))
                )
            ),
            chefQuote = "La pachamanca no se apura: se cocina con paciencia y se comparte como un ritual."
        ),
        Recipe(
            id = "trucha-frita",
            name = "Trucha Frita con Papas Nativas",
            region = Region.SIERRA,
            category = RecipeCategory.FONDO,
            originLine = "Sierra • Junín",
            description = "Trucha arcoíris dorada al punto crocante, acompañada de sarza criolla de cebolla roja y papas huayro sancochadas.",
            imageRes = R.drawable.trucha_frita,
            prepMinutes = 30,
            portions = 2,
            difficulty = "Fácil",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.7,
            reviewCount = 298,
            calories = 410,
            proteinGrams = 36,
            carbsGrams = 24,
            badge = "Ríos de Altura",
            regionTag = "Rápido y fresco",
            tags = listOf("Lago Titicaca & Junín", "Fácil"),
            ingredients = listOf(
                ing("Truchas frescas", 2.0, "unid.", "Pescadería"),
                ing("Papas nativas", 500.0, "g", "Verdulería"),
                ing("Cebolla roja", 1.0, "unid.", "Verdulería"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Harina", 0.5, "taza", "Abarrotes"),
                ing("Aceite", 1.0, "taza", "Abarrotes"),
                ing("Sal y pimienta", null, "al gusto", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Preparar la trucha",
                    "Limpia la trucha, sazona con sal, pimienta y limón y pásala por harina, sacudiendo el exceso.",
                    10, "Preparación", "Sin fuego",
                    "Seca bien la trucha antes de enharinar para que la piel quede crocante.",
                    0,
                    listOf(si("2 truchas", "limpias"), si("1/2 taza harina", "para enharinar"))
                ),
                step(
                    "Freír dorada",
                    "Calienta el aceite a fuego medio alto y fríe la trucha por ambos lados hasta que esté dorada y crocante. Escurre sobre papel.",
                    10, "Fritura", "Fuego medio alto",
                    "No la muevas durante los primeros minutos: la piel se desprende si la volteas antes de tiempo.",
                    600,
                    listOf(si("Aceite", "para freír"), si("Trucha", "dorada por ambos lados"))
                ),
                step(
                    "Preparar la sarza y servir",
                    "Sancocha las papas, corta la cebolla en pluma y macérala con limón y sal. Sirve la trucha con papas y sarza fresca.",
                    10, "Emplatado", "Fuego medio",
                    "La sarza se prepara al final para que la cebolla conserve su crocante.",
                    600,
                    listOf(si("1 cebolla roja", "en pluma"), si("500 g papas", "sancochadas"))
                )
            ),
            chefQuote = "Una trucha fresca solo necesita limón, harina y un aceite bien caliente."
        ),
        Recipe(
            id = "chicha-jora",
            name = "Chicha de Jora Ancestral",
            region = Region.SIERRA,
            category = RecipeCategory.BEBIDA,
            originLine = "Sierra • Picantería",
            description = "El néctar sagrado de los Incas: bebida fermentada de maíz amarillo germinado, clavo de olor y chancaca andina.",
            imageRes = R.drawable.chicha_jora,
            prepMinutes = 40,
            portions = 6,
            difficulty = "Maíz malteado",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.8,
            reviewCount = 244,
            calories = 160,
            proteinGrams = 2,
            carbsGrams = 38,
            badge = "Fermentación",
            regionTag = "Bebida Sagrada",
            tags = listOf("Tradición de Picantería", "Maíz malteado"),
            ingredients = listOf(
                ing("Maíz jora", 500.0, "g", "Abarrotes"),
                ing("Chancaca", 250.0, "g", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Agua", 3.0, "L", "Abarrotes"),
                ing("Limón", 1.0, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Hervir la jora",
                    "Hierve el maíz jora con la canela y el clavo durante 30 minutos, hasta que el líquido tome color ámbar y aroma profundo.",
                    30, "Decocción", "Fuego medio",
                    "La jora ya viene germinada y seca: solo necesita liberar su sabor en el agua.",
                    1800,
                    listOf(si("500 g maíz jora", "germinado"), si("2 ramas canela", "y clavo"))
                ),
                step(
                    "Endulzar y colar",
                    "Cuela el líquido, añade la chancaca y remueve hasta disolver. Deja enfriar a temperatura tibia antes de fermentar.",
                    8, "Endulzado", "Fuego bajo",
                    "La chancaca aporta cuerpo y alimento a la fermentación.",
                    300,
                    listOf(si("250 g chancaca", "en trozos"), si("Agua", "de la cocción"))
                ),
                step(
                    "Fermentar y servir",
                    "Deja reposar en un recipiente de barro de 1 a 2 días, según el clima. Sirve bien fría con unas gotas de limón.",
                    2, "Fermentación", "Sin fuego",
                    "En climas cálidos la fermentación avanza más rápido: prueba a las 24 horas.",
                    0,
                    listOf(si("Recipiente de barro", "tapado"), si("1 limón", "para servir"))
                )
            ),
            chefQuote = "La chicha de jora se fermenta con paciencia: cada día le da un carácter distinto."
        ),
        Recipe(
            id = "mate-muna",
            name = "Mate de Muña Digestivo",
            region = Region.SIERRA,
            category = RecipeCategory.BEBIDA,
            originLine = "Sierra • Botica Natural",
            description = "Hierba silvestre de fragancia mentolada, reconfortante e ideal para el mal de altura o para cerrar un banquete andino.",
            imageRes = R.drawable.mate_muna,
            prepMinutes = 10,
            portions = 2,
            difficulty = "Ultra rápido",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 189,
            calories = 15,
            proteinGrams = 0,
            carbsGrams = 3,
            badge = "Digestivo",
            regionTag = "Infusión serrana",
            tags = listOf("Botica natural", "Ultra rápido"),
            ingredients = listOf(
                ing("Muña fresca", 1.0, "puñado", "Hierbas y hojas"),
                ing("Agua", 2.0, "tazas", "Abarrotes"),
                ing("Miel", 1.0, "cda.", "Abarrotes"),
                ing("Limón", 0.5, "unid.", "Verdulería")
            ),
            steps = listOf(
                step(
                    "Calentar el agua",
                    "Lleva el agua a punto de ebullición y retírala del fuego antes de añadir la muña para no amargar la infusión.",
                    4, "Infusión", "Fuego alto",
                    "La muña es delicada: el agua debe estar caliente, no hirviendo a borbotones.",
                    240,
                    listOf(si("2 tazas agua", "caliente"), si("1 puñado muña", "fresca"))
                ),
                step(
                    "Infusionar",
                    "Añade la muña, tapa y deja reposar de 3 a 5 minutos hasta que suelte su aroma mentolado.",
                    5, "Reposo", "Fuego apagado",
                    "Tapar conserva los aceites esenciales que dan el aroma característico.",
                    300,
                    listOf(si("Muña fresca", "recién lavada"))
                ),
                step(
                    "Endulzar y servir",
                    "Cuela, endulza con miel y añade unas gotas de limón. Sirve caliente en tazas de cerámica.",
                    1, "Servicio", "Sin fuego",
                    "La miel se añade cuando el mate está tibio para no perder sus propiedades.",
                    0,
                    listOf(si("1 cda. miel", "de abeja"), si("1/2 limón", "jugo"))
                )
            ),
            chefQuote = "Un mate de muña bien tapado es el mejor abrazo para el mal de altura."
        ),
        Recipe(
            id = "queso-helado",
            name = "Queso Helado de Arequipa",
            region = Region.SIERRA,
            category = RecipeCategory.POSTRE,
            originLine = "Sierra • Arequipa",
            description = "Artesanal postre helado sin queso real: leche fresca reducida, coco rallado, canela aromatizada y clavo de olor.",
            imageRes = R.drawable.queso_helado_arequipa,
            prepMinutes = 35,
            portions = 4,
            difficulty = "Canela & coco",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 5.0,
            reviewCount = 203,
            calories = 290,
            proteinGrams = 7,
            carbsGrams = 38,
            badge = "Arequipa",
            regionTag = "Postre Insignia",
            tags = listOf("Dulcería colonial", "Sin horno"),
            ingredients = listOf(
                ing("Leche fresca", 2.0, "L", "Lácteos"),
                ing("Azúcar", 1.0, "taza", "Abarrotes"),
                ing("Coco rallado", 0.5, "taza", "Abarrotes"),
                ing("Canela", 2.0, "ramas", "Abarrotes"),
                ing("Clavo de olor", 3.0, "unid.", "Abarrotes"),
                ing("Yemas de huevo", 3.0, "unid.", "Lácteos")
            ),
            steps = listOf(
                step(
                    "Reducir la leche",
                    "Hierve la leche con canela y clavo a fuego bajo hasta reducirla a la mitad, moviendo para que no se pegue.",
                    20, "Reducción", "Fuego bajo",
                    "La reducción es lo que da cuerpo al queso helado: no la acortes.",
                    1200,
                    listOf(si("2 L leche", "fresca"), si("Canela y clavo", "aromáticos"))
                ),
                step(
                    "Integrar las yemas",
                    "Retira las especias, añade el azúcar y el coco. Templa las yemas con un poco de leche caliente y vuelve a la olla sin dejar de mover.",
                    8, "Cremado", "Fuego bajo",
                    "Templa las yemas antes de incorporarlas para que no se cuajen.",
                    300,
                    listOf(si("1 taza azúcar", "al gusto"), si("1/2 taza coco", "rallado"))
                ),
                step(
                    "Congelar y servir",
                    "Deja enfriar, vierte en moldes y congela hasta que tome cuerpo. Sirve con canela en polvo y coco rallado.",
                    7, "Congelado", "Sin fuego",
                    "Mueve la mezcla cada hora si buscas una textura más cremosa.",
                    0,
                    listOf(si("Canela en polvo", "para decorar"), si("Coco", "rallado extra"))
                )
            ),
            chefQuote = "El queso helado no lleva queso: lleva leche, paciencia y la dulzura de Arequipa."
        ),
        Recipe(
            id = "juane",
            name = "Juane de Arroz con Gallina",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • San Juan",
            description = "Tradicional de San Juan cocido al vapor en aromática hoja de bijao, con gallina de chacra, arroz y especias amazónicas.",
            imageRes = R.drawable.juane,
            prepMinutes = 105,
            portions = 4,
            difficulty = "Medio",
            spiceLevel = 2,
            spiceLabel = "Medio picor",
            rating = 4.9,
            reviewCount = 320,
            calories = 540,
            proteinGrams = 32,
            carbsGrams = 55,
            badge = "Emblemático",
            regionTag = "Fiesta de San Juan",
            tags = listOf("Hoja de bijao", "Gallina de chacra"),
            ingredients = listOf(
                ing("Gallina de chacra en presas", 1.0, "kg", "Carnicería"),
                ing("Arroz", 500.0, "g", "Abarrotes"),
                ing("Huevos", 4.0, "unid.", "Lácteos"),
                ing("Hojas de bijao", 8.0, "unid.", "Hierbas y hojas"),
                ing("Cúrcuma / guisador", 1.0, "cda.", "Abarrotes"),
                ing("Ají charapita", 1.0, "puñado", "Frutas y verduras"),
                ing("Caldo de gallina", 3.0, "tazas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocinar la gallina",
                    "Sancocha la gallina con cúrcuma, sal y ají charapita hasta que la carne esté tierna. Reserva el caldo aromático.",
                    45, "Cocción", "Fuego medio",
                    "La gallina de chacra necesita tiempo: cocínala hasta que la carne se separe del hueso.",
                    2400,
                    listOf(si("1 kg gallina", "en presas"), si("Cúrcuma", "para el color"))
                ),
                step(
                    "Preparar el arroz",
                    "Sofríe el arroz en la grasa de la gallina, añade el caldo y cocina hasta que esté al dente. Mezcla con las presas y huevos duros.",
                    20, "Arroz", "Fuego medio",
                    "El arroz debe quedar húmedo pero cocido: el vapor terminará la cocción.",
                    900,
                    listOf(si("500 g arroz", "graneadito"), si("4 huevos", "duros"))
                ),
                step(
                    "Envolver y cocer al vapor",
                    "Envueltas en hojas de bijao humedecidas, forma los juanes y cuécelos al vapor 40 minutos para que las hojas perfumen el relleno.",
                    40, "Al vapor", "Fuego medio",
                    "Pasa las hojas por agua caliente antes de envolver: así no se rompen y sueltan su aroma.",
                    2400,
                    listOf(si("8 hojas de bijao", "amortiguadas"), si("Ají charapita", "para acompañar"))
                )
            ),
            chefQuote = "El juane se sirve en su hoja: al abrirlo, la mesa huele a fiesta de San Juan."
        ),
        Recipe(
            id = "tacacho",
            name = "Tacacho con Cecina y Chorizo",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Tarapoto",
            description = "Plátano bellaco asado al carbón machacado con manteca y cecina ahumada de Tarapoto, servido con chorizo y ají charapita.",
            imageRes = R.drawable.tacacho,
            prepMinutes = 35,
            portions = 2,
            difficulty = "Fácil",
            spiceLevel = 2,
            spiceLabel = "Picor amazónico",
            rating = 4.8,
            reviewCount = 280,
            calories = 610,
            proteinGrams = 30,
            carbsGrams = 52,
            badge = "Ahumado Tarapoto",
            regionTag = "Ahumado Tarapoto",
            tags = listOf("Cecina de cerdo", "Ají charapita"),
            ingredients = listOf(
                ing("Plátano bellaco verde", 4.0, "unid.", "Verdulería"),
                ing("Cecina de cerdo", 300.0, "g", "Carnicería"),
                ing("Chorizo artesanal", 2.0, "unid.", "Carnicería"),
                ing("Manteca de cerdo", 3.0, "cdas.", "Abarrotes"),
                ing("Ají charapita", 1.0, "puñado", "Frutas y verduras"),
                ing("Sal", null, "al gusto", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Asar los plátanos",
                    "Asa los plátanos bellaco verdes al carbón o en sartén seca hasta que la cáscara se abrase y la pulpa esté suave.",
                    15, "Asado", "Fuego medio",
                    "El plátano debe estar bien maduro por dentro y ligeramente ahumado por fuera.",
                    900,
                    listOf(si("4 plátanos", "verdes"), si("Carbón", "o sartén seca"))
                ),
                step(
                    "Machacar con manteca",
                    "Pela los plátanos, machácalos con la manteca y sal hasta obtener una masa rústica. Mezcla la cecina picada y el chorizo dorado.",
                    10, "Machacado", "Fuego medio",
                    "Machaca mientras están calientes: la manteca se integra mejor.",
                    600,
                    listOf(si("3 cdas. manteca", "de cerdo"), si("300 g cecina", "ahumada"))
                ),
                step(
                    "Servir con ají charapita",
                    "Forma bolas de tacacho y sírvelas con chorizo dorado y un ajicito de charapita fresco.",
                    10, "Emplatado", "Fuego medio",
                    "El ají charapita es pequeño pero potente: dosifica según tu gusto.",
                    300,
                    listOf(si("Ají charapita", "para el ajicito"), si("Chorizo", "dorado"))
                )
            ),
            chefQuote = "El tacacho sabe a humo, a plátano y a selva: no necesita nada más."
        ),
        Recipe(
            id = "inchicapi",
            name = "Inchicapi de Gallina con Maní",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Loreto",
            description = "Sopa ancestral cremosa elaborada a base de maní molido, maíz suave, sachaculantro y yuca.",
            imageRes = R.drawable.inchicapi,
            prepMinutes = 50,
            portions = 4,
            difficulty = "Reconfortante",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.7,
            reviewCount = 195,
            calories = 480,
            proteinGrams = 31,
            carbsGrams = 42,
            badge = "Reconfortante",
            regionTag = "Sopa Ancestral",
            tags = listOf("Maní tostado", "Sachaculantro"),
            ingredients = listOf(
                ing("Gallina en presas", 1.0, "kg", "Carnicería"),
                ing("Maní tostado", 1.0, "taza", "Abarrotes"),
                ing("Maíz suave", 2.0, "unid.", "Verdulería"),
                ing("Yuca", 300.0, "g", "Verdulería"),
                ing("Sachaculantro", 1.0, "puñado", "Hierbas y hojas"),
                ing("Ajo", 2.0, "dientes", "Verdulería"),
                ing("Caldo de gallina", 2.0, "L", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Cocinar la gallina",
                    "Sancocha la gallina con ajo y sal en dos litros de agua hasta que esté tierna. Reserva el caldo y deshilacha parte de la carne.",
                    30, "Cocción", "Fuego medio",
                    "El caldo es la base de la sopa: no lo deseches.",
                    1800,
                    listOf(si("1 kg gallina", "en presas"), si("2 dientes ajo", "machacados"))
                ),
                step(
                    "Licuar el maní",
                    "Tuesta y muele el maní, luego licúalo con un poco de caldo hasta obtener una crema espesa y aromática.",
                    10, "Cremado", "Sin fuego",
                    "El maní tostado debe estar frío antes de licuar para evitar que amargue.",
                    300,
                    listOf(si("1 taza maní", "tostado"), si("Caldo", "para licuar"))
                ),
                step(
                    "Servir con yuca y sachaculantro",
                    "Añade la yuca y el maíz al caldo, cocina hasta que ablanden y suma la crema de maní. Sirve con sachaculantro fresco.",
                    10, "Servicio", "Fuego medio",
                    "Agrega el sachaculantro al final para que su aroma no se pierda.",
                    600,
                    listOf(si("300 g yuca", "en trozos"), si("Sachaculantro", "fresco"))
                )
            ),
            chefQuote = "El inchicapi es una sopa de abuela: espesa, cálida y con aroma de selva."
        ),
        Recipe(
            id = "patarashca",
            name = "Patarashca de Pescado Amazónico",
            region = Region.SELVA,
            category = RecipeCategory.FONDO,
            originLine = "Selva • Río Amazonas",
            description = "Pescado de río sazonado con hierbas silvestres y asado a las brasas, envuelto en hoja de bijao.",
            imageRes = R.drawable.patarashca,
            prepMinutes = 40,
            portions = 3,
            difficulty = "Paiche / Doncella",
            spiceLevel = 1,
            spiceLabel = "Suave",
            rating = 4.8,
            reviewCount = 214,
            calories = 390,
            proteinGrams = 34,
            carbsGrams = 18,
            badge = "Pescado de Río",
            regionTag = "Pescado de Río",
            tags = listOf("Hoja de bijao", "Sachaculantro"),
            ingredients = listOf(
                ing("Pescado doncella o paiche", 1.0, "kg", "Pescadería"),
                ing("Hojas de bijao", 4.0, "unid.", "Hierbas y hojas"),
                ing("Sachaculantro", 1.0, "puñado", "Hierbas y hojas"),
                ing("Ají charapita", 1.0, "puñado", "Frutas y verduras"),
                ing("Limón", 2.0, "unid.", "Verdulería"),
                ing("Sal", null, "al gusto", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Sazonar el pescado",
                    "Sazona el pescado con limón, sal, ají charapita picado y sachaculantro. Deja reposar 10 minutos para que tome los aromas.",
                    10, "Marinado", "Sin fuego",
                    "El pescado de río es delicado: no lo maceres demasiado tiempo.",
                    600,
                    listOf(si("1 kg pescado", "de río"), si("Sachaculantro", "picado"))
                ),
                step(
                    "Envolver en bijao",
                    "Coloca el pescado sobre hojas de bijao amortiguadas, cubre con las hierbas y cierra el paquete con cuidado.",
                    8, "Envoltura", "Sin fuego",
                    "Aprieta el paquete para que el vapor se concentre y cocine el pescado.",
                    0,
                    listOf(si("4 hojas de bijao", "amortiguadas"), si("Ají charapita", "al gusto"))
                ),
                step(
                    "Asar a las brasas",
                    "Asa el paquete sobre brasas medianas de 15 a 20 minutos, volteando una vez. Abre en la mesa y sirve con plátano asado.",
                    15, "Brasas", "Fuego medio",
                    "Si no tienes brasas, cocina en horno a 200 °C por 20 minutos.",
                    900,
                    listOf(si("Brasas", "medianas"), si("Plátano asado", "para acompañar"))
                )
            ),
            chefQuote = "La patarashca se abre en la mesa: el aroma a bijao y río es parte del plato."
        ),
        Recipe(
            id = "camu-camu",
            name = "Refresco de Camu Camu y Aguaje",
            region = Region.SELVA,
            category = RecipeCategory.BEBIDA,
            originLine = "Selva • Frutos Silvestres",
            description = "Dúo de néctares refrescantes repletos de antioxidantes y frescura pura de la selva virgen.",
            imageRes = R.drawable.camu_camu,
            prepMinutes = 15,
            portions = 6,
            difficulty = "Frío / Helado",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 342,
            calories = 70,
            proteinGrams = 1,
            carbsGrams = 16,
            badge = "Vitamina C Pura",
            regionTag = "Súper Frutos",
            tags = listOf("Camu camu", "Aguaje"),
            ingredients = listOf(
                ing("Pulpa de camu camu", 1.0, "taza", "Frutas y verduras"),
                ing("Aguaje fresco", 3.0, "unid.", "Frutas y verduras"),
                ing("Agua", 1.0, "L", "Abarrotes"),
                ing("Miel", 3.0, "cdas.", "Abarrotes"),
                ing("Hielo", 2.0, "tazas", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Licuar los frutos",
                    "Licúa la pulpa de camu camu con el aguaje pelado y un poco de agua hasta obtener un jugo homogéneo.",
                    7, "Licuado", "Sin fuego",
                    "El camu camu es muy ácido: empieza con poca pulpa y ajusta.",
                    420,
                    listOf(si("1 taza camu camu", "pulpa"), si("3 aguajes", "frescos"))
                ),
                step(
                    "Colar y endulzar",
                    "Cuela la mezcla, añade el resto del agua y endulza con miel al gusto. Mezcla bien.",
                    5, "Colado", "Sin fuego",
                    "La miel silvestre aporta un aroma floral que combina con el aguaje.",
                    300,
                    listOf(si("3 cdas. miel", "al gusto"), si("1 L agua", "bien fría"))
                ),
                step(
                    "Servir helado",
                    "Sirve sobre hielo abundante y decora con una rodaja de aguaje. Bebe de inmediato.",
                    3, "Servicio", "Sin fuego",
                    "Este refresco no se guarda: la vitamina C se pierde con el tiempo.",
                    0,
                    listOf(si("2 tazas hielo", "abundante"), si("Aguaje", "para decorar"))
                )
            ),
            chefQuote = "Un vaso de camu camu es la selva entera en un sorbo: ácido, fresco y lleno de vida."
        ),
        Recipe(
            id = "mousse-copoazu",
            name = "Mousse de Copoazú y Castañas",
            region = Region.SELVA,
            category = RecipeCategory.POSTRE,
            originLine = "Selva • Madre de Dios",
            description = "Postre exótico y cremoso a base de cacao blanco amazónico con castañas de Madre de Dios.",
            imageRes = R.drawable.mousse_copoazu,
            prepMinutes = 30,
            portions = 4,
            difficulty = "Sin cocción",
            spiceLevel = 0,
            spiceLabel = "Sin picor",
            rating = 4.9,
            reviewCount = 168,
            calories = 310,
            proteinGrams = 6,
            carbsGrams = 34,
            badge = "Amazónico",
            regionTag = "Cacao Blanco",
            tags = listOf("Copoazú", "Castañas"),
            ingredients = listOf(
                ing("Pulpa de copoazú", 1.0, "taza", "Frutas y verduras"),
                ing("Crema batida", 1.0, "taza", "Lácteos"),
                ing("Castañas amazónicas", 0.5, "taza", "Abarrotes"),
                ing("Leche condensada", 0.5, "taza", "Lácteos"),
                ing("Chocolate blanco", 100.0, "g", "Abarrotes")
            ),
            steps = listOf(
                step(
                    "Derretir el chocolate",
                    "Derrite el chocolate blanco a baño maría y mezcla con la pulpa de copoazú hasta integrar.",
                    6, "Baño maría", "Fuego bajo",
                    "No dejes que el chocolate blanco se sobrecaliente: se corta con facilidad.",
                    300,
                    listOf(si("100 g chocolate", "blanco"), si("1 taza copoazú", "pulpa"))
                ),
                step(
                    "Montar la crema",
                    "Bate la crema hasta que forme picos suaves e incorpórala con movimientos envolventes junto a la leche condensada.",
                    8, "Montado", "Sin fuego",
                    "Incorpora la crema en tres partes para no perder el aire.",
                    0,
                    listOf(si("1 taza crema", "bien fría"), si("1/2 taza leche", "condensada"))
                ),
                step(
                    "Refrigerar y servir",
                    "Reparte en copas, cubre con castañas tostadas picadas y refrigera al menos 2 horas antes de servir.",
                    16, "Refrigerado", "Sin fuego",
                    "El mousse necesita frío para cuajar: mínimo dos horas.",
                    0,
                    listOf(si("1/2 taza castañas", "tostadas"), si("Copas", "bien frías"))
                )
            ),
            chefQuote = "El copoazú es el primo blanco del cacao: cremoso, suave y con un aroma que sabe a selva."
        )
    ) + RecetasExtendidas.all

    val marketSeed: List<MarketItem> = listOf(
        MarketItem(
            id = "m-1",
            recipeId = "lomo-saltado",
            recipeName = "Lomo Saltado Criollo",
            recipeImageRes = R.drawable.lomo_market,
            label = "600 g Lomo fino de res",
            aisle = "Carnicería",
            note = "En despensa",
            checked = true,
            estimatedPrice = 28.50,
            weightKg = 0.6
        ),
        MarketItem(
            id = "m-2",
            recipeId = "lomo-saltado",
            recipeName = "Lomo Saltado Criollo",
            recipeImageRes = R.drawable.lomo_market,
            label = "2 unid. Cebollas rojas medianas",
            aisle = "Verdulería",
            note = "Corte pluma",
            checked = false,
            estimatedPrice = 2.50,
            weightKg = 0.2
        ),
        MarketItem(
            id = "m-3",
            recipeId = "lomo-saltado",
            recipeName = "Lomo Saltado Criollo",
            recipeImageRes = R.drawable.lomo_market,
            label = "3 unid. Tomates maduros",
            aisle = "Verdulería",
            note = "En gajos",
            checked = false,
            estimatedPrice = 3.90,
            weightKg = 0.3
        ),
        MarketItem(
            id = "m-4",
            recipeId = "lomo-saltado",
            recipeName = "Lomo Saltado Criollo",
            recipeImageRes = R.drawable.lomo_market,
            label = "1 unid. Ají amarillo fresco",
            aisle = "Verdulería",
            note = "Sin venas",
            checked = false,
            estimatedPrice = 1.20,
            weightKg = 0.05
        ),
        MarketItem(
            id = "m-5",
            recipeId = "lomo-saltado",
            recipeName = "Lomo Saltado Criollo",
            recipeImageRes = R.drawable.lomo_market,
            label = "3 cdas. Sillao oscuro",
            aisle = "Abarrotes",
            note = "En despensa",
            checked = true,
            estimatedPrice = 0.0,
            weightKg = 0.0
        ),
        MarketItem(
            id = "m-6",
            recipeId = "juane",
            recipeName = "Juane de Gallina",
            recipeImageRes = R.drawable.juane_market,
            label = "1 kg Gallina de chacra en presas",
            aisle = "Carnicería",
            note = "Con menudencia",
            checked = false,
            estimatedPrice = 8.40,
            weightKg = 1.0
        ),
        MarketItem(
            id = "m-7",
            recipeId = "juane",
            recipeName = "Juane de Gallina",
            recipeImageRes = R.drawable.juane_market,
            label = "8 unid. Hojas de bijao frescas",
            aisle = "Hierbas y hojas",
            note = "Amortiguadas",
            checked = false,
            estimatedPrice = 2.00,
            weightKg = 0.05
        ),
        MarketItem(
            id = "m-8",
            recipeId = "juane",
            recipeName = "Juane de Gallina",
            recipeImageRes = R.drawable.juane_market,
            label = "Ají charapita y cocona",
            aisle = "Frutas y verduras",
            note = "Para ajicito",
            checked = true,
            estimatedPrice = 1.50,
            weightKg = 0.0
        )
    )
}
