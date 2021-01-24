package com.leveloper.library

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerTreeViewAdapter<VH: RecyclerTreeViewHolder<ViewDataBinding>>(
    nodes: List<TreeNode<*>>? = null
): RecyclerView.Adapter<VH>() {

    protected val displayNodes: MutableList<TreeNode<*>> = mutableListOf()

    init {
        nodes?.let {
            findDisplayNodes(it)
        }
    }

    private fun findDisplayNodes(nodes: List<TreeNode<*>>) {
        nodes.forEach { node ->
            displayNodes.add(node)
            if (!node.isLeaf && node.isExpand) {
                findDisplayNodes(node.childList)
            }
        }
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemViewType(position: Int): Int {
        return displayNodes[position].content.getLayoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return getViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int = displayNodes.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(displayNodes[position])
    }
}