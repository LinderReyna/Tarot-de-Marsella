package com.jossemar.tarotdemarsella.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jossemar.tarotdemarsella.model.Arcane

class GameViewModel: ViewModel() {
    private val cards = MutableLiveData<List<Arcane>>().apply {
        value = listOf ( Arcane(0, "el loco", "Para El Loco la vida es una aventura, desafía a explorar nuevos terrenos, a pasar a la acción, a buscar nuestra verdad y vivir la vida como una aventura. Necesitamos hacer cosas nuevas en la vida, llenarnos de conocimiento.", "el_loco"),
            Arcane(1,"el mago","El Mago nos habla del papel de la comunicación, del desarrollo de la capacidad para hacernos entender con claridad. Evolucionaremos mediante el ejercicio de la mente y el uso de la razón.","el_mago"),
            Arcane(2,"la sacerdotisa","La Sacerdotisa es sinónimo de virginidad o pureza (La Virgen María, la diosa Isis). En este caso, la virginidad es el símbolo de la pureza de pensamientos, sentimientos, deseos, miradas, palabras y gestos.","la_sacerdotisa"),
            Arcane(3,"la emperatriz","La Emperatriz nos enseña a amar. Es el amor el que hace que nuestra vida se desarrolle y crezca. Su único fin es amar por el puro placer de amar. El amor perfecto actúa sin pensar en el amor.","la_emperatriz"),
            Arcane(4,"el emperador","El Emperador representa el liderazgo y la autodeterminación. Nos muestra cómo desarrollar esas cualidades en la vida.","el_emperador"),
            Arcane(5,"el sumo sacerdote","El Sumo Sacerdote o Hierofante simbolizaba la religión a través de la imagen del Papa. La religión es el reconocimiento del vínculo existente entre el hombre y Dios.","el_sumo_sacerdote"),
            Arcane(6,"los enamorados","En la carta de los Enamorados encontramos toda la dimensión del amor, la atracción, el deseo y la sexualidad. ¡Es una gran carta! Esta carta nos induce a contemplar a las personas que nos atraen y a las que se sienten atraídas por nosotros.","los_enamorados"),
            Arcane(7,"el carro","El Carro Ha derrotado a sus enemigos y ha conquistado vastas y nuevas tierras. Este es el espíritu del carro. La carta 7 representa las victorias que son posibles a través de la fuerza de voluntad y el autodominio.","el_carro"),
            Arcane(8,"la justicia","La Justicia. Ella tiene las escalas de igualdad y juicio imparcial en una mano, y la espada de decisión en la otra.","la_justicia"),
            Arcane(9,"el hermitaño","Todos necesitamos estar solos en ocasiones tanto como estar acompañados. ¿Con qué frecuencia nos planteamos delegar temporalmente nuestras responsabilidades en otros y reservar ese valioso tiempo para nuestro propio disfrute?","hermitano"),
            Arcane(10,"rueda de la fortuna","La Rueda de la fortuna es una de las pocas cartas en los arcanos principales que no tiene una figura humana como punto focal.","rueda_de_la_fortuna"),
            Arcane(11,"la fuerza","Por lo general, pensamos en la fuerza en términos físicos (brazos grandes, piernas poderosas), pero también hay fuerza interior. La fuerza interior proviene de un ejercicio del músculo del corazón.","la_fuerza"),
            Arcane(12,"el colgado","El Colgado nos invita a observar nuestros complejos, las inhibiciones que nos mantienen atemorizados y en estado de servidumbre. No tenemos que interpretar el papel de víctima.","el_colgado"),
            Arcane(13,"la muerte","En las lecturas, la muerte a menudo representa un final importante que iniciará un gran cambio. Señala el fin de una era; Un momento en que una puerta se está cerrando.","la_muerte"),
            Arcane(14,"la templanza","Ser templado es mostrar moderación y autocontrol. En un mundo lleno de indulgencias atractivas, a menudo es necesario encontrar el término medio. ¿Sensible, tal vez, pero también un poco aburrido?","la_templanza"),
            Arcane(15,"el diablo","El diablo es nuestro símbolo de lo que es malo e indeseable. Desde nuestra perspectiva humana, vemos el mundo como una lucha entre la luz y la oscuridad. Queremos vencer lo malo para que prevalezca lo bueno.","el_diablo"),
            Arcane(16,"la torre","La torre es una carta inquietante. Fuego, relámpago, caída sobre rocas irregulares, ¡definitivamente parece un problema! La tarjeta 16 no será bienvenida por aquellos a quienes no les gusta el cambio.","la_torre"),
            Arcane(17,"la estrella","La Estrella: esta relacionada con esperanza y expresa, en el plano espiritual, la inmortalidad, la vida eterna no de lo físico sino más bien relacionado con el plano intelectual, la luz interior que alumbra al espíritu.","la_estrella"),
            Arcane(18,"la luna","La Luna es la luz de este reino, el mundo de la sombra y la noche. Aunque este lugar es impresionante, no tiene por qué ser aterrador. En las circunstancias adecuadas, la Luna inspira y encanta.","la_luna"),
            Arcane(19,"el sol","Brillante. Radiante. Espumoso. Muchas de nuestras palabras reflejan el poder y la gloria de la luz. Cuando encendemos la luz en una habitación, la iluminamos para que todos los rincones oscuros sean visibles.","el_sol"),
            Arcane(20,"el juicio","El Juicio: Este arcano simboliza la liberación, el final de un tiempo de sufrimiento, has concluido una etapa de tu vida y es hora de hacer balance pero no solo de lo bueno y de lo malo de dicha etapa, sino también de todo lo que ha significado todo el proceso en si mismo.","el_juicio"),
            Arcane(21,"el mundo","El mundo representa estos momentos y todo lo que lleva en ellos. En las lecturas, es una señal muy positiva de que está en condiciones de realizar el deseo de su corazón. Lo que sea para ti depende de la situación, pero siempre se sentirá bien.","el_mundo")
        )
    }
    val listCards: LiveData<List<Arcane>> = cards

}