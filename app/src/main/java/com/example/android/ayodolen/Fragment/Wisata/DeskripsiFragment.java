package com.example.android.ayodolen.Fragment.Wisata;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.ayodolen.DetailWisataActivity;
import com.example.android.ayodolen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DeskripsiFragment extends Fragment {
    private String idWisata;
    private static final String ARG_TITLE = "title";


    public static DeskripsiFragment newInstance(){

        DeskripsiFragment fragment = new DeskripsiFragment();
        return fragment;

    }

    public DeskripsiFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();
        if (extras != null) {
            idWisata = extras.getString(ARG_TITLE);
        }
    }



    ArrayList<String> imageUrl ;
    DetailWisataActivity mActv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deskripsi,container,false);
        mActv = (DetailWisataActivity) getActivity();
        ImageView img1,img2,img3,img4,img5,img6;


        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img2);
        img3 = (ImageView) view.findViewById(R.id.img3);
        img4 = (ImageView) view.findViewById(R.id.img4);
        img5 = (ImageView) view.findViewById(R.id.img5);
        img6 = (ImageView) view.findViewById(R.id.img6);


        imageUrl = new ArrayList<>();
        if (mActv.wis.getId_kategori().equals("1")){
            setUrlPantai(imageUrl);
        }else if (mActv.wis.getId_kategori().equals("2")){
            setUrlTaman(imageUrl);
        }else if (mActv.wis.getId_kategori().equals("3")){
            setUrlAlam(imageUrl);
        }else if (mActv.wis.getId_kategori().equals("4")){
            setUrlRekresi(imageUrl);
        }


        setimgnlistener(img1,0);
        setimgnlistener(img2,1);
        setimgnlistener(img3,2);
        setimgnlistener(img4,3);
        setimgnlistener(img5,4);
        setimgnlistener(img6,5);

        return view;
    }


    private void setimgnlistener(ImageView img, final Integer i){
//        set img
        Picasso.with(getContext()).load(imageUrl.get(i)).into(img);
//        listener img
        if (i!=5){
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dial = new Dialog(getContext(),android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dial.setContentView(R.layout.item_show_foto);
                    ImageView imgv = dial.findViewById(R.id.img_view);
                    ImageView btnbck = dial.findViewById(R.id.btn_back);

                    Picasso.with(getContext()).load(imageUrl.get(i)).into(imgv);
                    btnbck.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dial.dismiss();
                        }
                    });

                    dial.show();
                }
            });
        }

    }


    private void setUrlPantai(ArrayList<String> imageUrl) {
        imageUrl.add("https://allesgroen.files.wordpress.com/2017/06/pantai-goa-cina-malang.jpg");
        imageUrl.add("https://www.pantainesia.com/wp-content/uploads/2018/05/pantai-gua-cina-680x382.jpg");
        imageUrl.add("https://cdn.yukepo.com/content-images/main-images/2017/11/07/main_image_13292.jpg");
        imageUrl.add("http://1.bp.blogspot.com/_ZHVA2Oi-bfU/TLv2BdYKTFI/AAAAAAAAAm4/JKuB49uC6ok/s1600/19868881.jpg");
        imageUrl.add("https://awsimages.detik.net.id/content/2015/04/27/1025/img_20150427125705_553dcfb19197c.jpg?w=650");
        imageUrl.add("https://backpackerjakarta.com/wp-content/uploads/2016/07/bajulmati2.jpg");
        imageUrl.add("https://www.traveladvisor.id/wp-content/uploads/2018/08/Pantai-Goa-Cina.jpg");
    }

    private void setUrlAlam(ArrayList<String> imageUrl) {
        imageUrl.add("https://ksmtour.com/media/images/articles12/air-terjun-temam-sumatera-selatan.jpg");
        imageUrl.add("https://cdns.klimg.com/dream.co.id/resized/750x500/news/2018/05/07/83656/melihat-gunung-meletus-dari-jarak-dekat-di-ntt-180507c_3x2.jpg");
        imageUrl.add("https://i1.wp.com/www.sobatjogja.com/wp-content/uploads/2015/03/Menembus-Buleleng-Demi-Wisata-Air-terjun-Sekumpul.jpg?resize=500%2C332&ssl=1");
        imageUrl.add("https://awsimages.detik.net.id/visual/2017/04/18/30656317-cedc-466c-8c84-a8b988da7f87_169.jpg?w=650");
        imageUrl.add("https://www.twisata.com/wp-content/uploads/2015/06/Gambar-Gunung-Bromo.jpg");
        imageUrl.add("https://sites.google.com/site/wisataairterjun/_/rsrc/1443867540673/home/tumpak-sewu-%40bromosemerutours-com.jpg");
    }

    private void setUrlTaman(ArrayList<String> imageUrl) {
        imageUrl.add("https://bisniswisata.co.id/wp-content/uploads/2018/03/Taman-Rekreasi-Matahari.jpg");
        imageUrl.add("https://awsimages.detik.net.id/content/2015/12/25/1519/tam1.jpg");
        imageUrl.add("https://explorewisata.com/wp-content/uploads/2017/08/taman-langit-batu.jpg");
        imageUrl.add("http://3.bp.blogspot.com/-2JgcKJNMs8o/Vl-_44OGBcI/AAAAAAAAAQM/BW5WJUWIh7I/s1600/12497750134_3fc26b922e_b.jpg");
        imageUrl.add("http://anekatempatwisata.com/wp-content/uploads/2014/10/Taman-Wisata-Mekarsari-5.jpg");
        imageUrl.add("https://kerincitime.co.id/wp-content/uploads/2016/10/Hendri-Samrena-bersama-istrinya-Masnani.jpg");
    }

    private void setUrlRekresi(ArrayList<String> imageUrl) {
        imageUrl.add("http://wisatajawatimur.net/wp-content/uploads/2016/05/Jatim-Park-1-b.jpg");
        imageUrl.add("http://panduanwisata.id/files/2015/09/Jatim-Park-jawatimurpark.jpg");
        imageUrl.add("https://wisatapedi.com/wp-content/uploads/2015/06/marcopolo-bogor-3.jpg");
        imageUrl.add("http://blog.vokamo.com/wp-content/uploads/2018/06/wisata-taman-safari-bogor.jpg");
        imageUrl.add("https://lh3.googleusercontent.com/tGJ4DeKoj2a00ai6yTost5ZR9WJjLXumVIebYT3IhvGwYHaTQWsMVu9tnJ44NVLrW269Af3mIiWEOg=w4160-h3120-rw-no");
        imageUrl.add("http://www.metropolitan.id/wp-content/uploads/2018/07/Taman-Wisata-Matahari.jpg");
    }


}
