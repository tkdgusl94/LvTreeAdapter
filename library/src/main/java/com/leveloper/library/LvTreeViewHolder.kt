package com.leveloper.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class LvTreeViewHolder<out B: ViewDataBinding>(parent: ViewGroup, @LayoutRes val layoutRes: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    protected val context = itemView.context

    protected val binding: B = DataBindingUtil.bind(itemView)!!

    abstract fun bind(node: TreeNode<*>)

    abstract fun onToggle(node: TreeNode<*>, isExpand: Boolean)
}