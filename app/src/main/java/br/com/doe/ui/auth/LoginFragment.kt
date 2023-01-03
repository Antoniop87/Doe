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
import br.com.doe.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class loginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        initClicks()
    }

    private fun initClicks(){
        binding.btnlogin.setOnClickListener { validateData() }

        binding.btnCriarconta.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_criarContaFragment)
        }
        binding.btnRecuperarconta.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recuperarContaFragment)
        }
    }

    private fun validateData(){
        val email = binding.inputemail.text.toString().trim()
        val senha = binding.inputsenha.text.toString().trim()

        if(email.isNotEmpty()){
            if(email.isNotEmpty()){
                binding.progessbar.isVisible = true

                loginUser(email, senha)
            } else{
                Toast.makeText(requireContext(), "Informe sua Senha", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(requireContext(), "Informe seu Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginUser(email:String, senha:String){

        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    binding.progessbar.isVisible = false
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}