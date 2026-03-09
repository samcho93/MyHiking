package com.myhiking.app.ui.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.databinding.FragmentPhotoListBinding
import com.myhiking.app.ui.photoview.PhotoViewFragment

class PhotoListFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    private var mountainWithPhotos: MountainWithPhotos? = null
    private var onPhotoReassign: ((DevicePhoto) -> Unit)? = null
    private var onPhotoExclude: ((DevicePhoto) -> Unit)? = null
    private var onAddPhoto: (() -> Unit)? = null
    private lateinit var adapter: PhotoListAdapter

    companion object {
        const val TAG = "PhotoListFragment"
        private var pendingData: MountainWithPhotos? = null
        private var pendingReassignCallback: ((DevicePhoto) -> Unit)? = null
        private var pendingExcludeCallback: ((DevicePhoto) -> Unit)? = null
        private var pendingAddPhotoCallback: (() -> Unit)? = null

        fun newInstance(
            mountain: MountainWithPhotos,
            onPhotoReassign: ((DevicePhoto) -> Unit)? = null,
            onPhotoExclude: ((DevicePhoto) -> Unit)? = null,
            onAddPhoto: (() -> Unit)? = null
        ): PhotoListFragment {
            pendingData = mountain
            pendingReassignCallback = onPhotoReassign
            pendingExcludeCallback = onPhotoExclude
            pendingAddPhotoCallback = onAddPhoto
            return PhotoListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mountainWithPhotos = pendingData
        onPhotoReassign = pendingReassignCallback
        onPhotoExclude = pendingExcludeCallback
        onAddPhoto = pendingAddPhotoCallback
        pendingData = null
        pendingReassignCallback = null
        pendingExcludeCallback = null
        pendingAddPhotoCallback = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mtn = mountainWithPhotos ?: run {
            dismiss()
            return
        }

        binding.tvMountainName.text = mtn.mountain.name
        binding.tvMountainInfo.text = getString(
            R.string.altitude_format, mtn.mountain.altitude
        ) + " | ${mtn.mountain.region} | " + getString(
            R.string.photos_count, mtn.photoCount
        )

        binding.btnClose.setOnClickListener { dismiss() }

        // 사진 추가 버튼
        if (onAddPhoto != null) {
            binding.btnAddPhoto.visibility = View.VISIBLE
            binding.btnAddPhoto.setOnClickListener {
                dismiss()
                onAddPhoto?.invoke()
            }
        }

        val hasLongPressActions = onPhotoReassign != null || onPhotoExclude != null

        adapter = PhotoListAdapter(
            onPhotoClick = { photo -> showPhotoViewer(photo) },
            onPhotoLongClick = if (hasLongPressActions) { photo ->
                showPhotoActionDialog(photo)
            } else null
        )

        binding.rvPhotos.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvPhotos.adapter = adapter
        adapter.submitList(mtn.photos)
    }

    /**
     * 사진 길게 눌렀을 때: 이동 / 제외 선택 다이얼로그
     */
    private fun showPhotoActionDialog(photo: DevicePhoto) {
        val items = mutableListOf<String>()
        if (onPhotoReassign != null) {
            items.add(getString(R.string.action_move_photo))
        }
        if (onPhotoExclude != null) {
            items.add(getString(R.string.action_exclude_photo))
        }

        AlertDialog.Builder(requireContext())
            .setTitle(photo.displayName)
            .setItems(items.toTypedArray()) { _, which ->
                val action = items[which]
                when (action) {
                    getString(R.string.action_move_photo) -> {
                        dismiss()
                        onPhotoReassign?.invoke(photo)
                    }
                    getString(R.string.action_exclude_photo) -> {
                        onPhotoExclude?.invoke(photo)
                        // 목록에서 해당 사진 제거 (UI 즉시 반영)
                        val currentList = adapter.currentList.toMutableList()
                        currentList.removeAll { it.id == photo.id }
                        adapter.submitList(currentList)
                        // 사진이 없으면 바텀시트 닫기
                        if (currentList.isEmpty()) dismiss()
                    }
                }
            }
            .show()
    }

    private fun showPhotoViewer(photo: DevicePhoto) {
        val fragment = PhotoViewFragment.newInstance(photo)
        fragment.show(parentFragmentManager, PhotoViewFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
