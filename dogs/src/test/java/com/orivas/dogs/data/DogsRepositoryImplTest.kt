package com.orivas.dogs.data

import com.orivas.dogs.domain.model.DataFrom
import com.orivas.dogs.domain.model.DogDto
import com.orivas.dogs.domain.model.DogsDto
import com.orivas.dogs.domain.repository.DogsRepository
import com.orivas.shared.domain.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.StrictStubs::class)
class DogsRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: DogsDataSource

    @Mock
    private lateinit var localSource: LocalDataSource

    private lateinit var dogsRepository: DogsRepository

    // Rule for coroutines
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        dogsRepository = DogsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localSource
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test success data from local`() = runTest {
        val list = listOf(DogDto(dogName = "Rex", description = "desc", age = "age", image = ""))
        val response = NetworkResult.OnSuccess(DogsDto(list))
        Mockito.`when`(localSource.getAllDogs()).thenReturn(flowOf(response))


        val result = dogsRepository.getAllDogs().last()

        assert(result is NetworkResult.OnSuccess)
        assertEquals("Rex", (result as NetworkResult.OnSuccess).data.dogs.first().dogName)
        assertEquals(DataFrom.Local, (result as NetworkResult.OnSuccess).data.dataFrom)
        verify(localSource).getAllDogs()
    }

    @Test
    fun `test success data from remote`() = runTest {
        val list = listOf(DogDto(dogName = "Rex", description = "desc", age = "age", image = ""))
        val response = NetworkResult.OnError(Exception())
        val remoteResponse = NetworkResult.OnSuccess(DogsDto(list))
        Mockito.`when`(localSource.getAllDogs()).thenReturn(flowOf(response))
        Mockito.`when`(remoteDataSource.getAllDogs()).thenReturn(flowOf(remoteResponse))


        val result = dogsRepository.getAllDogs().last()

        assert(result is NetworkResult.OnSuccess)
        assertEquals("Rex", (result as NetworkResult.OnSuccess).data.dogs.first().dogName)
        assertEquals(DataFrom.Remote, (result as NetworkResult.OnSuccess).data.dataFrom)
        verify(localSource).getAllDogs()
        verify(remoteDataSource).getAllDogs()
    }

    @Test
    fun `test error data from remote`() = runTest {
        val response = NetworkResult.OnError(Exception())
        val remoteResponse = NetworkResult.OnError(Exception())
        Mockito.`when`(localSource.getAllDogs()).thenReturn(flowOf(response))
        Mockito.`when`(remoteDataSource.getAllDogs()).thenReturn(flowOf(remoteResponse))


        val result = dogsRepository.getAllDogs().last()

        assert(result is NetworkResult.OnError)
        verify(localSource).getAllDogs()
        verify(remoteDataSource).getAllDogs()
    }

    @Test
    fun `test error data from local`() = runTest {
        val response = NetworkResult.OnError(Exception())
        val remoteResponse = NetworkResult.OnError(Exception())
        Mockito.`when`(localSource.getAllDogs()).thenReturn(flowOf(response))
        Mockito.`when`(remoteDataSource.getAllDogs()).thenReturn(flowOf(remoteResponse))


        val result = dogsRepository.getAllDogs().last()

        assert(result is NetworkResult.OnError)
        verify(localSource).getAllDogs()
        verify(remoteDataSource).getAllDogs()
    }

    @Test
    fun `test loading data`() = runTest {
        val response = NetworkResult.Loading
        Mockito.`when`(localSource.getAllDogs()).thenReturn(flowOf(response))

        val result = dogsRepository.getAllDogs().first()

        assert(result is NetworkResult.Loading)
    }
}