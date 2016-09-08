package manor9421.com.dailynutrition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;

/**
 * Created by manor on 8/25/16.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "foods";
    private static final int DB_VERSION = 1;

    private final Context mContext;

    public DBHelper(Context c) {
        super(c,DB_NAME,null,DB_VERSION);
        this.mContext = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        updateDB(db,0,DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,int newVersion) {
        updateDB(sqLiteDatabase,oldVersion,newVersion);
    }



    public void insertFood(SQLiteDatabase db, String name, float water,float kcal,float protein,float lipid_tot,float ash,float carbohydrt,float fiber_TD,float sugar_Tot,
                           float calcium,float iron,float magnesium,float phosphorus,float potassium,float sodium,float zinc,float copper,float manganese,float selenium,
                           float vit_C,float thiamin,float riboflavin,float niacin,float panto_Acid,float vit_B6,float folate_Tot,float folic_Acid,float food_Folate,
                           float folate_DFE,float choline_Tot,float vit_B12,float vit_A_IU,float vit_A_RAE, float retinol,float alpha_Carot,float beta_Carot,float beta_Crypt,
                           float lycopene,float lutZea,float vit_E,float vit_D_µg,float vit_D_IU,float vit_K,float FA_Sat, float FA_Mono,float FA_Poly,float cholestrl){
        ContentValues foodValues = new ContentValues();

        foodValues.put("name",name);
        foodValues.put("water",water);
        foodValues.put("kcal",kcal);
        foodValues.put("protein",protein);
        foodValues.put("lipid_tot",lipid_tot);
        foodValues.put("ash",ash);
        foodValues.put("carbohydrt",carbohydrt);
        foodValues.put("fiber_TD",fiber_TD);
        foodValues.put("sugar_Tot",sugar_Tot);
        foodValues.put("calcium",calcium);
        foodValues.put("iron",iron);
        foodValues.put("magnesium",magnesium);
        foodValues.put("phosphorus",phosphorus);
        foodValues.put("potassium",potassium);
        foodValues.put("sodium",sodium);
        foodValues.put("zinc",zinc);
        foodValues.put("copper",copper);
        foodValues.put("manganese",manganese);
        foodValues.put("selenium",selenium);
        foodValues.put("vit_C",vit_C);
        foodValues.put("thiamin",thiamin);
        foodValues.put("riboflavin",riboflavin);
        foodValues.put("niacin",niacin);
        foodValues.put("panto_Acid",panto_Acid);
        foodValues.put("vit_B6",vit_B6);
        foodValues.put("folate_Tot",folate_Tot);
        foodValues.put("folic_Acid",folic_Acid);
        foodValues.put("food_Folate",food_Folate);
        foodValues.put("folate_DFE",folate_DFE);
        foodValues.put("choline_Tot",choline_Tot);
        foodValues.put("vit_B12",vit_B12);
        foodValues.put("vit_A_IU",vit_A_IU);
        foodValues.put("vit_A_RAE",vit_A_RAE);
        foodValues.put("retinol",retinol);
        foodValues.put("alpha_Carot",alpha_Carot);
        foodValues.put("beta_Carot",beta_Carot);
        foodValues.put("beta_Crypt",beta_Crypt);
        foodValues.put("lycopene",lycopene);
        foodValues.put("lutZea",lutZea);
        foodValues.put("vit_E",vit_E);
        foodValues.put("vit_D_µg",vit_D_µg);
        foodValues.put("vit_D_IU",vit_D_IU);
        foodValues.put("vit_K",vit_K);
        foodValues.put("FA_Sat",FA_Sat);
        foodValues.put("FA_Mono",FA_Mono);
        foodValues.put("FA_Poly",FA_Poly);
        foodValues.put("cholestrl",cholestrl);

        db.insert("FOOD", null, foodValues);

    }

    public Cursor selectAllProdNames(SQLiteDatabase db){
        Cursor c = db.query("FOOD",
                new String[]{"_id","NAME"},
                null, null, null,null,null);

        return c;
    }

    public Cursor findProducts(SQLiteDatabase db,String text){
        // разбиваем строку по пробелу
        Cursor c;
        if(text.contains(" ")){
            //если строка имеет несколько продуктов
            String[] separated = text.split(" ");

            StringBuilder s = new StringBuilder();

            for(String f : separated){
                s.append("NAME LIKE '%"+f+"%' AND ");
            }
            s.delete(s.length()-5,s.length()-1);
            c = db.query("FOOD",
                    new String[]{"_id","NAME","kcal","protein","lipid_tot","carbohydrt"},
                    s+"", null, null,null,null);
        }else{
            //если в строке 1 продукт
            c = db.query("FOOD",
                    new String[]{"_id","NAME","kcal","protein","lipid_tot","carbohydrt"},
                    "NAME LIKE '%"+text+"%'", null, null,null,null);
        }
        return c;
    }

    public Cursor selectProdDetails(SQLiteDatabase db,int prodId){

        Cursor c = db.rawQuery("SELECT _id,NAME,water,kcal,protein,lipid_tot,ash,carbohydrt,fiber_TD," +
                "sugar_Tot,calcium,iron,magnesium,phosphorus,potassium,sodium,zinc,copper,manganese," +
                "selenium,vit_C,thiamin,riboflavin,niacin,panto_Acid,vit_B6,folate_Tot,folic_Acid," +
                "food_Folate,folate_DFE,choline_Tot,vit_B12,vit_A_IU,vit_A_RAE,retinol,alpha_Carot," +
                "beta_Carot,beta_Crypt,lycopene,lutZea,vit_E,vit_D_µg,vit_D_IU,vit_K,FA_Sat,FA_Mono," +
                "FA_Poly,cholestrl FROM FOOD WHERE _id=?",new String[]{prodId+""});
        /*Cursor c = db.query("FOOD",
                new String[]{"_id","NAME","water","kcal","protein","lipid_tot","ash","carbohydrt",
                        "fiber_TD","sugar_Tot","calcium","iron","magnesium","phosphorus","potassium",
                        "sodium","zinc","copper","manganese","selenium","vit_C","thiamin","riboflavin",
                        "niacin","panto_Acid","vit_B6","folate_Tot","folic_Acid","food_Folate","folate_DFE",
                        "choline_Tot","vit_B12","vit_A_IU","vit_A_RAE","retinol","alpha_Carot","beta_Carot",
                        "beta_Crypt","lycopene","lutZea","vit_E","vit_D_µg","vit_D_IU","vit_K","FA_Sat",
                        "FA_Mono","FA_Poly","cholestrl"},
                "_id = '"+prodId+"'", null, null,null,null);*/
        return c;
    }

    public Cursor selectNames(SQLiteDatabase db,String[] names){
        StringBuilder que = new StringBuilder();
        int nLen = names.length;// 0.1.2.3.4    5
        for(int i=0;i<nLen;i++){
            que.append("?,");
        }
        que.deleteCharAt(que.length()-1);

        Cursor c = db.query("FOOD",
                new String[]{"NAME"},
                "_id IN("+que+")", names, null,null,null);
        //Cursor c = db.rawQuery("SELECT NAME FROM FOOD WHERE _id=?",names);
        return c;
    }


    public Cursor selectDailyDetails(SQLiteDatabase db,int prodId){

        Cursor c = db.rawQuery("SELECT DATE,PRODUCTS,water,kcal,protein,lipid_tot,ash,carbohydrt,fiber_TD," +
                "sugar_Tot,calcium,iron,magnesium,phosphorus,potassium,sodium,zinc,copper,manganese," +
                "selenium,vit_C,thiamin,riboflavin,niacin,panto_Acid,vit_B6,folate_Tot,folic_Acid," +
                "food_Folate,folate_DFE,choline_Tot,vit_B12,vit_A_IU,vit_A_RAE,retinol,alpha_Carot," +
                "beta_Carot,beta_Crypt,lycopene,lutZea,vit_E,vit_D_µg,vit_D_IU,vit_K,FA_Sat,FA_Mono," +
                "FA_Poly,cholestrl FROM USER_MEALS WHERE _id=?",new String[]{prodId+""});
        return c;
    }

    public Cursor selectDetailsKcalProtFatCarb(SQLiteDatabase db){
        Cursor c = db.query("USER_MEALS",
                new String[]{"_id","DATE","kcal","protein","lipid_tot","carbohydrt"},
                null, null, null,null,"_id DESC","100");
        return c;
    }


    public Cursor selectKcalProtFatCarb(SQLiteDatabase db){
        Cursor c = db.query("FOOD",
                new String[]{"_id","NAME","kcal","protein","lipid_tot","carbohydrt"},
                null, null, null,null,null);
        return c;
    }



    public Cursor selectProd(SQLiteDatabase db,String[] names){
        StringBuilder que = new StringBuilder();
        int nLen = names.length;// 0.1.2.3.4    5
        for(int i=0;i<nLen;i++){
            que.append("?,");
        }
        que.deleteCharAt(que.length()-1);
        Cursor cr = db.query("FOOD",
                new String[]{"_id","NAME","water","kcal","protein","lipid_tot","ash","carbohydrt","fiber_TD","sugar_Tot","calcium","iron","magnesium","phosphorus","potassium","sodium","zinc","copper","manganese","selenium","vit_C","thiamin","riboflavin","niacin","panto_Acid","vit_B6","folate_Tot","folic_Acid","food_Folate","folate_DFE","choline_Tot","vit_B12","vit_A_IU","vit_A_RAE","retinol","alpha_Carot","beta_Carot","beta_Crypt","lycopene","lutZea","vit_E","vit_D_µg","vit_D_IU","vit_K","FA_Sat","FA_Mono","FA_Poly","cholestrl"},
                "NAME IN("+que+")",
                names,null,null,null);
        return cr;
    }




    public void insertNewMeal(SQLiteDatabase db,String names,float water,float kcal,float protein,float lipid_tot,float ash,float carbohydrt,float fiber_TD,float sugar_Tot,
                              float calcium,float iron,float magnesium,float phosphorus,float potassium,float sodium,float zinc,float copper,float manganese,float selenium,
                              float vit_C,float thiamin,float riboflavin,float niacin,float panto_Acid,float vit_B6,float folate_Tot,float folic_Acid,float food_Folate,
                              float folate_DFE,float choline_Tot,float vit_B12,float vit_A_IU,float vit_A_RAE, float retinol,float alpha_Carot,float beta_Carot,float beta_Crypt,
                              float lycopene,float lutZea,float vit_E,float vit_D_µg,float vit_D_IU,float vit_K,float FA_Sat, float FA_Mono,float FA_Poly,float cholestrl){
        ContentValues newIngestionValues = new ContentValues();
        Date d = new Date();

        newIngestionValues.put("DATE",d.getTime());
        newIngestionValues.put("PRODUCTS",names);
        newIngestionValues.put("water",water);
        newIngestionValues.put("kcal",kcal);
        newIngestionValues.put("protein",protein);
        newIngestionValues.put("lipid_tot",lipid_tot);
        newIngestionValues.put("ash",ash);
        newIngestionValues.put("carbohydrt",carbohydrt);
        newIngestionValues.put("fiber_TD",fiber_TD);
        newIngestionValues.put("sugar_Tot",sugar_Tot);
        newIngestionValues.put("calcium",calcium);
        newIngestionValues.put("iron",iron);
        newIngestionValues.put("magnesium",magnesium);
        newIngestionValues.put("phosphorus",phosphorus);
        newIngestionValues.put("potassium",potassium);
        newIngestionValues.put("sodium",sodium);
        newIngestionValues.put("zinc",zinc);
        newIngestionValues.put("copper",copper);
        newIngestionValues.put("manganese",manganese);
        newIngestionValues.put("selenium",selenium);
        newIngestionValues.put("vit_C",vit_C);
        newIngestionValues.put("thiamin",thiamin);
        newIngestionValues.put("riboflavin",riboflavin);
        newIngestionValues.put("niacin",niacin);
        newIngestionValues.put("panto_Acid",panto_Acid);
        newIngestionValues.put("vit_B6",vit_B6);
        newIngestionValues.put("folate_Tot",folate_Tot);
        newIngestionValues.put("folic_Acid",folic_Acid);
        newIngestionValues.put("food_Folate",food_Folate);
        newIngestionValues.put("folate_DFE",folate_DFE);
        newIngestionValues.put("choline_Tot",choline_Tot);
        newIngestionValues.put("vit_B12",vit_B12);
        newIngestionValues.put("vit_A_IU",vit_A_IU);
        newIngestionValues.put("vit_A_RAE",vit_A_RAE);
        newIngestionValues.put("retinol",retinol);
        newIngestionValues.put("alpha_Carot",alpha_Carot);
        newIngestionValues.put("beta_Carot",beta_Carot);
        newIngestionValues.put("beta_Crypt",beta_Crypt);
        newIngestionValues.put("lycopene",lycopene);
        newIngestionValues.put("lutZea",lutZea);
        newIngestionValues.put("vit_E",vit_E);
        newIngestionValues.put("vit_D_µg",vit_D_µg);
        newIngestionValues.put("vit_D_IU",vit_D_IU);
        newIngestionValues.put("vit_K",vit_K);
        newIngestionValues.put("FA_Sat",FA_Sat);
        newIngestionValues.put("FA_Mono",FA_Mono);
        newIngestionValues.put("FA_Poly",FA_Poly);
        newIngestionValues.put("cholestrl",cholestrl);
        db.insert("USER_MEALS", null, newIngestionValues);




    }

    private void updateDB(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion < 1) {

           //создаем БД
            //this.getReadableDatabase();// создаем пустую базу
            //открываем файл с бд в assets ( src/main/assets/ )


            //db.execSQL("CREATE TABLE FOOD(" +"_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"NAME TEXT, "+"water FLOAT, "+"kcal FLOAT,"+"protein FLOAT,"+"lipid_tot FLOAT,"+"ash FLOAT,"+"carbohydrt FLOAT,"+"fiber_TD FLOAT,"+"sugar_Tot FLOAT,"+"calcium FLOAT,"+"iron FLOAT,"+"magnesium FLOAT,"+"phosphorus FLOAT,"+"potassium FLOAT,"+"sodium FLOAT,"+"zinc FLOAT,"+"copper FLOAT,"+"manganese FLOAT,"+"selenium FLOAT,"+"vit_C FLOAT,"+"thiamin FLOAT,"+"riboflavin FLOAT,"+"niacin FLOAT,"+"panto_Acid FLOAT,"+"vit_B6 FLOAT,"+"folate_Tot FLOAT,"+"folic_Acid FLOAT,"+"food_Folate FLOAT,"+"folate_DFE FLOAT,"+"choline_Tot FLOAT,"+"vit_B12 FLOAT,"+"vit_A_IU FLOAT,"+"vit_A_RAE FLOAT,"+"retinol FLOAT,"+"alpha_Carot FLOAT,"+"beta_Carot FLOAT,"+"beta_Crypt FLOAT,"+"lycopene FLOAT,"+"lutZea FLOAT,"+"vit_E FLOAT,"+"vit_D_µg FLOAT,"+"vit_D_IU FLOAT,"+"vit_K FLOAT,"+"FA_Sat FLOAT,"+"FA_Mono FLOAT,"+"FA_Poly FLOAT,"+"cholestrl FLOAT)");
            db.execSQL("CREATE TABLE USER_MEALS("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DATE INTEGER, "
                    + "PRODUCTS TEXT, "
                    + "water FLOAT,"
                    + "kcal FLOAT,"
                    + "protein FLOAT,"
                    + "lipid_tot FLOAT,"
                    + "ash FLOAT,"
                    + "carbohydrt FLOAT,"
                    + "fiber_TD FLOAT,"
                    + "sugar_Tot FLOAT,"
                    + "calcium FLOAT,"
                    + "iron FLOAT,"
                    + "magnesium FLOAT,"
                    + "phosphorus FLOAT,"
                    + "potassium FLOAT,"
                    + "sodium FLOAT,"
                    + "zinc FLOAT,"
                    + "copper FLOAT,"
                    + "manganese FLOAT,"
                    + "selenium FLOAT,"
                    + "vit_C FLOAT,"
                    + "thiamin FLOAT,"
                    + "riboflavin FLOAT,"
                    + "niacin FLOAT,"
                    + "panto_Acid FLOAT,"
                    + "vit_B6 FLOAT,"
                    + "folate_Tot FLOAT,"
                    + "folic_Acid FLOAT,"
                    + "food_Folate FLOAT,"
                    + "folate_DFE FLOAT,"
                    + "choline_Tot FLOAT,"
                    + "vit_B12 FLOAT,"
                    + "vit_A_IU FLOAT,"
                    + "vit_A_RAE FLOAT,"
                    + "retinol FLOAT,"
                    + "alpha_Carot FLOAT,"
                    + "beta_Carot FLOAT,"
                    + "beta_Crypt FLOAT,"
                    + "lycopene FLOAT,"
                    + "lutZea FLOAT,"
                    + "vit_E FLOAT,"
                    + "vit_D_µg FLOAT,"
                    + "vit_D_IU FLOAT,"
                    + "vit_K FLOAT,"
                    + "FA_Sat FLOAT,"
                    + "FA_Mono FLOAT,"
                    + "FA_Poly FLOAT,"
                    + "cholestrl FLOAT)");

        }
    }

}
