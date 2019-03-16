package com.example.jmfs1.ebec.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmfs1.ebec.R;
import com.example.jmfs1.ebec.core.Product;

import java.util.List;

/**
 * Created by jeronimo on 31/12/2016.
 */

public class ShopAdapter extends ArrayAdapter<Product> {

    private Context context;
    public List<Product> products;

    public ShopAdapter(Context context, List<Product> products) {
        super(context, -1, products);
        this.context = context;
        this.products = products;
    }

    public int getCount()
    {
        return products.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.shop_item_layout, parent, false);

        // Get shop item
        Product product = products.get(position);

        // Set name, cost and quantity
        TextView nameTextView = (TextView) rowView.findViewById(R.id.shop_item_name);
        TextView priceTextView = (TextView) rowView.findViewById(R.id.shop_item_price);
        TextView quantityTextView = (TextView) rowView.findViewById(R.id.shop_item_quantity);
        ImageView photoImageView = (ImageView) rowView.findViewById(R.id.shop_item_photo);

        nameTextView.setText(product.getName());
        photoImageView.setImageResource(mapProductToImage(product.getName()));

        if (product.getPrice() >= 0) {
            priceTextView.setText("Preço: " + product.getPrice() + " créditos");
        } else {
            priceTextView.setText("--");
        }

        if (product.getQuantity() >= 0) {
            quantityTextView.setText("Quantidade: " + product.getQuantity() + " " + product.getUnits());
        } else {
            quantityTextView.setText("--");
        }

        return rowView;
    }

    private int mapProductToImage(String name) {

        switch (name) {
            case "Hard wire":
                return R.drawable.arame;
            case "Yellow Hose":
                return R.drawable.mangueiramarela;
            case "Hot Glue tube":
                return R.drawable.colaquente;
            case "Washer A":
                return R.drawable.anilhasa;
            case "Washer C":
                return R.drawable.anilhasc;
            case "Washer D":
                return R.drawable.anilhasd;
            case "Washer E":
                return R.drawable.anilhase;
            case "Baloons":
                return R.drawable.baloes;
            case "Marble ball":
                return R.drawable.berlindes;
            case "Sewing bobbin":
                return R.drawable.bobinascostura;
            case "Armband large":
            case "Armband medium":
            case "Armband small":
                return R.drawable.bracadeiras;
            case "Buzzer":
                return R.drawable.buzzer;
            case "Egg Box":
                return R.drawable.caixasovos;
            case "Screw eye A":
                return R.drawable.camaroesa;
            case "Screw eye B":
                return R.drawable.camaroesb;
            case "Screw eye C":
                return R.drawable.camaroesc;
            case "Cardboard Angle":
                return R.drawable.cantoneiracartao;
            case "Cilindros de plástico":
                return R.drawable.cilindrosplastico;
            case "Battery conector":
                return R.drawable.conetorespilha;
            case "Plastic Shot Glasses":
                return R.drawable.coposhot;
            case "Cordel azul nylon":
            case "Cordel vermelho nylon":
                return R.drawable.cordelnylon;
            case "Hinge":
                return R.drawable.dobradicas;
            case "Elásticos":
                return R.drawable.elasticos;
            case "Fio amarelo":
            case "Fio azul":
            case "Fio branco":
            case "Fio laranja":
            case "Fio verde":
                return R.drawable.fiocor;
            case "Fishing line":
                return R.drawable.fiopesca;
            case "Electric wire":
                return R.drawable.fioeletrico;
            case "Aluminum foil":
                return R.drawable.folhaluminio;
            case "Glass jar":
                return R.drawable.frascovidro;
            case "Small matches":
            case "Big matches":
                return R.drawable.fosforos;
            case "Laser":
                return R.drawable.laser;
            case "LDR":
                return R.drawable.ldr;
            case "Yellow LED":
                return R.drawable.ledamarela;
            case "White LED":
                return R.drawable.ledbranco;
            case "Green LED":
                return R.drawable.ledverde;
            case "Red LED":
                return R.drawable.ledvermelha;
            case "Wool thread":
                return R.drawable.la;
            case "Switch":
                return R.drawable.microswitch;
            case "d":
                return R.drawable.molas;
            case "Drinking Straw":
                return R.drawable.palhinhas;
            case "Wooden skewer 1":
            case "Wooden skewer 2":
            case "Wooden skewer 3":
                return R.drawable.palitos;
            case "Toothpick":
                return R.drawable.palitos;
            case "Cardboard":
                return R.drawable.papelao;
            case "Screw A":
                return R.drawable.parafusosa;
            case "Screw B":
                return R.drawable.parafusosb;
            case "Screw C":
                return R.drawable.parafusosc;
            case "Screw D":
                return R.drawable.parafusosd;
            case "Screw E":
                return R.drawable.parafusose;
            case "Screw F":
                return R.drawable.parafusosf;
            case "Screw G":
                return R.drawable.parafusosg;
            case "Screw H":
                return R.drawable.parafusosh;
            case "Screw I":
                return R.drawable.parafusosi;
            case "Screw J":
                return R.drawable.parafusosj;
            case "Screw L":
                return R.drawable.parafusosl;
            case "Screw N":
                return R.drawable.parafusosn;
            case "Screw P":
                return R.drawable.parafusosp;
            case "Adhesive foil":
                return R.drawable.peliculaderente;
            case "Domino":
                return R.drawable.pecasdomino;
            case "9V Battery":
                return R.drawable.pilhas9v;
            case "AA 1_5V Battery":
                return R.drawable.pilhasaa15v;
            case "AAA 1_5V Battery":
                return R.drawable.pilhasaaa15v;
            case "Pioneses":
                return R.drawable.pioneses;
            case "Plasticine":
                return R.drawable.plasticina;
            case "Big nut":
                return R.drawable.porcas;
            case "Nail 1":
                return R.drawable.pregotipo1;
            case "Nail 2":
                return R.drawable.pregotipo2;
            case "Nail 3":
                return R.drawable.pregotipo3;
            case "Nail 4":
                return R.drawable.pregotipo4;
            case "Nail 5":
                return R.drawable.pregotipo5;
            case "Nail 6":
                return R.drawable.pregotipo6;
            case "Nail 7":
                return R.drawable.pregotipo7;
            case "Nail 8":
                return R.drawable.pregotipo8;
            case "Nail 9":
                return R.drawable.pregotipo9;
            case "100 ohm resistance":
            case "120 ohm resistance":
            case "270 ohm resistance":
            case "330 ohm resistance":
            case "470 ohm resistance":
            case "680 ohm resistance":
            case "6_8 ohm resistance":
            case "820 ohm resistance":
                return R.drawable.resistencias;
            case "Metal wheel":
                return R.drawable.rodasmetal;
            case "Plastic Wheel":
                return R.drawable.rodasplastico;
            case "Pulley":
                return R.drawable.roldanas;
            case "Rolhas de cortiça":
                return R.drawable.rolhascortica;
            case "Roofmate":
                return R.drawable.roofmate;
            case "Syringe Type A":
                return R.drawable.seringastipoa;
            case "Syringe Type B":
                return R.drawable.seringastipob;
            case "Syringe Type C":
                return R.drawable.seringastipoc;
            case "Syringe Type D":
                return R.drawable.seringastipod;
            case "Syringe Type E":
                return R.drawable.seringastipoe;
            case "Switch":
                return R.drawable.sw;
            case "Plastic Caps":
                return R.drawable.tampasplastico;
            case "Transistor NPN":
            case "Transistor PNP":
                return R.drawable.transistorespnp;
            case "Tubo cartão 1":
            case "Tubo cartão 2":
            case "Tubo cartão 3":
            case "Tubo cartão 4":
                return R.drawable.tuboscartao;
            case "PVC Pipe 1":
            case "PVC Pipe 2":
            case "PVC Pipe 3":
                return R.drawable.tubospvc;
            case "Candle":
                return R.drawable.velascompridas;
            case "Cooling fan":
                return R.drawable.ventoinha;
            case "Esticadores":
                return R.drawable.esticador;
            case "Can":
                return R.drawable.lata;
            case "Wooden stick":
                return R.drawable.paumadeira;
            case "Toilet paper card tube":
                return R.drawable.rolodepapel;
            case "Tubo de borracha":
                return R.drawable.tubopreto;
            default:
                return R.drawable.icon_foto;

        }
    }
}
