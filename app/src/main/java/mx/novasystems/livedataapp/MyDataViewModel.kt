package mx.novasystems.livedataapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyDataViewModel : ViewModel() {
    private var _dataName = MutableLiveData<String>().apply { value = "Steve Rogers" }
    val dataName:LiveData<String>
        get() = _dataName

    private var _dataWork = MutableLiveData<String>().apply { value = "Soldado" }
    val dataWork:LiveData<String>
        get() = _dataWork

    private var _dataWeapon = MutableLiveData<String>().apply { value = "Escudo Vibranio" }
    val dataWeapon:LiveData<String>
        get() = _dataWeapon

    private var _imageAvengers = MutableLiveData<Boolean>().apply { value = false }
    val imageAvengers:LiveData<Boolean>
        get() = _imageAvengers

    fun setCapt(){
        _dataName.value = "Steve Rogers"
        _dataWork.value = "Soldado"
        _dataWeapon.value = "Escudo Vibranio"
    }

    fun setIron(){
        _dataName.value = "Tony Stark"
        _dataWork.value = "Empresario / Inventor"
        _dataWeapon.value = "Armadura Iron Man"

    }
    fun setThor(){
        _dataName.value = "Thor OdinSon"
        _dataWork.value = "Dios Asgardiano"
        _dataWeapon.value = "Martillo Mjolnir"

    }

    fun setVisible(){
        val valor = imageAvengers.value
        if(valor != null){
            _imageAvengers.value = !valor
        }
    }
}
/**
En este ejemplo inicial, cargamos la informacion del Capitan America, por medio de hacer el
UI y su ViewModel, usando apply en cada viable del de informacion, mas adelante explico este
proceso

La buena practica de desarrollo que recomienda Google, es que unamos el DataBinding, con ViewModel
y este a su vez con los LiveData. Pero debemos mostrar siempre un LiveData para consultar la informacion
y para actualizar debemos usar el MutableData. El ejemplo consiste de esta manera

var _dataName = MutableLiveData<String>().apply { value = "Steve Rogers" } contiene la informacion
dinamica, a nivel de ViewModel actualizamos siempre esta variable, el nombre debe ser:
_nombreVar

Usaremos a nivel de ViewModel metodos para actualizar la informacion. Nuestra vairable debe ser private,
para que nadie fuera de la clase pueda modificarla, si no es usando sus metodos

fun setCapt(){
_dataName.value = "Steve Rogers"
_dataWork.value = "Soldado"
_dataWeapon.value = "Escudo Vibranio"
}

Para recuperar la informacion desde FUERA del viewModel, usaremos la variable LiveData Publica, que
llama a su vez al valor de MutableLiveData:
val dataName:LiveData<String>
get() = _dataName

Tambien es importante mencionar, que cada vez que hacemos cambios en las variables, a diferencia
de como las manejamos en SABADELL, podemos delegar el actualizar la UI al propio XML, si necesidad
de usar Observables en la Clase View. Por ejemplo del xml tenemos:

<layout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools">

<data>
<import type="android.view.View"/>
<variable
name="viewModel"
type="mx.novasystems.livedataapp.MyDataViewModel" /> <- Creamos nuestra variable ViewModel
</data>

<TextView
android:id="@+id/tv_name"
android:layout_width="0dp"
android:layout_height="wrap_content"
android:text="@{viewModel.dataName}" <- Hacemos referencia a nuestro ViewModel y la propiedad a actualizar
tools:text="Steve Rogers" />
</layout>

Para llamar a nuestras propiedades de viewModel Tenemos que hacer referencia de la siguiente manera
@{viewModel.propiedad}

android:text="@{viewModel.dataName}"

De esta manera, cuando en el ViewModel se actualiza el valor del mutableLiveData _dataName.value = "Steve Rogers"
automaticamente ese cambio se refleja en dataName.value. Y a su vez, actualiza el XML
android:text="@{viewModel.dataName}"

En este ejemplo cada vez que orpimimos un boton, como btn_capt en el View, llamamos a la funcion
setCapt() del ViewModel, para actualizar la UI con informacion del Capt America, pasa lo mismo con el
boton btn_iron que llama a viewModel.setIron()

El caso de Thor es diferente, porque usamos en el XML una llamada DIRECTA al ViewModel por medio de
android:onClick="@{()->viewModel.setThor()}"

Con esto incluso podemos ahorrarnos mas codigo en el Fragmento, delegando la llamada del evento
al XML, es un poco mas complejo de entender, pero al dominarlo, podemos tener las Views aun mas
limpias de codigo. Y eliminando la linea de codigo de
//binding.btnThor.setOnClickListener { viewModel.setThor() } que esta comentada en el MyDataFragmnet

MOSTRAR IMAGNEES
En el casp de la imagen de los Avengers:
<ImageView
android:id="@+id/iv_avengers"
android:visibility="@{viewModel.imageAvengers ? View.VISIBLE : View.GONE}"

Podemos controlar su visibilidad de manera interesante, hacemos referencia a su propiedad en el
ViewModel de viewModel.imageAvengers que es un Boolean, con esete codigo en el XML, mostramos o
no cambiando su estado. Con el Binding en el XML podemos hacer muchas cosas interesantes a los XML
por medio de la interaccion de ViewModel, como controlar los Visible, formatos y otros temas

Mas informacion en:
https://developer.android.com/topic/libraries/data-binding?hl=es-419

El curso completo, donde para ver todos estos temas mas explicados en Udacity es:

https://classroom.udacity.com/courses/ud9012/lessons/da3967cc-ba85-4045-bb46-dea1c770fb8b/concepts/bf448bba-9989-40fb-808f-4cc66f79c10e

 */