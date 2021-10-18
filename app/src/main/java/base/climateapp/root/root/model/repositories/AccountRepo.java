package base.climateapp.root.root.model.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import base.climateapp.root.root.api.MyRetrofit;
import base.climateapp.root.root.model.dao.AccountDao;
import base.climateapp.root.root.model.entity.Account;
import base.climateapp.root.root.model.roomdatabase.MyRoomDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepo {
    private MyRetrofit retrofit;
    private AccountDao currentAccountDao;


    public AccountRepo(Application application) {
        retrofit = new MyRetrofit();
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        currentAccountDao = db.currentAccountDao();

    }

    public void login(String username, String password){

        Call<Account> call = retrofit.api.logIn(username,password);
        call.enqueue(new Callback<Account>(){
            @Override
            public void onResponse (Call <Account> call, Response<Account> response){
                System.out.println("SUCCESS " + response.body());
                accountInsert(response.body());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                System.out.println("Failed at Login");
                System.out.println(t.getMessage());
            }

        });
    }

    public void accountInsert(Account account) {
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            currentAccountDao.insertAccount(account);
        });
    }

    //delete all accounts
    public void emptyRepo(){
        MyRoomDatabase.databaseWriteExecutor.execute(() -> {
            currentAccountDao.deleteAll();
        });

    }

    // return a list of all accounts to the viewmodel
    public LiveData<List<Account>> getCurrentAccountList(){
        return currentAccountDao.getCurrentAccountList();
    }

}
