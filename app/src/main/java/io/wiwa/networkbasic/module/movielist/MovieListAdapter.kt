package io.wiwa.networkbasic.module.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import io.wiwa.networkbasic.base.BaseViewHolder
import io.wiwa.networkbasic.data.model.MovieListItemModel
import io.wiwa.networkbasic.databinding.CellMovieListItemBinding

class MovieListAdapter(
    var context: Context,
    var dataList: ArrayList<MovieListItemModel>,
    var itemClickCallback: (MovieListItemModel) -> Unit
) : RecyclerView.Adapter<BaseViewHolder>() {
    private val TAG = this.javaClass.simpleName
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return MovieListViewHolder(CellMovieListItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MovieListViewHolder(
        var binding: CellMovieListItemBinding
    ) : BaseViewHolder(binding.root) {

        override fun bind(position: Int) {
            val itemData = dataList[position]

            Glide.with(context)
                .load(itemData.medium_cover_image)
                .transform(CenterInside())
                .into(binding.image)

            binding.titleText.text = itemData.title
            binding.yearText.text = "${itemData.year}"

            binding.root.setOnClickListener {
                itemClickCallback.invoke(itemData)
            }
        }
    }
}