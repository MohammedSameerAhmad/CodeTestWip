package com.wipro.codetest.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wipro.codetest.adapters.CustomAdapter
import com.wipro.codetest.utilities.MyApplication
import com.wipro.codetest.utilities.ViewModelFactory
import com.wipro.codetest.viewmodels.ListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_info_list.*
import kotlinx.android.synthetic.main.fragment_info_list.view.*
import javax.inject.Inject


class InfoListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: ListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view =
            inflater.inflate(com.wipro.codetest.R.layout.fragment_info_list, container, false)
        (activity?.application as MyApplication).apiComponent().inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ListFragmentViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.recyclerView.let {
            it.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = CustomAdapter(viewModel, this, activity!!)
        }
        observeViewModel()
        floatingActionButton.setOnClickListener(View.OnClickListener {
            viewModel.fetchResponse()
        })
    }

    private fun observeViewModel() {
        viewModel.response.observe(this, Observer {
            activity?.title = it.title
        })
        viewModel.error.observe(this, Observer {
            tv_error.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.loading.observe(this, Observer {
            loading_view.visibility = if (it) View.VISIBLE else View.GONE
            // when refresh hide recyclerview
            recyclerView.visibility = if (!it) View.VISIBLE else View.GONE
        })
    }
}
