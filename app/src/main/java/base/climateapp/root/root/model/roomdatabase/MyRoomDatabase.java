package base.climateapp.root.root.model.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import base.climateapp.root.root.model.dao.AccountDao;
import base.climateapp.root.root.model.dao.ClimateDao;
import base.climateapp.root.root.model.dao.DayDao;
import base.climateapp.root.root.model.dao.PlantDao;
import base.climateapp.root.root.model.dao.ScheduleDao;
import base.climateapp.root.root.model.entity.Account;
import base.climateapp.root.root.model.entity.Climate;
import base.climateapp.root.root.model.entity.Day;
import base.climateapp.root.root.model.entity.Plant;
import base.climateapp.root.root.model.entity.Schedule;

@Database(entities = {Day.class, Account.class,  Schedule.class, Plant.class, Climate.class
},
        version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract DayDao accountsDao();
    public abstract ScheduleDao saunaDao();
    public abstract ClimateDao dataPointDao();
    public abstract PlantDao reservationDao();
    public abstract AccountDao currentAccountDao();

    private static volatile MyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 12;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class, "my_room_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
