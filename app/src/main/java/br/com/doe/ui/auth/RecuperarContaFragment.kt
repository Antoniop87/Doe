package br.com.doe.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import br.com.doe.R
import br.com.doe.databinding.FragmentRecuperarContaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RecuperarContaFragment : Fragment() {

    private var _binding: FragmentRecuperarContaBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecuperarContaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClicks()
    }

    private fun initClicks() {
        binding.btnrecuperarconta.setOnClickListener { validateData() }
        
        }

        private fun validateData() {
            val email = binding.inputemail.text.toString().trim()



            if (email.isNotEmpty()) {
                binding.progessbar.isVisible = true

                recuperarConta(email)

        } else{
            Toast.makeText(requireContext(), "Informe seu Email", Toast.LENGTH_SHORT).show()
        }
    }
    private fun recuperarConta(email: String) {

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Link enviado para o Email", Toast.LENGTH_SHORT).show()
                }
                binding.progessbar.isVisible = false
            }

    }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}