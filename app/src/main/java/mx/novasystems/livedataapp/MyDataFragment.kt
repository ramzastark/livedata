package mx.novasystems.livedataapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import mx.novasystems.livedataapp.databinding.MyDataFragmentBinding


class MyDataFragment : Fragment() {

    /**
     * Creamos las variables de Binding y ViewModel que vamos a utlizar de manera clasica
     */
    lateinit var binding: MyDataFragmentBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(MyDataViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * - Creamos nuestro layout con Binding usando DataBindingUtil
         * - Anclamos el ViewModel(viewModel = MyDataViewModel) a la propiedad binding.viewModel
         * - Delegamos el control del ciclo de vida a la propiedad  binding.lifecycleOwner
         */
        binding = DataBindingUtil.inflate(inflater, R.layout.my_data_fragment, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Comununicamos nuestro ViewModel
        binding.btnCapt.setOnClickListener { viewModel.setCapt() }
        binding.btnIron.setOnClickListener { viewModel.setIron() }
        //binding.btnThor.setOnClickListener { viewModel.setThor() }
        binding.btnAvengers.setOnClickListener { viewModel.setVisible() }
    }
}
/**
 - El proposito de este tutorial, es mostrar las diferencias entre usar LiveData y MutableLiveda
 LiveData es una clase de contenedor de datos observable. A diferencia de una clase observable regular,
 LiveData está optimizada para ciclos de vida, lo que significa que respeta el ciclo de vida de otros
 componentes de las apps, como actividades, fragmentos o servicios. Esta optimización garantiza que
 LiveData solo actualice observadores de componentes de apps que tienen un estado de ciclo de vida activo.

 Cómo actualizar objetos LiveData
 LiveData no tiene métodos disponibles públicamente para actualizar los datos almacenados.
 La clase MutableLiveData expone los métodos setValue(T) y postValue(T) de forma pública; debes usarlos
 si necesitas editar el valor almacenado en un objeto LiveData. Por lo general, MutableLiveData
 se usa en ViewModel y, luego, ViewModel solo expone objetos LiveData inmutables a los observadores.

 En resumen los LiveData nos permiten tener informacion en el ViewModel y recuperarla para mostrarla
 cada vez que sea necesario actualizar los UI, pero para informacion dinamica tenemos que usar
 MutableLiveData ya que este contiene los metodos para hacer Update de esta misma informacion

 En el ViewModel MyDataViewModel continuo con la explicacion

 Explicacion Larga
 https://developer.android.com/topic/libraries/architecture/livedata

 CONVERTIR EL LAYOUT PARA USAR DATA BINDING
 Tenemos que agregar la nomenclatura como sigue

 <layout xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
 xmlns:tools="http://schemas.android.com/tools">

 <data>
     <import type="android.view.View"/>
     <variable
     name="viewModel"
     type="mx.novasystems.livedataapp.MyDataViewModel" />
 </data>

 <Resto Layout />

 </layout>
 */
