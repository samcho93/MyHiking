package com.myhiking.app.ui.reassign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myhiking.app.R
import com.myhiking.app.data.model.DevicePhoto
import com.myhiking.app.data.model.Mountain
import com.myhiking.app.data.model.MountainWithPhotos
import com.myhiking.app.databinding.FragmentReassignBinding

class ReassignFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentReassignBinding? = null
    private val binding get() = _binding!!

    // 벌크 모드 (마커 전체 이동)
    private var mountainWithPhotos: MountainWithPhotos? = null
    private var onReassign: ((MountainWithPhotos, Int) -> Unit)? = null

    // 개별 사진 모드
    private var singlePhoto: DevicePhoto? = null
    private var singlePhotoMountainName: String? = null
    private var onPhotoReassign: ((Long, Int) -> Unit)? = null

    // 공통
    private var nearbyMountains: List<Pair<Mountain, Double>> = emptyList()
    private var onDismissWithoutSelection: (() -> Unit)? = null
    private var didSelectTarget = false

    companion object {
        const val TAG = "ReassignFragment"

        // 벌크 모드 pending data
        private var pendingData: MountainWithPhotos? = null
        private var pendingNearby: List<Pair<Mountain, Double>>? = null
        private var pendingCallback: ((MountainWithPhotos, Int) -> Unit)? = null

        // 개별 사진 모드 pending data
        private var pendingPhoto: DevicePhoto? = null
        private var pendingPhotoMountainName: String? = null
        private var pendingPhotoCallback: ((Long, Int) -> Unit)? = null

        private var pendingDismissCallback: (() -> Unit)? = null

        private fun clearPending() {
            pendingData = null
            pendingNearby = null
            pendingCallback = null
            pendingPhoto = null
            pendingPhotoMountainName = null
            pendingPhotoCallback = null
            pendingDismissCallback = null
        }

        /**
         * 벌크 모드: 마커의 모든 사진을 다른 산으로 이동
         */
        fun newInstance(
            mountain: MountainWithPhotos,
            nearbyMountains: List<Pair<Mountain, Double>>,
            onReassign: (MountainWithPhotos, Int) -> Unit
        ): ReassignFragment {
            clearPending()
            pendingData = mountain
            pendingNearby = nearbyMountains
            pendingCallback = onReassign
            return ReassignFragment()
        }

        /**
         * 개별 사진 모드: 사진 한 장을 다른 산으로 이동
         */
        fun newInstanceForPhoto(
            photo: DevicePhoto,
            currentMountainName: String,
            nearbyMountains: List<Pair<Mountain, Double>>,
            onReassign: (Long, Int) -> Unit
        ): ReassignFragment {
            clearPending()
            pendingPhoto = photo
            pendingPhotoMountainName = currentMountainName
            pendingNearby = nearbyMountains
            pendingPhotoCallback = onReassign
            return ReassignFragment()
        }
    }

    /**
     * 선택 없이 dismiss 시 호출할 콜백 설정 (드래그 snap-back 용)
     * show() 전에 호출해야 함
     */
    fun setOnDismissWithoutSelectionListener(listener: () -> Unit) {
        pendingDismissCallback = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mountainWithPhotos = pendingData
        singlePhoto = pendingPhoto
        singlePhotoMountainName = pendingPhotoMountainName
        nearbyMountains = pendingNearby ?: emptyList()
        onReassign = pendingCallback
        onPhotoReassign = pendingPhotoCallback
        onDismissWithoutSelection = pendingDismissCallback
        clearPending()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReassignBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCloseReassign.setOnClickListener { dismiss() }

        if (singlePhoto != null) {
            setupSinglePhotoMode()
        } else {
            setupBulkMode()
        }
    }

    /**
     * 벌크 모드: 마커 전체 사진 이동
     */
    private fun setupBulkMode() {
        val mtn = mountainWithPhotos ?: run { dismiss(); return }

        binding.tvReassignTitle.text = getString(R.string.reassign_title, mtn.mountain.name)
        binding.tvReassignInfo.text = getString(
            R.string.reassign_bulk_info, mtn.photoCount
        )

        // 사진 목록은 숨기고, 바로 근처 산 목록 표시
        binding.rvReassignPhotos.visibility = View.GONE

        // 근처 산 목록 (현재 산 제외)
        val filteredNearby = nearbyMountains.filter { it.first.id != mtn.mountain.id }
        showNearbyMountains(filteredNearby) { targetMountain ->
            didSelectTarget = true
            onReassign?.invoke(mtn, targetMountain.id)
            Toast.makeText(
                requireContext(),
                getString(R.string.reassign_bulk_done, mtn.photoCount, targetMountain.name),
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }

    /**
     * 개별 사진 모드: 사진 한 장 이동
     */
    private fun setupSinglePhotoMode() {
        val photo = singlePhoto ?: run { dismiss(); return }
        val mountainName = singlePhotoMountainName ?: ""

        binding.tvReassignTitle.text = getString(R.string.reassign_photo_title)
        binding.tvReassignInfo.text = getString(
            R.string.reassign_photo_info, mountainName, photo.displayName
        )

        // 사진 목록은 숨기기
        binding.rvReassignPhotos.visibility = View.GONE

        // 근처 산 목록 표시
        showNearbyMountains(nearbyMountains.toList()) { targetMountain ->
            didSelectTarget = true
            onPhotoReassign?.invoke(photo.id, targetMountain.id)
            Toast.makeText(
                requireContext(),
                getString(R.string.reassign_photo_done, targetMountain.name),
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }
    }

    /**
     * 공통: 근처 산 목록 표시
     */
    private fun showNearbyMountains(
        mountains: List<Pair<Mountain, Double>>,
        onSelect: (Mountain) -> Unit
    ) {
        binding.tvNearbyLabel.visibility = View.VISIBLE
        binding.rvNearbyMountains.visibility = View.VISIBLE
        binding.rvNearbyMountains.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNearbyMountains.adapter = MountainAdapter(mountains, onSelect)
    }

    override fun onDismiss(dialog: android.content.DialogInterface) {
        super.onDismiss(dialog)
        if (!didSelectTarget) {
            onDismissWithoutSelection?.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Nearby mountain list adapter
    private inner class MountainAdapter(
        private val mountains: List<Pair<Mountain, Double>>,
        private val onSelect: (Mountain) -> Unit
    ) : RecyclerView.Adapter<MountainAdapter.VH>() {

        inner class VH(view: View) : RecyclerView.ViewHolder(view) {
            val tvName: TextView = view.findViewById(R.id.tvMountainName)
            val tvDetail: TextView = view.findViewById(R.id.tvMountainDetail)
            val tvDistance: TextView = view.findViewById(R.id.tvDistance)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nearby_mountain, parent, false)
            return VH(view)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            val (mountain, distance) = mountains[position]
            holder.tvName.text = mountain.name
            holder.tvDetail.text = getString(R.string.altitude_format, mountain.altitude) +
                    if (mountain.region.isNotEmpty()) " | ${mountain.region}" else ""
            holder.tvDistance.text = if (distance < 1000) {
                "${distance.toInt()}m"
            } else {
                String.format("%.1fkm", distance / 1000)
            }
            holder.itemView.setOnClickListener { onSelect(mountain) }
        }

        override fun getItemCount() = mountains.size
    }
}
