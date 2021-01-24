package com.leveloper.library

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

abstract class RecyclerTreeViewAdapter<VH: RecyclerTreeViewHolder<ViewDataBinding>>(
    nodes: List<TreeNode<*>>? = null
): RecyclerView.Adapter<VH>() {

    private val displayNodes: MutableList<TreeNode<*>> = mutableListOf()

    private var onTreeNodeListener: OnTreeNodeListener? = null

    abstract var padding: Int

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

    fun replaceAll(nodes: List<TreeNode<*>>) {
        displayNodes.clear()
        findDisplayNodes(nodes)
        notifyDataSetChanged()
    }

    fun setOnTreeNodeListener(listener: OnTreeNodeListener) {
        this.onTreeNodeListener = listener
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
        holder.itemView.setPadding(displayNodes[position].depth * padding, 0, 0, 0)

        holder.itemView.setOnClickListener {
            try {
                val lastClickTime = holder.itemView.tag as Long
                if (System.currentTimeMillis() - lastClickTime < 500)
                    return@setOnClickListener
            } catch (e: Exception) {}

            holder.itemView.tag = System.currentTimeMillis()

            val selectedNode = displayNodes[position]
            if (onTreeNodeListener != null && onTreeNodeListener?.onClick(selectedNode, holder) == false)
                return@setOnClickListener

            if (selectedNode.isLeaf)
                return@setOnClickListener

            if (selectedNode.isLocked)
                return@setOnClickListener

            val isExpand = selectedNode.isExpand
            val startPosition = displayNodes.indexOf(selectedNode) + 1

            if (!isExpand)
                notifyItemRangeInserted(startPosition, addChildNodes(selectedNode, startPosition))
            else
                notifyItemRangeRemoved(startPosition, removeChildNodes(selectedNode, true))
        }

        holder.bind(displayNodes[position])
    }

    private fun addChildNodes(parent: TreeNode<*>, startIndex: Int): Int {
        val childList = parent.childList
        var addChildCount = 0

        childList.forEach { child ->
            displayNodes.add(startIndex + addChildCount++, child)
            if (child.isExpand) {
                addChildCount += addChildNodes(child, startIndex + addChildCount)
            }
        }

        if (!parent.isExpand) parent.toggle()

        return addChildCount
    }

    private fun removeChildNodes(parent: TreeNode<*>, shouldToggle: Boolean = true): Int {
        if (parent.isLeaf) return 0

        val childList = parent.childList
        var removeChildCount = childList.size
        displayNodes.removeAll(childList)

        childList.forEach { child ->
            if (child.isExpand) {
                child.toggle()
                removeChildCount += removeChildNodes(child, false)
            }
        }

        if (shouldToggle) parent.toggle()
        return removeChildCount
    }

    interface OnTreeNodeListener {
        fun onClick(node: TreeNode<*>, viewHolder: RecyclerTreeViewHolder<*>): Boolean
    }
}