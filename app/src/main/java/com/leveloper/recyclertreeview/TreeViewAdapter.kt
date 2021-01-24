package com.leveloper.recyclertreeview

import android.view.ViewGroup
import com.leveloper.library.RecyclerTreeViewAdapter
import com.leveloper.library.RecyclerTreeViewHolder
import com.leveloper.library.TreeNode
import com.leveloper.recyclertreeview.databinding.ItemFileBinding
import com.leveloper.recyclertreeview.databinding.ItemFolderBinding

class TreeViewAdapter(node: List<TreeNode<*>>): RecyclerTreeViewAdapter<RecyclerTreeViewHolder<*>>(node) {

    override var padding: Int = 50

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerTreeViewHolder<*> {
        return when (viewType) {
            R.layout.item_file -> FileViewHolder(parent)
            else -> FolderViewHolder(parent)
        }
    }

    inner class FileViewHolder(parent: ViewGroup): RecyclerTreeViewHolder<ItemFileBinding>(parent, R.layout.item_file) {
        override fun bind(node: TreeNode<*>) {
            val content = node.content as File
            binding.file.text = content.name
        }

        override fun onToggle(node: TreeNode<*>, isExpand: Boolean) {}
    }

    inner class FolderViewHolder(parent: ViewGroup): RecyclerTreeViewHolder<ItemFolderBinding>(parent, R.layout.item_folder) {
        override fun bind(node: TreeNode<*>) {
            val content = node.content as Folder
            binding.folder.text = content.id.toString()
        }

        override fun onToggle(node: TreeNode<*>, isExpand: Boolean) {
            val rotateDegree = if (isExpand) 90f else -90f
            binding.image.animate().rotationBy(rotateDegree)
                .start()
        }
    }
}