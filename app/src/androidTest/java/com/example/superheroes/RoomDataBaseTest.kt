package com.example.superheroes
import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superheroes.data.local.SuperheroeDao
import com.example.superheroes.data.local.SuperheroeDataBase
import com.example.superheroes.data.local.SuperheroeEntity
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class RoomDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var superheroeDao: SuperheroeDao
    private lateinit var db: SuperheroeDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SuperheroeDataBase::class.java).build()
        superheroeDao = db.getSuperheroeDao()
    }

    @After
    fun tearDown() {
        db.close()

    }

    @Test
    fun insertSuperheroe_empty() = runBlocking {

        // Given
        val superheroeList = listOf<SuperheroeEntity>()

        // When
        superheroeDao.insertAllSuperheroes(superheroeList)

        // Then A
        val it = superheroeDao.getAllSuperheroes().getOrAwaitValue()

        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        superheroeDao.getAllSuperheroes().observeForever {

            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)

fun <T> LiveData<T>.getOrAwaitValue(

    time: Long = 2,

    timeUnit: TimeUnit = TimeUnit.SECONDS,

    afterObserve: () -> Unit = {}

): T {

    var data: T? = null

    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {

        override fun onChanged(value: T) {

            data = value

            latch.countDown()

            this@getOrAwaitValue.removeObserver(this)

        }

    }

    this.observeForever(observer)




    try {

        afterObserve.invoke()


        // Don't wait indefinitely if the LiveData is not set.

        if (!latch.await(time, timeUnit)) {

            throw TimeoutException("LiveData value was never set.")

        }


    } finally {

        this.removeObserver(observer)

    }




    @Suppress("UNCHECKED_CAST")

    return data as T

}