package loop.conexaopendrive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;

import loop.conexaopendrive.Dominio.Pen;
import loop.conexaopendrive.PenFragment.CartoonFragment;
import loop.conexaopendrive.PenFragment.HeroFragment;
import loop.conexaopendrive.PenFragment.PenFragent;
import loop.conexaopendrive.PenFragment.SaudeFragment;
import loop.conexaopendrive.PenFragment.StarFragment;
import loop.conexaopendrive.PenFragment.TimesFragment;


public class MainActivity extends ActionBarActivity {


    private Toolbar mToolbar;
    private Toolbar mToolbarBottom;
    private Drawer navegationDrawerLeft;
    private AccountHeader headerNavegationLeft;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity.this, "Novidades: "+ (b ? "Ativado" : "Desativado"), Toast.LENGTH_SHORT).show();

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle("Conexao Pendrive");
        setSupportActionBar(mToolbar);



        mToolbarBottom = (Toolbar) findViewById(R.id.inc_tb_buttom);
        mToolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent it = null;

                switch (menuItem.getItemId()) {
                    case R.id.action_facebook:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("https://www.facebook.com/pages/Conexao-PenDrive/1626339194255371"));
                        break;
                    case R.id.action_instagram:
                        it = new Intent(Intent.ACTION_VIEW);
                        it.setData(Uri.parse("https://instagram.com/conexaopendrive/"));
                        break;

                }

                startActivity(it);
                return true;
            }
        });
        mToolbarBottom.inflateMenu(R.menu.menu_bottom);

        mToolbarBottom.findViewById(R.id.action_whatsapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "(79)9800-9596/(79)9653-8500", Toast.LENGTH_SHORT).show();
            }
        });

        PenFragent frag = (PenFragent) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if (frag == null){
            frag = new PenFragent();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rt_fragment_container, frag, "mainFrag");
            ft.commit();
        }

        navegationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                        Fragment tela = null;

                        switch (i) {
                            case 0:
                                tela = new PenFragent();
                                break;
                            case 2:
                                tela = new StarFragment();
                                break;
                            case 3:
                                tela = new HeroFragment();
                                break;
                            case 4:
                                tela = new CartoonFragment();
                                break;
                            case 5:
                                tela = new TimesFragment();
                                break;
                            case 6:
                                tela = new SaudeFragment();
                                break;
                        }

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.rt_fragment_container, tela)
                                .commit();
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        Toast.makeText(MainActivity.this, "Setado " + i, Toast.LENGTH_SHORT).show();

                        return false;
                    }
                })
                .build();
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Home").withIcon(getResources().getDrawable(R.mipmap.ic_home)));
        navegationDrawerLeft.addItem(new SectionDrawerItem().withName("Categorias"));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Star Wars").withIcon(getResources().getDrawable(R.mipmap.ic_star_wars)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Herois").withIcon(getResources().getDrawable(R.mipmap.ic_hero)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Cartoons").withIcon(getResources().getDrawable(R.mipmap.ic_tv)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Times").withIcon(getResources().getDrawable(R.mipmap.ic_times)));
        navegationDrawerLeft.addItem(new PrimaryDrawerItem().withName("Area da saude").withIcon(getResources().getDrawable(R.mipmap.ic_area_medica)));
        navegationDrawerLeft.addItem(new SectionDrawerItem().withName("Configuracoes"));
        navegationDrawerLeft.addItem(new SwitchDrawerItem().withName("Notificacao").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener));


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    public List<Pen> getSetstarList(int qnt){
            String[] modelo = new String[]{"Darth Vader","Yoda","Clone"};
            String[] categoria = new String[]{"Star Wars","Star Wars","Star Wars"};
            int[] foto = new int[]{R.drawable.pen_dart,R.drawable.pen_yoda,R.drawable.pen_clones};
            List<Pen> listAux = new ArrayList<>();

            for (int i = 0; i < qnt; i++){
                Pen p = new Pen(modelo[i % modelo.length], categoria[i % categoria.length], foto[i % modelo.length]);
                listAux.add(p);

            }
            return (listAux);

    }
    public List<Pen> getSetcartoonList(int qnt){
        String[] modelo = new String[]{"Bob Esponja","Frajola","Garfield"};
        String[] categoria = new String[]{"Cartoons","Cartoons","Cartoons"};
        int[] foto = new int[]{R.drawable.pen_bob,R.drawable.pen_frajola,R.drawable.pen_garfield};
        List<Pen> listAux = new ArrayList<>();

        for (int i = 0; i < qnt; i++){
            Pen p = new Pen(modelo[i % modelo.length], categoria[i % categoria.length], foto[i % modelo.length]);
            listAux.add(p);

        }
        return (listAux);

    }

    public List<Pen> getSetsaudeList(int qnt){
        String[] modelo = new String[]{"Enfermeira tp1","Enfermeira tp2","Enfermeira tp3","Enfermeiro"};
        String[] categoria = new String[]{"Area da Saude","Area da Saude","Area da Saude","Area da Saude",};
        int[] foto = new int[]{R.drawable.pen_enfermeira,R.drawable.pen_enfermeira2,R.drawable.pen_enfermeira3,R.drawable.pen_enfermeiro};
        List<Pen> listAux = new ArrayList<>();

        for (int i = 0; i < qnt; i++){
            Pen p = new Pen(modelo[i % modelo.length], categoria[i % categoria.length], foto[i % modelo.length]);
            listAux.add(p);

        }
        return (listAux);

    }

    public List<Pen> getSettimesList(int qnt){
        String[] modelo = new String[]{"Corinthians","Palmeiras","Flamengo"};
        String[] categoria = new String[]{"Times","Times","Times"};
        int[] foto = new int[]{R.drawable.pen_corinthians,R.drawable.pen_palmeiras,R.drawable.pen_flamengo};
        List<Pen> listAux = new ArrayList<>();

        for (int i = 0; i < qnt; i++){
            Pen p = new Pen(modelo[i % modelo.length], categoria[i % categoria.length], foto[i % modelo.length]);
            listAux.add(p);

        }
        return (listAux);

    }

    public List<Pen> getSetheroList(int qnt){
        String[] modelo = new String[]{"Batman","Cap.America","Superman","Ironman"};
        String[] categoria = new String[]{"Herois","Herois","Herois","Herois"};
        int[] foto = new int[]{R.drawable.pen_batman,R.drawable.pen_cap,R.drawable.pen_super,R.drawable.pen_mao};
        List<Pen> listAux = new ArrayList<>();

        for (int i = 0; i < qnt; i++){
            Pen p = new Pen(modelo[i % modelo.length], categoria[i % categoria.length], foto[i % modelo.length]);
            listAux.add(p);

        }
        return (listAux);

    }



}
