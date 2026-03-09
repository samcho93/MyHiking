package com.myhiking.app.ui.photoview

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.databinding.FragmentPhotoViewBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoViewFragment : DialogFragment() {

    private var _binding: FragmentPhotoViewBinding? = null
    private val binding get() = _binding!!

    private var photo: DevicePhoto? = null

    companion object {
        const val TAG = "PhotoViewFragment"
        private var pendingPhoto: DevicePhoto? = null

        fun newInstance(photo: DevicePhoto): PhotoViewFragment {
            pendingPhoto = photo
            return PhotoViewFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_MyHiking_FullScreen)
        photo = pendingPhoto
        pendingPhoto = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentPhoto = photo ?: run {
            dismiss()
            return
        }

        Glide.with(this)
            .load(currentPhoto.uri)
            .into(binding.ivFullPhoto)

        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        binding.tvPhotoInfo.text = "${currentPhoto.displayName}\n${dateFormat.format(Date(currentPhoto.dateTaken))}"

        binding.btnCloseViewer.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
