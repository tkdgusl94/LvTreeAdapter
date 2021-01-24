package com.leveloper.recyclertreeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.leveloper.library.LvTreeAdapter
import com.leveloper.library.LvTreeViewHolder
import com.leveloper.library.TreeNode
import com.leveloper.recyclertreeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerView.adapter = TreeViewAdapter(getNodeList()).apply {
            setOnTreeNodeListener(object : LvTreeAdapter.OnTreeNodeListener {
                override fun onClick(node: TreeNode<*>, viewHolder: LvTreeViewHolder<*>): Boolean {
                    if (!node.isLeaf) {
                        viewHolder.onToggle(node, !node.isExpand)
                    }

                    return true
                }
            })
        }

    }

    private fun getNodeList(): List<TreeNode<*>> {
        val nodeList = mutableListOf<TreeNode<*>>()

        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(File("new File")))
                .addChild(TreeNode(File("file"))
                    .addChild(TreeNode(File("new File")))
                    .addChild(TreeNode(File("file")))
                    .addChild(TreeNode(File("new File")))
                    .addChild(TreeNode(File("file"))
                        .addChild(TreeNode(File("new File")))
                        .addChild(TreeNode(File("file")))))
        )
        nodeList.add(
            TreeNode(Folder(2))
        )
        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(File("new File")))
                .addChild(TreeNode(File("file")))
        )
        nodeList.add(
            TreeNode(Folder(2))
        )
        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(File("new File")))
                .addChild(TreeNode(File("file")))
        )
        nodeList.add(
            TreeNode(Folder(2))
        )
        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(File("new File")))
                .addChild(TreeNode(File("file")))
        )
        nodeList.add(
            TreeNode(Folder(2))
        )
        nodeList.add(
            TreeNode(Folder(1))
                .addChild(TreeNode(File("new File")))
                .addChild(TreeNode(File("file")))
        )
        nodeList.add(
            TreeNode(Folder(2))
        )


        return nodeList
    }
}