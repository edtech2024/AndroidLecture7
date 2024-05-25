package com.example.myapplicationseven.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentListStateAdapter(fragment: Fragment, private var bundle: Bundle) : FragmentStateAdapter(fragment) {

    // возвращает количество страниц, которые будут в ViewPager2
    override fun getItemCount(): Int = 2

    // по номеру страницы, передаваемому в качестве параметра position, возвращает объект фрагмента
    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> ListFragmentType1.newInstance(bundle.getString("Type 1"))
            1 -> ListFragmentType2.newInstance(bundle.getString("Type 2"))
            else ->  throw Exception("Error")
        }

    }

}