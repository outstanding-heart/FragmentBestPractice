package com.example.fragmentbestpractice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment implements OnItemClickListener {

	private ListView newsTitleListView;
	private List<News> newsList;
	private NewsAdapter adapter;
	private boolean isTwoPane;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		newsList = getNews();
		adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanBundle) {
		View view = inflater.inflate(R.layout.news_title_frag, container, false);
		
		newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
		newsTitleListView.setAdapter(adapter);
		newsTitleListView.setOnItemClickListener(this);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (getActivity().findViewById(R.id.news_content_layout) != null) {
			isTwoPane = true;
		} else {
			isTwoPane = false;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		News news = newsList.get(position);
		
		if (isTwoPane) {
			NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
			newsContentFragment.refresh(news.getTitle(), news.getContent());
		} else {
			NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
		}
	}
	
	private List<News> getNews() {
		List<News> newsList = new ArrayList<News>();
		News news1 = new News();		
		News news2 = new News();
		
		news1.setTitle("欧洲古代的君王或者贵族有哪些有意思的外号？");
		news1.setContent("“生于紫室的”，指的是罗马皇帝在登基后被诞下的子女。紫室指的是君士坦丁堡大皇宫里一间由紫色斑岩装饰的房间，皇帝的子女在这个房间里被接生。" +
				"第一个以“生于紫室的”为绰号的皇帝是君士坦丁七世，不过这里的“生于紫室”其实是首都人民讥讽他的出身，而不是百度百科所说的体现出生尊贵。");
		newsList.add(news1);

		news2.setTitle("如何评价38号车评中心评测奥迪A4L 40TFSI 产品力全面落后迈腾330TSI？");
		news2.setContent("我个人很欣赏38号，绝对是KOL界里的一股清流。不知道38号是否有被充值过（西那瓦大学……），但至少不像其它KOL那样，跟厂家走得如此近，商业运" +
				"营痕迹也没那么明显。尤其是自费买车，这种掏腰包为“公益”，假如38号长得帅气一点，估计会被冠以蝙蝠侠、钢铁侠的名义……当然啦，欣赏一个人，不代表要认同他全" +
				"部观点，否则就变成拜神。不只是针对38号，这个道理对圈内其它KOL同样成立。");
		newsList.add(news2);
		
		return newsList;
	}
	
}
