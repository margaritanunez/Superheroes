package com.example.superheroes.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.superheroes.R
import com.example.superheroes.databinding.FragmentDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"

class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: Int = 0
    private lateinit var binding: FragmentDetailBinding
    private val superheroeViewModel: SuperheroeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        superheroeViewModel.getDetailsSuperheroeViewModel(id)
        initListener()
        return binding.root
    }

    private fun initListener() {
        superheroeViewModel.detailSuperheroeLiveData(id).observe(viewLifecycleOwner){
            if (it!= null){
                binding.imgSuperheroeDetail.load(it.imageLink)
                binding.tvColourDetail.text = it.colour
                binding.tvNameDetail.text = it.name
                binding.tvOriginDetail.text = it.origin
                binding.tvPower.text = it.power
                binding.tvYearDetail.text = it.yearCreation.toString()
                if (!it.translation) {
                    binding.tvTranslationDetail.text = "Sin traducción"
                } else {
                    binding.tvTranslationDetail.text = "Cuenta con traducción al español"
                }
                val id = it.id

                binding.floatingButtonEmail!!.setOnClickListener {
                    sendEmail(id)
                }
            }
        }
    }

    private fun sendEmail(id: Int) {
        //mail cliente
        val destinatario = getString(R.string.destinatario_email)
        val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(destinatario))
        intentEmail.type = "plain/text"
        //destinatario
        intentEmail.putExtra(Intent.EXTRA_EMAIL,arrayOf(destinatario))
        //asunto
        intentEmail.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.asunt_email,id))
        //cuerpo correo
        //intentEmail.putExtra(Intent.EXTRA_TEXT,getString(R.string.body_email,id))

        startActivity(Intent.createChooser(intentEmail, "Votación superheroe"))

    }

}