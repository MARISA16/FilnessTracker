package co.tiagoaguiar.codelab.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rvMain = findViewById(R.id.main_rv);

		List<MainItem> mainItems = new ArrayList<>();
		mainItems.add(new MainItem(1, R.drawable.ic_baseline_flare_24, R.string.label_imc, Color.GREEN));
		mainItems.add(new MainItem(2, R.drawable.ic_baseline_visibility_24, R.string.label_tmb, Color.RED));

		//1-> Definir o comportamento de exibição do layout da recyclerview
		//nosaic
		//grid
		//linear (horizonat e vertical)
		rvMain.setLayoutManager(new GridLayoutManager(this, 2));
		MainAdapter adapter = new MainAdapter(mainItems);

		adapter.setListener(id -> {
//			Intent intent = new Intent(MainActivity.this, ImcActivity.class);
//			startActivity(intent);
			switch (id) {
				case 1:
					startActivity(new Intent(MainActivity.this,	ImcActivity.class));
					break;
				case 2:
					startActivity(new Intent(MainActivity.this,	TmbActivity.class));
					break;

			}

		});

		rvMain.setAdapter(adapter);
	}

	private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

		private List mainItems;
		private OnItemClickListener listener;

		public MainAdapter(List mainItems) {
			this.mainItems = mainItems;

		}

		public void setListener(OnItemClickListener listener) {
			this.listener = listener;
		}

		@NonNull
		@Override
		public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
		}

		public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
			MainItem mainItemCurrent = (MainItem) mainItems.get(position);
			holder.bind(mainItemCurrent);

		}

		@Override
		public int getItemCount() {
			return mainItems.size();
		}

		//o ViewHolder Entenda como sendo a VIEW DA CELULA que está dentro do RecyclerView
		private class MainViewHolder extends RecyclerView.ViewHolder {

			public MainViewHolder(@NonNull  View itemView) {
				super(itemView);

			}
// public void bind tem que estar dentro do public MainViewHolder
			public void bind(MainItem item) {
				TextView textName = itemView.findViewById(R.id.item_text_name);
				ImageView imgIcn = itemView.findViewById(R.id.item_img_icon);
				LinearLayout btnImc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

				btnImc.setOnClickListener(view -> {
					listener.onClick(item.getId());

				});
				textName.setText(item.getTextStrigId());
				imgIcn.setImageResource(item.getDrowableId());
				btnImc.setBackgroundColor(item.getColor());


			}

		}
	}


}
