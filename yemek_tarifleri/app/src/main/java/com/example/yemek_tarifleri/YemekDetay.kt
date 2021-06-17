package com.example.yemek_tarifleri

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yemek_tarifleri.databinding.FragmentYemekDetayBinding
import java.io.ByteArrayOutputStream
import java.io.IOException


class YemekDetay : Fragment() {
    private var _binding: FragmentYemekDetayBinding? = null
    private val binding get() = _binding!!
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    var selectedPicture: Uri? = null
    var selectedBitmap: Bitmap? = null
    private var secilenYemekName: String? = null
    private var secilenYemekDetay: String? = null
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentYemekDetayBinding.inflate(layoutInflater, container, false)
        context?.let {
            dbHelper = DatabaseHelper(it)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestLauncher()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getString("navigateInfo")?.let { it1 -> Log.i("ARG", it1) }
            val arg = it.getString("navigateInfo")
            if (arg.equals("fromMenu")) {
                binding.yemekIsimEditText.setText("")
                binding.yemekDetayEditText.setText("")
                binding.kaydetButton.visibility = View.VISIBLE

            } else {
                val id = it.getInt("itemId")
                dbHelper.showSelectedItem(item_id = id, binding = binding)
            }
        }


        binding.yemekImageView.setOnClickListener {
            selectImage()
        }

        binding.kaydetButton.setOnClickListener {
            context?.let {
                secilenYemekName = binding.yemekIsimEditText.text.toString()
                secilenYemekDetay = binding.yemekDetayEditText.text.toString()

                val compressedBitmap = compressBitmap(selectedBitmap!!, 300)
                val outputStream = ByteArrayOutputStream()
                compressedBitmap!!.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
                val byteArray = outputStream.toByteArray()

                dbHelper.addYemek(yemekAd = secilenYemekName!!, yemekDetay = secilenYemekDetay!!, yemekResmi = byteArray)

                findNavController().navigate(R.id.action_yemekDetay_to_yemekListeFragment)

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun selectImage() {
        activity?.let {
            if (ContextCompat.checkSelfPermission(requireActivity().applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }
    }

    private fun requestLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
                selectedPicture = result.data!!.data
                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, selectedPicture!!)
                        selectedBitmap = ImageDecoder.decodeBitmap(source)
                        binding.yemekImageView.setImageBitmap(selectedBitmap)
                    } else {

                        selectedBitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedPicture)
                        binding.yemekImageView.setImageBitmap(selectedBitmap)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }


        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->
            if (permission) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(galleryIntent)
            } else {
                Toast.makeText(requireContext(), "Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun compressBitmap(bitmap: Bitmap, size: Int): Bitmap? {

        var width = bitmap.width
        var height = bitmap.height

        val bitmapRatio: Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1) {
            width = size
            height = (width / bitmapRatio).toInt()
        } else {
            height = size
            width = (height * bitmapRatio).toInt()
        }

        return Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

}