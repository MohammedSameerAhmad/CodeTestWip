package com.wipro.codetest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.wipro.codetest.R
import com.wipro.codetest.models.Rows
import com.wipro.codetest.utilities.GlideApp
import com.wipro.codetest.viewmodels.ListFragmentViewModel
import java.util.*

class CustomAdapter constructor(
    viewModel: ListFragmentViewModel,
    lifecycleOwner: LifecycleOwner,
    val context: Context
) : RecyclerView.Adapter<RepoViewHolder>() {

    private val data = ArrayList<Rows>()

    init {
        viewModel.response.observe(lifecycleOwner, Observer {
            data.clear()
            if (it != null) {
                data.addAll(it.rows)
            }
            notifyDataSetChanged()
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(data[position], context)

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class RepoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.row_item, parent, false)) {
    private var txtTitle: TextView? = null
    private var txtDescription: TextView? = null
    private var imgIcon: ImageView? = null

    init {
        txtTitle = itemView.findViewById(R.id.txtTitle)
        txtDescription = itemView.findViewById(R.id.txtDescription)
        imgIcon = itemView.findViewById(R.id.imgIcon)

    }

    fun bind(row: Rows, context: Context) {
        txtTitle?.text = row.title
        txtDescription?.text = row.description
        imgIcon?.let {
            GlideApp.with(context).load(row.imageHref).apply(RequestOptions().override(200, 200))
                .into(it)
        }
    }
}
