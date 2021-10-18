package base.climateapp.root.root.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import base.climateapp.root.root.model.entity.Account;

public interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccount(Account account);

    @Query("DELETE FROM Account")
    void deleteAll();

    @Query("SELECT * FROM Account")
    LiveData<List<Account>> getCurrentAccountList();
}
